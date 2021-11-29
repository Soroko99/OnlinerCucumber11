package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class BrowserFactory {

    public static WebDriver createDriver(){
        WebDriver driver = null;
        //PropertyManager propertyManager = new PropertyManager();
        //String browser = propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "browser");
        String browser = System.getenv("browser");
        //String browser = System.getProperty("BROWSER");
        switch (browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                Assert.fail(browser + " " + "driver is absent(");
        }
        return driver;
    }
}
