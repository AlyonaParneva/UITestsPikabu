import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PobedaInfoPopupPage {
    @FindBy(xpath = "//div[@class='dp-ukl30h-root']")
    private WebElement informationModal;

    @FindBy(xpath = "//a[contains(@href,'#flight')]")
    private WebElement preparingForFlight;

    @FindBy(xpath = "//a[contains(@href,'#useful')]")
    private WebElement usefulInfo;

    @FindBy(xpath = "//a[contains(@href,'#company')]")
    private WebElement aboutCompany;

    public PobedaInfoPopupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Проверка появления всплывающего окна Информация")
    public boolean isPopupDisplayed() {
        return informationModal.isDisplayed();
    }

    @Step("Чтение текста заголовка Подготовка к полету")
    public String getPreparingForFlightText() {
        return preparingForFlight.getText();
    }

    @Step("Чтение текста заголовка Полезная информация")
    public String getUsefulInfoText() {
        return usefulInfo.getText();
    }

    @Step("Чтение текста заголовка О компании")
    public String getAboutCompanyText() {
        return aboutCompany.getText();
    }
}
