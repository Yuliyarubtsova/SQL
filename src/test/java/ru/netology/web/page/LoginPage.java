package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement blockedInfo = $("[data-test-id=blocked]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
    }

    public LoginPage clearFields() {
        clear();
        return new LoginPage();
    }

    public void blockedMessage(DataHelper.AuthInfo info) {
        blockedInfo.shouldBe(visible).shouldHave(text("Система заблокирована"));
    }

    public void clear() {
        loginField.sendKeys(Keys.chord(Keys.CONTROL + "A" + Keys.DELETE));
        passwordField.sendKeys(Keys.chord(Keys.CONTROL + "A" + Keys.DELETE));
    }

}