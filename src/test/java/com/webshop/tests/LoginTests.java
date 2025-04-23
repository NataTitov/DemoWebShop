package com.webshop.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnLogOutLink();
        }
    }

    @Test
    public void loginPositiveTest() {
        app.getUser().clickOnLoginLink();
        app.getUser().fillRegisterLoginForm(app.getUser().userLogin);
        app.getUser().clickOnLoginButton();
        Assert.assertTrue(app.getUser().isLogOutLinkPresent());
    }

}
