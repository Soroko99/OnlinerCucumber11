package framework.elements;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import framework.Browser;
import framework.PropertyManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseElement{

    PropertyManager propertyManager = new PropertyManager();
    WebDriver driver = Browser.driver;
    WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "explicit_wait")));
    By locator;
    WebElement element;
    List<WebElement> elementList;

    public BaseElement(By locator){
        this.locator = locator;
    }

    public void click() {
        waitForIsElementPresent();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        element.click();
    }

    public void clickViaJS() {
        waitForIsElementPresent();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid blue'", element);
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public WebElement getElement(){
        waitForIsElementPresent();
        return element;
    }

    public boolean waitForIsElementPresent(){
        return isPresent();
    }

    public boolean isPresent() {
        wait.until((ExpectedCondition<Boolean>) (x) -> {
            try {
            element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
                return false;
            }
    });
        try {
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "explicit_wait")), TimeUnit.SECONDS);
        return element.isDisplayed();
    } catch (Exception e) {
        e.printStackTrace();
    }
        return false;
}

    public List<WebElement> getElementList(){
        if (arePresent()) return elementList;
        else return null;
    }

    private boolean arePresent() {
        try {
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver driver) {
                    try {
                        elementList = driver.findElements(locator);
                        for (WebElement el : elementList) {
                            if (el != null && el.isDisplayed()) {
                                element = el;
                                return element.isDisplayed();
                            }
                        }
                        element = driver.findElement(locator);
                    } catch (Exception e) {
                        return false;
                    }
                    return element.isDisplayed();
                }
            });
        } catch (Exception e) {
            return false;
        }
        try {
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "explicit_wait")), TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
