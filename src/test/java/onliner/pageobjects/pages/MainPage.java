package onliner.pageobjects.pages;

import framework.BasePage;
import org.testng.Assert;

public class MainPage extends BasePage {

    @Override
    public void isRightPageOpenedAssertion(String catalogExpectedTitle) {
        Assert.assertEquals(getTitle(), catalogExpectedTitle);
    }
}
