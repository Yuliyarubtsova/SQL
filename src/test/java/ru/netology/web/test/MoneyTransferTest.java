package ru.netology.web.test;

import lombok.val;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.*;
import ru.netology.web.page.LoginPage;

import static ru.netology.web.data.DataHelper.AuthInfo.getAuthInfoVasya;
import static ru.netology.web.data.DataHelper.getAuthInfoPetya;
import static ru.netology.web.data.SQLdata.clearSQL;
import static ru.netology.web.data.SQLdata.getVerificationCode;

public class MoneyTransferTest {

    @AfterAll
    public static void clearDB() {
        clearSQL();
    }

    @Test
    void shouldValidVerification1() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = getAuthInfoVasya();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.DashPage();
    }

    @Test
    void shouldValidVerification2() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = getAuthInfoPetya();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.DashPage();
    }

    @Test
    void shouldTransferBlockedSystem() {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        $("[data-test-id=login] input").setValue("vasya");
        $("[data-test-id=password] input").setValue("111");
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible);
        $("[data-test-id=password] input").setValue("222");
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Неверно указан логин или пароль")).shouldBe(visible);
        $("[data-test-id=password] input").setValue("333");
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Система заблокирована")).shouldBe(visible);
    }
}