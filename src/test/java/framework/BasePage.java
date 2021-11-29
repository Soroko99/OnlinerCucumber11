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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) (x) -> {
                try {
                    while (!(((JavascriptExecutor) driver).executeScript("return document.ready.").equals("complete"))){
                        return false;
                    }
                }catch (Exception e) {
                    return false;
                }
                return true;
        });
    }
}
