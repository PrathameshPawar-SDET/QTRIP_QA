package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_03 {

    static WebDriver driver;

    @BeforeTest(enabled = true)
    public static void createDriver() throws MalformedURLException, InterruptedException {
       
        driver = DriverSingleton.getdriver();
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        Thread.sleep(2000);
    }

    @Test(dataProvider = "excelReading", dataProviderClass = DP.class,description ="Verify Booking and Cancellation Flow", groups = {"Booking and Cancellation Flow"}, priority = 3)
    public void testcase03(String userName, String password, String searchCity, 
    String AdventureName, String guestName, String date, String count) throws InterruptedException{

        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        Thread.sleep(2000);
        home.navigateToRegisterPage();
        Thread.sleep(1000);
        RegisterPage register = new RegisterPage(driver);
        register.RegisternewUser(userName, password, password, true);
        String Lastgeneratedusername = register.usernamevalue;
        Thread.sleep(2000);
        
        LoginPage login = new LoginPage(driver);
        login.LoginQtrip(Lastgeneratedusername, password);

        home.searchData(searchCity);
        home.selectCity();

        AdventurePage adventure = new AdventurePage(driver);
        adventure.searchAdventure(AdventureName);
        Thread.sleep(1000);
        adventure.selectAdventure();
        
        Thread.sleep(2000);

        AdventureDetailsPage adventureDetails = new AdventureDetailsPage(driver);
        adventureDetails.fillAdventureDetails(guestName, date, count);
        Thread.sleep(3000);
        adventureDetails.reserveBooking();
        Thread.sleep(5000);
        adventureDetails.verifyBooking();
        Thread.sleep(1000);
        adventureDetails.navigateToHistoryPage();

        HistoryPage history = new HistoryPage(driver);
        history.fetchBookingId();
        Thread.sleep(2000);
        history.cancelBooking();
        Thread.sleep(5000);

        driver.navigate().refresh();

        // Thread.sleep(2000);
        // history.isbookingpresent();
        Thread.sleep(1000);

        history.logout();
        Thread.sleep(2000);

        

    }
//     @AfterTest(enabled = true)
//     public void quitedriver(){
//         driver.close();
//     }
}
