package com.selenium.course.tests;

import com.selenium.course.base.TestUtil;
import com.selenium.course.pages.LoginPage;
import com.selenium.course.pages.ProductListerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends TestUtil {

    @Test
    public void executeProductTest(){

        LoginPage loginPage = new LoginPage(driver);
        ProductListerPage productListerPage = loginPage.login("standard_user","secret_sauce");
        productListerPage.addToCart("Sauce Labs Backpack");


    }
}
