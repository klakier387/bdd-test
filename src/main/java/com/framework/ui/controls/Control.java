package com.framework.ui.controls;

import com.framework.Configuration;
import com.framework.Driver;
import com.framework.ui.Page;
import com.framework.ui.PageFactory;
import com.framework.ui.SubItem;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class Control {
    protected static final long TIMEOUT = Configuration.timeout();
    private Page parent;
    private By locator;
    private String locatorText = "";
    private String itemLocatorText = "";
    private HashMap<String, SubItem> subItemsMap;
    private boolean excludeFromSearch = false;

    public Control(Page parentValue, By locatorValue) {
        this.parent = parentValue;
        this.locator = locatorValue;
        this.locatorText = this.locator.toString().replaceFirst("^By\\.(\\S+): ", "");
        subItemsMap = new HashMap<String, SubItem>();
    }

    public Page getParent() {
        return parent;
    }

    public String getLocatorText() {
        return locatorText;
    }

    public String getItemLocatorText() {
        return itemLocatorText;
    }

    public void setItemLocatorText(String subItemLocatorText) {
        this.itemLocatorText = subItemLocatorText;
    }

    public void addSubItems(SubItem[] items) {
        for (SubItem item : items) {
            this.subItemsMap.put(item.name(), item);
        }
    }

    public HashMap<String, SubItem> getSubItemsMap() {
        return subItemsMap;
    }

    public boolean isExcludeFromSearch() {
        return excludeFromSearch;
    }

    public void setExcludeFromSearch(boolean excludeFromSearch) {
        this.excludeFromSearch = excludeFromSearch;
    }

    public WebDriver getDriver() {
        return parent.getDriver();
    }

    public WebElement element() {
        return getDriver().findElement(locator);
    }

    public boolean waitUntil(ExpectedCondition<?> condition, long timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        try {
            wait.until(condition);
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean exists(long timeout) {
        return waitUntil(ExpectedConditions.presenceOfElementLocated(locator), timeout);
    }

    public boolean exists() {
        return exists(TIMEOUT);
    }

    public boolean isElementPresent() {
        try {
            this.getText();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean disappears(long timeout) {
        return waitUntil(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(locator)), timeout);
    }

    public boolean disappears() {
        return disappears(TIMEOUT);
    }

    public boolean visible(long timeout) {
        return waitUntil(ExpectedConditions.visibilityOfElementLocated(locator), timeout);
    }

    public boolean visible() {
        Assert.assertTrue(
                "Unable to find element: " + this.locator.toString(),
                exists());
        return visible(TIMEOUT);
    }

    public boolean invisible(long timeout) {
        return waitUntil(ExpectedConditions.invisibilityOfElementLocated(locator), timeout);
    }

    public boolean invisible() {
        Assert.assertTrue(
                "Unable to find element: " + this.locator.toString(),
                exists());
        return invisible(TIMEOUT);
    }

    public boolean enabled(long timeout) {
        return waitUntil(ExpectedConditions.elementToBeClickable(locator), timeout);
    }

    public boolean enabled() {
        return enabled(TIMEOUT);
    }

    public boolean disabled(long timeout) {
        return waitUntil(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(locator)), timeout);
    }

    public boolean disabled() {
        return !enabled(TIMEOUT);
    }

    public boolean clickable(long timeout) {
        return waitUntil(ExpectedConditions.elementToBeClickable(locator), timeout);
    }

    public boolean clickable() {
        return clickable(TIMEOUT);
    }

    public boolean hidden(long timeout) {
        return waitUntil(ExpectedConditions.attributeToBe(locator, "class", "splash hide"), timeout);
    }

    public boolean hidden() {
        return hidden(TIMEOUT);
    }


    public void click() {
        exists();
        if (!visible()) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) Driver.current();
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element());
        }
        visible();
        clickable();
        Assert.assertTrue(
                "Element is still invisible: " + this.locator.toString(),
                visible());
        this.element().click();
    }


    public void doubleclick() {
        exists();
        if (!visible()) {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) Driver.current();
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element());
        }
        visible();
        clickable();
        Assert.assertTrue(
                "Element is still invisible: " + this.locator.toString(),
                visible());
        Actions action = new Actions(Driver.current());
        action.doubleClick(this.element()).perform();
    }

    public <T extends Page> T clickAndWaitFor(Class<T> pageClass) throws Exception {
        this.click();
        T page = PageFactory.init(pageClass);
        Assert.assertTrue(String.format("The page of %s class didn't appear during specified timeout", pageClass.getName()),
                page.isCurrent());
        return page;
    }

    public String getText() {
        return this.element().getText();
    }

    public void clickOver() {
        Actions action = new Actions(getDriver());
        action.moveToElement(element())
                .click()
                .build()
                .perform();
    }

    public String getAttribute(String name) {
        return element().getAttribute(name);
    }

    public WebElement findElementByXpath(String name) {
        return element().findElement(By.xpath(name));
    }


}