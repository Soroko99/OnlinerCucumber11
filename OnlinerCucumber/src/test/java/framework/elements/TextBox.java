package framework.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TextBox extends BaseElement{

    public TextBox(By locator) {
        super(locator);
    }

    public void sendKeys(String keys){
        WebElement textBox = driver.findElement(locator);
        textBox.sendKeys(keys);
    }
}
