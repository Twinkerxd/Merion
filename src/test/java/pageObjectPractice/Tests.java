package pageObjectPractice;

import org.junit.jupiter.api.Test;

public class Tests extends BaseTest{

    @Test
    public void test1() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        ResultPage resultPage = mainPage.header.search("Java");
        resultPage.addAllItemsToCards();
        String iconText = resultPage.header.getIconText();
        System.out.println("iconText = " + iconText);

        CartPage cartPage = resultPage.header.clickCartIcon();
        String price = cartPage.getCartPrice();
        String counter = cartPage.getCartCounter();

        System.out.println("price = " + price);
        System.out.println("counter = " + counter);
    }
}
