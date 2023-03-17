package my.hikandgo.POM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class OrderPage {


    private WebDriver driver;
    // Поле "имя"
    private By name = By.xpath("//input[@placeholder='* Имя']");
    // Поле "фамилия"
    private By surname = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле "адрес"
    private By adres = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле "станция метро" (выпадающий список)* Станция метро
    private By metro = By.className("select-search__input");
    private By searchList = By.className("select-search has-focus");
    // Поле "телефон"
    private By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее"
    private By buttonNext = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillName(String userName) {
        driver.findElement(name).sendKeys(userName);
    }

    public void fillSurname(String userSurname) {
        driver.findElement(surname).sendKeys(userSurname);
    }

    public void fillAdres(String userAdres) {
        driver.findElement(adres).sendKeys(userAdres);
    }

    public void fillMetro(String userMetro) {
        driver.findElement(metro).click();
        driver.findElement(metro).sendKeys(userMetro + Keys.ARROW_DOWN + Keys.ENTER);
    }

    public void fillPhone(String userPhone) {
        driver.findElement(phone).sendKeys(userPhone);
    }

    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }

    public void fillOrderForm(String userName, String userSurname, String userAdres, String userMetro, String userPhone) {
        fillName(userName);
        fillSurname(userSurname);
        fillAdres(userAdres);
        fillMetro(userMetro);
        fillPhone(userPhone);
        clickButtonNext();
    }
}
