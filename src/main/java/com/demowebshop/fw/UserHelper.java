package com.demowebshop.fw;

import com.demowebshop.data.UserData;
import com.demowebshop.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends BaseHelper{

    public User userPositivTest = new User()
            .setName(UserData.NAME_REG)
            .setLastName(UserData.LASTNAME_REG)
            .setEmail(UserData.EMAIL_REG)
            .setPassword(UserData.PASSWORD_REG_LOGIN);
    public User userNegativeTest = new User()//repeat EMAIL
            .setName(UserData.NAME_REG_NEGATIVE)
            .setLastName(UserData.LASTNAME_REG_NEGATIVE)
            .setEmail(UserData.EMAIL_REG_NEGATIVE)
            .setPassword(UserData.PASSWORD_REG_LOGIN);
    public User userLogin = new User()
            .setEmail(UserData.EMAIL_LOGIN)
            .setPassword(UserData.PASSWORD_REG_LOGIN);

    public UserHelper(WebDriver driver) {
        super(driver);
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

    public void repeatPassword(User user) {
        type(By.id("ConfirmPassword"), user.getPassword());
    }

    public void fillNameLastName(User user) {
        type(By.id("FirstName"), user.getName());
        type(By.id("LastName"), user.getLastName());
    }

    public void clickOnLogOutLink() {
        click(By.cssSelector(".ico-logout"));
    }

    public boolean isLogOutLinkPresent() {
        return isElementPresent(By.cssSelector(".ico-logout"));
    }

    public boolean isErrorMessagePresent() {
        return isElementLocator(By.cssSelector(".validation-summary-errors"));
    }

    public boolean isLoginLinkPresent() {
        return isElementLocator(By.cssSelector(".ico-login"));
    }
}
