package com.steps;

//import com.POM.LackOfUploadedFilesPage;
import com.POM.login.LoginPage;
//import com.POM.RowTableCellModificationPage;
import com.framework.Context;
import com.framework.Driver;
import com.framework.ui.Page;
import com.framework.ui.PageFactory;
import com.framework.ui.controls.Control;
import com.framework.ui.controls.TableView;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenericSimpleSteps {

    @Given("^I am logged in as \"(.*)\" in platform$")
    public void iAmLoggedInAs(String username) throws Throwable {
        LoginPage loginPage = PageFactory.init(LoginPage.class);
        loginPage.navigate();
        if (username.equals("Sabina Subik")) {
            loginPage.loginTo.click();
            loginPage.editUsername.setText("sabina.subik@gmail.com");
            loginPage.continueLoginTo.click();
            loginPage.editPassword.setText("Password1234");
            loginPage.continuePasswordTo.click();

        }
        Driver.current().findElement(By.id("onetrust-accept-btn-handler")).click();
    }


    @Given("^I am on the \"(.*)\" (?:page|screen)$")
    @When("^I go to the \"(.*)\" (?:page|screen)$")
    public void navigateToPage(String name) throws Exception {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        Page target = Page.screen(name);
        Assert.assertNotNull("Unable to find the'" + name + "' page", target);
        target.navigate();
        genericVerification.verifyCurrentPage(name);
    }

    @When("^(?:I |)(?:click|tap) on the \"(.*)\" (?:button|element|control)$")
    public void clickOnTheButton(String name) throws Exception {
        Page.getCurrent().onPage(name).visible();
        Control control = Page.getCurrent().onPage(name);
        Assert.assertNotNull("Unable to find'" + name + "' element on current page", control);
        control.click();
    }

    @When("^(?:I |)am not able to (?:click|tap) on the \"(.*)\" (?:button|element|control)$")
    public void amNotAbleToclickOnTheButton(String name) throws Exception {
        Page.getCurrent().onPage(name).visible();
        Control control = Page.getCurrent().onPage(name);
        Assert.assertNotNull("Unable to find'" + name + "' element on current page", control);
        Assert.assertTrue("Element '" + name + "' is enabled on current page", control.disabled());
    }

    @When("^(?:I |)accept the alert message$")
    public void acceptAlert() {
        Driver.current().switchTo().alert().accept();
    }

    @When("^(?:I |)note the \"(.*)\" (?:table|list) (?:row|item) count as \"(.*)\"")
    public void noteRowCountAs(String list, String varName) throws Exception {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        TableView control = (TableView) genericVerification.verifyElementExists(list);
        Context.put(varName, control.getItemsCount());
    }

    @When("^(?:I |)note the \"(.*)\" field text as \"(.*)\"")
    public void noteControlTextAs(String list, String varName) throws Exception {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        Control control = genericVerification.verifyElementExists(list);
        Context.put(varName, control.getText());
    }

    @And("^I click over \"(.*)\" (?:button|element|control)$")
    public void iHoverOverElement(String name) throws Exception {
        Page.getCurrent().onPage(name).visible();
        Control control = Page.getCurrent().onPage(name);
        Assert.assertNotNull("Unable to find'" + name + "' element on current page", control);
        control.clickOver();
    }

    @When("^(?:I |)(?:click|tap) on the (first|last) \"(.*)\" element of the \"(.*)\" (?:list|table)$")
    public void clickOnSubItem(String firstLast, String item, String list) throws Exception {
        int index = 0;
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        TableView control = (TableView) genericVerification.verifyElementExists(list);
        if (firstLast.equals("last")) {
            index = control.getItemsCount() - 1;
        }
        control.getSubItem(item, index).click();
    }

    @When("^(?:I |)(?:click|tap) on the \"(.*)\" element of \"(.*)\" column of the \"(.*)\" (?:list|table)$")
    public void clickOnSubItemByText(String subElementText, String item, String list) throws Exception {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        TableView control = (TableView) genericVerification.verifyElementExists(list);
        for (int i = 0; i < control.getItemsCount(); i++) {
            if (control.getSubItem(item, i).getText().equals(subElementText)) {
                control.getSubItem(item, i).click();
                break;
            }
            if (i + 1 == control.getItemsCount()) {
                throw new RuntimeException("Not found element: " + subElementText);
            }
        }
    }

/*
    @When("^(?:I |)(?:set) \"(.*)\" text on the \"(.*)\" element of \"(.*)\" column of the \"(.*)\" (?:list|table)$")
    public void doubleClickSetTextOnSubItemByText(String textInput, String subElementText, String item, String list) throws Exception {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        TableView control = (TableView) genericVerification.verifyElementExists(list);

        if (list.equals("Data Browser VAT Records Table")) {


            for (int i = 0; i < control.getItemsCount(); i++) {
                control.getSubItem(item, i).doubleclick();
                RowTableCellModificationPage rowTableCellModificationPage = PageFactory.init(RowTableCellModificationPage.class);
                if (control.getSubItem(item, i).getText().equals(subElementText)) {

                    Thread.sleep(50);
                    Actions actions = new Actions(Driver.current());
                    actions.moveToElement(control.getSubItem(item, i).element())
                            .sendKeys(textInput)
                            .build()
                            .perform();
                    Thread.sleep(50);
                    rowTableCellModificationPage.cSaveEditor.click();
                    Thread.sleep(50);
                    break;
                }
                Thread.sleep(50);
                rowTableCellModificationPage.cCancelEditor.click();


                if (i + 1 == control.getItemsCount()) {
                    throw new RuntimeException("Not found element: " + subElementText);
                }
            }
        } else {
            for (int i = 0; i < control.getItemsCount(); i++) {

                if (control.getSubItem(item, i).getText().equals(subElementText)) {
                    control.getSubItem(item, i).doubleclick();
                    break;
                }
                if (i + 1 == control.getItemsCount()) {
                    throw new RuntimeException("Not found element: " + subElementText);
                }
            }
        }
    }
*/
    @When("^(?:I |)(?:click|tap) on \"(.*)\" on the \"(.*)\" element of \"(.*)\" column of the \"(.*)\" (?:list|table)$")
    public void clickOnSubItemButtonByText(String button, String subElementText, String item, String list) throws Exception {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        TableView control = (TableView) genericVerification.verifyElementExists(list);

        for (int i = 0; i < control.getItemsCount(); i++) {

            if (control.getSubItem(item, i).getText().equals(subElementText)) {
                control.getSubItem(item, i).click();
                control.getSubItem(button, i).click();
                break;
            }
        }
    }
/*
    @When("^(?:I |)(?:remove all|) rows of the \"(.*)\" (?:list|table) by clicking on the \"(.*)\" element$")
    public void removeAllSubItemButtonByText(String list, String button) throws Exception {
        LackOfUploadedFilesPage lackOfUploadedFilesPage = PageFactory.init(LackOfUploadedFilesPage.class);
        if (!lackOfUploadedFilesPage.cLackOfUploadedFiles.exists()) {


            GenericVerificationSteps genericVerification = new GenericVerificationSteps();
            Page target = Page.screen(list);
            Assert.assertNotNull("Unable to find the'" + list + "' page", target);
            target.navigate();
            genericVerification.verifyCurrentPage(list);

            TableView control = (TableView) genericVerification.verifyElementExists(list);

            Assert.assertTrue("The '" + list + "' element is empty", control.isNotEmpty());

            for (int i = 0; i < control.getItemsCount(); i++) {
                control.getSubItem(button, i).click();

                Thread.sleep(500);
                RemoveFilePage removeFilePage = PageFactory.init(RemoveFilePage.class);
                removeFilePage.cRemoveFileFrameYes.click();
            }
        }
    }
*/
    @Then("^I take screenshot$")
    public void iTakeScreenshot() throws Throwable {
        Page page = new Page(Driver.current());

        Thread.sleep(1000);

        JavascriptExecutor jsx = (JavascriptExecutor) Driver.current();
        jsx.executeScript("window.scrollBy(0,150)", "");

        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime localDate = LocalDateTime.now();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localDate.format(formatter);

        page.captureScreenShot("screenshot/About" + formattedDateTime + ".png");
    }

    @And("^I wait for \"(.*)\" seconds$")
    public void iWaitForSeconds(int miliseconds) throws Throwable {

        Thread.sleep(miliseconds * 1000);
    }

    @And("^I refresh the page$")
    public void iRefreshThePage() throws Throwable {

        Robot robot = new Robot();

        robot.delay(500);
        robot.keyPress(KeyEvent.VK_F5);
        robot.keyRelease(KeyEvent.VK_F5);
        robot.delay(500);
    }

    @Given("^I try to log in as \"([^\"]*)\" and password \"([^\"]*)\" in platform$")

    public void iTryToLogInAsInPlatform(String username, String password) throws Throwable {
        LoginPage loginPage = PageFactory.init(LoginPage.class);
        loginPage.navigate();
        if (username.equals("sabina.subik@gmail.com")) {
            loginPage.loginTo.click();
            loginPage.editUsername.setText(username);
            loginPage.continueLoginTo.click();
            loginPage.editPassword.setText(password);
            loginPage.continuePasswordTo.click();
            loginPage.loginMessage.exists();
            Assert.assertEquals(loginPage.loginMessage.getText(), "Podane e-mail lub hasło są błędne");
        }
        else if (username.equals("sab.subik@gmail.com")){
            loginPage.loginTo.click();
            loginPage.editUsername.setText(username);
            loginPage.continueLoginTo.click();
            loginPage.newPassword.setText(password);
            loginPage.confirmPassword.setText(password);
            loginPage.continuePasswordTo.exists();

        }
    }

/*
    @And("^I go to \"(.*)\" web page$")
    public void iGoToWebPage(String name) throws Throwable {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        Page target = Page.screen(name);
        Assert.assertNotNull("Unable to find the'" + name + "' page", target);
        target.navigate();
        genericVerification.verifyCurrentPage(name);
    }*/
}
