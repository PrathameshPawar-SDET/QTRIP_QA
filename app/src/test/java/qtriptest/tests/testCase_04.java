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

public class testCase_04 {
    static WebDriver driver;

    @BeforeTest(enabled = true)
    public static void createDriver() throws MalformedURLException, InterruptedException {
       
        driver = DriverSingleton.getdriver();
        driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        Thread.sleep(2000);
    }

    @Test(dataProvider = "excelReading", dataProviderClass = DP.class,description ="Verify Search and Filter flow", groups = {"Reliability Flow"}, priority = 4)
    public void testcase04(String userName, String password, String dataset1, 
    String dataset2, String dataset3) throws InterruptedException{

        HomePage home = new HomePage(driver);
        RegisterPage register = new RegisterPage(driver);
        LoginPage login = new LoginPage(driver);
        AdventurePage adventure = new AdventurePage(driver);
        AdventureDetailsPage adventureDetails = new AdventureDetailsPage(driver);
        HistoryPage history = new HistoryPage(driver);
        home.navigateToHomePage();
        Thread.sleep(2000);
        home.navigateToRegisterPage();
        Thread.sleep(1000);
        
        register.RegisternewUser(userName, password, password, true);
        String Lastgeneratedusername = register.usernamevalue;
        Thread.sleep(2000);
        
        
        login.LoginQtrip(Lastgeneratedusername, password);

        String[] dataSet1 = dataset1.split(";");
        String[] dataSet2 = dataset2.split(";");
        String[] dataSet3 = dataset3.split(";");

        home.searchData(dataSet1[0]);
        home.selectCity();
        Thread.sleep(2000);
        adventure.searchAdventure(dataSet1[1]);
        Thread.sleep(1000);
        adventure.selectAdventure();
        Thread.sleep(2000);
        adventureDetails.fillAdventureDetails(dataSet1[2], dataSet1[3], dataSet1[4]);
        Thread.sleep(3000);
        adventureDetails.reserveBooking();
        Thread.sleep(5000);
        adventureDetails.verifyBooking();
        Thread.sleep(1000);

        home.navigateToHomePage();
        home.searchData(dataSet2[0]);
        home.selectCity();
        Thread.sleep(2000);
        adventure.searchAdventure(dataSet2[1]);
        Thread.sleep(1000);
        adventure.selectAdventure();
        Thread.sleep(2000);
        adventureDetails.fillAdventureDetails(dataSet2[2], dataSet2[3], dataSet2[4]);
        Thread.sleep(3000);
        adventureDetails.reserveBooking();
        Thread.sleep(5000);
        adventureDetails.verifyBooking();
        Thread.sleep(1000);

        home.navigateToHomePage();
        home.searchData(dataSet3[0]);
        home.selectCity();
        Thread.sleep(2000);
        adventure.searchAdventure(dataSet3[1]);
        Thread.sleep(1000);
        adventure.selectAdventure();
        Thread.sleep(2000);
        adventureDetails.fillAdventureDetails(dataSet3[2], dataSet3[3], dataSet3[4]);
        Thread.sleep(3000);
        adventureDetails.reserveBooking();
        Thread.sleep(5000);
        adventureDetails.verifyBooking();
        Thread.sleep(1000);


        adventureDetails.navigateToHistoryPage();
        history.fetchBookingId();
        Thread.sleep(2000);
        // history.cancelBooking();
        // Thread.sleep(5000);

        // driver.navigate().refresh();

        // Thread.sleep(2000);
        // history.isbookingpresent();
        Thread.sleep(1000);

        history.logout();
        Thread.sleep(2000);

        
        

    }
    @AfterTest(enabled = true)
    public void quitedriver(){
        driver.quit();
    }
}

