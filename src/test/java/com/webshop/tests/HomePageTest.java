package com.webshop.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
    @BeforeMethod
    public void precondition() {
        if(!app.getHome().isHomeComponentPresent()){
            app.getHome().clickOnHomeLink();
        }
    }



    @Test
    public void isHomeComponentPresentTest() {
        Assert.assertTrue(app.getHome().isHomeComponentPresent());

    }
}
