package framework;

import framework.elements.Label;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage{

    public WebDriver driver = Browser.driver;
    PropertyManager propertyManager = new PropertyManager();
    public abstract void isRightPageOpenedAssertion(String title);
    Label label = new Label(By.xpath("//span[contains(text(), 'Реклама')]"));

    public void waitForPageISLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "implicit_wait")));
        wait.until((ExpectedCondition<Boolean>) (x) -> {
                try {
                    if (((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")){
                        return true;
                    }
                }catch (Exception e) {
                    return false;
                }
                return true;
        });
    }

    public void waitForFiltering(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(propertyManager.getExactProperty(PropertyManager.seleniumPropertyPath, "explicit_wait")));
            wait.until(ExpectedConditions.stalenessOf(label.getElement()));
        } catch (NoSuchElementException ignored){
        }
    }
}
