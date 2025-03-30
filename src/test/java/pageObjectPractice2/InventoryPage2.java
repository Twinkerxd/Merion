package pageObjectPractice2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage2 extends BasePage2 {

    @FindBy(css = "[data-test='shopping-cart-link']")
    private WebElement cartButton;

    public InventoryPage2(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public InventoryPage2 findAndAddItemByName(String itemName) {
        String xpath = "//div[text()='" + itemName + "']/parent::a/parent::div/following-sibling::*/button";
        driver.findElement(By.xpath(xpath)).click();
        return this;
    }

    public CartPage2 clickCartButton() {
        cartButton.click();
        return new CartPage2(driver);
    }

}
