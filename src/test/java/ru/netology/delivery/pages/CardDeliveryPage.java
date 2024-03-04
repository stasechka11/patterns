package ru.netology.delivery.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardDeliveryPage {
    private final SelenideElement cityField = $("[data-test-id='city'] input");
    private final SelenideElement dateField = $("[data-test-id='date'] input");
    private final SelenideElement nameField = $("[data-test-id='name'] input");
    private final SelenideElement phoneField = $("[data-test-id='phone'] input");
    private final SelenideElement agreementCheckbox = $("[data-test-id='agreement']");
    private final SelenideElement scheduleButton = $$("button").find(exactText("Запланировать"));
    private final SelenideElement successMessage = $(byText("Успешно!"));
    private final SelenideElement successMessageContent = $("[data-test-id='success-notification'] .notification__content");
    private final SelenideElement replanMessage = $("[data-test-id='replan-notification'] .notification__content");
    private final SelenideElement replanButton = $$("[data-test-id='replan-notification'] button").find(exactText("Перепланировать"));

    @Step("Ввод данных в поле Город: {city}")
    public void fillInCityField(String city) {
        cityField.setValue(city);
    }

    @Step("Ввод данных в поле Дата: {date}")
    public void fillInDateField(String date) {
        dateField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        dateField.setValue(date);
    }

    @Step("Ввод данных в поле Фамилия и Имя: {name}")
    public void fillInNameField(String name) {
        nameField.setValue(name);
    }

    @Step("Ввод данных в поле Мобильный телефон: {phone}")
    public void fillInPhoneField(String phone) {
        phoneField.setValue(phone);
    }

    @Step("Отметка чекбокса 'Я соглашаюсь с условиями...'")
    public void setAgreementCheckbox() {
        agreementCheckbox.click();
    }

    @Step("Нажатие кнопки 'Запланировать'")
    public void clickPlanDeliveryButton() {
        scheduleButton.click();
    }

    @Step("Заполнение и отправка формы доставки карты")
    public void fillInCardDeliveryForm(String city, String date, String name, String phone) {
        fillInCityField(city);
        fillInDateField(date);
        fillInNameField(name);
        fillInPhoneField(phone);
        setAgreementCheckbox();
        clickPlanDeliveryButton();
    }

    @Step("Проверка сообщения об успешном планировании доставки")
    public void checkSuccessScheduleDeliveryMessage(String meetingDate) {
        successMessage.shouldBe(visible, Duration.ofSeconds(15));
        successMessageContent
                .shouldBe(visible)
                .shouldHave(Condition.exactText("Встреча успешно запланирована на " + meetingDate));
    }

    @Step("Проверка сообщения о перепланировании даты доставки")
    public void checkReplanMessage() {
        replanMessage
                .shouldBe(visible)
                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
    }

    @Step("Нажатие кнопки 'Перепланировать'")
    public void clickReplanDeliveryButton() {
        replanButton.click();
    }
}
