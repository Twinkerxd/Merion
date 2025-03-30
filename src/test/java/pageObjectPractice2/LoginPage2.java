package pageObjectPractice2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage2 extends BasePage2 {

    @FindBy(css = "#user-name")
    private WebElement userNameInput;

    @FindBy(css = "#password")
    private WebElement passwordInput;

    @FindBy(css = "#login-button")
    private WebElement loginButton;

    public LoginPage2(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage2 open() {
        driver.get("https://www.saucedemo.com/");
        return this;
    }

    public InventoryPage2 login(String login, String password) {
        userNameInput.sendKeys(login);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new InventoryPage2(driver);
    }
}
