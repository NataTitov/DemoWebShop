package com.webshop.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase {
    @Test
    public void newUserRegistrationPositiveTest() {
        //click on Login Link
        driver.findElement(By.cssSelector("[href='/register']")).click();

        //check Gender
        driver.findElement(By.xpath("//label[.='Female']")).click();

        //enter First name, Last name, Email to fields
        //click, ochishenie, dannie
        driver.findElement(By.name("FirstName")).click();
        driver.findElement(By.name("FirstName")).clear();
        driver.findElement(By.name("FirstName")).sendKeys("Natalia");

        driver.findElement(By.name("LastName")).click();
        driver.findElement(By.name("LastName")).clear();
        driver.findElement(By.name("LastName")).sendKeys("Titov");

        driver.findElement(By.id("Email")).click();
        driver.findElement(By.id("Email")).clear();
        driver.findElement(By.id("Email")).sendKeys("pataha@gmx.de");

        //enter Password to password field
        driver.findElement(By.id("Password")).click();
        driver.findElement(By.id("Password")).clear();
        driver.findElement(By.id("Password")).sendKeys("vgBH123456!");

        driver.findElement(By.id("ConfirmPassword")).click();
        driver.findElement(By.id("ConfirmPassword")).clear();
        driver.findElement(By.id("ConfirmPassword")).sendKeys("vgBH123456!");

        // click on Registration button
        driver.findElement(By.name("register-button")).click();

        //verify SingOut Link is displayed
        Assert.assertTrue(isElementLocator(By.xpath("//a[@href='/logout']")));
    }
}
