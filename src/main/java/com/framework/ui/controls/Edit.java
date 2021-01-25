package com.framework.ui.controls;

import com.framework.Driver;
import com.framework.ui.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class Edit extends Control {

    public Edit(Page parentValue, By locatorValue) {
        super(parentValue, locatorValue);
    }

    @Override
    public String getText() {
        return element().getAttribute("value");
    }

    public void setText(String value) throws Exception {
        click();
        element().clear();
        Thread.sleep(50);
        element().sendKeys(value);
    }

    public void sendText(String value) throws Exception {
        Actions actions = new Actions(Driver.current());
        actions.moveToElement(element())
                .click()
                .sendKeys(value)
                .build()
                .perform();
    }
}
