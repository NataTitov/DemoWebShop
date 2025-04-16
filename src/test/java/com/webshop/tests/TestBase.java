package com.webshop.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestBase {
    WebDriver driver;
    int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
    User userPositivTest = new User()
            .setName("John")
            .setLastName("Snow")
            .setEmail("test" + i + "@test.com")
            .setPassword("ASqw123456!");
    User userNegativeTest = new User()//repeat EMAIL
            .setName("Natalia")
            .setLastName("Titov")
            .setEmail("test101@test.com")
            .setPassword("ASqw123456!");
    User userLogin = new User()
            .setEmail("pataha@gmx.com")
            .setPassword("vgBH123456!");

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        driver.quit();
    }

    public boolean isHomeComponentPresent(){
        return driver.findElements(By.cssSelector("h2.topic-html-content-header")).size()>0;
    }


    public boolean isElementLocator(By locator){
        return driver.findElements(locator).size()>0;
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) { //bil private, rename an public
        driver.findElement(locator).click();
    }


    public void clickOnLoginButton() {
        click(By.cssSelector("input.button-1.login-button"));
    }

    public void fillRegisterLoginForm(User user) {
        type(By.id("Email"), user.getEmail());
        type(By.id("Password"), user.getPassword());
    }

    public void clickOnLoginLink() {
        click(By.cssSelector(".ico-login"));
    }

    public void clickOnRegisterButton() {
        click(By.name("register-button"));
    }

    public void clickOnGender() {
        click(By.xpath("//label[.='Female']"));
    }

    public void clickOnRegisterLink() {
        click(By.cssSelector("[href='/register']"));
    }

//    public boolean isElementPresent(By locator) {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
//        boolean result = isElementLocator(locator);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        return result;
    //eto moy variant, no mozhno sdelat' bolee krasivo
//    }
    public boolean isElementPresent(By locator) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        boolean result = !driver.findElements(locator).isEmpty();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return result;
    }

    public void repeatPassword(User user) {
        type(By.id("ConfirmPassword"), user.getPassword());
    }

    public void fillNameLastName(User user) {
        type(By.id("FirstName"), user.getName());
        type(By.id("LastName"), user.getLastName());
    }
}
