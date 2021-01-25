package com.POM.topBar;

import com.framework.bdd.Alias;
import com.framework.ui.FindBy;
import com.framework.ui.Page;
import com.framework.ui.controls.AutoComplete;
import com.framework.ui.controls.Control;
import com.framework.ui.controls.Edit;
import com.framework.ui.controls.SelectList;
import org.openqa.selenium.WebDriver;
@Alias("Airports Taxi Search")
public class AirportsTaxiSearchPage extends Page {
    public AirportsTaxiSearchPage(WebDriver driverValue){super(driverValue);}

    @Alias("One Way Airports Taxi")
    @FindBy(locator = "css=span[data-test='returnJourneyNegative-label']")
    public Control oneWayAirportTaxi;

    @Alias("Round Airports Taxi")
    @FindBy(locator ="css=span[data-test='returnJourneyAffirmative-label']")
    public Control returnAirportsTaxi;

    @Alias("Pick Up Location Airports Taxi")
    @FindBy(locator = "pickupLocation")
    public AutoComplete pickUpLocationAirportsTaxi;

    @Alias("Destination Location Airports Taxi")
    @FindBy(locator = "dropoffLocation")
    public AutoComplete destinationLocationAirportsTaxi;

    @Alias("Date Picker Airports Taxi")
    @FindBy(locator = "css=button[aria-label='pickup date input field']")
    public Control datePickerAirportsTaxi;

    @Alias("Time Picker Airports Taxi")
    @FindBy(locator = "css=button[aria-label='pickup time input field']")
    public Control timePickerAirportsTaxi;

    @Alias("Passengers Airports Taxi")
    @FindBy(locator = "passengers")
    public SelectList passengersAirportTaxi;

    @Alias("Submit Search Airports Taxi")
    @FindBy(locator = "css=div[class='gb-u-display-none gb-u-display-block@l gb-u-mb-']")
    public Control submitSearchAirportsTaxi;

}
