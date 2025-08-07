import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PobedaMainHeaderPage {
    @FindBy(xpath = "//a[@class='dp-1f2hhsq-root-root-root']//img")
    private WebElement logo;

    @FindBy(xpath = "//a[text()='Информация']")
    private WebElement information;

    public PobedaMainHeaderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Проверка отображения логотипа Победы")
    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    @Step("Наведение мышкой на пункт Информация")
    public void hoverInformation() {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.moveToElement(information).perform();
    }
}
