package com.selenium.course.base;

import com.selenium.course.driver.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class TestUtil {
    public WebDriver driver;
    private String url;
    private int implicitlyWait;
    private String browser;

    @BeforeSuite
    public void readConfigProperties() throws FileNotFoundException {
        try (FileInputStream configFile = new
                FileInputStream("src/test/resources/config.properties")) {
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("urlAddress");
            implicitlyWait = Integer.parseInt(config.getProperty("implicitlyWait"));
            browser = config.getProperty("browser");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public void setUp() {
        setupBrowserDriver();
        loadUrl();


       /* WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
*/
    }

    private void loadUrl() {
        driver.get(url);
    }

    private void setupBrowserDriver() {
        switch (browser) {
            case "chrome":
                driver = DriverFactory.getChromeDriver(implicitlyWait);
                break;
            case "firefox":
                driver = DriverFactory.getFirefoxDriver(implicitlyWait);
                break;
            default:
               // log.error("Unsupported browser!");
                throw new IllegalStateException("Unsupported browser!");
        }
    }


    @AfterTest
    public void tearDown() {
        driver.quit();

    }
}
