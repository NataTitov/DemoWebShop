package com.webshop.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {

    @Test
    public void newUserRegistrationPositiveTest() {
        clickOnRegisterLink();
        clickOnGender();
        fillNameLastName(userPositivTest);
        fillRegisterLoginForm(userPositivTest);
        repeatPassword(userPositivTest);
        clickOnRegisterButton();
        Assert.assertTrue(isElementLocator(By.xpath("//a[@href='/logout']")));
    }

    @Test
    public void existedUserRegistrationNegativeTest() {
        clickOnRegisterLink();
        clickOnGender();
        fillNameLastName(userNegativeTest);
        fillRegisterLoginForm(userNegativeTest);
        repeatPassword(userNegativeTest);
        clickOnRegisterButton();

        Assert.assertTrue(isElementLocator(By.cssSelector(".validation-summary-errors")));
        Assert.assertFalse(isElementPresent(By.cssSelector(".ico-logout")));
    }

}
