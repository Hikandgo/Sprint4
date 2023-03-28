package my.hikandgo.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import my.hikandgo.POM.Constants;
import my.hikandgo.POM.HomePage;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

@RunWith(Parameterized.class)
public class TestAccordingElements {
    private WebDriver driver;
    private final int count;
    private final String question;
    private final String answer;


    public TestAccordingElements(int count, String question, String answer) {
        this.count = count;
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getAccording() {
        return new Object[][] {
                {0, Constants.QUESTION_1.getQuestion(), Constants.QUESTION_1.getAnswer()},
                {1, Constants.QUESTION_2.getQuestion(), Constants.QUESTION_2.getAnswer()},
                {2, Constants.QUESTION_3.getQuestion(), Constants.QUESTION_3.getAnswer()},
                {3, Constants.QUESTION_4.getQuestion(), Constants.QUESTION_4.getAnswer()},
                {4, Constants.QUESTION_5.getQuestion(), Constants.QUESTION_5.getAnswer()},
                {5, Constants.QUESTION_6.getQuestion(), Constants.QUESTION_6.getAnswer()},
                {6, Constants.QUESTION_7.getQuestion(), Constants.QUESTION_7.getAnswer()},
                {7, Constants.QUESTION_8.getQuestion(), Constants.QUESTION_8.getAnswer()}
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
    public void accordingElementsChrome() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objHomePage = new HomePage(driver);

        List<WebElement> accordingList = objHomePage.getAccordingButtons();
        List<WebElement> textFieldsList = objHomePage.getTextFields();

        WebElement el = accordingList.get(count);
        String exText = textFieldsList.get(count).getText();
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", el);
        el.click();
        Assert.assertEquals("Текст элемента не соответствует техническому заданию", answer, exText);
    }
}
