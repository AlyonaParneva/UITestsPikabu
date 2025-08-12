import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.numberOfWindows;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PobedaTest extends BaseTestPobeda {
    private PobedaMainHeaderPage header;
    private PobedaInfoPopupPage infoPopup;
    private PobedaSearchBlockPage searchBlock;
    private PobedaMainPage mainPage;

    @BeforeEach
    void initPages() {
        header = new PobedaMainHeaderPage();
        infoPopup = new PobedaInfoPopupPage();
        searchBlock = new PobedaSearchBlockPage();
        mainPage = new PobedaMainPage();
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
        step("Проверка заголовка страницы", () ->
                assertEquals(TestData.EXPECTED_TITLE_POBEDA, title())
        );
        step("Проверка отображения логотипа", () ->
                assertTrue(header.isLogoDisplayed())
        );
        step("Навести мышь на пункт «Информация»", header::hoverInformation);
        step("Проверить, что появился попап «Информация»", () ->
                assertTrue(infoPopup.isPopupDisplayed())
        );
        step("Проверка текстов в попапе", () -> {
            assertEquals(TestData.PREPARING_FOR_FLIGHT_TEXT, infoPopup.getPreparingForFlightText());
            assertEquals(TestData.USEFUL_INFORMATION_TEXT, infoPopup.getUsefulInfoText());
            assertEquals(TestData.ABOUT_COMPANY_TEXT, infoPopup.getAboutCompanyText());
        });
    }

    @Test
    @DisplayName("UI тест на проверку блока поиска билетов сайта Победа")
    public void testSearchTicketsPobeda() {
        step("Проверка заголовка страницы", () ->
                assertEquals(TestData.EXPECTED_TITLE_POBEDA, title())
        );
        step("Проверка отображения логотипа", () ->
                assertTrue(header.isLogoDisplayed())
        );
        step("Проверить, что блок поиска билетов отображается", () ->
                assertTrue(searchBlock.isSearchBlockDisplayed())
        );
        step("Поля поиска отображаются", () -> {
            assertTrue(searchBlock.isInputWhereFromDisplayed());
            assertTrue(searchBlock.isInputWhereDisplayed());
            assertTrue(searchBlock.isInputThereDateDisplayed());
            assertTrue(searchBlock.isInputBackDateDisplayed());
        });
        step("Ввести город отправления — " + TestData.CITY_FROM, () ->
                searchBlock.enterWhereFrom(TestData.CITY_FROM)
        );
        step("Ввести город прибытия — " + TestData.CITY_TO, () ->
                searchBlock.enterWhere(TestData.CITY_TO)
        );
        step("Закрыть рекламный попап, если он есть", mainPage::closeAdsPopupIfPresent);
        step("Нажать «Поиск» в блоке билетов", searchBlock::clickOnSearchButon);
        step("Проверить, что поле «Туда» подсвечено красным", () ->
                assertTrue(searchBlock.waitDepartureError())
        );
    }

    @Test
    @DisplayName("UI тест на проверку результата поиска бронирования сайта Победа")
    public void testSearchBookingPobeda() {
        step("Проверка заголовка страницы", () ->
                assertEquals(TestData.EXPECTED_TITLE_POBEDA, title())
        );
        step("Проверка отображения логотипа", () ->
                assertTrue(header.isLogoDisplayed())
        );
        step("Открыть блок «Управление бронированием»", searchBlock::clickOnBookingManagement);
        step("Проверить видимость элементов блока бронирования", () -> {
            assertTrue(searchBlock.isBookingBlockDisplayed());
            assertTrue(searchBlock.isSurnameDisplayed());
            assertTrue(searchBlock.isBookingOrTicketsDisplayed());
            assertTrue(searchBlock.isSearchBookingButtonDisplayed());
        });
        step("Закрыть рекламный попап, если он есть", mainPage::closeAdsPopupIfPresent);
        step("Ввести фамилию клиента — " + TestData.SURNAME, () ->
                searchBlock.enterSurname(TestData.SURNAME)
        );
        step("Ввести номер бронирования/билета — " + TestData.BOOKING_OR_TICKET, () ->
                searchBlock.enterBookingOrTicket(TestData.BOOKING_OR_TICKET)
        );
        step("Закрыть рекламный попап, если он есть", mainPage::closeAdsPopupIfPresent);
        step("Нажать «Поиск» и дождаться новой вкладки", () -> {
            searchBlock.clickOnSearchBookingButton();
            webdriver().shouldHave(numberOfWindows(2));
            switchTo().window(1);
        });
        //на момент написания теста на данные параметры открывалась новая страница с 403 ошибкой
        step("Проверка что появилось сообщение об ошибке", () -> {
            assertEquals(mainPage.getErrorMessageForSearchBookingText(), TestData.ERROR_MESSAGE_403);
        });
    }
}
