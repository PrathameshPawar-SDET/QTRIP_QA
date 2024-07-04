package qtriptest.pages;

import org.openqa.selenium.WebDriver;
import qtriptest.SeleniumWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {

    WebDriver driver;
    String url="https://qtripdynamic-qa-frontend.vercel.app";

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }


    @FindBy(css="#navbarNavDropdown li:last-child > a")
    private WebElement registerButton;

    @FindBy(xpath = "//li/a[text()='Login Here']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[text()='Logout']")
    private WebElement logoutbutton;

    @FindBy(id ="autocomplete")
    private WebElement searchBox;

    @FindBy(xpath ="//h5[text()='No City found']")
    private WebElement noResultFound;

    @FindBy(xpath = "//ul[@id='results']")
    private WebElement searchresultfound;

    public void navigateToHomePage(){

        // if(!driver.getCurrentUrl().equals(url)){
        //     driver.get(url);
        // }
        SeleniumWrapper.navigate(driver, url);
    }
    public boolean isRegisterButtondisplay(){
        return registerButton.isDisplayed() && registerButton.isEnabled();
    }

    public void navigateToRegisterPage(){
        // registerButton.click();
        SeleniumWrapper.click(this.registerButton, driver);
    }

    public boolean islogoutbuttondisplayed(){
        return logoutbutton.isDisplayed() && loginButton.isEnabled();
    }

    public void searchData(String city) throws InterruptedException{
        searchBox.clear();
        Thread.sleep(3000);
        // searchBox.sendKeys(city);
        SeleniumWrapper.sendKeys(driver, this.searchBox, city);
    }

    public boolean noResultFound(){
        boolean status = false;
        try{
            status = noResultFound.isDisplayed();
            return status;

        }catch(Exception e){
            return status;
        }
    }

    public boolean searchResultFound(){
        boolean status = false;
        try{
            Thread.sleep(2000);
            status = searchresultfound.isDisplayed();
            // searchresultfound.click();
            return status;

        }catch(Exception e){
            return status;
        }
    }

    public void selectCity() throws InterruptedException{
        Thread.sleep(3000);
        // searchresultfound.click();
        SeleniumWrapper.click(this.searchresultfound, driver);
    }
}
