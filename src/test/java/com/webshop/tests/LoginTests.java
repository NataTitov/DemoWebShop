package com.webshop.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    @Test
    public void loginPositiveTest() {
        click(By.cssSelector(".ico-login"));
        type(By.id("Email"), "pataha@gmx.com");
        type(By.id("Password"), "vgBH123456!");
        click(By.cssSelector("input.button-1.login-button"));

        Assert.assertTrue(isElementLocator(By.cssSelector(".ico-logout")));
    }


}
