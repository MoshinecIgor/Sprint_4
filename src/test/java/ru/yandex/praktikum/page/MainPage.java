package ru.yandex.praktikum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.time.Duration.ofSeconds;
public class MainPage {
    private final WebDriver webDriver;
    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    //Локаторы
    private final By orderStatusLocator = By.xpath("//button[text()='Статус заказа']");
    //Статус заказа
    private final By cookiesButtonLocator = By.id("rcc-confirm-button");
    //Кнопка соглсия с куками
    private final By orderNumberInputLocator = By.xpath("//input[@placeholder='Введите номер заказа']");
    //Ввод номера заказа
    private final By goButtonLocator = By.xpath("//button[contains(text(), 'Go!')]");
    //Кнопка Go
    private final By notFoundImgLocator = By.xpath("//img[@alt='Not found']");
    //Локатор поискать картинки о том что заказ не найден
    private static final By createOrderHeaderButtonLocator = By.xpath("//div[contains(@class, 'Header')]//button[text()='Заказать']");
    //Кнопка Заказать в Хэдере страницы
    private static final By createOrderBodyButtonLocator = By.xpath("//div[contains(@class, 'Home')]/button[text()='Заказать']");
    //Кнопка Заказать в боди страницы
    private final String questionLocator = "accordion__heading-%s";
    //Локатор вопроса в аккордеоне
    private final String answerLocator = "//div[contains(@id,'accordion__panel')][.='%s']";
    //Локатор ответа в аккордеоне
    public void clickOrderStatusButton() {
        WebElement orderStatusButtonBtn = webDriver.findElement(orderStatusLocator);
        orderStatusButtonBtn.click();
    }
    public void enterOrderNumber(String orderNumber) {
        WebElement orderInput = webDriver.findElement(orderNumberInputLocator);
        new WebDriverWait(webDriver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(orderInput));
        orderInput.sendKeys(orderNumber);
    }
    public void clickGoButton() {
        WebElement goButton = webDriver.findElement(goButtonLocator);
        new WebDriverWait(webDriver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(goButton));
        goButton.click();
    }
    public void clickCreateOrderHeaderButton() {
        WebElement clickCreateOrderHeaderButton = webDriver.findElement(createOrderHeaderButtonLocator);
        clickCreateOrderHeaderButton.click();
    }
    public void clickCreateOrderBodyButton() {
        WebElement clickCreateOrderBodyButton = webDriver.findElement(createOrderBodyButtonLocator);
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",clickCreateOrderBodyButton);
        new WebDriverWait(webDriver, ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(clickCreateOrderBodyButton));
        clickCreateOrderBodyButton.click();
    }
    public boolean notFoundImgIsDisplayed() {
        new WebDriverWait(webDriver, ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(notFoundImgLocator));
        return webDriver.
                findElement(notFoundImgLocator).isDisplayed();
    }
    public void closeCookiesWindow() {
        webDriver.findElement(cookiesButtonLocator).click();
    }
    public void expandQuestion(int index) {
        WebElement element = webDriver.findElement(By.id(String.format(questionLocator,index)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",element);
        new WebDriverWait(webDriver, ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    public boolean answerIsDisplayed(String expectedAnswer) {
        WebElement element = webDriver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        return element.isDisplayed();
    }
}
