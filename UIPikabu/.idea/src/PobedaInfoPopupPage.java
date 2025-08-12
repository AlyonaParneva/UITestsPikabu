import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PobedaInfoPopupPage {
    private final SelenideElement informationModal = $(".dp-ukl30h-root");
    private final SelenideElement preparingForFlight = $x("//a[contains(@href,'#flight')]");
    private final SelenideElement usefulInfo = $x("//a[contains(@href,'#useful')]");
    private final SelenideElement aboutCompany = $x("//a[contains(@href,'#company')]");

    @Step("Проверка появления всплывающего окна «Информация»")
    public boolean isPopupDisplayed() {
        informationModal.shouldBe(visible);
        return true;
    }

    @Step("Чтение текста «Подготовка к полету»")
    public String getPreparingForFlightText() {
        return preparingForFlight.shouldBe(visible).text();
    }

    @Step("Чтение текста «Полезная информация»")
    public String getUsefulInfoText() {
        return usefulInfo.shouldBe(visible).text();
    }

    @Step("Чтение текста «О компании»")
    public String getAboutCompanyText() {
        return aboutCompany.shouldBe(visible).text();
    }
}
