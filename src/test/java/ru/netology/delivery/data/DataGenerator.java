package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {
    private DataGenerator() {
    }

    @Step("Генерация даты доставки")
    public static String generateDate(int shift) {
        var deliveryDate = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return deliveryDate;
    }

    @Step("Генерация города доставки")
    public static String generateCity() {
        var cities = new String[]{"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала", "Магас", "Нальчик", "Петразоводск", "Сыктывкар", "Йошкар-Ола",
                "Саранск", "Якутск", "Владикавказ", "Казань", "Кызыл", "Ижевск", "Абакан", "Грозный", "Чебоксары", "Барнаул", "Чита", "Петропавловск-Камчатский",
                "Краснодар", "Красноярск", "Пермь", "Ставрополь", "Хабаровск", "Благовещенск", "Архангельск", "Астрахань", "Белгород", "Брянск", "Владимир",
                "Волгоград", "Вологда", "Воронеж", "Иваново", "Иркутск", "Калининград", "Калуга", "Кемерово", "Киров", "Кострома", "Курск", "Санкт-Петербург",
                "Липецк", "Магадан", "Москва", "Мурманск", "Нижний Новгород", "Великий Новгород", "Новосибирск", "Омск", "Пенза", "Рязань", "Саратов", "Ярославль"};
        var city = cities[new Random().nextInt(cities.length)];
        return city;
    }

    @Step("Генерация фамилии и имени")
    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        var fullName = faker.name().lastName() + " " + faker.name().firstName();
        return fullName;
    }

    @Step("Генерация номера телефона")
    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        var phoneNumber = faker.phoneNumber().phoneNumber();
        return phoneNumber;
    }

    public static class Registration {
        private Registration() {
        }
@Step("Генерация данных для ввода")
        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
