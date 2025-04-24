package com.demowebshop.fw;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseHelper {
    WebDriver driver;


    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementLocator(By locator) {
        return driver.findElements(locator).size() > 0;
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

    public WebElement waitForElementVisible(By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public String takeScreenshot() {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("screenshots/screen - " + System.currentTimeMillis() + ".png");
        try {
            Files.copy(tmp, screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenshot.getAbsolutePath();
    }
}
