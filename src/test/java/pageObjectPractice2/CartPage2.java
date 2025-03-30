package pageObjectPractice2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage2 extends BasePage2{

    @FindBy(css = "#checkout")
    private WebElement checkoutButton;

    public CartPage2(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckoutStepOnePage2 clickCheckoutButton() {
        checkoutButton.click();
        return new CheckoutStepOnePage2(driver);
    }
}
