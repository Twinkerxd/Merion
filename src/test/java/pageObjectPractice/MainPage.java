package pageObjectPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.labirint.ru/
public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public MainPage open() {
        driver.get("https://www.labirint.ru/");
        setUpCoockie();
        driver.navigate().refresh();
        return this;
    }
}