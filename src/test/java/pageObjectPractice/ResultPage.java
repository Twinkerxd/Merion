package pageObjectPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ResultPage extends BasePage {

    @FindBy(css = ".search-result")
    private WebElement searchResult;

    @FindBy(css = ".product-card")
    public List<WebElement> cards;

    public ResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addAllItemsToCards() {
        int counter = 0;

        for (WebElement element : cards) {
            List<WebElement> buyLinks = element.findElements(By.cssSelector(".buy-link"));
            if (!buyLinks.isEmpty()) {
                buyLinks.get(0).click();
                counter++;
            }

        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBePresentInElement(header.getCartIconElement(), String.valueOf(counter)));
    }
}
