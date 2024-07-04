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
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_03 extends BaseTest {


    @Test(dataProvider = "excelReading", dataProviderClass = DP.class,description ="Verify Booking and Cancellation Flow", groups = {"Booking and Cancellation Flow"}, priority = 3)
    public void testcase03(String userName, String password, String searchCity,
                           String AdventureName, String guestName, String date, String count) throws InterruptedException{

        test = reports.startTest("testcase03 - Verify Booking and Cancellation Flow");
        try {
            HomePage home = new HomePage(driver);
            test.log(LogStatus.INFO, "Navigating to Homepage");
            home.navigateToHomePage();
            Thread.sleep(2000);
            test.log(LogStatus.INFO, "Navigating to Register Page");
            home.navigateToRegisterPage();
            Thread.sleep(1000);
            RegisterPage register = new RegisterPage(driver);
            test.log(LogStatus.INFO, "Register New User");
            register.RegisternewUser(userName, password, password, true);
            String Lastgeneratedusername = register.usernamevalue;
            Thread.sleep(2000);

            LoginPage login = new LoginPage(driver);
            test.log(LogStatus.INFO, "Login into QTRIP");
            login.LoginQtrip(Lastgeneratedusername, password);
            test.log(LogStatus.INFO, "Search for city name");
            home.searchData(searchCity);

            test.log(LogStatus.INFO, "Selecting the city:" + searchCity);
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

            test.log(LogStatus.INFO, "Logging out from QTRIP");
            history.logout();
            Thread.sleep(2000);

            test.log(LogStatus.PASS, "Testcase 3 has been Passed");
            captureScreenshot("Testcase03_Passed");

        }catch (Exception e) {
            captureScreenshot("Testcase03_Failed");
            test.log(LogStatus.FAIL, "Testcase 3 has failed: " + e.getMessage());
            throw e;
        } finally {
            reports.endTest(test);
        }

    }

}
