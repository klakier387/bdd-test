package com.steps;

import com.framework.ui.controls.AutoComplete;
import com.framework.ui.controls.Edit;
import com.framework.ui.controls.SelectList;
import cucumber.api.DataTable;
import cucumber.api.java.en.When;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class GenericPopulateDataSteps {


    protected String changingEnumDatesIntoDatesAndSeconds(EnumDates text) {
        Calendar calendar = Calendar.getInstance();
        switch (text) {
            case TODAY:
                return new SimpleDateFormat("yyyyMMdd_HHmmss").format(calendar.getTime());
            case TOMORROW:
                calendar.add(Calendar.DATE, 1);
                return new SimpleDateFormat("yyyyMMdd_HHmmss").format(calendar.getTime());
            case YESTERDAY:
                calendar.add(Calendar.DATE, -1);
                return new SimpleDateFormat("yyyyMMdd_HHmmss").format(calendar.getTime());
            default:
                return null;
        }

    }

    protected String changingEnumDatesIntoDates(EnumDates text) {
        Calendar calendar = Calendar.getInstance();
        switch (text) {
            case TODAY:
                return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            case TOMORROW:
                calendar.add(Calendar.DATE, 1);
                return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            case YESTERDAY:
                calendar.add(Calendar.DATE, -1);
                return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            default:
                return null;
        }
    }


    @When("^(?:I |)enter \"(.*)\" text into the \"(.*)\" field$")
    public void enterValue(String text, String fieldName) throws Exception {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        Edit control = (Edit) genericVerification.verifyElementExists(fieldName);
        control.setText(text);
    }

    @When("^(?:I |)enter \"(.*)\" text into (?:File Manager|text field)$")
    public void enterValueIntoFileManager(String text) throws Exception {

        Robot robot = new Robot();

        robot.delay(500);
        for (int i = 0; i < text.length(); i++) {
            robot.keyPress(Character.toUpperCase(text.charAt(i)));
            robot.keyRelease(Character.toUpperCase(text.charAt(i)));
        }
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(500);

    }

    @When("^(?:I |)enter \"(.*)\" enum into the \"(.*)\" field$")
    public void enterEnumValue(EnumDates text, String fieldName) throws Exception {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        Edit control = (Edit) genericVerification.verifyElementExists(fieldName);
        control.setText(changingEnumDatesIntoDatesAndSeconds(text));
    }

    @When("^(?:I |)enter \"(.*)\" date into the \"(.*)\" field$")
    public void enterDateValue(EnumDates text, String fieldName) throws Exception {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        Edit control = (Edit) genericVerification.verifyElementExists(fieldName);
        control.click();
        control.sendText(changingEnumDatesIntoDates(text));
    }

    @When("^(?:I |)choose \"(.*)\" select list from the \"(.*)\" field$")
    public void chooseValueFromSelectList(String text, String fieldName) throws Exception {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        SelectList control = (SelectList) genericVerification.verifyElementExists(fieldName);
        control.selectByText(text);
    }

    @When("^(?:I |)enter \"(.*)\" auto complete into the \"(.*)\" field$")
    public void enterValueIntoAutoComplete(String text, String fieldName) throws Exception {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        AutoComplete control = (AutoComplete) genericVerification.verifyElementExists(fieldName);
        control.selectByText(text);
    }


    @When("^(?:I |)populate current page with the following data:$")
    public void populatePageWithData(DataTable data) throws Throwable {
        List<Map<String, String>> content = data.asMaps(String.class,
                String.class);
        for (Map<String, String> row : content) {
            enterValue(row.get("Value"), row.get("Field"));
        }
    }

    @When("^(?:I |)populate current page with the following enums:$")
    public void populatePageWithEnum(DataTable data) throws Throwable {
        List<Map<String, String>> content = data.asMaps(String.class,
                String.class);
        for (Map<String, String> row : content) {
            enterEnumValue(EnumDates.valueOf(row.get("Value")), row.get("Field"));
        }
    }

    @When("^(?:I |)populate current page with the following dates:$")
    public void populatePageWithDate(DataTable data) throws Throwable {
        List<Map<String, String>> content = data.asMaps(String.class,
                String.class);
        for (Map<String, String> row : content) {
            enterDateValue(EnumDates.valueOf(row.get("Value")), row.get("Field"));
        }
    }

    @When("^(?:I |)populate current page with the following select list:$")
    public void populatePageWithSelectList(DataTable data) throws Throwable {
        List<Map<String, String>> content = data.asMaps(String.class,
                String.class);
        for (Map<String, String> row : content) {
            chooseValueFromSelectList(row.get("Value"), row.get("Field"));
        }
    }

    @When("^(?:I |)populate current page with the following auto complete:$")
    public void populatePageWithAutoComplete(DataTable data) throws Throwable {
        List<Map<String, String>> content = data.asMaps(String.class,
                String.class);
        for (Map<String, String> row : content) {
            enterValueIntoAutoComplete(row.get("Value"), row.get("Field"));
        }
    }

    public enum EnumDates {
        TODAY,
        TOMORROW,
        YESTERDAY
    }


}
