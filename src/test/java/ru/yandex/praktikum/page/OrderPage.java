package ru.yandex.praktikum.page;

import org.openqa.selenium.*;
public class OrderPage {
    private final WebDriver webDriver;
    public OrderPage(WebDriver webDriver){
        this.webDriver = webDriver;
    }
    private final By nameInputLocator =  By.xpath("//input[@placeholder='* Имя']");
    //Локатор поля для  ввода Имени
    private final By lastNameInputLocator =  By.xpath("//input[@placeholder='* Фамилия']");
    //Локатор поля для  ввода Фамилии
    private final By addressInputLocator =  By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Локатор поля для  ввода Адреса куда привезти заказ
    private final By subwayInputLocator =  By.xpath("//input[@placeholder='* Станция метро']");
    //Локатор поля выбора станции метро
    private final By phoneInputLocator =  By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Локатор поля для  ввода Телефона
    private final By nextButtonLocator = By.xpath("//button[text()='Далее']");
    //Локатор кнопки Далее
    private final String stationMenuItemLocator = "//div[text()='%s']";
    //Локатор выбора станции метро
    public void fillCustomer(String name, String lastname, String address, String subwayTitle, String phone) {
        WebElement nameInput = webDriver.findElement(nameInputLocator);
        nameInput.sendKeys(name);
        WebElement lastnameInput = webDriver.findElement(lastNameInputLocator);
        lastnameInput.sendKeys(lastname);
        WebElement addressInput = webDriver.findElement(addressInputLocator);
        addressInput.sendKeys(address);
        WebElement subwayInput = webDriver.findElement(subwayInputLocator);
        subwayInput.click();
        WebElement arbatskayaStationMenu = webDriver.findElement(By.xpath(String.format(stationMenuItemLocator,subwayTitle)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",arbatskayaStationMenu);
        arbatskayaStationMenu.click();
        WebElement phoneInput = webDriver.findElement(phoneInputLocator);
        phoneInput.sendKeys(phone);
    }
    public void clickNextButton() {
        WebElement nextButton = webDriver.findElement(nextButtonLocator);
        nextButton.click();
    }
}
