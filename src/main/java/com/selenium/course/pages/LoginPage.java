package com.selenium.course.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    protected WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement userName;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(className = "btn_action")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public ProductListerPage login(String usernameField, String passwordField){
        userName.sendKeys(usernameField);
        password.sendKeys(passwordField);
        loginBtn.click();

        return new ProductListerPage(driver);

    }
}
