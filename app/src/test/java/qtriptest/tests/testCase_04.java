package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class testCase_04 extends BaseTest {

    @Test(dataProvider = "excelReading", dataProviderClass = DP.class, description ="Verify Search and Filter flow", groups = {"Reliability Flow"}, priority = 4)
    public void testcase04(String userName, String password, String dataset1, String dataset2, String dataset3) throws InterruptedException {

        test = reports.startTest("testcase04 - Verify Search and Filter flow");
        try {
            HomePage home = new HomePage(driver);
            RegisterPage register = new RegisterPage(driver);
            LoginPage login = new LoginPage(driver);
            AdventurePage adventure = new AdventurePage(driver);
            AdventureDetailsPage adventureDetails = new AdventureDetailsPage(driver);
            HistoryPage history = new HistoryPage(driver);

            test.log(LogStatus.INFO, "Navigating to Homepage");
            home.navigateToHomePage();
            Thread.sleep(2000);
            test.log(LogStatus.INFO, "Navigating to Register Page");
            home.navigateToRegisterPage();
            Thread.sleep(1000);

            test.log(LogStatus.INFO, "Register New User");
            register.RegisternewUser(userName, password, password, true);
            String Lastgeneratedusername = register.usernamevalue;
            Thread.sleep(2000);

            test.log(LogStatus.INFO, "Login into QTRIP");
            login.LoginQtrip(Lastgeneratedusername, password);

            String[][] datasets = {dataset1.split(";"), dataset2.split(";"), dataset3.split(";")};

            for (String[] dataSet : datasets) {
                home.searchData(dataSet[0]);
                home.selectCity();
                Thread.sleep(2000);
                adventure.searchAdventure(dataSet[1]);
                Thread.sleep(1000);
                adventure.selectAdventure();
                Thread.sleep(2000);
                adventureDetails.fillAdventureDetails(dataSet[2], dataSet[3], dataSet[4]);
                Thread.sleep(3000);
                adventureDetails.reserveBooking();
                Thread.sleep(5000);
                adventureDetails.verifyBooking();
                Thread.sleep(1000);
                home.navigateToHomePage();
            }

            adventureDetails.navigateToHistoryPage();
            history.fetchBookingId();
            Thread.sleep(2000);
            test.log(LogStatus.INFO, "Logging out from QTRIP");
            history.logout();
            Thread.sleep(2000);

            test.log(LogStatus.PASS, "Testcase 4 has been Passed");
            captureScreenshot("Testcase04_Passed");

        } catch (Exception e) {
            captureScreenshot("Testcase04_Failed");
            test.log(LogStatus.FAIL, "Testcase 4 has failed: " + e.getMessage());
            throw e;
        } finally {
            reports.endTest(test);
        }
    }
}
