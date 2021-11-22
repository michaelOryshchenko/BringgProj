import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class Base {
    protected WebDriver driver;
    protected String URL = "https://web2.0calc.com/";
    private static final int TIMEOUT = 30;
    private static final int POLLING = 200;

    public void fluentWaitElementClickable(By waitElement){
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(TIMEOUT))
                .pollingEvery(Duration.ofMillis(POLLING))
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.elementToBeClickable(waitElement));
    }

    public void clickElement(By element){
        driver.findElement(element).click();
    }

    public void mouseHoverElement(By element){
        WebElement ele = driver.findElement(element);
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();
    }

    public void waitUpdateInputText(By element, String text){
        new WebDriverWait(driver, Duration.ofMillis(5000))
                .until(ExpectedConditions.attributeContains(
                        element,
                        "value",
                        text));
    }

    public void printHistory(By ui){
        WebElement listElm = driver.findElement(ui);
        List<WebElement> links = listElm.findElements(By.tagName("li"));
        for (WebElement link : links) {
            System.out.println(link.getText());
        }
    }
}
