package com.POM.topBar;

import com.framework.bdd.Alias;
import com.framework.ui.FindBy;
import com.framework.ui.Page;
import com.framework.ui.controls.Control;
import com.framework.ui.controls.Edit;
import org.openqa.selenium.WebDriver;

@Alias("Stays Search Bar")
public class StaysSearchBarPage extends Page {
    public StaysSearchBarPage(WebDriver driverValue){super(driverValue);}

    @Alias("Destination Stays Search")
    @FindBy(locator = "ss")
    public Edit destinationStaysSearch;

    @Alias("Checkin Checkout Stays Search")
    @FindBy(locator = "css=div[data-mode='checkin']")
    public Control checkinCheckoutStaysSearch;

    @Alias("People Number Stays Search")
    @FindBy(locator = "xp__guests__toggle")
    public Control peopleNumberStaysSearch;
}
