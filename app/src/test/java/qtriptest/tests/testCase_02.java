package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_02 extends BaseTest {


    @Test(dataProvider = "excelReading", dataProviderClass = DP.class,description ="Verify Search and Filter flow", groups = {"Search and Filter flow"}, priority = 2)
    public void testcase02(String cityName, String categoryFilter, String durationFilter, String ExpectedFilteredResults, String ExpectedUnFilteredResults) throws InterruptedException{

        test = reports.startTest("testcase02 - Verify Search and Filter flow");
        try {
            HomePage home = new HomePage(driver);
            test.log(LogStatus.INFO, "Navigating to Homepage");
            home.navigateToHomePage();
            Thread.sleep(2000);
            test.log(LogStatus.INFO, "Search for city name");
            home.searchData(cityName);

            Assert.assertTrue(home.noResultFound()||home.searchResultFound());

            test.log(LogStatus.INFO, "Selecting the city:" + cityName);

            home.selectCity();
            AdventurePage adventure = new AdventurePage(driver);
            adventure.selectDuration(durationFilter);
            Thread.sleep(1000);
            adventure.addCategory(categoryFilter);
            Thread.sleep(2000);
            adventure.verifyAdventure(ExpectedFilteredResults);
            Thread.sleep(1000);
            adventure.clearduration();
            Thread.sleep(1000);
            adventure.clearCategory();
            Thread.sleep(1000);
            adventure.verifyAdventure(ExpectedUnFilteredResults);
            test.log(LogStatus.PASS, "Testcase 2 has been Passed");
            captureScreenshot("Testcase02_Passed");
        }catch (Exception e) {
            captureScreenshot("Testcase02_Failed");
            test.log(LogStatus.FAIL, "Testcase 2 has failed: " + e.getMessage());
            throw e;
        } finally {
            reports.endTest(test);
        }
    }




}
