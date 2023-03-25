package my.hikandgo.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import my.hikandgo.POM.HomePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestOrderStatus {
    private WebDriver driver;
    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }
    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void checkStatusOrder() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        HomePage objHomePage = new HomePage(driver);

        objHomePage.checkStatus(" ");

        boolean result = objHomePage.statusNotFoundIsVisible();

        Assert.assertEquals("Окно об отсутствие заказа не найдено", true, result);

    }

}
