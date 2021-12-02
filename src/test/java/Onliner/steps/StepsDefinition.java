package Onliner.steps;

import Onliner.main_menu.MainMenu;
import Onliner.pageobjects.CatalogPage;
import Onliner.pageobjects.MainPage;
import Onliner.pageobjects.TVPage;
import framework.Browser;
import framework.TestListener;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.annotations.Listeners;

public class StepsDefinition {
    Browser browser = new Browser();
    MainPage mainPage = new MainPage();
    MainMenu mainMenu = new MainMenu();
    CatalogPage catalogPage = new CatalogPage();
    TVPage tvPage = new TVPage();


    @Before()
    public void setup(){
        browser.setup();
    }

    @Given("I go to main page")
    public void iGoToMainPage() {
        browser.getURl();
    }

    @When("I go to {string} page")
    public void iGoToPage(String pageName) {
        mainMenu.topNavigationChoice(pageName);
    }

    @And("I check whether the {string} page was opened")
    public void iCheckWhetherThePageWasOpened(String pageTitle) {
        mainPage.isRightPageOpenedAssertion(pageTitle);
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
    }

    @And("I check whether page {string} was opened")
    public void iCheckWhetherPageWasOpened(String productPageTitle) {
        catalogPage.isRightPageOpenedAssertion(productPageTitle);
    }

    @And("I filter product by manufacturer {string}")
    public void iFilterProductByManufacturer(String manufacturerName) {
        tvPage.manufacturerFiltration(manufacturerName);
    }

    @And("I filter product by maxPrice {string} using {string} field")
    public void iFilterProductByMaxPriceUsingField(String maxPrice, String placeholder) {
        tvPage.priceFiltration(maxPrice, placeholder);
    }

    @And("I filter product by resolution {string}")
    public void iFilterProductByResolution(String resolutionIs) {
        tvPage.resolutionFiltration(resolutionIs);
    }

    @And("I filter product by minScreenSize {string} using {string} field")
    public void iFilterProductByMinScreenSize(String placeholder, String minScreenSize) {
        tvPage.screenSizeFiltration(placeholder, minScreenSize);
    }

    @And("I filter product by maxScreenSize {string} using {string} field")
    public void iFilterProductByMaxScreenSize(String placeholder, String maxScreenSize) {
        tvPage.screenSizeFiltration(placeholder, maxScreenSize);
    }

    @Then("I get products according to parameters : {string}, {string}, {string}, {string}, {string}")
    public void iGetProductsAccordingTo(String manufacturer, String maximumPrice, String resolution, String minScreenSize, String maxScreenSize) {
        tvPage.manufacturerValidation(manufacturer);
        tvPage.priceValidation(maximumPrice);
        tvPage.resolutionValidation(resolution);
        tvPage.screenSizeValidation(minScreenSize, maxScreenSize);
    }

    @After()
    public void driverKill(){
        browser.driverClose();
    }

}
