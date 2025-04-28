package com.demowebshop.fw;

import com.demowebshop.utils.MyListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class ApplicationManager{

    String browser;
    WebDriver driver;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    UserHelper user;
    ProductHelper product;
    HomePageHelper home;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }


    public void init() {
        if(browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
            logger.info("Test start in Chrome browser");
        }else if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
            logger.info("Test start in Firefox browser");
        }else if(browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
            logger.info("Test start in Edge browser");
        }
        WebDriverListener listener = new MyListener();
        driver = new EventFiringDecorator<>(listener).decorate(driver);

        driver.get("https://demowebshop.tricentis.com/");
        logger.info("Current url -> " + driver.getCurrentUrl());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        user = new UserHelper(driver);
        product = new ProductHelper(driver);
        home = new HomePageHelper(driver);
    }

    public void stop() {
        driver.quit();
    }

    public UserHelper getUser() {
        return user;
    }

    public ProductHelper getProduct() {
        return product;
    }

    public HomePageHelper getHome() {
        return home;
    }
}
