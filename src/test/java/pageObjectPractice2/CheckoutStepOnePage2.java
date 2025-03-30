package pageObjectPractice2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutStepOnePage2 extends BasePage2{

    public CheckoutStepOnePage2(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#first-name")
    private WebElement firstNameInput;

    @FindBy(css = "#last-name")
    private WebElement lastNameInput;

    @FindBy(css = "#postal-code")
    private WebElement postalCodeInput;

    @FindBy(css = "#continue")
    private WebElement continueButton;


    public CheckoutStepOnePage2 fillFirstNameInput(String text) {
        firstNameInput.sendKeys(text);
        return this;
    }

    public CheckoutStepOnePage2 fillLastNameInput(String text) {
        lastNameInput.sendKeys(text);
        return this;
    }

    public CheckoutStepOnePage2 fillPostalCodeInput(String text) {
        postalCodeInput.sendKeys(text);
        return this;
    }

    public CheckoutStepTwo2 clickContinueButton() {
        continueButton.click();
        return new CheckoutStepTwo2(driver);
    }
}
