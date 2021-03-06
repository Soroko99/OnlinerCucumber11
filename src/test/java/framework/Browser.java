package framework;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

import static framework.BrowserFactory.createDriver;

public class Browser {

    PropertyManager propertyManager = new PropertyManager();
    public static WebDriver driver = createDriver();

    public void driverClose()
    {
        driver.quit();
    }

    public void getURl(){
        driver.get(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "main_url"));
    }

    public void setup(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);}

}
