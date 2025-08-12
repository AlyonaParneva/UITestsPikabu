//import com.codeborne.selenide.SelenideElement;
//import io.qameta.allure.Step;
//
//import java.time.Duration;
//
//import static com.codeborne.selenide.Condition.*;
//import static com.codeborne.selenide.Selenide.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class Pikabu {
//    SelenideElement loginButton = $x("//header//button[contains(@class, 'login-button')]");
//    SelenideElement modalWindow = $x("//div[@class='auth-modal']");
//    SelenideElement loginField = $x("//div[@class='popup__container']//input[@placeholder='Логин']");
//    SelenideElement passwordField = $x("//div[@class='popup__container']//input[@placeholder='Пароль']");
//    SelenideElement submitButton = $x("//div[@class='popup__container']//span[text()='Войти']");
//    SelenideElement errorMessage = $x("//div[@class='auth__notice']/following-sibling::span");
//
//    @Step("Клик по кнопке Войти")
//    public void clickLoginButton() {
//        loginButton.shouldBe(visible, enabled).scrollIntoView(true).click();
//    }
//
//    @Step("Ввод данных логина и пароля в модальном окне авторизации")
//    public void fillCredentials(String login, String password) {
//        loginField.shouldBe(visible).setValue(login);
//        passwordField.shouldBe(visible).setValue(password);
//    }
//
//    @Step("Клик по кнопке войти в модальном окне авторизации")
//    public void clickSubmit() {
//        submitButton.shouldBe(visible).click();
//    }
//
//    @Step("Проверка отображения окна авторизации")
//    public Boolean checkAuthorizationModalIsDiplayed() {
//        return modalWindow.shouldBe(visible).isDisplayed();
//    }
//
//    @Step("Проверка отображения поля логина в окне авторизации")
//    public Boolean checkLoginAuthorizationModalIsDiplayed() {
//        return loginField.shouldBe(visible).isDisplayed();
//    }
//
//    @Step("Проверка отображения поля пароля в окне авторизации")
//    public Boolean checkPasswordAuthorizationModalIsDiplayed() {
//        return passwordField.shouldBe(visible).isDisplayed();
//    }
//
//    @Step("Проверка отображения кнопки войти в окне авторизации")
//    public Boolean checkSubmitAuthorizationModalIsDiplayed() {
//        return submitButton.shouldBe(visible).isDisplayed();
//    }
//
//    @Step("Чтение текста сообщения об ошибке")
//    public String getErrorMessageText() {
//        return errorMessage.shouldBe(visible).getText();
//    }
//}
//
