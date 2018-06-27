import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    private static final String USERNAME = "EugenBorisik";
    private static final String PASSWORD = "qwerty12345";
    private static final String RMSYS_URL = "https://192.168.100.26/";
    private static final String LOGGED_USERNAME_TEXT = "Borisik, Eugen";
    private WebDriver driver = new ChromeDriver();

    public void login(String usernme, String pass) {
        driver.findElement(By.id("Username")).sendKeys(usernme);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(pass);
        driver.findElement(By.cssSelector(".submit-button")).submit();
    }

    @DataProvider
    public Object[][] testData() {
        return new Object[][]{
                new Object[]{"eugenborisik", "qwerty12345"},
                new Object[]{"EugenBorisik", "qwerty12345"},
                new Object[]{"EUGENBORISIK", "qwerty12345"},
                new Object[]{"TamaraLukashonak", "blahblah"},
        };
    }

    @BeforeClass
    public void start() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(RMSYS_URL);
    }

    @Test
    public void loginTest() throws InterruptedException {
        login(USERNAME, PASSWORD);
        String findGetUserName = driver.findElement(By.cssSelector("#info div")).getText();
        Thread.sleep(500);  //Explicit wait
        Assert.assertEquals(LOGGED_USERNAME_TEXT, findGetUserName);
    }

    @Test(dataProvider = "testData")
    public void loginWithExplicitWaitAndDdtTest(String username, String password) {
        login(username, password);
        WebElement signOut = driver.findElement(By.cssSelector(".sign-out"));
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(signOut));
        Assert.assertTrue(signOut.isDisplayed(), "SignOut is not displayed!");
    }

    @Test
    public void explicitWaitWithOfficeTabTest() {
        login(USERNAME, PASSWORD);
        driver.findElement(By.cssSelector("a[href*='Office/Index']")).click();
        WebElement searchInput = driver.findElement(By.cssSelector("#input-search"));
        new WebDriverWait(driver, 15).pollingEvery(Duration.ofMillis(2700)).until(ExpectedConditions.visibilityOf(searchInput));
        Assert.assertTrue(searchInput.isDisplayed(), "Search for Office input is not displayed!");
    }

    @AfterMethod
    public void closetest() {
        driver.findElement(By.cssSelector(".sign-out")).click();
    }

    @AfterClass
    public void end() {
        driver.quit();
    }
}
