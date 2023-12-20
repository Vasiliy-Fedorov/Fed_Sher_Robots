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
        // проверяем заполнение формы и её отправку
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
            email.sendKeys("123abc@tort.com");
            currentAddress.sendKeys("Spain");
            permanentAddress.sendKeys("Russia");
            submit.click();

            List<WebElement> listValue = driver.findElements(By.xpath("//div[@id = 'output']"));
            String strExpected = "Name:Alien\n" +
                    "Email:123abc@tort.com\n" +
                    "Current Address :Spain\n" +
                    "Permananet Address :Russia";

            Assert.assertEquals(listValue.get(0).getText(), strExpected);
    }

    @Test
        // Проверяем, что при выделении чекбокса Home выделяются все чекбоксы в количестве 17 штук
    void selectAllCheckBox () throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        driver.findElement(By.xpath("//div[@class = 'card-body']/h5 [contains(text(), 'Elements')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Check Box')]")).click();

        WebElement checkBoxHome = driver.findElement(By.xpath("//span[contains(text(), 'Home')]/../span[@class = 'rct-checkbox']"));
        checkBoxHome.click();

        List<WebElement> selectedList = driver.findElements(By.xpath("//div[@id = 'result']/span[@class = 'text-success']"));
        Thread.sleep(5000);
        Assert.assertEquals(selectedList.size(), 17);
    }

    @Test
        // Тест проверяет выбор одного чекбокса - React
    void selectOneCheckBox () throws InterruptedException {
        WebDriverManager.chromedriver().setup();

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
    @Test
    // Проверка коллапс кнопок, что при нажатии на "+", аккордеон открывается
    void collapseButton () throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        driver.findElement(By.xpath("//div[@class = 'card-body']/h5 [contains(text(), 'Elements')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Check Box')]")).click();

        WebElement expandAll = driver.findElement(By.xpath("//button[@title = 'Expand all']"));
        WebElement collapseAll = driver.findElement(By.xpath("//button[@title = 'Collapse all']"));

        expandAll.click();

        List<WebElement> result = driver.findElements(By.xpath("//li[@class = 'rct-node rct-node-parent rct-node-expanded']"));

        Assert.assertEquals(result.size(), 6);
    }

    @Test
    // Проверка коллапс кнопок, что при нажатии на "-", аккордеон закрывается.
    void expandButton () throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        driver.findElement(By.xpath("//div[@class = 'card-body']/h5 [contains(text(), 'Elements')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Check Box')]")).click();

        WebElement expandAll = driver.findElement(By.xpath("//button[@title = 'Expand all']"));
        WebElement collapseAll = driver.findElement(By.xpath("//button[@title = 'Collapse all']"));

        expandAll.click();
        collapseAll.click();

        List<WebElement> result = driver.findElements(By.xpath("//li[@class = 'rct-node rct-node-parent rct-node-collapsed']"));

        Assert.assertEquals(result.size(), 1);
    }
}
