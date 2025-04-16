package com.webshop.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @Test
    public void loginPositiveTest() {
        clickOnLoginLink();
        fillRegisterLoginForm(userLogin);
        clickOnLoginButton();
        Assert.assertTrue(isElementLocator(By.cssSelector(".ico-logout")));
    }

}
