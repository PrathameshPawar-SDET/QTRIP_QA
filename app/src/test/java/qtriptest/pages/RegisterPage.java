package qtriptest.pages;

import org.openqa.selenium.WebDriver;
import qtriptest.SeleniumWrapper;
import java.util.UUID;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {

    WebDriver driver;
    public String usernamevalue;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);

    }

    private static String registerPageEndpoint ="pages/register/";

    @FindBy(css = "h2.formtitle")
    private WebElement registerPageHeader;

    @FindBy(id="floatingInput")
    private WebElement email;

    @FindBy(id="floatingPassword")
    private WebElement password;

    @FindBy(name="confirmpassword")
    private WebElement cnfpassword;

    @FindBy(xpath = "//button[text()='Register Now']")
    private WebElement registerButton;

    public boolean isNavtoRegisterPagesucceded(){
        return driver.getCurrentUrl().contains(registerPageEndpoint) &&
                registerPageHeader.getText().equals("Register");
    }

    public boolean RegisternewUser(String username, String Password, String ConfirmPassword, boolean uniqueuser) throws InterruptedException{
        String uniqueusername;
        if(uniqueuser)
        {
            uniqueusername = UUID.randomUUID().toString() +"_"+ username;
            System.out.println("Random Generated UUID : " + uniqueusername);
        }
        else{
            uniqueusername = username;
        }
        Thread.sleep(2000);
        // email.sendKeys(uniqueusername);
        SeleniumWrapper.sendKeys(driver, this.email, uniqueusername);
        Thread.sleep(2000);
        // password.sendKeys(Password);
        SeleniumWrapper.sendKeys(driver,this.password, Password);
        Thread.sleep(2000);
        // cnfpassword.sendKeys(ConfirmPassword);
        SeleniumWrapper.sendKeys(driver, this.cnfpassword, ConfirmPassword);
        Thread.sleep(2000);
        // registerButton.click();
        SeleniumWrapper.click(this.registerButton, driver);
        Thread.sleep(2000);
        this.usernamevalue = uniqueusername;
        if(driver.getCurrentUrl().endsWith("/login")){
            return true;
        }
        else{
            return false;
        }

        // LoginPage login = new LoginPage(driver);
        // return login.isloginpagedisplayed();


    }


}
