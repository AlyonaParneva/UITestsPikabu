//import com.codeborne.selenide.ElementsCollection;
//import com.codeborne.selenide.SelenideElement;
//import com.codeborne.selenide.WebDriverRunner;
//import io.qameta.allure.Step;
//import org.openqa.selenium.By;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.time.Duration;
//import java.util.List;
//
//import static com.codeborne.selenide.Condition.visible;
//import static com.codeborne.selenide.Selenide.$$x;
//import static com.codeborne.selenide.Selenide.$x;
//
//public class Pobeda {
//    SelenideElement searchInput = $x("//textarea[@name='q']");
//    SelenideElement firstSearchResult = $x("(//div[@class='MjjYud']//a)[1]");
//
//    ElementsCollection banners = $$x("//div[contains(@class,'dp-1ihjhh6-root')]");
//    SelenideElement languageSwitcher = $x("//button[contains(@class, 'dp-etauof-root-root') and text()='РУС']");
//    SelenideElement englishOption = $x("//div[@role='menuitem' and text()='English']");
//
//    SelenideElement ticketSearchText = $x("//*[contains(text(),'Ticket search')]");
//    SelenideElement onlineCheckinText = $x("//*[contains(text(),'Online check-in')]");
//    SelenideElement manageBookingText = $x("//*[contains(text(),'Manage my booking')]");
//
//    SelenideElement logo = $x("//a[@class='dp-1f2hhsq-root-root-root']//img");
//    SelenideElement information = $x("//a[text()='Информация']");
//    SelenideElement informationModal = $x("//div[@class='dp-ukl30h-root']");
//    SelenideElement preparingForFlight = $x("//a[contains(@href,'#flight')]");
//    SelenideElement usefulInfo = $x("//a[contains(@href,'#useful')]");
//    SelenideElement aboutCompany = $x("//a[contains(@href,'#company')]");
//
//    @Step("Кастомное ожидание появления баннера с текстом 'Полетели в Калининград!'")
//    public void waitForBannerKaliningradText() {
//        new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(120))
//                .pollingEvery(Duration.ofSeconds(1))
//                .until(driver -> {
//                    return banners.stream()
//                            .anyMatch(el -> el.getText().contains(TestData.KALININGRAD_BANNER_TEXT));
//                });
//    }
//
//    @Step("Открытие переключателя языка и выбор английского")
//    public void switchToEnglish() {
//        languageSwitcher.shouldBe(visible).click();
//        englishOption.shouldBe(visible).click();
//    }
//
//    @Step("Проверка наличия текста на английской версии сайта (с явным ожиданием)")
//    public void verifyEnglishTexts() {
//        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.textToBePresentInElement(ticketSearchText.toWebElement(), "Ticket search"));
//        wait.until(ExpectedConditions.textToBePresentInElement(onlineCheckinText.toWebElement(), "Online check-in"));
//        wait.until(ExpectedConditions.textToBePresentInElement(manageBookingText.toWebElement(), "Manage my booking"));
//    }
//
//    @Step("Ввод текста в строку поиска Google")
//    public void search(String text) {
//        searchInput.shouldBe(visible).setValue(text).pressEnter();
//    }
//
//    @Step("Клик по первой ссылке в результатах поиска")
//    public void clickFirstResult() {
//        firstSearchResult.shouldBe(visible).click();
//    }
//
//    @Step("Проверка отображения логотипа Победы")
//    public Boolean checkLogoPobedaIsDiplayed() {
//        return logo.isDisplayed();
//    }
//
//    @Step("Наведение мышкой на пункт Информация")
//    public void hoverInformation() {
//        information.shouldBe(visible).hover();
//    }
//
//    @Step("Проверка появления всплывающего окна Информация")
//    public Boolean checkInformationModalIsDiplayed() {
//        return informationModal.shouldBe(visible).isDisplayed();
//    }
//
//    @Step("Чтение текста заголовка Подготовка к полету")
//    public String getPreparingForFlightText() {
//        return preparingForFlight.shouldBe(visible).getText();
//    }
//
//    @Step("Чтение текста заголовка Полезная информация")
//    public String getUsefulInfoText() {
//        return usefulInfo.shouldBe(visible).getText();
//    }
//
//    @Step("Чтение текста заголовка О компании")
//    public String getAboutCompanyText() {
//        return aboutCompany.shouldBe(visible).getText();
//    }
//}
