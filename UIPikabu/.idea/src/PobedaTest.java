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
import static io.qameta.allure.Allure.step;
import static com.codeborne.selenide.Selenide.open;

public class PobedaTest extends BaseTestPobeda {
    Pobeda pobeda = new Pobeda();

    @Test
    @DisplayName("UI тест на проверку сайта Победа через Google")
    @Description("Проверяем поиск через Google, переход на сайт Победа, появление баннера и смену языка")
    public void testPobedaFlow() {
        open("https://www.google.com");
        step("Поиск сайта компании Победа", () -> {
            pobeda.search(TestData.SEARCH_QUERY);
        });
        step("Переход по первой ссылке в результатах", () -> {
            pobeda.clickFirstResult();
        });
        step("Ожидание загрузки сайта Победа и проверка баннера", () -> {
            pobeda.waitForBannerKaliningradText();
        });
        step("Смена языка сайта на английский", () -> {
            pobeda.switchToEnglish();
        });
        step("Проверка текстов на английской версии сайта", () -> {
            pobeda.verifyEnglishTexts();
        });
    }

}
