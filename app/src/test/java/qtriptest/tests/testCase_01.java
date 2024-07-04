package qtriptest.tests;

import qtriptest.DP;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;
import com.relevantcodes.extentreports.LogStatus;
import qtriptest.*;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.*;
import org.testng.annotations.*;

public class testCase_01 extends BaseTest {


    @Test(dataProvider = "excelReading", dataProviderClass = DP.class,description ="Verify login flow" , groups = {"Login Flow"}, priority = 1)
    public void testcase01(String username, String password) throws InterruptedException{

        test = reports.startTest("testcase01 - Verify login flow");
        try {

            // driver.navigate().to("https://qtripdynamic-qa-frontend.vercel.app/pages/register/");
            HomePage home = new HomePage(driver);
            test.log(LogStatus.INFO, "Navigating to Homepage");
            home.navigateToHomePage();
            test.log(LogStatus.INFO, "Navigating to Register Page");
            home.navigateToRegisterPage();
            Thread.sleep(2000);
            RegisterPage register = new RegisterPage(driver);
            test.log(LogStatus.INFO, "Register New User");
            register.RegisternewUser(username, password, password, true);
            String Lastgeneratedusername = register.usernamevalue;

            LoginPage login =new LoginPage(driver);
            test.log(LogStatus.INFO, "Login into QTRIP");
            login.LoginQtrip(Lastgeneratedusername, password);
            test.log(LogStatus.INFO, "Logging out from QTRIP");
            login.logout();
            test.log(LogStatus.PASS, "Testcase 1 has been Passed");
            captureScreenshot("Testcase01_Passed");

        }catch (Exception e) {
            captureScreenshot("Testcase01_Failed");

            test.log(LogStatus.FAIL, "Testcase 1 has failed: " + e.getMessage());
            throw e;
        } finally {
            reports.endTest(test);
        }


    }


}
