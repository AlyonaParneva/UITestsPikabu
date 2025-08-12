//
//import com.codeborne.selenide.Selenide;
//import io.qameta.allure.Step;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.openqa.selenium.Keys;
//
//import static com.codeborne.selenide.Condition.appear;
//import static com.codeborne.selenide.Condition.visible;
//import static com.codeborne.selenide.Selenide.$x;
//import static com.codeborne.selenide.Selenide.actions;
//import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
//import static io.qameta.allure.Allure.step;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class PikabuTestPikabu extends BaseTestPikabu {
//    Pikabu pikabu = new Pikabu();
//
//    @Test
//    @DisplayName("Тест для сайта Пикабу с проверкой неверной авторизации")
//    public void testAuthorizationError() throws InterruptedException {
//        step("Проверка, что заголовок страницы соответствует ожидаемому", () -> {
//            String actualTitle = getWebDriver().getTitle();
//            assertEquals(TestData.EXPECTED_TITLE, actualTitle);
//        });
//        actions().sendKeys(Keys.ESCAPE).perform();
//        pikabu.clickLoginButton();
//        step("Проверка, что появилось окно авторизации и видны поля логина и пароля и кнопка Войти", () -> {
//            assertTrue(pikabu.checkAuthorizationModalIsDiplayed());
//            assertTrue(pikabu.checkLoginAuthorizationModalIsDiplayed());
//            assertTrue(pikabu.checkPasswordAuthorizationModalIsDiplayed());
//            assertTrue(pikabu.checkSubmitAuthorizationModalIsDiplayed());
//        });
//        pikabu.fillCredentials(TestData.LOGIN, TestData.PASSWORD);
//        pikabu.clickSubmit();
//        step("Проверка появления сообщения об ошибке авторизации", () -> {
//            assertEquals(TestData.ERROR_TEXT, pikabu.getErrorMessageText());
//        });
//    }
//
//}
