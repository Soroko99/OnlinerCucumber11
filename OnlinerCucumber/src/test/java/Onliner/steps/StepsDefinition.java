package Onliner.steps;

import Onliner.pageobjects.CatalogPage;
import Onliner.pageobjects.MainPage;
import Onliner.pageobjects.TVPage;
import framework.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static Onliner.pageobjects.TVPage.*;
import static Onliner.pageobjects.TVPage.resolutionValidationXpath;

public class StepsDefinition {
    Browser browser = new Browser();
    MainPage mainPage = new MainPage();
    CatalogPage catalogPage = new CatalogPage();
    TVPage tvPage = new TVPage();

    @Before
    public void setup(){
        browser.setup();
    }

    @Given("I go to main page")
    public void iGoToMainPage() {
        browser.getURl();
    }

    @When("I go to {string} page")
    public void iGoToPage(String pageName) {
        mainPage.topNavigationChoice(pageName);
        mainPage.isRightPageOpenedAssertion();
    }

    @And("at this page I choose {string} category")
    public void iChooseCategory(String categoryName) {
        catalogPage.catalogSubsectionChoice(categoryName);
    }

    @And("I choose {string} subcategory")
    public void iChooseSubcategory(String subCategoryName) {
        catalogPage.electronicsSubsectionChoice(subCategoryName);
    }

    @And("I choose {string} product")
    public void iChooseProduct(String productName) {
        catalogPage.productChoice(productName);
        catalogPage.isRightPageOpenedAssertion();
    }

    @And("I filter product by manufacturer {string}")
    public void iFilterProductByManufacturer(String manufacturerName) {
        tvPage.manufacturerFiltration(manufacturerName);
    }

    @And("I filter product by maxPrice {string}")
    public void iFilterProductByMaxPrice(String maxPrice) {
        tvPage.priceFiltration("до", maxPrice);
    }

    @And("I filter product by resolution {string}")
    public void iFilterProductByResolution(String resolutionIs) {
        tvPage.resolutionFiltration(resolutionIs);
    }

    @And("I filter product by minScreenSize {string}")
    public void iFilterProductByMinScreenSize(String minScreenSize) {
        tvPage.screenSizeFiltration("from", minScreenSize);
    }

    @And("I filter product by maxScreenSize {string}")
    public void iFilterProductByMaxScreenSize(String maxScreenSize) {
        tvPage.screenSizeFiltration("to", maxScreenSize);
    }

    @Then("I get products according to parameters : {string}, {string}, {string}, {string}, {string}")
    public void iGetProductsAccordingTo(String manufacturer, String maximumPrice, String resolution, String minScreenSize, String maxScreenSize) {
        tvPage.manufacturerValidation(tvPage.generatingTvCharacteristicsList(manufacturerXpath), manufacturer);
        tvPage.maxPriceValidation(tvPage.generatingTvCharacteristicsList(priceXpath), maximumPrice);
        tvPage.resolutionValidation(tvPage.generatingTvCharacteristicsList(resolutionValidationXpath), resolution);
        tvPage.screenSizeValidation(tvPage.generatingTvCharacteristicsList(screenSizeValidationXpath), minScreenSize, maxScreenSize);
    }

    @After
    public void driverKill(){
        browser.driverClose();
    }
}
