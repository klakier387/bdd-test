package com.POM.topBar;

import com.framework.bdd.Alias;
import com.framework.ui.FindBy;
import com.framework.ui.Page;
import com.framework.ui.controls.Control;
import org.openqa.selenium.WebDriver;

@Alias("Go to Gate Page")
public class GotoGatePage extends Page {
    public GotoGatePage(WebDriver driverValue){super(driverValue);}

    @Alias("Go to Gate Flights")
    @FindBy(locator = "css=li.mainNavigation__listItem.mainNavigation__listItem--topLevel:nth-child(1) > a")
    public Control goToGateFlights;
}
