package ru.netology.delivery.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.pages.CardDeliveryPage;

import static com.codeborne.selenide.Selenide.*;
import static ru.netology.delivery.data.DataGenerator.*;

@Feature("Планирование доставки карты")
public class DeliveryTest {
    CardDeliveryPage cardDeliveryPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        cardDeliveryPage = open("http://localhost:9999", CardDeliveryPage.class);
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 5;
        var secondMeetingDate = generateDate(daysToAddForSecondMeeting);

        cardDeliveryPage.fillInCardDeliveryForm(validUser.getCity(), firstMeetingDate, validUser.getName(), validUser.getPhone());
        cardDeliveryPage.checkSuccessScheduleDeliveryMessage(firstMeetingDate);

        cardDeliveryPage.fillInDateField(secondMeetingDate);
        cardDeliveryPage.clickPlanDeliveryButton();
        cardDeliveryPage.checkReplanMessage();
        cardDeliveryPage.clickReplanDeliveryButton();

        cardDeliveryPage.checkSuccessScheduleDeliveryMessage(secondMeetingDate);
    }
}
