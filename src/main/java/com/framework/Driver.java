package com.framework;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public final class Driver {

    private static final Map<String, Class<?>> driverMap = new HashMap<String, Class<?>>() {
        {
            put(Platform.CHROME.getValue(), ChromeDriver.class);
            put(Platform.FIREFOX.getValue(), FirefoxDriver.class);
        }
    };

    ;
    private static ConcurrentHashMap<String, WebDriver> driverThreadMap = new ConcurrentHashMap<String, WebDriver>();

    private Driver() {
    }

    public static String getThreadName() {

        return Thread.currentThread().getName() + "-" + Thread.currentThread().getId();
    }

    public static void add(String browser, Capabilities capabilities) throws Exception {

        Class<?> driverClass = driverMap.get(browser);
        WebDriver driver = (WebDriver) driverClass.getConstructor(Capabilities.class).newInstance(capabilities);
        String threadName = getThreadName();
        driverThreadMap.put(threadName, driver);

    }

    public static WebDriver current() {

        String threadName = getThreadName();
        return driverThreadMap.get(threadName);
    }

    public static RemoteWebDriver SetupRemoteChromeDriver() {

        Integer pageTimeout = 50;


        final DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setJavascriptEnabled(true);
        capabilities.setBrowserName(Configuration.get("browser"));

        capabilities.setCapability("name", "uploadfile");

        RemoteWebDriver browser = null;
        try {
            browser = new RemoteWebDriver(
                    new URL("http://127.0.0.1:4444/wd/hub"),
                    capabilities
            );

            //File upload issue specific to chromedriver
            browser.setFileDetector(new LocalFileDetector());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        browser.manage().timeouts().pageLoadTimeout(pageTimeout, TimeUnit.SECONDS);
        browser.manage().timeouts().setScriptTimeout(pageTimeout, TimeUnit.SECONDS);
        browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        return browser;
    }

}
