package Onliner.tests;

import Onliner.pageobjects.CatalogPage;
import Onliner.pageobjects.MainPage;
import Onliner.pageobjects.TVPage;
import framework.ConfigureClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static Onliner.pageobjects.TVPage.*;


public class OnlinerTest extends ConfigureClass {

    @Parameters({"manufacturer", "maximumPrice", "minScreenSize", "maxScreenSize", "resolution"})
    @Test
    public void test(String manufacturer, String maximumPrice, String minScreenSize, String maxScreenSize, String resolution){
        MainPage mainPage = new MainPage(driver);
        mainPage.topNavigationChoice("Каталог");
        mainPage.catalogTitleAssertion();

        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.catalogSubsectionChoice("Электроника");
        catalogPage.electronicsSubsectionChoice("Телевидение");
        catalogPage.productChoice("Телевизоры");
        catalogPage.tvTitleAssertion();

        TVPage tvPage = new TVPage(driver, (JavascriptExecutor) driver);
        tvPage.manufacturerFiltration(manufacturer);
        tvPage.priceFiltration("до", maximumPrice);
        tvPage.resolutionFiltration(resolution);
        tvPage.screenSizeFiltration("from", minScreenSize);
        tvPage.screenSizeFiltration("to", maxScreenSize);

        //sleep();
        tvPage.manufacturerValidation(tvPage.generatingTvCharacteristicsList(driver, manufacturerXpath), manufacturer);
        tvPage.maxPriceValidation(tvPage.generatingTvCharacteristicsList(driver, priceXpath), maximumPrice);
        tvPage.screenSizeValidation(tvPage.generatingTvCharacteristicsList(driver, screenSizeValidationXpath), minScreenSize, maxScreenSize);
        tvPage.resolutionValidation(tvPage.generatingTvCharacteristicsList(driver, resolutionValidationXpath), resolution);
    }
}
