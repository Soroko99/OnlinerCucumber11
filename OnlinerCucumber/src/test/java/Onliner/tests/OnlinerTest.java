package Onliner.tests;

import Onliner.main_menu.MainMenu;
import Onliner.pageobjects.CatalogPage;
import Onliner.pageobjects.MainPage;
import Onliner.pageobjects.TVPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OnlinerTest extends BaseTest{

    @Parameters({"manufacturer", "maximumPrice", "minScreenSize", "maxScreenSize", "resolution"})
    @Test
    public void run(String manufacturer, String maximumPrice, String minScreenSize, String maxScreenSize, String resolution){
        MainMenu mainMenu = new MainMenu();
        mainMenu.topNavigationChoice("Каталог");

        MainPage mainPage = new MainPage();
        mainPage.isRightPageOpenedAssertion("Каталог Onliner");

        CatalogPage catalogPage = new CatalogPage();
        catalogPage.catalogSubsectionChoice("Электроника");
        catalogPage.electronicsSubsectionChoice("Телевидение");
        catalogPage.productChoice("Телевизоры");
        catalogPage.isRightPageOpenedAssertion("Телевизор купить в Минске");

        TVPage tvPage = new TVPage();
        tvPage.manufacturerFiltration(manufacturer);
        tvPage.priceFiltration("до", maximumPrice);
        tvPage.resolutionFiltration(resolution);
        tvPage.screenSizeFiltration("from", minScreenSize);
        tvPage.screenSizeFiltration("to", maxScreenSize);

//        tvPage.manufacturerValidation(tvPage.generatingTvCharacteristicsList(manufacturerXpath), manufacturer);
//        tvPage.maxPriceValidation(tvPage.generatingTvCharacteristicsList(priceXpath), maximumPrice);
//        tvPage.screenSizeValidation(tvPage.generatingTvCharacteristicsList(screenSizeValidationXpath), minScreenSize, maxScreenSize);
//        tvPage.resolutionValidation(tvPage.generatingTvCharacteristicsList(resolutionValidationXpath), resolution);
    }
}
