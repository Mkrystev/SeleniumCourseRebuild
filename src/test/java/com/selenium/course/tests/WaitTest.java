package com.selenium.course.tests;

import com.opencsv.exceptions.CsvException;
import com.selenium.course.utils.CsvReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WaitTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() {

        // Стартира се от локален path в intellij
       /* System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();*/
        /*System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();*/

        //Стартиране чрез WebDriverManager (отделна библиотека за maven)

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Implicitly wait
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @DataProvider(name = "login-data")
    public static Object[][] dataProviderHardcodedData() {
        return new Object[][]{
                {"user1", "pass1"},
                {"user2", "pass2"}
        };

    }

    @DataProvider(name = "login-data-file")
    public static Object[][] dataProviderFromCsvFile() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/login-data.csv");
    }

    @Test(dataProvider = "login-data-file")
    public void executeSimpleTest(String userName, String password) {
        driver.get("https://www.saucedemo.com/");

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.cssSelector("[value=LOGIN]"));
        loginBtn.click();

        //Explicit wait (преди него се изключва implicitly wait, защото заедно не работят) След като приключим с Explicitly wait, стартираме отново Implicitly wait
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("product_label1")));
        //wait.until(ExpectedConditions.stalenessOf(passwordInput));
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        FluentWait fluentWait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        fluentWait.until(ExpectedConditions.invisibilityOf(loginBtn));



        //Метод който екзекютва скрипт на JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", loginBtn);
        js.executeScript("arguments[0].click()", loginBtn);

    }

    @AfterTest
    public void tearDown() {
        driver.quit(); // затваря браузъра и спира драйвъра

        // driver.close(); //затваря браузъра
    }
}
