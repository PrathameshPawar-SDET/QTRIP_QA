package qtriptest;

import java.io.ObjectInputFilter.Status;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverSingleton {

    private static WebDriver driver = null;

    private DriverSingleton(){}

    public static WebDriver getdriver() throws MalformedURLException{

        if(driver==null){
        // final DesiredCapabilities capabilities = new DesiredCapabilities();
        // capabilities.setBrowserName(BrowserType.CHROME);
        // driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        System.out.println("createDriver()");
        }
        driver.manage().window().maximize();

        return driver;
    }
}