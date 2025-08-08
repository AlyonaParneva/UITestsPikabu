import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class PobedaSearchBlockPage {
    @FindBy(xpath = "//div[@class='dp-m018yk-root-container']")
    private WebElement searchBlock;

    @FindBy(xpath = "(//input[@placeholder='Откуда'])[1]")
    private WebElement whereFrom;

    @FindBy(xpath = "(//input[@placeholder='Куда'])[1]")
    private WebElement where;

    @FindBy(xpath = "(//input[@placeholder='Туда'])[1]")
    private WebElement thereDate;

    @FindBy(xpath = "(//input[@placeholder='Обратно'])[1]")
    private WebElement backDate;

    @FindBy(xpath = "(//div[contains(@class,'dp-1eljsv-root-root-root')])[1]")
    private WebElement firstCitySuggestion;

    @FindBy(xpath = "//button[@class='dp-1ikqo3w-root-root']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class,'dp-1dr6zbu-root') and .//input[@placeholder='Туда']]")
    private WebElement departureDateContainer;

    public PobedaSearchBlockPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод города в поле Откуда и выбор первого совпадения")
    public void enterWhereFrom(WebDriver driver, String city) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(whereFrom));
        whereFrom.click();
        wait.until(ExpectedConditions.visibilityOf(whereFrom));
        whereFrom.clear();
        whereFrom.sendKeys(city);
        wait.until(ExpectedConditions.elementToBeClickable(firstCitySuggestion));
        firstCitySuggestion.click();
    }

    @Step("Ввод города в поле Куда и выбор первого совпадения")
    public void enterWhere(WebDriver driver, String city) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(where));
        where.click();
        wait.until(ExpectedConditions.visibilityOf(where));
        where.clear();
        where.sendKeys(city);
        wait.until(ExpectedConditions.elementToBeClickable(firstCitySuggestion));
        firstCitySuggestion.click();
    }

    @Step("Проверка отображения блока поиска билетов + скролл к нему")
    public boolean isSearchBlockDisplayed(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(searchBlock).perform();
        return searchBlock.isDisplayed();
    }

    @Step("Проверка отображения поля Откуда в блоке поиска билетов")
    public boolean isInputWhereFromDisplayed(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(whereFrom));
        return whereFrom.isDisplayed();
    }

    @Step("Проверка отображения поля Куда в блоке поиска билетов")
    public boolean isInputWhereDisplayed(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(where));
        return where.isDisplayed();
    }

    @Step("Проверка отображения поля Дата вылета Туда в блоке поиска билетов")
    public boolean isInputThereDateDisplayed(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(thereDate));
        return thereDate.isDisplayed();
    }

    @Step("Проверка отображения поля Дата вылета Обратно в блоке поиска билетов")
    public boolean isInputBackDateDisplayed(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(backDate));
        return backDate.isDisplayed();
    }

    @Step("Клик по кнопке Поиск")
    public void clickOnSearchButon() {
        searchButton.click();
    }

    @Step("Ждём красную обводку поля 'Туда'")
    public boolean waitDepartureError(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeToBe(departureDateContainer, "data-failed", "true"));
        return "true".equals(departureDateContainer.getAttribute("data-failed"));
    }

}
