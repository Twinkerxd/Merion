package pageObjectPractice;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    protected HeaderElement header;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderElement(driver);
    }

    public void setUpCoockie() {
        Cookie cookie = new Cookie("cookie_policy", "1");
        driver.manage().addCookie(cookie);
    }

}
