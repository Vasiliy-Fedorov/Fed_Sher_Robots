import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class MainTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/");
    }

    @AfterMethod
    public  void setDown(){
        driver.quit();
    }

    @Test
    void textBox() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

            driver.findElement(By.xpath("//div[@class = 'card-body']/h5 [contains(text(), 'Elements')]")).click();
            driver.findElement(By.xpath("//span[contains(text(), 'Text Box')]")).click();

            WebElement fullName = driver.findElement(By.xpath("//input[@id= 'userName']"));
            WebElement email = driver.findElement(By.xpath("//input[@id= 'userEmail']"));
            WebElement currentAddress = driver.findElement(By.xpath("//textarea[@id = 'currentAddress']"));
            WebElement permanentAddress = driver.findElement(By.xpath("//textarea[@id = 'permanentAddress']"));
            WebElement submit = driver.findElement(By.xpath("//button[@id = 'submit']"));

            fullName.sendKeys("Alien");
            Thread.sleep(1000);
            email.sendKeys("123abc@tort.com");
            Thread.sleep(1000);
            currentAddress.sendKeys("Spain");
            permanentAddress.sendKeys("Russia");
            submit.click();
            Thread.sleep(1000);
            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://demoqa.com/text-box";

            Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    void checkBox () throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        driver.findElement(By.xpath("//div[@class = 'card-body']/h5 [contains(text(), 'Elements')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Check Box')]")).click();

        WebElement toggleHome = driver.findElement(By.xpath("//span[contains(text(), 'Home')]/../../button"));
        Thread.sleep(5000);
        toggleHome.click();
        Thread.sleep(5000);

    }
}