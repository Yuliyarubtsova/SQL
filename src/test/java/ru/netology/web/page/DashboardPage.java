package ru.netology.web.page;

import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public void personalOfficePage() {
        heading.shouldBe(visible);
    }
}