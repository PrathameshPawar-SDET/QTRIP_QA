package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_02 {

    static WebDriver driver;

    @BeforeTest(enabled = true)
    public static void createDriver() throws MalformedURLException, InterruptedException {
       
        driver = DriverSingleton.getdriver();
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        Thread.sleep(2000);
    }

    @Test(dataProvider = "excelReading", dataProviderClass = DP.class,description ="Verify Search and Filter flow", groups = {"Search and Filter flow"}, priority = 2)
    public void testcase02(String cityName, String categoryFilter, String durationFilter, String ExpectedFilteredResults, String ExpectedUnFilteredResults) throws InterruptedException{

        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        Thread.sleep(2000);
        home.searchData(cityName);
        
        Assert.assertTrue(home.noResultFound()||home.searchResultFound());
        
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
    }

    // @AfterTest(enabled = true)
    // public void quitedriver(){
    //     driver.close();
    // }


}
