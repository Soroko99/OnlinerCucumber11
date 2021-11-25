package Onliner.pageobjects;

import framework.BasePage;
import framework.elements.CheckBox;
import framework.elements.Dropdown;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Locale;

public class TVPage extends BasePage {

    protected String manufacturerFilterXpath = "//input[@value='%s']//ancestor::label";
    protected String priceFiltrationXpath = "//input[contains(@placeholder, '%s')]";
    protected String resolutionXpath = "//input[@value='%s']//ancestor::label";
    protected String screenSizeXpath = "//select[contains(@data-bind, 'value: facet.value.%s')]";
    Label manufacturerLabel = new Label(By.xpath("//div[@class='schema-product']//span[starts-with(text(),'Телевизор')]"));
    Label priceValLabel = new Label(By.xpath("//div[@class='schema-product']//span[contains(@data-bind,'BYN')]"));
    Label screenSizeValLabel = new Label(By.xpath("//div[@class='schema-product']//span[contains(@data-bind, 'description')]"));
    Label resolutionValLabel = new Label(By.xpath("//div[@class='schema-product']//span[contains(@data-bind, 'description')]"));

    public void manufacturerValidation(String manufacturer){
        waitForFiltering();
        for (int i = 0; i < manufacturerLabel.getElementList().size(); i++){
            Assert.assertTrue(manufacturerLabel.getElementList().get(i).getText().contains(manufacturer));
        }
    }
    public void priceValidation(String price){
        waitForFiltering();
        for (int i = 0; i < priceValLabel.getElementList().size(); i++)
        {
            Assert.assertTrue(Double.parseDouble(priceValLabel.getElementList().get(i).getText().split(" ")[0].replaceAll(",", "."))
                    <= Double.parseDouble(price.replaceAll(",", ".")));
        }
    }

    public void screenSizeValidation(String minScreenSize, String maxScreenSize){
        waitForFiltering();
        for (int i = 0; i < screenSizeValLabel.getElementList().size(); i++){
            Assert.assertTrue(Integer.parseInt(screenSizeValLabel.getElementList().get(i).getText().substring(0, 2)) >= Integer.parseInt(minScreenSize)
                    && Integer.parseInt(screenSizeValLabel.getElementList().get(i).getText().substring(0, 2)) <= Integer.parseInt(maxScreenSize));
        }
    }

    public void resolutionValidation(String resolution){
        waitForFiltering();
        for (int i = 0; i < resolutionValLabel.getElementList().size(); i++){
            Assert.assertTrue(resolutionValLabel.getElementList().get(i).getText().contains(resolution));
        }
    }

    public void manufacturerFiltration(String manufacturerName)
    {
        CheckBox manufacturerCheckBox = new CheckBox(By.xpath(String.format(manufacturerFilterXpath, manufacturerName).toLowerCase(Locale.ROOT)));
        manufacturerCheckBox.click();
    }

    public void priceFiltration(String placeholderText, String keysToSend)
    {
        TextBox textBox = new TextBox(By.xpath((String.format(priceFiltrationXpath, placeholderText))));
        textBox.sendKeys(keysToSend);
    }

    public void resolutionFiltration(String resolutionIs){
        CheckBox resolutionCheckBox = new CheckBox(By.xpath((String.format(resolutionXpath, resolutionIs))));
        resolutionCheckBox.clickViaJS();
    }

    public void screenSizeFiltration(String fromOrTo, String screenSize){
        Dropdown screenSizeDropdown = new Dropdown(By.xpath(String.format(screenSizeXpath, fromOrTo)));
        screenSizeDropdown.select(Integer.toString(Integer.parseInt(screenSize) * 10));
    }

    public void waitForFiltering(){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//span[contains(text(), 'Реклама')]"))));
        } catch (NoSuchElementException ignored){
        }
    }

    @Override
    public void isRightPageOpenedAssertion(String title) {
    }
}
