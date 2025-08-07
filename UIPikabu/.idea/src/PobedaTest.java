import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PobedaTest extends BaseTestPobeda {
    Pobeda pobeda = new Pobeda();
    PobedaMainHeaderPage pobedaMainHeaderPage = new PobedaMainHeaderPage(WebDriverRunner.getWebDriver());
    PobedaInfoPopupPage pobedaInfoPopupPage=new PobedaInfoPopupPage(WebDriverRunner.getWebDriver());

//    @Test
//    @DisplayName("UI тест на проверку сайта Победа через Google")
//    @Description("Проверяем поиск через Google, переход на сайт Победа, появление баннера и смену языка")
//    public void testPobedaFlow() {
//        open("https://www.google.com");
//        step("Поиск сайта компании Победа", () -> {
//            pobeda.search(TestData.SEARCH_QUERY);
//        });
//        step("Переход по первой ссылке в результатах", () -> {
//            pobeda.clickFirstResult();
//        });
//        step("Ожидание загрузки сайта Победа и проверка баннера", () -> {
//            pobeda.waitForBannerKaliningradText();
//        });
//        step("Смена языка сайта на английский", () -> {
//            pobeda.switchToEnglish();
//        });
//        step("Проверка текстов на английской версии сайта", () -> {
//            pobeda.verifyEnglishTexts();
//        });
//    }

    @Test
    @DisplayName("UI тест на проверку всплывающих элементов сайта Победа")
    public void testHoverWindowPobeda() {
        step("Проверка, что заголовок страницы соответствует ожидаемому", () -> {
            String actualTitle = getWebDriver().getTitle();
            //текст заголовка сейчас на сайте отличается немного от того, что дано в задании
            assertEquals(TestData.EXPECTED_TITLE_POBEDA, actualTitle);
        });
        step("Проверка отображения логотипа Победа", () -> {
            pobedaMainHeaderPage.isLogoDisplayed();
        });
        pobedaMainHeaderPage.hoverInformation();
        step("Проверка что появилось всплывающее окно после наведения на пункт Информация", () -> {
            pobedaInfoPopupPage.isPopupDisplayed();
        });
        step("Проверка что в вслывающем окне есть заголовок Подготовка к полету", () -> {
            assertEquals(TestData.PREPARING_FOR_FLIGHT_TEXT, pobedaInfoPopupPage.getPreparingForFlightText());
        });
        step("Проверка что в вслывающем окне есть заголовок Полезная информация", () -> {
            assertEquals(TestData.USEFUL_INFORMATION_TEXT, pobedaInfoPopupPage.getUsefulInfoText());
        });
        step("Проверка что в вслывающем окне есть заголовок О компании", () -> {
            assertEquals(TestData.ABOUT_COMPANY_TEXT, pobedaInfoPopupPage.getAboutCompanyText());
        });
    }

}
