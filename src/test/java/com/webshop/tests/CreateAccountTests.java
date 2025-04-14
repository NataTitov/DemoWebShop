package com.webshop.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {
    int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

    @Test
    public void newUserRegistrationPositiveTest() {
        //click on Register Link
        click(By.cssSelector("[href='/register']"));

        //check Gender
        click(By.xpath("//label[.='Female']"));

        //enter First name, Last name, Email to fields
        type(By.id("FirstName"),"Natalia");
        type(By.id("LastName"),"Titov");
        type(By.id("Email"),"test"+i+"@test.com");

        //enter Password to password field
        type(By.id("Password"),"vgBH123456!");
        type(By.id("ConfirmPassword"),"vgBH123456!");

        // click on Registration button
        click(By.name("register-button"));

        //verify SingOut Link is displayed
        Assert.assertTrue(isElementLocator(By.xpath("//a[@href='/logout']")));
    }

    @Test
    public void existedUserRegistrationNegativeTest() {
        //click on Register Link
        click(By.cssSelector("[href='/register']"));

        //check Gender
        click(By.xpath("//label[.='Female']"));

        //enter First name, Last name, Email to fields
        type(By.id("FirstName"),"Natalia");
        type(By.id("LastName"),"Titov");
        type(By.id("Email"),"pataha@gmx.com");

        //enter Password to password field
        type(By.id("Password"),"vgBH123456!");
        type(By.id("ConfirmPassword"),"vgBH123456!");

        // click on Registration button
        click(By.name("register-button"));

        //verify SingOut Link is displayed
        Assert.assertTrue(isElementLocator(By.cssSelector(".validation-summary-errors")));
        Assert.assertFalse(isElementLocator(By.cssSelector(".ico-logout")));
    }
}
