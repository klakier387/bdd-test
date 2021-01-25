package com.POM.topBar;

import com.framework.bdd.Alias;
import com.framework.ui.FindBy;
import com.framework.ui.Page;
import com.framework.ui.controls.Control;
import com.framework.ui.controls.Edit;
import org.openqa.selenium.WebDriver;
@Alias("Cars Rental Search Page")
public class CarsRentalSearchPage extends Page {
    public CarsRentalSearchPage(WebDriver driverValue){super(driverValue);}

    @Alias("Return to the Same Location Cars Rental Search")
    @FindBy(locator = "css=label[for='return-location-same']")
    public Control returnToTheSameLocationCarsRentalSearch;

    @Alias("Pick Up Location Cars Rental Search")
    @FindBy(locator = "ss_origin")
    public Edit pickUpLocationCarsRentalSearch;
}
