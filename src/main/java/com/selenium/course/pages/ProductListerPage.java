package com.selenium.course.pages;

import org.jsoup.Connection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductListerPage extends BasePage {

    private static final String ADD_TO_CART_BTN_BY_PRODUCT_NAME = "//div[text()='%s']//ancestor::div[@class='inventory_item']//button";
    private static final String PRODUCT_PRICE_BY_NAME = "//div[text()='%s']//ancestor::div[@class='inventory_item']//div[@class='inventory_item_price']";

    public ProductListerPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(String productName) {
        String xpathFormatted = String.format(ADD_TO_CART_BTN_BY_PRODUCT_NAME, productName);
        WebElement addToCartBtn = driver.findElement(By.xpath(xpathFormatted));
        addToCartBtn.click();
    }

}

