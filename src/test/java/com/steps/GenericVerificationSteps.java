package com.steps;

import com.framework.Context;
import com.framework.ui.Page;
import com.framework.ui.PageFactory;
import com.framework.ui.controls.Control;
import com.framework.ui.controls.TableView;
import com.udojava.evalex.Expression;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import org.junit.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class GenericVerificationSteps {


    @Then("^(?:I should see |)the \"(.*)\" field is available$")
    public Control verifyElementExists(String fieldName) throws Exception {
        Control control = Page.getCurrent().onPage(fieldName);
        Assert.assertNotNull("Unable to find the '" + fieldName + "' element on current page.", control);
        return control;
    }

    @Then("^(?:I shouldn't see |)the \"(.*)\" field is available$")
    public Control verifyElementDoesntExists(String fieldName) throws Exception {
        Control control = Page.getCurrent().onPage(fieldName);
        Assert.assertNull("Able to find the '" + fieldName + "' element on current page.", control);
        return control;
    }

    @Then("^(?:I should see |)the \"(.*)\" field contains the \"(.*)\" text$")
    public void verifyFieldText(String fieldName, String text) throws Exception {
        Control control = verifyElementExists(fieldName);
        String actualText = control.getText();
        Assert.assertTrue(
                String.format("The '%s' field has unexpected text. Expected: '%s', Actual: '%s'",
                        fieldName,
                        text,
                        actualText
                ),
                text.equals(actualText) || actualText.contains(text));
    }


    @Then("^I should see the \"(.*)\" (?:page|screen|bar)$")
    public void verifyCurrentPage(String name) throws Exception {
        Page target = Page.screen(name);
        Assert.assertTrue("The '" + name + "' screen is not current", target.isCurrent());
        Page.setCurrent(target);

    }

    @Then("^(?:I should see |)the \"(.*)\" (?:text|label) is shown$")
    public void verifyTextPresent(String text) throws Exception {
        Assert.assertTrue("Unable to find text: '" + text + "'", Page.getCurrent().isTextPresent(text));
    }

    @Then("^(?:I should see |)the following fields are shown:$")
    public void verifyMultipleFieldsAvailability(List<String> elements) throws Exception {
        for (String element : elements) {
            verifyElementExists(element);
        }
    }

    @Then("^(?:I should see |)the following labels are shown:$")
    public void verifyMultipleLabelsAvailability(List<String> elements) throws Exception {
        for (String element : elements) {
            verifyTextPresent(element);
        }
    }

    @Then("^(?:I should see |)the page contains the following data:$")
    public void pageContainsData(DataTable data) throws Throwable {
        List<Map<String, String>> content = data.asMaps(String.class,
                String.class);
        for (Map<String, String> row : content) {
            verifyFieldText(row.get("Field"), row.get("Value"));
        }
    }

    @Then("^(?:I should see |)the \"(.*)\" (?:list|table) is (|not )empty$")
    public void verifyListEmptyState(String list, String emptyState) throws Throwable {
        boolean empty = emptyState.trim().equals("");
        TableView control = (TableView) verifyElementExists(list);
        if (empty) {
            Assert.assertTrue("The '" + list + "' element is not empty", control.isEmpty());
        } else {
            Assert.assertTrue("The '" + list + "' element is empty", control.isNotEmpty());
        }
    }

    @Then("^(?:I should see |)the \"(.*)\" (?:list|table) contains the following data:$")
    public void verifyListRowDataByText(String list, DataTable data) throws Throwable {
        int controlNumber = 0;
        TableView control = (TableView) verifyElementExists(list);
        List<Map<String, String>> content = data.asMaps(String.class,
                String.class);

        int number = content.size();
        for (Map<String, String> row : content) {
            for (Map.Entry<String, String> entry : row.entrySet()) {
                for (int i = 0; i < control.getItemsCount(); i++) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (control.getSubItem(key, i).getText().equals(value)) {
                        controlNumber++;
                        break;
                    }
                    if (i > (control.getItemsCount() - 1))
                        Assert.assertTrue(
                                String.format("The '%s' field has unexpected text. Expected: '%s', Actual: '%s'",
                                        key,
                                        value,
                                        control.getSubItem(key, i).getText()
                                ),
                                value.equals(control.getSubItem(key, i).getText()) || control.getSubItem(key, i).getText().contains(value));

                }
            }

        }

        Assert.assertEquals(String.format("There is missing row element in the %s", list),
                content.size(), controlNumber);
    }

    @Then("^(?:I should see |)the all rows in \"(.*)\" (?:list|table) contains the following data:$")
    public void verifyAllRowListRowDataByText(String list, DataTable data) throws Throwable {
        int controlNumber = 0;
        TableView control = (TableView) verifyElementExists(list);
        List<Map<String, String>> content = data.asMaps(String.class,
                String.class);

        for (Map<String, String> row : content) {
            for (Map.Entry<String, String> entry : row.entrySet()) {
                for (int i = 0; i < control.getItemsCount(); i++) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Assert.assertTrue(
                            String.format("The '%s' field has unexpected text. Expected: '%s', Actual: '%s'",
                                    key,
                                    value,
                                    control.getSubItem(key, i).getText()
                            ),
                            value.equals(control.getSubItem(key, i).getText()) || control.getSubItem(key, i).getText().contains(value));
                }
            }

        }
    }


    @Then("^(?:I should see |)the (first|last) (?:row|item) of the \"(.*)\" (?:list|table) contains the following data:$")
    public void verifyListRowData(String firstLast, String list, DataTable data) throws Throwable {
        int index = 0;
        TableView control = (TableView) verifyElementExists(list);
        if (firstLast.equals("last")) {
            index = control.getItemsCount() - 1;
        }
        List<Map<String, String>> content = data.asMaps(String.class,
                String.class);
        for (Map<String, String> row : content) {
            for (Map.Entry<String, String> entry : row.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                Assert.assertEquals(String.format("The %s row element '%s' has unexpected value", firstLast, key),
                        value, control.getSubItem(key, index).getText());
            }
        }
    }

    @Then("^(?:I should see |)the \"(.*)\" (?:table|list) has \"(.*)\" (?:items|rows)$")
    public void verifyTableRowCount(String list, String countValue) throws Exception {
        TableView control = (TableView) verifyElementExists(list);
        BigDecimal actualCount = new BigDecimal(control.getItemsCount());
        String expectedCountValue = countValue;
        for (String key : Context.variables()) {
            expectedCountValue = expectedCountValue.replaceAll(key, Context.get(key).toString());
        }
        Expression expression = new Expression(expectedCountValue);
        BigDecimal expectedCount = expression.setPrecision(0).eval();
        Assert.assertEquals("Unexpected row count for the '" + list + "' table", expectedCount, actualCount);
    }

    @Then("^(?:I should see |)the \"(.*)\" element is clickable$")
    public void theIsClickable(String fieldName) throws Throwable {
        Control control = Page.getCurrent().onPage(fieldName);
        Assert.assertNotNull("Unable to find the '" + fieldName + "' element on current page.", control);
        Assert.assertNotNull("Element '" + fieldName + "'is not clickable.", control.clickable());
    }


/*
    @Then("^(?:I should see |)the \"(.*)\" element of \"(.*)\" column of the \"(.*)\" (?:list|table) is not clickable$")
    public void clickOnSubItemByText(String subElementText, String item, String list) throws Exception {
        GenericVerificationSteps genericVerification = new GenericVerificationSteps();
        RowTableCellModificationPage rowTableCellModificationPage = PageFactory.init(RowTableCellModificationPage.class);
        TableView control = (TableView) genericVerification.verifyElementExists(list);
        for (int i = 0; i < control.getItemsCount(); i++) {
            if (control.getSubItem(item, i).getText().equals(subElementText)) {
                control.getSubItem(item, i).click();
                Assert.assertFalse("Able to click on the '" + control.getSubItem(item, i).getLocatorText() + "' element on current page.", rowTableCellModificationPage.cSaveEditor.exists());
                break;
            }
            if (i + 1 == control.getItemsCount()) {
                throw new RuntimeException("Not found element: " + subElementText);
            }
        }
    }*/


}
