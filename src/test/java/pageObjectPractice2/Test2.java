package pageObjectPractice2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class Test2 extends BaseTest2 {

    @Test
    public void test() {
        LoginPage2 loginPage2 = new LoginPage2(driver);
        String actualResult = loginPage2
                .open()
                .login("standard_user", "secret_sauce")
                .findAndAddItemByName("Sauce Labs Backpack")
                .findAndAddItemByName("Sauce Labs Bolt T-Shirt")
                .findAndAddItemByName("Sauce Labs Onesie")
                .clickCartButton()
                .clickCheckoutButton()
                .fillFirstNameInput("Viktor")
                .fillLastNameInput("Testowy")
                .fillPostalCodeInput("1613")
                .clickContinueButton()
                .getToTalLabelText();

        Assertions.assertTrue(actualResult.equals("Total: $58.28"));
    }
}
