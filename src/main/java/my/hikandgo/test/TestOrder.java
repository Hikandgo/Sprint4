package my.hikandgo.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import my.hikandgo.POM.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class TestOrder {
    private WebDriver driver;

    private final String userName;
    private final String userSurname;
    private final String userAdres;
    private final String userMetro;
    private final String userPhone;
    private final String date;
    private final DropList rent;
    private final String color;
    private final String comment;
    private final By button;
    private final boolean result;

    public TestOrder(By button, String userName, String userSurname, String userAdres, String userMetro, String userPhone, String date, DropList rent, String color, String comment, boolean result) {
        this.button = button;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userAdres = userAdres;
        this.userMetro = userMetro;
        this.userPhone = userPhone;
        this.date = date;
        this.rent = rent;
        this.color = color;
        this.comment = comment;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Object[][] getUserInfo() {
        return new Object[][] {
                {StaticButtons.buttonBottPath,"Тестовый", "Тестован", "Где-то на окраинах", "Лубянка", "+111111111111", "16.03.01", DropList.ONE_DAY, "grey", " ", true},
                {StaticButtons.buttonTopPath,"Тестовый", "Тестован", "Где-то на окраинах", "Лубянка", "+111111111111", "16.03.01", DropList.ONE_DAY, "grey", " ", true},
                {StaticButtons.buttonBottPath,"Алексей", "Калисей", "Хдета", " ", "+111111111111", "16.03.01", DropList.TWO_DAY, "black", "asdas", false },
                {StaticButtons.buttonTopPath,"Алексей", "Калисей", "Хдета", " ", "+111111111111", "16.03.01", DropList.TWO_DAY, "black", "asdas", false },
                {StaticButtons.buttonBottPath, "Александр", "Шрумпавулен", "Пр. Героев 93", "Черкизовская", "+7977732159", "16.01.2024", DropList.SEVEN_DAYS, "grey", "no comm", true},
                {StaticButtons.buttonTopPath, "Александр", "Шрумпавулен", "Пр. Героев 93", "Черкизовская", "+7977732159", "16.01.2024", DropList.SEVEN_DAYS, "grey", "no comm", true}
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();

    }
    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void buyScooterTopButton() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        HomePage objHomePage = new HomePage(driver);
        OrderPage objOrderPage = new OrderPage(driver);

        objHomePage.scrollTtoButton(button);
        driver.findElement(button).click();

        objOrderPage.fillOrderForm(userName, userSurname, userAdres, userMetro, userPhone);

        RentPage objRentPage = new RentPage(driver);
        objRentPage.fillOrder( date, rent, color, comment);
        Assert.assertEquals("Подтверждение оформления заказа отсутствует", objRentPage.checkOrder(), result);
    }
}
