package Onliner.pageobjects;

import framework.BasePage;
import framework.elements.CheckBox;
import framework.elements.Dropdown;
import framework.elements.Label;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
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

    public void manufacturerValidation(String manufacturer) {
        try {
            for (int i = 0; i < manufacturerLabel.getElementList().size(); i++) {
                Assert.assertTrue(manufacturerLabel.getElementList().get(i).getText().contains(manufacturer));
            }
        } catch (NullPointerException e) {
            waitForPageISLoaded();
        } catch (StaleElementReferenceException s) {
            waitForFiltering();
        }

    }

    public void priceValidation(String price) {
        try {
            for (int i = 0; i < priceValLabel.getElementList().size(); i++) {
                Assert.assertTrue(Double.parseDouble(priceValLabel.getElementList().get(i).getText().split(" ")[0].replaceAll(",", "."))
                        <= Double.parseDouble(price.replaceAll(",", ".")));
            }
        } catch (NullPointerException e) {
            waitForPageISLoaded();
        } catch (StaleElementReferenceException s) {
            waitForFiltering();
        }
    }

    public void screenSizeValidation(String minScreenSize, String maxScreenSize) {
        try {
            for (int i = 0; i < screenSizeValLabel.getElementList().size(); i++) {
                Assert.assertTrue(Integer.parseInt(screenSizeValLabel.getElementList().get(i).getText().substring(0, 2)) >= Integer.parseInt(minScreenSize)
                        && Integer.parseInt(screenSizeValLabel.getElementList().get(i).getText().substring(0, 2)) <= Integer.parseInt(maxScreenSize));
            }
        } catch (NullPointerException e) {
            waitForPageISLoaded();
        } catch (StaleElementReferenceException s) {
            waitForFiltering();
        }
    }

    public void resolutionValidation(String resolution) {
        try {
            for (int i = 0; i < resolutionValLabel.getElementList().size(); i++) {
                Assert.assertTrue(resolutionValLabel.getElementList().get(i).getText().contains(resolution));
            }
        } catch (NullPointerException e) {
            waitForPageISLoaded();
        } catch (StaleElementReferenceException s) {
            waitForFiltering();
        }

    }

    public void manufacturerFiltration(String manufacturerName) {
        CheckBox manufacturerCheckBox = new CheckBox(By.xpath(String.format(manufacturerFilterXpath, manufacturerName.toLowerCase(Locale.ROOT))));
        manufacturerCheckBox.clickViaJS();
    }

    public void priceFiltration(String keysToSend, String placeholderText) {
        TextBox textBox = new TextBox(By.xpath((String.format(priceFiltrationXpath, placeholderText))));
        textBox.sendKeys(keysToSend);
    }

    public void resolutionFiltration(String resolutionIs) {
        CheckBox resolutionCheckBox = new CheckBox(By.xpath((String.format(resolutionXpath, resolutionIs))));
        resolutionCheckBox.clickViaJS();
    }

    public void screenSizeFiltration(String screenSize, String fromOrTo) {
        Dropdown screenSizeDropdown = new Dropdown(By.xpath(String.format(screenSizeXpath, fromOrTo)));
        screenSizeDropdown.select(Integer.toString(Integer.parseInt(screenSize) * 10));
    }

    @Override
    public void isRightPageOpenedAssertion(String title) {
    }
}
