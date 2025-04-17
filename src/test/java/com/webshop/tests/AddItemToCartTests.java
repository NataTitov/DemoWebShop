package com.webshop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

public class AddItemToCartTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        clickOnLoginLink();
        fillRegisterLoginForm(userLogin);
        clickOnLoginButton();
    }

    @Test
    public void addFirstProductToCartPositiveTest() {
        Product laptop = getLaptop();
        click(By.xpath("//input[contains(@onclick,'catalog/31/1/1')]"));
        pause(1000);
        click(By.cssSelector("span[title='Close']"));
        click(By.cssSelector("li[id='topcartlink'] a[class='ico-cart']"));
        Assert.assertTrue(isProductInCart(laptop));
    }

    public Product getLaptop() {
        String firstProduct = driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(.,'14.1-inch Laptop')]")).getText();
        Product laptop = new Product(firstProduct);
        return laptop;
    }

    @Test
    public void addNextItemToCartPositiveTest() {
        Product computer = getComputer();
        click(By.xpath("//input[contains(@onclick,'catalog/72/1/1')]"));
        pause(1000);
        click(By.cssSelector("#add-to-cart-button-72"));
        pause(1000);
        click(By.cssSelector("span[title='Close']"));
        click(By.cssSelector("li[id='topcartlink'] a[class='ico-cart']"));
        Assert.assertTrue(isProductInCart(computer));
    }

    public Product getComputer() {
        String secondProduct = driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(.,'Build your own cheap computer')]")).getText();
        Product computer = new Product(secondProduct);
        return computer;
    }

    public boolean isProductInCart(Product product) {
        List<WebElement> cartItems = driver.findElements(By.xpath("//a[@class='product-name']"));
        for (WebElement cartItem : cartItems) {
            System.out.println(cartItem.getText());
            if (cartItem.getText().equals(product.getTitle())) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void addTwoItemsToCartPositiveTest1() {
        Product laptop = getLaptop();
        Product computer = getComputer();
        List<Product> products = new ArrayList<>(Arrays.asList(laptop, computer));
        click(By.xpath("//input[contains(@onclick,'catalog/31/1/1')]"));
        pause(1000);
        click(By.cssSelector("span[title='Close']"));
        click(By.xpath("//input[contains(@onclick,'catalog/72/1/1')]"));
        pause(1000);
        click(By.cssSelector("#add-to-cart-button-72"));
        pause(1000);
        click(By.cssSelector("span[title='Close']"));
        click(By.cssSelector("li[id='topcartlink'] a[class='ico-cart']"));
        Assert.assertTrue(isProductsInCart(products));
    }

    private boolean isProductsInCart(List<Product> products) {
        List<WebElement> cartItems = driver.findElements(By.xpath("//a[@class='product-name']"));
        Set<String> cartItemTitles = cartItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toSet());

        Set<String> productTitles = products.stream()
                .map(Product::getTitle)
                .collect(Collectors.toSet());

        return cartItemTitles.containsAll(productTitles);
    }


    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
