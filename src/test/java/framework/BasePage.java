package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    public WebDriver driver = Browser.driver;
    public abstract void isRightPageOpenedAssertion(String title);

    public void waitForPAgeIsLoaded(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver driver) {
                try {
                    if (((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete")){
                        return true;
                    }
                    else return false;
                } catch (Exception e) {
                    return false;
                }
            }
        });
}
}
