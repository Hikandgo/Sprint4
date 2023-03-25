package my.hikandgo.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import my.hikandgo.POM.Constants;
import my.hikandgo.POM.HomePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TestAccordingElements {
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
    public void accordingElementsChrome() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objHomePage = new HomePage(driver);

        List<WebElement> accordingList = objHomePage.getAccordingButtons();
        List<WebElement> textFieldsList = objHomePage.getTextFields();

        for (int i = 0; i < accordingList.size(); i++) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", accordingList.get(i));
            accordingList.get(i).click();
            String questionText = accordingList.get(i).getText();
            String answerText = textFieldsList.get(i).getText();
            String checkText = Constants.getAnswer(questionText);
            Assert.assertEquals("Текст не совпадает", answerText, checkText);
        }

    }

}
