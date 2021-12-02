package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class Dropdown extends BaseElement{

    public Dropdown(By locator) {
        super(locator);
    }

    public void selectByValue(String value){
        Select select = new Select(getElement());
        select.selectByValue(value);
    }
}
