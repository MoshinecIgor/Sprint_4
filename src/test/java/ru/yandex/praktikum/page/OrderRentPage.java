package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.time.Duration.ofSeconds;
public class OrderRentPage {//Решил сделать отдельный класс, так как у станицы другой URL
    private final WebDriver webDriver;
    public OrderRentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    private final By dateInputLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Выбор даты когда привести самокат
    private final By rentalPeriodInputLocator = By.xpath("//div[text()='* Срок аренды']");
    //Выбор поля срока аренды
    private final By rentalPeriodInputChoseLocator = By.xpath("//div[text()='трое суток']");
    //Выбор значения срока аренды
    private final By clickOrderFinishLocator = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    //Кнопка совершения заказа
    private final By clickAgreeButtonLocator = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");
    //Кнопка подтверждения соверщенного заказа
    private final By toSeeStatusOrderLocator = By.xpath("//div[@class='Order_NextButton__1_rCA']/button[text()='Посмотреть статус']");
    //Подтверждение что заказ сформирован
    public void dateInput(String dateRent) {

        WebElement dateInput = webDriver.findElement(dateInputLocator);
        dateInput.sendKeys(dateRent, Keys.ENTER);
    }
    public void rentalPeriodClick() {
        WebElement rentalPeriodClick = webDriver.findElement(rentalPeriodInputLocator);
        rentalPeriodClick.click();

        WebElement rentPeriodMenuInput = webDriver.findElement(rentalPeriodInputChoseLocator);
        rentPeriodMenuInput.click();
    }
    public void clickEndCreateOrderButton() {
        WebElement clickOrderFinish = webDriver.findElement(clickOrderFinishLocator);
        clickOrderFinish.click();

    }
    public void clickAgreeButton() {

        WebElement clickAgreeButton = webDriver.findElement(clickAgreeButtonLocator);
        clickAgreeButton.click();
    }
    public void checkRentOrder() {
        WebElement checkRentOrder = webDriver.findElement(toSeeStatusOrderLocator);
        new WebDriverWait(webDriver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(toSeeStatusOrderLocator));
        checkRentOrder.isDisplayed();
    }
}
