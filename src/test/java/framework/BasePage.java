package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
}
