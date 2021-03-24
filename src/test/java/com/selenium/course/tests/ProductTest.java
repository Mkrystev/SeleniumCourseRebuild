package com.selenium.course.tests;

import com.selenium.course.base.TestUtil;
import com.selenium.course.pages.LoginPage;
import com.selenium.course.pages.ProductListerPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductTest extends TestUtil {

    @Test
    public void executeProductTest(){
        LoginPage loginPage = new LoginPage(driver);
        ProductListerPage productListerPage = loginPage.login("standard_user", "secret_sauce");
        Reporter.log("Login is successful!");
        productListerPage.addToCart("Sauce Labs Backpack");

        //Hard Assert
       // Assert.assertEquals(productListerPage.getProductPrice("Sauce Labs Backpack"), "$19.99");

        //Soft Assert
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productListerPage.getProductPrice("Sauce Labs Backpack"), "$19.99");
        softAssert.assertEquals(productListerPage.getProductPrice("Sauce Labs Backpack"), "$29.99");
        softAssert.assertEquals(productListerPage.getProductPrice("Sauce Labs Backpack"), "$28.99");

        softAssert.assertAll();
    }


}
