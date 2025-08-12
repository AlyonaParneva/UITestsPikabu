import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

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

    @FindBy(xpath = "//button//div[text()='Управление бронированием']")
    private WebElement bookingManagement;

    @FindBy(xpath = "//input[@placeholder='Фамилия клиента']")
    private WebElement surname;

    @FindBy(xpath = "//input[@placeholder='Номер бронирования или билета']")
    private WebElement bookingOrTicket;

    @FindBy(xpath = "//button[@class='dp-1vcyfp3-root-root']")
    private WebElement searchBookingButton;

    @FindBy(xpath = "//div[@class='dp-5hv1mu-root']")
    private WebElement searchBookingBlock;

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

    @Step("Клик и скролл по кнопке управление бронироваем")
    public void clickOnBookingManagement(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(bookingManagement).perform();
        bookingManagement.click();
    }

    @Step("Проверка отображения поля Фамилия клиента в блоке поиска бронирования")
    public boolean isSurnameDisplayed() {
        WebDriver driver = getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(surname));
        return surname.isDisplayed();
    }

    @Step("Проверка отображения поля Номер бронирования или билета в блоке поиска бронирования")
    public boolean isBookingOrTicketsDisplayed() {
        WebDriver driver = getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bookingOrTicket));
        return bookingOrTicket.isDisplayed();
    }

    @Step("Проверка отображения кнопки Поиск в блоке поиска бронирования")
    public boolean isSearchBookingButtonDisplayed() {
        WebDriver driver = getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchBookingButton));
        return searchBookingButton.isDisplayed();
    }

    @Step("Проверка отображения блока управление бронированием + скролл к нему")
    public boolean isBookingBlockDisplayed() {
        WebDriver driver = getWebDriver();
        Actions actions = new Actions(driver);
        actions.moveToElement(searchBookingBlock).perform();
        return searchBookingBlock.isDisplayed();
    }

    @Step("Ввод фамилии клиента в блоке Управление бронированием")
    public void enterSurname(String city) throws InterruptedException {
        WebDriver driver = getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(surname));
        surname.click();
        wait.until(ExpectedConditions.visibilityOf(surname));
        surname.clear();
        surname.sendKeys(city);
    }

    @Step("Ввод номера бронирования или билета в блоке Управление бронированием")
    public void enterBookingOrTicket(String city) throws InterruptedException {
        WebDriver driver = getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(bookingOrTicket));
        bookingOrTicket.click();
        wait.until(ExpectedConditions.visibilityOf(bookingOrTicket));
        bookingOrTicket.clear();
        bookingOrTicket.sendKeys(city);
    }

    @Step("Клик по кнопке Поиск в блоке Управление бронированием")
    public void clickOnSearchBookingButton() {
        searchBookingButton.click();
    }
}
