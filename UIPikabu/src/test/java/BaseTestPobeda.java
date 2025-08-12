import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

import static com.codeborne.selenide.Selenide.open;

public class BaseTestPobeda {

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadTimeout = 120000;
        Configuration.timeout = 10000;
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .includeSelenideSteps(true)
                        .screenshots(true)
                        .savePageSource(false));
        open("https://www.flypobeda.ru");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-blink-features=AutomationControlled");
//
//        WebDriver driver = new ChromeDriver(options);
//
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
//
//        WebDriverRunner.setWebDriver(driver);
    }

    @AfterEach
    public void tearDown() {
        SelenideLogger.removeListener("AllureSelenide");
        WebDriverRunner.closeWebDriver();
    }
}
