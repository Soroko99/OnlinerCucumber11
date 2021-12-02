package onliner.pageobjects.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.Dropdown;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CatalogPage extends BasePage {

    public String catalogSectionXpath = "//ul//span[contains(text(),'%s')]//ancestor::li";
    public String electronicsSectionXpath = "//div[contains(text(), '%s')]";
    public String productXpath = "//span[contains(text(),'%s')]//ancestor::a";

    public void catalogSubsectionChoice(String subSectionName){
        Button catalogSubsectionChoiceBtn = new Button(By.xpath(String.format(catalogSectionXpath, subSectionName)));
        catalogSubsectionChoiceBtn.click();
    }

    public void electronicsSubsectionChoice(String subSectionName){
        Dropdown electronicsSubsectionDropdown = new Dropdown(By.xpath(String.format(electronicsSectionXpath, subSectionName)));
        electronicsSubsectionDropdown.click();
    }

    public void productChoice(String productName){
        Button productChoiceBtn = new Button(By.xpath(String.format(productXpath, productName)));
        productChoiceBtn.click();
    }

    @Override
    public void isRightPageOpenedAssertion(String tvExpectedTitle) {
        Assert.assertEquals(driver.getTitle(), tvExpectedTitle);
    }
}
