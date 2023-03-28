package my.hikandgo.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import my.hikandgo.POM.Constants;
import my.hikandgo.POM.HomePage;
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
    private final String question;
    private final String answer;

    public TestAccordingElements(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getAccording() {
        return new Object[][] {
                {Constants.QUESTION_1.getQuestion(), Constants.QUESTION_1.getAnswer()},
                {Constants.QUESTION_2.getQuestion(), Constants.QUESTION_2.getAnswer()},
                {Constants.QUESTION_3.getQuestion(), Constants.QUESTION_3.getAnswer()},
                {Constants.QUESTION_4.getQuestion(), Constants.QUESTION_4.getAnswer()},
                {Constants.QUESTION_5.getQuestion(), Constants.QUESTION_5.getAnswer()},
                {Constants.QUESTION_6.getQuestion(), Constants.QUESTION_6.getAnswer()},
                {Constants.QUESTION_7.getQuestion(), Constants.QUESTION_7.getAnswer()},
                {Constants.QUESTION_8.getQuestion(), Constants.QUESTION_8.getAnswer()}
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

        int count = 0;

        for (WebElement el: accordingList) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", el);
            el.click();
            String str = el.getText();
            if (str.equals(question)) {
                break;
            } else {
                count ++;
            }
        }
        String checkText = textFieldsList.get(count).getText();

        Assert.assertEquals("Текст элемента не соответствует техническому заданию", answer, checkText);

    }

}
