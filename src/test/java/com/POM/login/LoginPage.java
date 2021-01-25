package com.POM.login;


import com.framework.Configuration;
import com.framework.bdd.Alias;
import com.framework.ui.FindBy;
import com.framework.ui.Page;
import com.framework.ui.controls.Control;
import com.framework.ui.controls.Edit;
import org.openqa.selenium.WebDriver;

@Alias("Login Page")
public class LoginPage extends Page {

    @FindBy(locator = "css=a.bui-button.bui-button--secondary.js-header-login-link:nth-child(2)")
    public Control loginTo;

    @FindBy(locator = "css=button.bui-button.bui-button--large.bui-button--wide:nth-child(1)")
    public Control continueLoginTo;

    @FindBy(locator = "css=button[type='submit'] span[class='bui-button__text']")
    public Control continuePasswordTo;

    @FindBy(locator = "username")
    public Edit editUsername;

    @FindBy(locator = "password")
    public Edit editPassword;

    @FindBy(locator = "new_password")
    public Edit newPassword;

    @FindBy(locator="confirmed_password")
    public Edit confirmPassword;

    @Alias("Wrong Credentials Message")
    @FindBy(locator = "password-description")
    public Control loginMessage;

    @FindBy(locator = "new_password-description")
    public Control newLoginMessage;

    String baseUrl;


    public LoginPage(WebDriver driverValue) {
        super(driverValue);
    }

    @Override
    public Page navigate() {

        if (System.getProperties().toString().contains("hostName"))
            baseUrl = System.getProperty("hostName");
        else
            baseUrl = Configuration.get("url");

        this.getDriver().get(baseUrl);
        return this;
    }
}
