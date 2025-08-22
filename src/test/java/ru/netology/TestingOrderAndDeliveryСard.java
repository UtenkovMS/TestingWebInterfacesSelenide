package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class TestingOrderAndDeliveryСard {

    @Test
    void sholdFillFieldsAndSubmitForm() {

        // Открываем веб-страницу http://localhost:9999
        Selenide.open("http://localhost:9999");

        // Заполняем поле "Город" словом "Самара"
        $("[data-test-id='city'] input").setValue("Самара");

        SelenideElement dataInput = $("[data-test-id='date'] input");

        // Очищаем поле ввода даты
        // C помощью метода .sendKeys() и команды Keys. вводим задаем сочетание клавиш Ctr и a, чтобы выделить в поле текст
        dataInput.sendKeys(Keys.CONTROL + "a");

        // C помощью метода .sendKeys() и команды Keys. задаем нажатие клавиши Del, чтобы удалить ранее выделенный текст
        dataInput.sendKeys(Keys.DELETE);

        // LocalDate - тип данных соответствующий дате.
        // Создаем переменную date.
        // С помощью класса LocalDate задаем текущую дату с помощью метода .now()
        // И к текущей дате с помощью метода .plusDays(3) добавляем три дня вперед.

        LocalDate date = LocalDate.now().plusDays(3);

        // DateTimeFormatter - класс форматирующий дату.
        // Создаем переменную formatter.
        // С помощью класса DateTimeFormatter задаем формат даты .ofPattern("yyyy MM dd")

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");

        // Тип данных для переменной formatDate задается String (текстовый)
        // Метод date.format() преобразует дату из числового типа данных в строчный тип данных.

        String formatDate = date.format(formatter);

        // Устанавливаем новую дату
        dataInput.setValue(formatDate);

        // Заполняем поле "Фамилия имя" словами "Утенков Максим"
        $("[data-test-id='name'] input").setValue("Утенков Максим");

        // Заполняем поле "Телефон" валидным значением "+11111111111"
        $("[data-test-id='phone'] input").setValue("+11111111111");

        // Кликаем по чекбоксу
        $("label[data-test-id='agreement']").click();

        // Кликаем по кнопке "Забронировать"
        $("span.button__text").click();

        // Осуществляем проверку наличие отражаемого текста в поле дата с помощью метода .shouldHave()
        // и настройки Condition.exactText, где указываем искомый текст "25.08.2025"
        $("[data-test-id='date'] [class='input__control']").shouldHave(Condition.exactText("25.08.2025"), Duration.ofSeconds(15)).shouldBe(Condition.visible);


        // Осуществляем проверку наличия отражаемого текста во всплывающем сообщении: "25.08.2025"

        $("[data-test-id='notification'] [class='notification__content']").shouldHave(Condition.exactText("25.08.2025"), Duration.ofSeconds(15)).shouldBe(Condition.visible);

//        $(Selector.withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }
}
