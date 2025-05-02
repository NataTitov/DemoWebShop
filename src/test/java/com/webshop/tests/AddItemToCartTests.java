package com.webshop.tests;

import com.demowebshop.models.Product;
import com.demowebshop.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.*;

public class AddItemToCartTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

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

    @Test(dataProvider = "addQuantityProductToCartNegativeTest", dataProviderClass = DataProviders.class)//svyasali dva klassa
    public void addQuantityProductToCartNegativeTestFromDataProviderTest(String quantity) {
        app.getProduct().clearCart();
        app.getProduct().addQuantityProductToCart(quantity);
        softAssert.assertTrue(app.getProduct().isWarningQuantityMessagePresent());
        app.getProduct().openCart();
        softAssert.assertTrue(app.getProduct().isCartEmptyMessagePresent());
        softAssert.assertAll();

    }


}
