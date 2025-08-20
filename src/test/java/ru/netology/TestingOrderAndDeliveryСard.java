package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class TestingOrderAndDeliveryСard {

    @Test
    void sholdFillFieldsAndSubmitForm(){

        // Открываем веб-страницу http://localhost:9999
        Selenide.open("http://localhost:9999");

        // Заполняем поле "Город" словом "Самара"
        $("[data-test-id='city'] input").setValue("Самара");

        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] input").sendKeys(Keys.DELETE);

        // String - строчный тип данных.
        // Создаем переменную selector и присваиваем ей значение CSS-селектора "[data-test-id='date'] input"
        String selector = "[data-test-id='date'] input";

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
        // Метод .format(formatter) преобразует дату (числовой тип данных) в строчный тип данных.

        String formatDate = date.format(formatter);

        // Переменной selector присваивается значение переменной formatDate

       $(selector).setValue(formatDate);

        // Заполняем поле "Фамилия имя" словами "Утенков Максим"
        $("[data-test-id='name'] input").setValue("Утенков Максим");

        // Заполняем поле "Телефон" валидным значением "+11111111111"
        $("[data-test-id='phone'] input").setValue("+11111111111");

        // Кликаем по чекбоксу
        $("label[data-test-id='agreement']").click();

        // Кликаем по кнопке "Забронировать"
        $("span.button__text").click();

        // Проверяем наличие всплывающего окна с надписью "Успешно!"
        $(Selectors.withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

}
