package com.framework.ui.controls;

import com.framework.ui.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SelectList extends Control {

    public SelectList(Page parentValue, By locatorValue) {
        super(parentValue, locatorValue);
    }

    public void selectByText(String value) {
        exists();
        List<WebElement> allOptions = element().findElements(By.tagName("option"));

        if (allOptions.isEmpty()) {
            element().click();
            allOptions.addAll(element().findElements(By.tagName("span")));
        }

        if (allOptions.isEmpty()) {
            element().click();
            allOptions.addAll(element().findElements(By.tagName("button")));
        }

        for (WebElement option : allOptions) {
            if (option.getText().contains(value)) {
                option.click();
                break;
            }
        }
    }
}
