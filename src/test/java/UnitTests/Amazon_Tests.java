package UnitTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

public class Amazon_Tests {
    WebDriver driver;
//Before each test WebDriver opens, go to given url and maximise the window
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.opencart.com/");
        driver.manage().window().maximize();
    }
//After each test WebDriver closes the browser
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
//First test finds the element by text and click. Then finds type field for e-mail and type a demonstration e-mail.
    @Test
    public void testFindAndClick() {
        driver.findElement(By.linkText("Features")).click();
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("demobox@gmail.com");
    }
//Second test finds "Register" button by xpath, then type name and surname into relevant fields.
    @Test
    public void testNamesPutting() {
        driver.findElement(By.xpath("//div[@class='navbar-right hidden-xs']//a[@href='https://www.opencart.com/index.php?route=account/register']")).click();
        driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Vladyslav");
        driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Lehkobyt");
    }
//Third test checks if the expected text matches the actual one.
    @Test
    public void testTextPresence() {
        driver.findElement(By.xpath("//a[@href='https://www.opencart.com/blog']")).click();
        Assert.assertEquals(driver.findElement(By.linkText("What is a good eCommerce store")).getText() , "What is a good eCommerce store");
    }
//Fourth test checks that the element with the given text is presences.
    @Test
    public void testListItemPresence() {
        driver.findElement(By.className("caret")).click();
        driver.findElements(By.linkText("CONTACT US"));
    }
//Fifth test finds the link guides outside and checks that the expected url is true.
    @Test
    public void testExpectedTabURL() {
        driver.findElement(By.linkText("Facebook")).click();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.facebook.com/opencart/?ref=aymt_homepage_panel");
    }
}
