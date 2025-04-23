package com.demowebshop.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseHelper {
    WebDriver driver;


    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementLocator(By locator){
        return driver.findElements(locator).size()>0;
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();

    }

    public boolean isElementPresent(By locator) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        boolean result = !driver.findElements(locator).isEmpty();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return result;
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement waitForElementVisible(By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));

    }
}
