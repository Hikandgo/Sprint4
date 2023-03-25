package my.hikandgo.POM;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class HomePage {

    private WebDriver driver;
    // Элементы выпадающего списка
    private By accordingElement = By.xpath("//div[@class='accordion__button']");
    // Текстовое поле элемента выпадающего списка
    private By textField = By.xpath("//div[@class='accordion__panel']/p");
    // Кнопка "Заказать" вверху страницы
    private By orderButtonTop = By.xpath("//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    // Кнопка "Заказать" внизу страницы
    private By orderButtonBot = By.xpath("//div[@class='Home_FinishButton__1_cWm']/button");
    private By logoButton = By.xpath("//img[@alt='Scooter']");
    private By yandexButton = By.xpath("//img[@alt='Yandex']");
    private By statusButton = By.className("Header_Link__1TAG7");
    private By statusField = By.xpath("//div[@class='Input_InputContainer__3NykH']/input");
    private By trackNotFound = By.className("Track_NotFound__6oaoY");
    private By goButton = By.xpath("//div[@class='Header_SearchInput__3YRIQ']/button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBot() {
        driver.findElement(orderButtonBot).click();
    }

    public List<WebElement> getAccordingButtons() {
        List<WebElement> list = driver.findElements(accordingElement);
        return list;
    }

    public List<WebElement> getTextFields() {
        List<WebElement> list = driver.findElements(textField);
        return list;
    }

    public void scrollToAccording() {
        WebElement element = driver.findElement(accordingElement);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToBotButton() {
        WebElement element = driver.findElement(orderButtonBot);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickLogo() {
        driver.findElement(logoButton).click();
    }

    public void clickYandex() {
        driver.findElement(yandexButton).click();
    }

    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        return url;
    }

    public void checkStatus(String order) {
        driver.findElement(statusButton).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(statusField));
        driver.findElement(statusField).sendKeys(order);
        driver.findElement(goButton).click();
    }

    public boolean statusNotFoundIsVisible() {
        return driver.findElement(trackNotFound).isDisplayed();
    }
}
