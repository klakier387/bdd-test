package com.POM;

import com.framework.bdd.Alias;
import com.framework.ui.FindBy;
import com.framework.ui.Page;
import com.framework.ui.controls.Control;
import org.openqa.selenium.WebDriver;
@Alias("Top Bar")
public class TopBarPage extends Page {
    public TopBarPage(WebDriver driverValue){super(driverValue); }
    @Alias("Top Bar Stays")
    @FindBy(locator = "css=nav[class='bui-tab bui-header__tab bui-tab--borderless bui-tab--light'] li:nth-child(1)")
    public Control topBarStays;

    @Alias("Top Bar Flights")
    @FindBy(locator = "css=nav[class='bui-tab bui-header__tab bui-tab--borderless bui-tab--light'] li:nth-child(2)")
    public Control topBarFlights;

    @Alias("Top Bar Car Rentals")
    @FindBy(locator = "css=nav[class='bui-tab bui-header__tab bui-tab--borderless bui-tab--light'] li:nth-child(3)")
    public Control topBarCarRentals;

    @Alias("Top Bar Attractions")
    @FindBy(locator = "css=nav[class='bui-tab bui-header__tab bui-tab--borderless bui-tab--light'] li:nth-child(4)")
    public Control topBarAttractions;

    @Alias("Top Bar Airports Taxi")
    @FindBy(locator = "css=nav[class='bui-tab bui-header__tab bui-tab--borderless bui-tab--light'] li:nth-child(5)")
    public Control topBarAirportsTaxi;





}
