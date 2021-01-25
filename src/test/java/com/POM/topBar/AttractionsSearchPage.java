package com.POM.topBar;

import com.framework.bdd.Alias;
import com.framework.ui.FindBy;
import com.framework.ui.Page;
import com.framework.ui.controls.Edit;
import org.openqa.selenium.WebDriver;
@Alias("Attractions Search")
public class AttractionsSearchPage extends Page {
    public AttractionsSearchPage(WebDriver driverValue){super(driverValue);}

    @Alias("Attractions Search Input")
    @FindBy(locator = "css=div[class='css-dws2ec']")
    public Edit attractionsSearch;
}
