import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PobedaTest extends BaseTestPobeda {
    private PobedaMainHeaderPage pobedaMainHeaderPage;
    private PobedaInfoPopupPage pobedaInfoPopupPage;
    private PobedaSearchBlockPage pobedaSearchBlockPage;
    private PobedaMainPage pobedaMainPage;

    @BeforeEach
    void initPages() {
        pobedaMainHeaderPage = new PobedaMainHeaderPage(WebDriverRunner.getWebDriver());
        pobedaInfoPopupPage = new PobedaInfoPopupPage(WebDriverRunner.getWebDriver());
        pobedaSearchBlockPage = new PobedaSearchBlockPage(WebDriverRunner.getWebDriver());
        pobedaMainPage = new PobedaMainPage(WebDriverRunner.getWebDriver());
    }


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
            assertTrue(pobedaMainHeaderPage.isLogoDisplayed(WebDriverRunner.getWebDriver()));
        });
        pobedaMainHeaderPage.hoverInformation();
        step("Проверка что появилось всплывающее окно после наведения на пункт Информация", () -> {
            assertTrue(pobedaInfoPopupPage.isPopupDisplayed());
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

    @Test
    @DisplayName("UI тест на проверку блока поиска билетов сайта Победа")
    public void testSearchTicketsPobeda() {
        step("Проверка, что заголовок страницы соответствует ожидаемому", () -> {
            String actualTitle = getWebDriver().getTitle();
            //текст заголовка сейчас на сайте отличается немного от того, что дано в задании
            assertEquals(TestData.EXPECTED_TITLE_POBEDA, actualTitle);
        });
        step("Проверка отображения логотипа Победа", () -> {
            assertTrue(pobedaMainHeaderPage.isLogoDisplayed(WebDriverRunner.getWebDriver()));
        });
        step("Проверка блока поиска билетов", () -> {
            assertTrue(pobedaSearchBlockPage.isSearchBlockDisplayed(WebDriverRunner.getWebDriver()));
        });
        step("Проверка отображения поля Откуда в блоке поиска билетов", () -> {
            assertTrue(pobedaSearchBlockPage.isInputWhereFromDisplayed(WebDriverRunner.getWebDriver()));
        });
        step("Проверка отображения поля Куда в блоке поиска билетов", () -> {
            assertTrue(pobedaSearchBlockPage.isInputWhereDisplayed(WebDriverRunner.getWebDriver()));
        });
        step("Проверка отображения поля Дата вылета Туда в блоке поиска билетов", () -> {
            assertTrue(pobedaSearchBlockPage.isInputThereDateDisplayed(WebDriverRunner.getWebDriver()));
        });
        step("Проверка отображения поля Дата вылета Обратно в блоке поиска билетов", () -> {
            assertTrue(pobedaSearchBlockPage.isInputBackDateDisplayed(WebDriverRunner.getWebDriver()));
        });
        step("Ввод города отправления — Минск", () -> {
            pobedaSearchBlockPage.enterWhereFrom(WebDriverRunner.getWebDriver(), TestData.CITY_FROM);
        });
        step("Ввод города прибытия — Санкт-Петербург", () -> {
            pobedaSearchBlockPage.enterWhere(WebDriverRunner.getWebDriver(), TestData.CITY_TO);
        });
        pobedaMainPage.closeAdsPopupIfPresent();
        pobedaSearchBlockPage.clickOnSearchButon();
        step("Проверка, что поле 'Туда' подсвечено красным", () -> {
            assertTrue(pobedaSearchBlockPage.waitDepartureError(WebDriverRunner.getWebDriver()));
        });
    }

}
