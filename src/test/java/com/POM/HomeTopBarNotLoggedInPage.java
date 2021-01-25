package com.POM;

import com.framework.bdd.Alias;
import com.framework.ui.FindBy;
import com.framework.ui.Page;
import com.framework.ui.controls.Control;
import org.openqa.selenium.WebDriver;
@Alias("Home Top Bar Not Logged In")
public class HomeTopBarNotLoggedInPage extends Page {

    public HomeTopBarNotLoggedInPage(WebDriver driverValue){super(driverValue);}

    @Alias("Register In")
    @FindBy(locator="css=a.bui-button.bui-button--secondary.js-header-login-link:nth-child(1)")
    public Control topBarRegisterIn;

    @Alias("Log In")
    @FindBy(locator = "css=a.bui-button.bui-button--secondary.js-header-login-link:nth-child(2)")
    public Control topBarLogIn;


}
