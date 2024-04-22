package ru.yandex.praktikum.plain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import ru.yandex.praktikum.WebDriverFactory;
import ru.yandex.praktikum.page.MainPage;
import ru.yandex.praktikum.page.OrderPage;
import ru.yandex.praktikum.page.OrderRentPage;
import static org.junit.Assert.assertTrue;

public class OrderTest {
    private WebDriver webDriver;
    @Before
    public void setup(){
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser","firefox"));
        webDriver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test//кейс "Успешный заказ с кнопкой Заказать из Хэдера"
    public void createOrderFromHeader() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCreateOrderHeaderButton();
        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomer("Игорь", "Мошинец", "Белгород", "Арбатская", "89611630987");
        orderPage.clickNextButton();
        OrderRentPage orderRentPage = new OrderRentPage(webDriver);
        orderRentPage.dateInput("01.01.2025");
        orderRentPage.rentalPeriodClick();
        orderRentPage.clickEndCreateOrderButton();
        orderRentPage.clickAgreeButton();
        orderRentPage.checkRentOrder();
    }
    @Test//кейс "Успешный заказ с кнопкой Заказать из тела сайта"
    public void createOrderFromBody() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickCreateOrderBodyButton();
        OrderPage orderPage = new OrderPage(webDriver);
        orderPage.fillCustomer("Игорь", "Мош", "Москва", "Арбатская", "89611630987");
        orderPage.clickNextButton();
        OrderRentPage orderRentPage = new OrderRentPage(webDriver);
        orderRentPage.dateInput("01.01.2025");
        orderRentPage.rentalPeriodClick();
        orderRentPage.clickEndCreateOrderButton();
        orderRentPage.clickAgreeButton();
        orderRentPage.checkRentOrder();
    }
    @Test
    public void orderNotFound() { //Кейс "Заказ не найден"
        MainPage mainPage = new MainPage(webDriver);
        mainPage.clickOrderStatusButton();
        mainPage.enterOrderNumber("89611629990");
        mainPage.clickGoButton();
        assertTrue(mainPage.notFoundImgIsDisplayed());
        webDriver.quit();
    }
    @After
    public void tearDown() {
        webDriver.quit();
    }
}
