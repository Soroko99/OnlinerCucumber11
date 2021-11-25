package Onliner.pageobjects;

import framework.BasePage;
import org.testng.Assert;

public class MainPage extends BasePage {


    @Override
    public void isRightPageOpenedAssertion(String catalogExpectedTitle) {
        Assert.assertEquals(driver.getTitle(), catalogExpectedTitle);
    }
}
