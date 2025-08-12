import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class PobedaMainPage {
    @FindBy(css = "[data-testid='ads-popup']")
    private WebElement adsPopup;

    @FindBy(css = "[data-testid='ads-popup-close-icon']")
    private WebElement adsCloseIcon;

    @FindBy(css = "[data-testid='ads-popup-close-btn']")
    private WebElement adsLaterBtn;

    @FindBy(xpath = "//*[self::h1 or self::h2 or self::p][contains(.,'403')]")
    private WebElement errorMessage;

    public PobedaMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void closeAdsPopupIfPresent() {
        WebDriver driver = getWebDriver();
        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));

        try {
            shortWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='ads-popup']")));
            try {
                if (adsCloseIcon.isDisplayed()) {
                    adsCloseIcon.click();
                }
            } catch (Exception e) {
                adsLaterBtn.click();
            }
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOf(adsPopup));
        } catch (TimeoutException ignore) {
        }
    }

    @Step("Чтение текста ошибки о неправльных данных для поиска бронирования")
    public String getErroeMessageForSearchBookingText() {
        return errorMessage.getText();
    }
}
