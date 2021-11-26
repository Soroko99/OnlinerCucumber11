package framework.elements;

import framework.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BaseElement{

    WebDriver driver = Browser.driver;
    WebDriverWait wait = new WebDriverWait(driver, 5);
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

    public void waitForElement(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForIsElementPresent(){
        return isPresent();
    }

    public boolean isPresent() {
        try {
            element = driver.findElement(locator);
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
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return element.isDisplayed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
