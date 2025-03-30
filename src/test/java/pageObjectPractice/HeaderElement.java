package pageObjectPractice;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderElement {

    private WebDriver driver;

    @FindBy(css = "#search-field")
    private WebElement searchField;

    @FindBy(css = ".b-header-b-personal-e-icon-count-m-cart")
    private WebElement cartIcon;

    public HeaderElement(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ResultPage search(String text) {
        searchField.sendKeys(text, Keys.ENTER);
        return new ResultPage(driver);
    }

    public String getIconText() {
        return cartIcon.getText();
    }

    public WebElement getCartIconElement() {
        return cartIcon;
    }

    public CartPage clickCartIcon() {
        cartIcon.click();
        return new CartPage(driver);
    }

}
