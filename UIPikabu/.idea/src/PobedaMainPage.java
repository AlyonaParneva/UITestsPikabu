import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class PobedaMainPage {
    @FindBy(css = "[data-testid='ads-popup']")
    private WebElement adsPopup;

    @FindBy(css = "[data-testid='ads-popup-close-icon']")
    private WebElement adsCloseIcon;

    @FindBy(css = "[data-testid='ads-popup-close-btn']")
    private WebElement adsLaterBtn;

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
}
