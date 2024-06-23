
package qtriptest.pages;

import org.apache.logging.log4j.core.layout.SyslogLayout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HistoryPage {
        WebDriver driver;
        String bookingID;
        
        public HistoryPage(WebDriver driver){
            this.driver = driver;
            PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
        }


        @FindBy(css = "#reservation-table th")
        private WebElement reservationid;

        @FindBy(className = "cancel-button")
        private WebElement cancelBooking;

        @FindBy(xpath = "//div[text()='Logout']")
        private WebElement logoutbutton;

        public void fetchBookingId(){
            this.bookingID = reservationid.getText();
            System.out.println("Reservation id:" + this.bookingID);
        }
        

        public void cancelBooking(){
            cancelBooking.click();           
        }

        public boolean isbookingpresent(){
            if(reservationid.isDisplayed() && reservationid.getText().contains(this.bookingID)){
                return true;
            }
            else{
                return false;
            }
        }

        public void logout(){
            logoutbutton.click();
        }
}