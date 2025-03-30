package pageObjectPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage{

    @FindBy(css = "#basket-default-sumprice-discount")
    private WebElement cartPrice;

    @FindBy(css = "#basket-default-prod-count2")
    private WebElement cartCounter;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getCartPrice() {
        return cartPrice.getText();
    }

    public String getCartCounter() {
        return cartCounter.getText();
    }
}
