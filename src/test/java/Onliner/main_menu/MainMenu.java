package Onliner.main_menu;

import framework.elements.Button;
import org.openqa.selenium.By;

public class MainMenu {
    public String topNavElementXpath = "//ul[@class='b-main-navigation']//span[contains(text(), '%s')]";

    public void topNavigationChoice(String topNavChoice){
        Button topNavChoiceBtn = new Button(By.xpath(String.format(topNavElementXpath, topNavChoice)));
        topNavChoiceBtn.click();
    }
}
