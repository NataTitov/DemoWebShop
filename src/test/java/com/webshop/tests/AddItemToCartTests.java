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
        clearCart();
        Product laptop = getLaptop();
        addLaptopToCart();
        openCart();
        Assert.assertTrue(isProductInCart(laptop));
    }

    public void addLaptopToCart() {
        click(By.xpath("//input[contains(@onclick,'catalog/31/1/1')]"));
        pause(1000);
        click(By.cssSelector("span[title='Close']"));
    }

    public void openCart() {
        click(By.cssSelector("li[id='topcartlink'] a[class='ico-cart']"));
    }

    public Product getLaptop() {
        String firstProduct = driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(.,'14.1-inch Laptop')]")).getText();
        Product laptop = new Product(firstProduct);
        return laptop;
    }

    @Test
    public void addNextItemToCartPositiveTest() {
        clearCart();
        Product computer = getComputer();
        addComputerToCart();
        openCart();
        Assert.assertTrue(isProductInCart(computer));
    }

    public void addComputerToCart() {
        click(By.xpath("//input[contains(@onclick,'catalog/72/1/1')]"));
        pause(1000);
        click(By.cssSelector("#add-to-cart-button-72"));
        pause(1000);
        click(By.cssSelector("span[title='Close']"));
    }

    public void clearCart() {
        openCart();
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@name='removefromcart']"));
        for(WebElement checkbox : checkboxes) {
            checkbox.click();
        }
        click(By.xpath("//input[@name='updatecart']"));
        toHomePage();
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
        clearCart();
        Product laptop = getLaptop();
        Product computer = getComputer();
        List<Product> products = List.of(laptop, computer);
        addLaptopToCart();
        toHomePage();
        addComputerToCart();
        openCart();
        Assert.assertTrue(isProductsInCart(products));
    }

    public boolean isProductsInCart(List<Product> products) {
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
    public void toHomePage() {
        click(By.cssSelector("a[href='/']"));
    }

}
