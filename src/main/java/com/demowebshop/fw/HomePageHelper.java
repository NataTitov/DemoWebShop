package com.demowebshop.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageHelper extends BaseHelper{
    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isHomeComponentPresent(){
        return isElementLocator(By.cssSelector("h2.topic-html-content-header"));
    }

    public void clickOnHomeLink() {
        click(By.cssSelector("a[href='/']"));
    }


}
