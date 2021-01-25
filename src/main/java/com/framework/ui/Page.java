package com.framework.ui;

import com.framework.Configuration;
import com.framework.Driver;
import com.framework.bdd.Alias;
import com.framework.ui.controls.Control;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public class Page {

    protected static final long TIMEOUT = Configuration.timeout();
    private static ConcurrentHashMap<String, Page> currentPages = new ConcurrentHashMap<String, Page>();
    private WebDriver driver;

    public Page(WebDriver driverValue) {
        super();
        this.driver = driverValue;
    }

    public static Page screen(String name) throws Exception {
        return screen(name, Configuration.get("pages_package"));
    }

    public static Page screen(String name, String pagePackage) throws Exception {
        Reflections reflections = new Reflections(pagePackage);
        Set<Class<? extends Page>> subTypes = reflections.getSubTypesOf(Page.class);
        for (Class<? extends Page> type : subTypes) {
            Alias annotation = type.getAnnotation(Alias.class);
            if (annotation != null && annotation.value().equals(name))
                return PageFactory.init(type);

        }
        return null;
    }

    public static Page getCurrent() {
        return currentPages.get(Driver.getThreadName());
    }

    public Control onPage(String name) throws Exception {
        for (Field field : this.getClass().getFields()) {
            if (Control.class.isAssignableFrom(field.getType())) {
                Alias alias = field.getAnnotation(Alias.class);
                if (alias != null && name.equals(alias.value()))
                    return ((Control) field.get(this));
            }
        }
        return null;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Page navigate() throws Exception {
        return this;
    }

    public boolean isTextPresent(String text) {
        String locator = String.format("//*[text()='%s' or contains(text(), '%s')]", text, text);
        Control element = new Control(this, By.xpath(locator));
        return element.exists();
    }

    public byte[] captureScreenShot() {
        WebDriver augmentedDriver = new Augmenter().augment(getDriver());
        byte[] data = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
        return data;
    }

    public File captureScreenShot(String destination) throws IOException {
        WebDriver augmentedDriver = new Augmenter().augment(getDriver());
        File srcFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        File output = new File(destination);
        FileUtils.copyFile(srcFile, output);
        return output;
    }

    public boolean isCurrent(long timeout) throws Exception {
        Field[] fields = this.getClass().getFields();
        for (Field field : fields) {
            if (Control.class.isAssignableFrom(field.getType())) {
                Control control = (Control) field.get(this);
                System.out.println(String.format("Checking %s : '%s", field.getName(), control.getLocatorText()));
                Assert.assertTrue("The '" + field.getName() + "' element does not exist", control.exists());
                if (!control.isExcludeFromSearch() && !control.exists(timeout))
                    return false;
            }
        }
        return true;
    }

    public boolean isCurrent() throws Exception {
        return isCurrent(TIMEOUT);
    }

    public static Page setCurrent(Page newPage) {
        return currentPages.put(Driver.getThreadName(), newPage);
    }

    protected boolean allElementsAre(Control[] elements, String state) throws Exception {
        for (Control element : elements) {
            if (!(boolean) element.getClass().getMethod(state).invoke(element))
                return false;
        }
        return true;
    }

    protected boolean anyOfElementsIs(Control[] elements, String state) throws Exception {
        for (Control element : elements) {
            if ((boolean) element.getClass().getMethod(state).invoke(element))
                return true;
        }
        return false;
    }

    public boolean allElementsAreExists(Control[] elements) throws Exception {
        return allElementsAre(elements, "exists");
    }

    public boolean allElementsAreVisible(Control[] elements) throws Exception {
        return allElementsAre(elements, "visible");
    }

    public boolean allElementsAreInvisible(Control[] elements) throws Exception {
        return allElementsAre(elements, "invisible");
    }

    public boolean allElementsAreEnabled(Control[] elements) throws Exception {
        return allElementsAre(elements, "enable");
    }

    public boolean allElementsAreDisabled(Control[] elements) throws Exception {
        return allElementsAre(elements, "disable");
    }

    public boolean anyOfElementsExist(Control[] elements) throws Exception {
        return anyOfElementsIs(elements, "exists");
    }

    public boolean anyOfElementsIsVisible(Control[] elements) throws Exception {
        return anyOfElementsIs(elements, "visible");
    }

    public boolean anyOfElementsIsInvisible(Control[] elements) throws Exception {
        return anyOfElementsIs(elements, "invisible");
    }

    public boolean anyOfElementsIsEnabled(Control[] elements) throws Exception {
        return anyOfElementsIs(elements, "enabled");
    }

    public boolean anyOfElementsIsDisabled(Control[] elements) throws Exception {
        return anyOfElementsIs(elements, "disabled");
    }


}
