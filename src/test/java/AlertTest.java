import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AlertTest {
    private WebDriver driver = new ChromeDriver();
    private static final String URLALERT = "https://the-internet.herokuapp.com/javascript_alerts";
    private static final String ALERT_TEXT = "I am a JS Alert";
    private static final String PROMRT_TEXT = "Hello Tamara!";
    private static final String CONFERM_TEXT = "You clicked: Cancel";

    @BeforeClass
    public void start() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(URLALERT);
    }

    @Test
    public void jsAlertTest() {
        driver.findElement(By.cssSelector("button[onclick*='jsAlert']")).click();
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        Assert.assertEquals(ALERT_TEXT, text);
    }

    @Test
    public void jsConfirmTest() {
        driver.findElement(By.cssSelector("button[onclick*='jsConfirm']")).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        String text = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(CONFERM_TEXT, text);
    }

    @Test
    public void jsPromptTest() {
        driver.findElement(By.cssSelector("button[onclick*='jsPrompt']")).click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(PROMRT_TEXT);
        alert.accept();
        String text = driver.findElement(By.id("result")).getText();
        Assert.assertEquals("You entered: " + PROMRT_TEXT, text);
    }

    @AfterClass
    public void end() {
        driver.quit();
    }
}
