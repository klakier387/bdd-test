package com.POM;

import com.framework.bdd.Alias;
import com.framework.ui.FindBy;
import com.framework.ui.Page;
import com.framework.ui.controls.Control;
import org.openqa.selenium.WebDriver;

@Alias("Home Top Bar")
public class HomeTopBarLoggedInPage extends Page {
    public HomeTopBarLoggedInPage(WebDriver driverValue) {
        super(driverValue);
    }
    @Alias("Logged In User")
    @FindBy(locator = "css=div[class='bui-avatar-block__text']")
    public Control loggedIn;
}
