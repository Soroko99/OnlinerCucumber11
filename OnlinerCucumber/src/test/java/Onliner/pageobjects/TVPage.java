package Onliner.pageobjects;

import framework.elements.CheckBox;
import framework.elements.Dropdown;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TVPage extends BasePage{

    public JavascriptExecutor js = (JavascriptExecutor) driver;
    protected String manufacturerFilterXpath = "//input[@value='%s']//ancestor::label";
    protected String priceFiltrationXpath = "//input[contains(@placeholder, '%s')]";
    protected String resolutionXpath = "//input[@value='%s']//ancestor::label";
    protected String screenSizeXpath = "//select[contains(@data-bind, 'value: facet.value.%s')]";
    public static String manufacturerXpath = "//span[starts-with(text(),'Телевизор')]";
    public static String priceXpath = "//span[contains(@data-bind,'BYN')]";
    public static String screenSizeValidationXpath = "//span[contains(@data-bind, 'description')]";
    public static String resolutionValidationXpath = "//span[contains(@data-bind, 'description')]";
    protected String commonXpath = "//div[@class='schema-product']";

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        try{
            wait.until((ExpectedCondition<Boolean>) (ExpectedCondition<Boolean>) (x) -> {
                try {
                    if (js.executeScript("arguments[0].document.readyState").equals("complete"))
                        return true;
                } catch (Exception e) {
                    return false;
                }
                return true;
            });
        } catch (Exception e){
            e.printStackTrace();
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

    public List<String> generatingTvCharacteristicsList(String specialXpath){
        waitForPageToLoad();
        List<WebElement> list = driver.findElements(By.xpath(commonXpath));
        List<String> paramsList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++)
        {
            paramsList.add(list.get(i).findElements(By.xpath(String.format("%s", specialXpath))).get(i).getText());
        }
        return paramsList;
    }

    public void manufacturerValidation(List<String> list, String manufacturer) {
        for (String currentManufacturer : list) {
            Assert.assertTrue(currentManufacturer.contains(manufacturer));
        }
    }

    public void maxPriceValidation(List<String> list, String price){
        for (String currentPrice : list){
            Assert.assertTrue(Double.parseDouble(currentPrice.split(" ")[0].replaceAll(",", "."))
                    <= Double.parseDouble(price.replaceAll(",", ".")));
        }
    }

    public void screenSizeValidation(List<String> list, String minScreenSize, String maxScreenSize){
        for (String currentScreenSize : list){
            Assert.assertTrue(Integer.parseInt(currentScreenSize.substring(0, 2)) >= Integer.parseInt(minScreenSize)
                    && Integer.parseInt(currentScreenSize.substring(0, 2)) <= Integer.parseInt(maxScreenSize));
        }
    }

    public void resolutionValidation(List<String> list, String resolution){
        for (String currentResolution : list){
            Assert.assertTrue(currentResolution.contains(resolution));
        }
    }
    //substring(4,13)
    @Override
    public void isRightPageOpenedAssertion() {
    }
}
