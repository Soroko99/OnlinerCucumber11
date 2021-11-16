package Onliner.pageobjects;

import framework.elements.Button;
import org.openqa.selenium.By;
import org.testng.Assert;

public class MainPage extends BasePage{

    public static String topNavElementXpath = "//ul[@class='b-main-navigation']//span[contains(text(), '%s')]";
    public String catalogExpectedTitle = "Каталог Onlíner";

    public void topNavigationChoice(String topNavChoice){
        Button topNavChoiceBtn = new Button(By.xpath(String.format(topNavElementXpath, topNavChoice)));
        topNavChoiceBtn.click();
    }

    @Override
    public void isRightPageOpenedAssertion() {
        Assert.assertEquals(driver.getTitle(), catalogExpectedTitle);
    }
}
