import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BaseTests extends Base {

    //*********Web Elements*********
    private final static By btn2X = By.id("Button2X");
    private final static By btnPow10 = By.id("BtnPow10");
    private final static By btn1 = By.id("Btn1");
    private final static By btnCalc = By.id("BtnCalc");
    private final static By inputRes = By.id("input");
    private final static By btn2 = By.id("Btn2");
    private final static By btn3 = By.id("Btn3");
    private final static By btnClear = By.id("BtnClear");
    private final static By btnHist = By.id("hist");
    private final static By histFrame = By.id("histframe");

    @BeforeClass
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void Test() {
        driver.get(URL);
        mouseHoverElement(btnPow10);
        fluentWaitElementClickable(btn2X);
        clickElement(btn2X);
        clickElement(btn1);
        clickElement(btnCalc);
        waitUpdateInputText(inputRes, "2");
        clickElement(btnClear);
        mouseHoverElement(btnPow10);
        fluentWaitElementClickable(btn2X);
        clickElement(btn2X);
        clickElement(btn2);
        clickElement(btnCalc);
        waitUpdateInputText(inputRes, "4");
        clickElement(btnClear);
        mouseHoverElement(btnPow10);
        fluentWaitElementClickable(btn2X);
        clickElement(btn2X);
        clickElement(btn3);
        clickElement(btnCalc);
        waitUpdateInputText(inputRes, "8");
        clickElement(btnHist);
        printHistory(histFrame);
    }

    @AfterClass
    public void quitBrowser() {
        driver.quit();
    }
}