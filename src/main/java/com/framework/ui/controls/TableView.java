package com.framework.ui.controls;

import com.framework.ui.Page;
import com.framework.ui.SubItem;
import org.openqa.selenium.By;

public class TableView extends Control {

    public TableView(Page parentValue, By locatorValue) {
        super(parentValue, locatorValue);
    }

    protected String getFullItemLocator() {
        return String.format("%s%s", getLocatorText(), getItemLocatorText());
    }

    public int getItemsCount() {
        return getDriver().findElements(By.xpath(getFullItemLocator())).size();
    }

    public Control getItem(int index) {
        String locator = String.format("(%s)[%d]", getFullItemLocator(), index + 1);
        return new Control(getParent(), By.xpath(locator));
    }

    public boolean isEmpty(long timeout) {
        return getItem(0).disappears(timeout);
    }

    public boolean isEmpty() {
        return isEmpty(TIMEOUT);
    }

    public boolean isNotEmpty(long timeout) {
        return getItem(0).exists();
    }

    public boolean isNotEmpty() {
        return isNotEmpty(TIMEOUT);
    }

    public By getSubItemLocator(String name, int index) {
        SubItem item = getSubItemsMap().get(name);
        String locator = String.format("(%s)[%d]%s", getFullItemLocator(), index + 1, item.locator());
        return By.xpath(locator);
    }

    public <T extends Control> T getSubItem(String name, int index, Class<T> itemType) throws Exception {
        return itemType.getConstructor(Page.class, By.class).
                newInstance(getParent(), getSubItemLocator(name, index));
    }

    public Control getSubItem(String name, int index) throws Exception {
        SubItem item = getSubItemsMap().get(name);
        return getSubItem(name, index, item.controlType());

    }

    public Control getSubItemByTypeText(String subItemType, String text) throws Exception {
        SubItem item = getSubItemsMap().get(subItemType);


        return null;
    }
}