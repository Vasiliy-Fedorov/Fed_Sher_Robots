import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.List;


public class MainTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

//        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("https://demoqa.com/");
    }

    @AfterMethod
    public  void setDown(){
        driver.quit();
    }

    @Test
    void textBox() throws InterruptedException {// проверяем заполнение формы и её отправку
        WebDriverManager.chromedriver().setup();

            driver.findElement(By.xpath("//div[@class = 'card-body']/h5 [contains(text(), 'Elements')]")).click();
            driver.findElement(By.xpath("//span[contains(text(), 'Text Box')]")).click();

            WebElement fullName = driver.findElement(By.xpath("//input[@id= 'userName']"));
            WebElement email = driver.findElement(By.xpath("//input[@id= 'userEmail']"));
            WebElement currentAddress = driver.findElement(By.xpath("//textarea[@id = 'currentAddress']"));
            WebElement permanentAddress = driver.findElement(By.xpath("//textarea[@id = 'permanentAddress']"));
            WebElement submit = driver.findElement(By.xpath("//button[@id = 'submit']"));

            fullName.sendKeys("Alien");

            email.sendKeys("123abc@tort.com");

            currentAddress.sendKeys("Spain");
            permanentAddress.sendKeys("Russia");
            submit.click();

            String actualUrl = driver.getCurrentUrl();
            String expectedUrl = "https://demoqa.com/text-box";
//TO DO переписать ассёрт
            Assert.assertEquals(actualUrl, expectedUrl);
    }

    @Test
    void selectAllCheckBox () throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        //В этом тесте проверяем, что при выделении чекбокса Home выделяются все чекбоксы в количестве 17 штук

        driver.findElement(By.xpath("//div[@class = 'card-body']/h5 [contains(text(), 'Elements')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Check Box')]")).click();

        WebElement checkBoxHome = driver.findElement(By.xpath("//span[contains(text(), 'Home')]/../span[@class = 'rct-checkbox']"));
        checkBoxHome.click();

        List<WebElement> selectedList = driver.findElements(By.xpath("//div[@id = 'result']/span[@class = 'text-success']"));
        Thread.sleep(5000);
        Assert.assertEquals(selectedList.size(), 17);
    }

    @Test
    void selectOneCheckBox () throws InterruptedException {
        WebDriverManager.chromedriver().setup();
//        Тест проверяет выбор одного чекбокса - React

        driver.findElement(By.xpath("//div[@class = 'card-body']/h5 [contains(text(), 'Elements')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Check Box')]")).click();

        WebElement toggleHome = driver.findElement(By.xpath("//span[contains(text(), 'Home')]/../../button"));
        toggleHome.click();

        WebElement toggleDocuments = driver.findElement(By.xpath("//span[contains(text(), 'Documents')]/../../button"));
        toggleDocuments.click();

        WebElement toggleWorkSpace = driver.findElement(By.xpath("//span[contains(text(), 'WorkSpace')]/../../button"));
        toggleWorkSpace.click();

        WebElement checkBoxReact = driver.findElement(By.xpath("//span[contains(text(), 'React')]/../span[@class = 'rct-checkbox']"));
        checkBoxReact.click();

        WebElement result = driver.findElement(By.xpath("//div[@id = 'result']/span[@class = 'text-success']"));
        String expectedText = "react";

        Assert.assertEquals(result.getText(), expectedText);
    }
}
