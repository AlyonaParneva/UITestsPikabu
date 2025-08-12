import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PobedaMainPage {
    private final SelenideElement adsPopup    = $("[data-testid='ads-popup']");
    private final SelenideElement adsCloseIcon= $("[data-testid='ads-popup-close-icon']");
    private final SelenideElement adsLaterBtn = $("[data-testid='ads-popup-close-btn']");
    private final SelenideElement errorMessage= $x("//*[self::h1 or self::h2 or self::p][contains(.,'403')]");

    @Step("Закрыть рекламный попап, если он есть")
    public void closeAdsPopupIfPresent() {
        if (adsPopup.exists()) {
            if (adsCloseIcon.is(visible)) adsCloseIcon.click();
            else if (adsLaterBtn.exists()) adsLaterBtn.click();
            adsPopup.should(disappear);
        }
    }

    @Step("Чтение текста ошибки 403 на новой странице")
    public String getErrorMessageForSearchBookingText() {
        return errorMessage.shouldBe(visible).shouldHave(text("403")).text();
    }
}
