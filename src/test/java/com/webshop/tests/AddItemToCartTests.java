package com.webshop.tests;

import com.demowebshop.models.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

public class AddItemToCartTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnLogOutLink();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillRegisterLoginForm(app.getUser().userLogin);
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addFirstProductToCartPositiveTest() {
        app.getProduct().clearCart();
        Product laptop = app.getProduct().getLaptop();
        app.getProduct().addLaptopToCart();
        app.getProduct().openCart();
        Assert.assertTrue(app.getProduct().isProductInCart(laptop));
    }

    @Test
    public void addNextItemToCartPositiveTest() {
        app.getProduct().clearCart();
        Product computer = app.getProduct().getComputer();
        app.getProduct().addComputerToCart();
        app.getProduct().openCart();
        Assert.assertTrue(app.getProduct().isProductInCart(computer));
    }

    @Test
    public void addTwoItemsToCartPositiveTest() {
        app.getProduct().clearCart();
        Product laptop = app.getProduct().getLaptop();
        Product computer = app.getProduct().getComputer();
        List<Product> products = List.of(laptop, computer);
        app.getProduct().addLaptopToCart();
        app.getProduct().toHomePage();
        app.getProduct().addComputerToCart();
        app.getProduct().openCart();
        Assert.assertTrue(app.getProduct().isProductsInCart(products));
    }

}
