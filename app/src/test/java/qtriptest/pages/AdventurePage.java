package qtriptest.pages;

import org.openqa.selenium.WebDriver;
import qtriptest.SeleniumWrapper;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {
    WebDriver driver;

    public AdventurePage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "duration-select")
    private WebElement durationFilter;

    @FindBy(xpath = "//div[@onclick='clearDuration(event)']")
    private WebElement clearDurationFilter;

    @FindBy(id ="category-select")
    private WebElement categoryFilter;

    @FindBy(xpath = "//div[@onclick='clearCategory(event)']")
    private WebElement clearCategoryFilter;

    @FindBy(id ="search-adventures")
    private WebElement searchAdventure;

    @FindBy(xpath = "//div[@onclick='resetAdventuresData()']")
    private WebElement clearSearchData;

    @FindAll({
            @FindBy(className = "activity-card"),
            @FindBy(id ="data")
    })
    List<WebElement> Adventures;

    @FindBy(className = "activity-card")
    WebElement adventure;


    public void selectDuration(String duration){
        Select s = new Select(durationFilter);
        s.selectByVisibleText(duration);
    }

    public void clearduration(){
        // clearDurationFilter.click();
        SeleniumWrapper.click(this.clearDurationFilter, driver);
    }

    public void addCategory(String category){
        Select s = new Select(categoryFilter);
        s.selectByVisibleText(category);
    }

    public void clearCategory(){
        // clearCategoryFilter.click();
        SeleniumWrapper.click(this.clearCategoryFilter, driver);
    }

    public void searchAdventure(String adventure){
        searchAdventure.clear();
        // searchAdventure.sendKeys(adventure);
        SeleniumWrapper.sendKeys(driver, this.searchAdventure, adventure);
    }

    public void clearSearch(){
        // clearSearchData.click();
        SeleniumWrapper.click(this.clearSearchData, driver);
    }

    public boolean verifyAdventure(String filterData){
        boolean status = false;

        Integer Actualresult = Adventures.size();
        String result = Actualresult.toString();
        if(result==filterData){
            status = true;
            return status;
        }

        return status;

    }

    public void selectAdventure()
    {
        // adventure.click();
        SeleniumWrapper.click(this.adventure, driver);
    }


}