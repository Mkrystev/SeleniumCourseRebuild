package com.selenium.course.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class SimpleTest {
    WebDriver driver;

    @BeforeTest
    public void setUp(){

        // Стартира се от локален path в intellij
       /* System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();*/
        /*System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();*/

        //Стартиране чрез WebDriverManager (отделна библиотека за maven)

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void executeSimpleTest(){
        driver.get("https://www.saucedemo.com/");

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.cssSelector("[value=LOGIN]"));
        loginBtn.click();

        WebElement selectElement = driver.findElement(By.className("product_sort_container"));
        Select list = new Select(selectElement);
        list.selectByValue("lohi");
        list.selectByVisibleText("Name (Z to A)");

        WebElement addToCartBtn = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']//ancestor::div[@class='inventory_item']//button"));
        addToCartBtn.click();

        WebElement badge = driver.findElement(By.cssSelector(".shopping_cart_badge"));
        assertEquals(badge.getText(),"1");


    }
    @AfterTest
    public void tearDown (){
        driver.quit(); // затваря браузъра и спира драйвъра

       // driver.close(); //затваря браузъра
    }
}
