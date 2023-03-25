package my.hikandgo.POM;

import org.openqa.selenium.*;


import java.util.List;

public class RentPage {

    private WebDriver driver;

    // Поле "когда привезти самокат" (дата) -> обязательное
    private By deliveryDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // Поле "срок аренды" (выпадающий фиксированный список) -> обязательное
    private By timeRentField = By.className("Dropdown-placeholder");
    private By timeRentList = By.className("Dropdown-option is-selected");
    // цвет самоката
    private By chooseBlack = By.xpath("//label[@for='black']");
    private By chooseGrey = By.xpath("//label[@for='grey']");
    // Поле "комментарий для курьера)
    private By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Назад"
    private By buttonBack = By.className("Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i");
    // Кнопка "Заказать"
    private By buttonOrder = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    private By buttonYes = By.xpath("//button[text()='Да']");
    private By buttonNo = By.xpath("//button[text()='Нет']");

    private By orderCheck = By.xpath("//div[(@class='Order_ModalHeader__3FDaJ') and (text()='Заказ оформлен')]");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillDate(String date) {
        driver.findElement(deliveryDate).sendKeys(date + Keys.ENTER);
    }

    public void fillTimeRent(DropList el) {
        driver.findElement(timeRentField).click();
        switch (el) {
            case ONE_DAY:
                driver.findElement(By.xpath("//div[(@class='Dropdown-option') and (text()='сутки')]")).click();
                break;
            case TWO_DAY:
                driver.findElement(By.xpath("//div[(@class='Dropdown-option') and (text()='двое суток')]")).click();
                break;
            case THREE_DAYS:
                driver.findElement(By.xpath("//div[(@class='Dropdown-option') and (text()='трое суток')]")).click();
                break;
            case FOUR_DAYS:
                driver.findElement(By.xpath("//div[(@class='Dropdown-option') and (text()='четверо суток')]")).click();
                break;
            case FIVE_DAYS:
                driver.findElement(By.xpath("//div[(@class='Dropdown-option') and (text()='пятеро суток')]")).click();
                break;
            case SIX_DAYS:
                driver.findElement(By.xpath("//div[(@class='Dropdown-option') and (text()='шестеро суток')]")).click();
                break;
            case SEVEN_DAYS:
                driver.findElement(By.xpath("//div[(@class='Dropdown-option') and (text()='семеро суток')]")).click();
                break;
            default:
                throw new ElementClickInterceptedException("!");
        }
    }

    public void chooseColor(String color) {
        if (color.equals("black")) {
            driver.findElement(chooseBlack).click();
        } else if (color.equals("grey")) {
            driver.findElement(chooseGrey).click();
        }
    }

    public void fillComment(String userComment) {
        driver.findElement(comment).sendKeys(userComment);
    }

    public void clickBackButton() {
        driver.findElement(buttonBack).click();
    }

    public void clickOrderButton() {
        List<WebElement> list = driver.findElements(buttonOrder);
        list.get(1).click();
    }

    public void clickYesButton() {
        driver.findElement(buttonYes).click();
    }

    public boolean checkOrder() {
        try {
            driver.findElement(orderCheck).isDisplayed();
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }



    public void fillOrder(String date, DropList time, String color, String comment) {
        fillDate(date);
        fillTimeRent(time);
        chooseColor(color);
        fillComment(comment);
        clickOrderButton();
        clickYesButton();
    }
}
