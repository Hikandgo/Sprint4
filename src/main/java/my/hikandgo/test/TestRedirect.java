package my.hikandgo.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import my.hikandgo.POM.HomePage;
import my.hikandgo.POM.StaticButtons;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class TestRedirect {
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
    public void redirectToHome() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage objHomePage = new HomePage(driver);

        driver.findElement(StaticButtons.buttonBottPath).click();
        objHomePage.clickLogo();

        String currUrl = objHomePage.getCurrentUrl();

        Assert.assertEquals("URL отличается от home", "https://qa-scooter.praktikum-services.ru/", currUrl);
    }

    @Test
    public void redirectToYandex() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomePage objHomePage = new HomePage(driver);

        objHomePage.clickYandex();
        String currUrl = null;

        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                currUrl = driver.getCurrentUrl();
                break;
            }
        }

        boolean result;
        if (currUrl == null) {
            result = false;
        } else if (currUrl.contains("yandex.ru") || currUrl.contains("dzen.ru") || currUrl.contains("ya.ru")) {
            result = true;
        } else {
            result = false;
        }

        Assert.assertTrue("URL отличается от yandex или dzen", result);
    }
}
