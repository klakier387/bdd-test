package com.framework.ui.controls;

import com.framework.Driver;
import com.framework.ui.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class AutoComplete extends Control {

    public AutoComplete(Page parentValue, By locatorValue) {
        super(parentValue, locatorValue);
    }

    public void selectByText(String value) {
        exists();

        Actions actions = new Actions(Driver.current());
        actions.moveToElement(element())
                .click()
                .sendKeys(value)
                .build()
                .perform();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> allOptions = element().findElements(By.tagName("i"));
        for (WebElement option : allOptions) {
            if (value.contains(option.getText())) {
                Actions sendEnter = new Actions(Driver.current());
                sendEnter.moveToElement(option)
                        .sendKeys(Keys.ENTER)
                        .build()
                        .perform();
            }
        }

    }

}
