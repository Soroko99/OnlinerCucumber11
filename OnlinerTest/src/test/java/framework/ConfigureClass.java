package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class ConfigureClass {
    public static WebDriver driver;
    protected static String mainUrl = "https://www.onliner.by/";
    private String TVFiltrationXpath = "//span[@class='schema-tags__text']";

    @BeforeSuite
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(mainUrl);
    }

    @AfterSuite
    public void driverClose(){
        driver.quit();
    }


}