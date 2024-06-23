package qtriptest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
     

        public static String loginPageUrlEndsWith = "pages/login";
        @FindBy(css = "h2.formtitle")
        private WebElement loginPageHeader;

        @FindBy(id="floatingInput")
        private WebElement useremail;

        @FindBy(id="floatingPassword")
        private WebElement password;

        @FindBy(xpath = "//button[text()='Login to QTrip']")
        private WebElement loginButton;

        @FindBy(xpath = "//div[text()='Logout']")
        private WebElement logoutbutton;
    

        public boolean isloginpagedisplayed(){
            return driver.getCurrentUrl().contains(loginPageUrlEndsWith) &&
            loginPageHeader.getText().equals("Login");
        }

        public boolean LoginQtrip(String username, String Password) throws InterruptedException{

            boolean status;
            Thread.sleep(2000);
            useremail.sendKeys(username);
            Thread.sleep(2000);
            password.sendKeys(Password);
            Thread.sleep(2000);
            loginButton.click();
            Thread.sleep(2000);
            if(logoutbutton.isDisplayed()){
                status = true;
            }
            else{
                status = false;
            }
            return status;
            //HomePage home = new HomePage(driver);
            //return home.islogoutbuttondisplayed();
        }

        public void logout(){
            logoutbutton.click();
        }
}
