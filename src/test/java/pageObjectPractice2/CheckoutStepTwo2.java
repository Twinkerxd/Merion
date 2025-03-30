package pageObjectPractice2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepTwo2 extends BasePage2 {

    @FindBy(css = "[data-test='total-label']")
    private WebElement totalLabel;

    public CheckoutStepTwo2(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String getToTalLabelText() {
        return totalLabel.getText();
    }
}
