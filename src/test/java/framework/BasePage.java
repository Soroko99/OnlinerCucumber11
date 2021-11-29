package framework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    public WebDriver driver = Browser.driver;
    public abstract void isRightPageOpenedAssertion(String title);

    public void waitForPageISLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
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
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//span[contains(text(), 'Реклама')]"))));
        } catch (NoSuchElementException ignored){
        }
    }
}
