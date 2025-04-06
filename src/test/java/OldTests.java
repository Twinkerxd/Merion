import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OldTests {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test_1() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");

        for (int i = 0; i < 5; i++) {
            driver.findElement(By.xpath("//button[text()='Add Element']")).click();
        }

        List<WebElement> webElementList = driver.findElements(By.cssSelector("#elements button"));
        System.out.println(webElementList.size());
    }

    @Test
    public void test_2() {
        driver.get("http://uitestingplayground.com/dynamicid");
        for (int i = 0; i < 4; i++) {
            driver.findElement(By.cssSelector(".btn.btn-primary")).click();
        }
    }

    @Test
    public void test_3() throws InterruptedException {
        driver.get("http://uitestingplayground.com/classattr");

        for (int i = 0; i < 3; i++) {
            driver.findElement(By.cssSelector(".btn-primary")).click();
            Thread.sleep(500);
            Alert alert = driver.switchTo().alert();
            Thread.sleep(500);
            alert.accept();
        }
    }

    @Test
    public void test_4() throws InterruptedException {
        driver.get("http://the-internet.herokuapp.com/entry_ad");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//p[text()='Close']")).click();
        System.out.println(driver.findElement(By.cssSelector("#content")).getText());
    }

    @Test
    public void test_5() {
        driver.get("http://the-internet.herokuapp.com/inputs");
        WebElement element = driver.findElement(By.cssSelector("[type='number']"));
        element.sendKeys("1000");
        element.clear();
        element.sendKeys("2000");
    }

    @Test
    public void test_6() {
        driver.get("http://the-internet.herokuapp.com/login");
        driver.findElement(By.cssSelector("#username")).sendKeys("tomsmith");
        driver.findElement(By.cssSelector("#password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("i")).click();
        System.out.println(driver.findElement(By.cssSelector("body")).getText());
    }

    @Test
    public void test_7() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36");

        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://google.com");
        Cookie cookie = new Cookie.Builder("SOCS", "CAISHAgBEhJnd3NfMjAyNTAzMjAtMF9SQzEaAnJ1IAEaBgiA5Ie_Bg")
                .domain(".google.com")
                .path("/")
                .isSecure(true)
                .sameSite("Lax")
                .build();
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement element = driver.findElement(By.name("q"));
//        element = driver.findElements(By.name("q")).get(0);
        element.sendKeys("Merion Academy wiki", Keys.ENTER);
        driver.findElement(By.cssSelector("#search a")).click();
        if (driver.getCurrentUrl().startsWith("https://wiki.merionet.ru")) {
            System.out.println("Добро пожаловать в Merion Academy!");
        } else {
            System.out.println("Мы попали куда-то не туда...");
        }
    }

    @Test
    public void test_8() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.get("http://uitestingplayground.com/ajax");
        driver.findElement(By.cssSelector("#ajaxButton")).click();
        System.out.println("-----> " + driver.findElement(By.cssSelector("#content p")).getText());
    }

    @Test
    public void test_9() {
        driver.get("http://uitestingplayground.com/textinput");
        driver.findElement(By.cssSelector("#newButtonName")).sendKeys("Merion");
        WebElement button = driver.findElement(By.cssSelector("#updatingButton"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(100));
        wait.until(ExpectedConditions.textToBe(By.cssSelector("#updatingButton"), "Merion"));

        String actualResult = driver.findElement(By.cssSelector("#updatingButton")).getText();

        Assertions.assertEquals("Merion", actualResult);
    }

    @Test
    public void test_10() {
        /*
        Перейти на сайт https://bonigarcia.dev/selenium-webdriver-java/loading-images.html
        Дождаться загрузки 3й картинки
        Получить значение атрибута src у 3й картинки
        Вывести значение в консоль
         */

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#award")));
        System.out.println("======> " + driver.findElement(By.cssSelector("#award")).getDomAttribute("src"));
    }

    @Test
    public void test_11() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#image-container img"), 2));
        List<WebElement> images = driver.findElements(By.cssSelector("#image-container img"));
        WebElement thirdImage = images.get(2);
        String srcValue = thirdImage.getDomAttribute("src");
        System.out.println("======> " + srcValue);
    }

    @Test
    public void test_12() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/data-types.html");
        driver.findElement(By.cssSelector("[name='first-name']")).sendKeys("Sergei");
        driver.findElement(By.cssSelector("[name='last-name']")).sendKeys("Testowy");
        driver.findElement(By.cssSelector("[name='address']")).sendKeys("Faker street 16/13");
        driver.findElement(By.cssSelector("[name='city']")).sendKeys("New York");
        driver.findElement(By.cssSelector("[name='country']")).sendKeys("USA");
        driver.findElement(By.cssSelector("[name='job-position']")).sendKeys("QA");
        driver.findElement(By.cssSelector("[name='company']")).sendKeys("JetBrains");

        driver.findElement(By.cssSelector(".btn.btn-outline-primary.mt-3")).click();

        String zipColor = driver.findElement(By.cssSelector("#zip-code")).getCssValue("background-color");
        String emailColor = driver.findElement(By.cssSelector("#e-mail")).getCssValue("background-color");
        String phoneColor = driver.findElement(By.cssSelector("#phone")).getCssValue("background-color");

        Assertions.assertEquals("rgba(248, 215, 218, 1)", zipColor);
        Assertions.assertEquals("rgba(248, 215, 218, 1)", emailColor);
        Assertions.assertEquals("rgba(248, 215, 218, 1)", phoneColor);
    }

    @Test
    public void test_13() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");
        driver.findElement(By.xpath("//span[text()='7']")).click();
        driver.findElement(By.xpath("//span[text()='+']")).click();
        driver.findElement(By.xpath("//span[text()='8']")).click();

        String oldText = driver.findElement(By.cssSelector(".screen")).getText();

        driver.findElement(By.xpath("//span[text()='=']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver1 -> {
            String newText = driver1.findElement(By.cssSelector(".screen")).getText();
            return !(oldText.equals(newText));
        });

        String actualResult = driver.findElement(By.cssSelector(".screen")).getText();
        Assertions.assertEquals("15", actualResult);
    }

    @Test
    public void test_14() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("#login-button")).click();

        //div[text()='Sauce Labs Backpack']/parent::a/parent::div/following-sibling::*/button
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/parent::a/parent::div/following-sibling::*/button")).click();
        driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']/parent::a/parent::div/following-sibling::*/button")).click();
        driver.findElement(By.xpath("//div[text()='Sauce Labs Onesie']/parent::a/parent::div/following-sibling::*/button")).click();

        driver.findElement(By.cssSelector("[data-test='shopping-cart-link']")).click();
        driver.findElement(By.cssSelector("#checkout")).click();

        driver.findElement(By.cssSelector("#first-name")).sendKeys("Viktor");
        driver.findElement(By.cssSelector("#last-name")).sendKeys("Testowy");
        driver.findElement(By.cssSelector("#postal-code")).sendKeys("1613");
        driver.findElement(By.cssSelector("#continue")).click();

        String actualResult = driver.findElement(By.cssSelector("[data-test='total-label']")).getText();
        Assertions.assertEquals("Total: $58.29", actualResult);
    }
}
