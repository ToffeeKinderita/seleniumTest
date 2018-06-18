import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private static final String USERNAME = "EugenBorisik";
    private static final String PASSWORD = "qwerty12345";
    WebDriver driver = new ChromeDriver();

    @BeforeMethod
    public void start() {
        driver.get("https://192.168.100.26/");

    }

    @Test
    public void loginTest() {
        driver.findElement(By.id("Username")).sendKeys(USERNAME);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector(".submit-button")).submit();
        Assert.assertEquals("Borisik, Eugen", driver.findElement(By.cssSelector("#info div")).getText());

    }

    @AfterMethod
    public void end() {
        driver.quit();
    }
}
