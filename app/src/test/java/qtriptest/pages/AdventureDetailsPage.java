package qtriptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventureDetailsPage {
    WebDriver driver;
    String verifybookingbanner = "Greetings! Reservation for this adventure is successful.";

public AdventureDetailsPage(WebDriver driver){
    this.driver = driver;
    PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
}

@FindBy(xpath = "//input[@name='name']")
private WebElement guestName;

@FindBy(xpath = "//input[@name='date']")
private WebElement dateofBooking;

@FindBy(xpath = "//input[@name='person']")
private WebElement personCount;

@FindBy(className = "reserve-button")
private WebElement reserveButton;

@FindBy(id = "reserved-banner")
private WebElement verifyBookingText;

@FindBy(xpath = "//li/a[text()='Reservations']")
private WebElement reservationbutton;



public void fillAdventureDetails(String name, String date, String personcount){

    guestName.sendKeys(name);
    dateofBooking.sendKeys(date);
    personCount.clear();
    personCount.sendKeys(personcount);

}

public void reserveBooking(){
    reserveButton.click();
}

public boolean verifyBooking(){
    if(verifyBookingText.getText().contains(verifybookingbanner))
    {
        return true;
    }
    else{
        return false;
    }
}

public void navigateToHistoryPage(){
    reservationbutton.click();
}



}