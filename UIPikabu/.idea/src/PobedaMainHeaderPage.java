import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PobedaMainHeaderPage {
    @FindBy(xpath = "//header//a[@href='/' and contains(@aria-label,'Победа')]//img")
    private WebElement logo;

    @FindBy(xpath = "//a[text()='Информация']")
    private WebElement information;

    public PobedaMainHeaderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Проверка отображения логотипа Победы")
    public boolean isLogoDisplayed(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(logo));
        return logo.isDisplayed();
    }

    @Step("Наведение мышкой на пункт Информация")
    public void hoverInformation() {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.moveToElement(information).perform();
    }
}
