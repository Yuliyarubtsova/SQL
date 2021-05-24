package ru.netology.web.test;

import lombok.val;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

import org.junit.jupiter.api.*;
import ru.netology.web.page.LoginPage;

import static ru.netology.web.data.DataHelper.AuthInfo.getAuthInfoVasya;
import static ru.netology.web.data.DataHelper.getAuthInfoInvalid;
import static ru.netology.web.data.DataHelper.getAuthInfoPetya;
import static ru.netology.web.data.SQLdata.clearSQL;
import static ru.netology.web.data.SQLdata.getVerificationCode;

public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    public static void clearDB() {
        clearSQL();
    }

    @Test
    void shouldValidVerification1() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInfoVasya();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.personalOfficePage();
    }

    @Test
    void shouldValidVerification2() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInfoPetya();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = getVerificationCode(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.personalOfficePage();
    }

    @Test
        void shouldTransferBlockedSystemAfter3InvalidPassword() {
        val loginPage = new LoginPage();
        val authInfo = getAuthInfoInvalid();
        loginPage.invalidLogin(authInfo);
        loginPage.clearFields();
        loginPage.invalidLogin(authInfo);
        loginPage.clearFields();
        loginPage.invalidLogin(authInfo);
        loginPage.blockedMessage(getAuthInfoInvalid());
    }
}
