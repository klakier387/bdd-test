package com.POM.airportsTaxi;

import com.POM.topBar.AirportsTaxiSearchPage;
import com.framework.bdd.Alias;
import com.framework.ui.FindBy;
import com.framework.ui.Page;
import com.framework.ui.controls.Control;
import org.openqa.selenium.WebDriver;

@Alias("Airports Taxi Results")
public class AirportsTaxiResultsPage extends Page {
    public AirportsTaxiResultsPage(WebDriver driver){super(driver);}

    @Alias("Explore Booking.com Airports Taxi Result")
    @FindBy(locator = "css=div[class='tx-c-explore-service-inner-btn gb-o-btn gb-u-ease-out-02 gb-o-btn--secondary gb-o-btn--horizontal']")
    public Control exploreBookingcomAirportsTaxiResult;
}
