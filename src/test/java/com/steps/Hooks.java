package com.steps;


import com.framework.Configuration;
import com.framework.Driver;
import com.framework.ui.Page;
import com.paulhammant.ngwebdriver.NgWebDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class Hooks {

    private static NgWebDriver ngDriver;
    protected Dimension dimension;


    @Before
    public void beforeScenario(Scenario scenario) throws Exception {

        Configuration.load();
        Configuration.print();
        if (System.getProperty("os.name").toLowerCase().equals("linux")) {
            System.setProperty("webdriver.gecko.driver", new File("drivers/geckodriver").getAbsolutePath());
            System.setProperty("webdriver.chrome.driver", new File("drivers/chromedriver").getAbsolutePath());

        } else {
            System.setProperty("webdriver.gecko.driver", new File("drivers/geckodriver.exe").getAbsolutePath());
            System.setProperty("webdriver.chrome.driver", new File("drivers/chromedriver.exe").getAbsolutePath());
        }

        Assert.assertTrue("Only web platforms are supported by this test", Configuration.platform().isWeb());

        DesiredCapabilities cap = new DesiredCapabilities();
        Driver.add(Configuration.get("browser"), cap);

        ngDriver = new NgWebDriver((JavascriptExecutor) Driver.current());
        ngDriver.waitForAngularRequestsToFinish();
        try {
            dimension = new Dimension(1918, 1078);
            Driver.current().manage().window().setSize(dimension);
            System.out.println("Browser resolution: " + Driver.current().manage().window().getSize());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @After
    public void afterScenario(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                Page page = new Page(Driver.current());
                page.captureScreenShot("screenshot/" + scenario.getName().toString() + ".png");
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            Driver.current().quit();
        }
    }
}
