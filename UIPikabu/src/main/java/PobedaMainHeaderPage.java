import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PobedaMainHeaderPage {
    private final SelenideElement logo = $("header a[href='/' ][aria-label*='Победа'] img");
    private final SelenideElement information = $x("//a[normalize-space()='Информация']");

    @Step("Проверка отображения логотипа Победы")
    public boolean isLogoDisplayed() {
        logo.shouldBe(visible);
        return true;
    }

    @Step("Наведение мышкой на пункт «Информация»")
    public void hoverInformation() {
        information.scrollIntoView(true).hover();
    }
}
