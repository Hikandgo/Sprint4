package my.hikandgo.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import my.hikandgo.POM.DropList;
import my.hikandgo.POM.HomePage;
import my.hikandgo.POM.OrderPage;
import my.hikandgo.POM.RentPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
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
    private final boolean result;

    public TestOrder(String userName, String userSurname, String userAdres, String userMetro, String userPhone, String date, DropList rent, String color, String comment, boolean result) {
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
                {"Тестовый", "Тестован", "Где-то на окраинах", "Лубянка", "+111111111111", "16.03.01", DropList.ONE_DAY, "grey", " ", true},
                {"Алексей", "Калисей", "Хдета", " ", "+111111111111", "16.03.01", DropList.TWO_DAY, "black", "asdas", false },
                {"DSasda", "SDAASDAS", "DSADASDAS", "Проспект", "---_---", DropList.FIVE_DAYS, "grey", " ", false},
                {"Александр", "Шрумпавулен", "Пр. Героев 93", "Черкизовская", "+7 977 73 21 59", "16.16.1923", DropList.SEVEN_DAYS, "grey", "no comm", false},
                {"Александр", "Шрумпавулен", "Пр. Героев 93", "Черкизовская", "+7977732159", "16.01.2024", DropList.SEVEN_DAYS, "grey", "no comm", true}
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

        objHomePage.clickOrderButtonTop();

        objOrderPage.fillOrderForm(userName, userSurname, userAdres, userMetro, userPhone);

        RentPage objRentPage = new RentPage(driver);
        objRentPage.fillOrder( date, rent, color, comment);
        Assert.assertEquals("Подтверждение оформления заказа отсутствует", objRentPage.checkOrder(), result);
    }

    @Test
    public void buyScooterBotButton() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        HomePage objHomePage = new HomePage(driver);
        OrderPage objOrderPage = new OrderPage(driver);

        objHomePage.scrollToBotButton();
        objHomePage.clickOrderButtonBot();

        objOrderPage.fillOrderForm(userName, userSurname, userAdres, userMetro, userPhone);

        RentPage objRentPage = new RentPage(driver);
        objRentPage.fillOrder( date, rent, color, comment);
        Assert.assertEquals("Подтверждение оформления заказа отсутствует", objRentPage.checkOrder(), result);
    }
}
