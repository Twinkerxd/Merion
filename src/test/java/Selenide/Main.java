package Selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.security.Key;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;


public class Main {

//    @Test
//    public void test() {
//
//    }

    public static void main(String[] args) {
        open("https://nofluffjobs.com/pl");

        String cookieName = "ucString";
        String cookieValue = "N4IgbgpgTgzglgewHYgFwEYA0ICucBq08yaIAzAHQAsADBTSNgMbIAuUCANp9AJIAmpAKwBDKiIBsEoQHZ+NABwKyTCQCMAZioX90ZCEPSSAnMbLpZx/kyEQZTMhKoAmKjY1rnXms5FkyVPIixkyMIJwiSADmOCJREKQADpxhMBCsrHDRaKCsAJ6JCaggAOIAIgAKAEphcILFEAASAPoA7gCCUWUA1t3tACoiAFYAQmGQsIgoxQrUlDLoIAC+2DiJ/CKsEPztrGjoMlRkzsoKEujoPqvrm9sjeaTIAKo3WwDK0GBwTBAwYUxQCC3HZ7DCHY4KLxkYwKWTYQEARzwgPqGhEnDS2BgrE2OD+xXaABlCc0ygBRAByvDJZTC+UKpDJAA0KoTeABhXj9MIACxEMB5pDUTAUVHQTA0orFxhEEAgtGcTBEChEfmCGnQGioohk0OVjk1lxkthC/H40I0zggPloam2NCEqU+3wgAnxAG0ALpLIA==";
        String ucData = "ucData";
        String ucDataValue = "{\"gcm\":{\"adsDataRedaction\":true,\"adStorage\":\"denied\",\"adPersonalization\":\"denied\",\"adUserData\":\"denied\",\"analyticsStorage\":\"denied\"},\"consent\":{\"services\":{\"H1Vl5NidjWX\":{\"name\":\"Usercentrics Consent Management Platform\",\"consent\":true},\"NDqG-4cU6ylFep\":{\"name\":\"Functional\",\"consent\":false},\"S1_9Vsuj-Q\":{\"name\":\"Google Ads\",\"consent\":false},\"HkocEodjb7\":{\"name\":\"Google Analytics\",\"consent\":false},\"-ntLeQATB2vENh\":{\"name\":\"Marketing\",\"consent\":false},\"Vf5Z2P7ec0szyb\":{\"name\":\"Performance\",\"consent\":false}}},\"ui\":{\"language\":\"pl\"}}";

        executeJavaScript("localStorage.setItem('ucString', arguments[0]);", cookieValue);
        executeJavaScript("localStorage.setItem('ucData', arguments[0]);", ucDataValue);

        refresh();

        $("#mat-mdc-chip-list-input-serverApp0").setValue("Test automation").press(Keys.ENTER);


        SelenideElement button = $x("//button[contains(text(),'Pokaż kolejne oferty')]");

        int count = 0;

        while (button.isDisplayed()) {
            button.click();
            count++;
            sleep(1000);
        }

        System.out.println("count: " + count);

        ElementsCollection elements = $$(".posting-tag.tw-flex-shrink-0");

        // Создаём Map для подсчёта частоты встречаемости
        Map<String, Integer> frequencyMap = new HashMap<>();

        // Перебираем элементы и считаем частоту
        for (var element : elements) {
            String text = element.getText().trim(); // Получаем текст элемента
            frequencyMap.put(text, frequencyMap.getOrDefault(text, 0) + 1);
        }

        // Сортируем по убыванию значений (по количеству повторений)
        LinkedHashMap<String, Integer> sortedMap = frequencyMap.entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Сортируем по убыванию
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        // Выводим отсортированные результаты
        for (var entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }
}
