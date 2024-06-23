package qtriptest.tests;

import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import qtriptest.*;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.testng.annotations.*;

public class testCase_01 {
    static WebDriver driver;

    @BeforeTest(enabled = true)
    public static void createDriver() throws MalformedURLException, InterruptedException {
        // Launch Browser using Zalenium
        // final DesiredCapabilities capabilities = new DesiredCapabilities();
        // capabilities.setBrowserName(BrowserType.CHROME);
        // driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        // System.out.println("createDriver()");

        driver = DriverSingleton.getdriver();
        //driver.get("https://qtripdynamic-qa-frontend.vercel.app/");
        Thread.sleep(2000);
    }

    @Test(dataProvider = "excelReading", dataProviderClass = DP.class,description ="Verify login flow" , groups = {"Login Flow"}, priority = 1)
    public void testcase01(String username, String password) throws InterruptedException{

        
        
        // driver.navigate().to("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
        HomePage home = new HomePage(driver);
        home.navigateToHomePage();
        home.navigateToRegisterPage();
        Thread.sleep(2000);
        RegisterPage register = new RegisterPage(driver);
        register.RegisternewUser(username, password, password, true);
        String Lastgeneratedusername = register.usernamevalue;

        LoginPage login =new LoginPage(driver);
        login.LoginQtrip(Lastgeneratedusername, password);
        login.logout();
        

    }

    // @AfterTest(enabled = true)
    // public void quitedriver(){
    //     driver.close();
    // }
}
