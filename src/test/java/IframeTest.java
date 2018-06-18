import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class IframeTest {
    private WebDriver driver = new ChromeDriver();
    private static final String URL = "https://the-internet.herokuapp.com/";
    private static final String TEXTFORFRAME = "Hello world!";

    @BeforeClass
    public void start() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test
    public void iFrameTest() {
        driver.findElement(By.cssSelector(" a[href*='/frames']")).click();
        driver.findElement(By.cssSelector("a[href*='/iframe']")).click();
        WebElement frame = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(frame);
        driver.findElement(By.tagName("body")).clear();
        driver.findElement(By.tagName("body")).sendKeys(TEXTFORFRAME);
        String text = driver.findElement(By.tagName("p")).getText();
        Assert.assertEquals(TEXTFORFRAME, text);
    }

    @AfterClass
    public void end() {
        driver.quit();
    }
}
