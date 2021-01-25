package com.framework.ui.controls;

import com.framework.ui.Page;
import org.openqa.selenium.By;

public class Label extends Control {

    public Label(Page parentValue, By locatorValue) {
        super(parentValue, locatorValue);
    }

    public String getText() {
        return element().getText();
    }
}
