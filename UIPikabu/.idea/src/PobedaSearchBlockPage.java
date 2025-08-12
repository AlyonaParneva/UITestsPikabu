import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class PobedaSearchBlockPage {
    private final SelenideElement searchBlock = $(".dp-m018yk-root-container");
    private final SelenideElement whereFrom = $x("(//input[@placeholder='Откуда'])[1]");
    private final SelenideElement where = $x("(//input[@placeholder='Куда'])[1]");
    private final SelenideElement thereDate = $x("(//input[@placeholder='Туда'])[1]");
    private final SelenideElement backDate = $x("(//input[@placeholder='Обратно'])[1]");
    private final SelenideElement firstCitySuggestion = $x("(//div[contains(@class,'dp-1eljsv-root-root-root')])[1]");
    private final SelenideElement searchButton = $("button.dp-1ikqo3w-root-root");
    private final SelenideElement departureContainer = $x("//div[contains(@class,'dp-1dr6zbu-root') and .//input[@placeholder='Туда']]");
    private final SelenideElement bookingManagement = $x("//button//div[normalize-space()='Управление бронированием']");
    private final SelenideElement surname = $x("//input[@placeholder='Фамилия клиента']");
    private final SelenideElement bookingOrTicket = $x("//input[@placeholder='Номер бронирования или билета']");
    private final SelenideElement searchBookingButton = $("button.dp-1vcyfp3-root-root");
    private final SelenideElement searchBookingBlock = $(".dp-5hv1mu-root");

    @Step("Проверка отображения блока поиска билетов (со скроллом)")
    public boolean isSearchBlockDisplayed() {
        searchBlock.scrollIntoView(true).shouldBe(visible);
        return true;
    }

    @Step("Поле «Откуда» отображается")
    public boolean isInputWhereFromDisplayed() {
        whereFrom.shouldBe(visible);
        return true;
    }

    @Step("Поле «Куда» отображается")
    public boolean isInputWhereDisplayed() {
        where.shouldBe(visible);
        return true;
    }

    @Step("Поле «Туда» отображается")
    public boolean isInputThereDateDisplayed() {
        thereDate.shouldBe(visible);
        return true;
    }

    @Step("Поле «Обратно» отображается")
    public boolean isInputBackDateDisplayed() {
        backDate.shouldBe(visible);
        return true;
    }

    @Step("Ввод города в поле «Откуда» и выбор первого совпадения")
    public void enterWhereFrom(String city) {
        whereFrom.shouldBe(visible).click();
        whereFrom.setValue(city);
        firstCitySuggestion.shouldBe(visible).click();
    }

    @Step("Ввод города в поле «Куда» и выбор первого совпадения")
    public void enterWhere(String city) {
        where.shouldBe(visible).click();
        where.setValue(city);
        firstCitySuggestion.shouldBe(visible).click();
    }

    @Step("Клик по кнопке «Поиск» (блок билетов)")
    public void clickOnSearchButon() {
        searchButton.shouldBe(enabled).click();
    }

    @Step("Ожидаем ошибку в поле «Туда» (красная обводка)")
    public boolean waitDepartureError() {
        departureContainer.shouldHave(attribute("data-failed", "true"));
        return true;
    }

    @Step("Переход в блок «Управление бронированием»")
    public void clickOnBookingManagement() {
        bookingManagement.scrollIntoView(true).click();
    }

    @Step("Блок «Управление бронированием» отображается (со скроллом)")
    public boolean isBookingBlockDisplayed() {
        searchBookingBlock.scrollIntoView(true).shouldBe(visible);
        return true;
    }

    @Step("Поле «Фамилия клиента» отображается")
    public boolean isSurnameDisplayed() {
        surname.shouldBe(visible);
        return true;
    }

    @Step("Поле «Номер бронирования или билета» отображается")
    public boolean isBookingOrTicketsDisplayed() {
        bookingOrTicket.shouldBe(visible);
        return true;
    }

    @Step("Кнопка «Поиск» (в управлении бронированием) отображается")
    public boolean isSearchBookingButtonDisplayed() {
        searchBookingButton.shouldBe(visible);
        return true;
    }

    @Step("Ввод фамилии клиента")
    public void enterSurname(String value) {
        surname.shouldBe(visible).setValue(value);
    }

    @Step("Ввод номера бронирования или билета")
    public void enterBookingOrTicket(String value) {
        bookingOrTicket.shouldBe(visible).setValue(value);
    }

    @Step("Клик по кнопке «Поиск» в управлении бронированием")
    public void clickOnSearchBookingButton() {
        searchBookingButton.shouldBe(enabled).click();
    }
}
