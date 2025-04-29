package com.demowebshop.fw;

import com.demowebshop.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductHelper extends BaseHelper {

    public ProductHelper(WebDriver driver) {
        super(driver);
    }

    public void addLaptopToCart() {
        click(By.xpath("//input[contains(@onclick,'catalog/31/1/1')]"));
        waitForElementVisible(By.cssSelector("span[title='Close']"), 10);
        click(By.cssSelector("span[title='Close']"));
    }

    public void openCart() {
        click(By.cssSelector("li[id='topcartlink'] a[class='ico-cart']"));
    }

    public Product getLaptop() {
        String firstProduct = driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(.,'14.1-inch Laptop')]")).getText();
        Product laptop = new Product(firstProduct);
        return laptop;
    }

    public void addComputerToCart() {
        click(By.xpath("//input[contains(@onclick,'catalog/72/1/1')]"));
        waitForElementVisible(By.cssSelector("#add-to-cart-button-72"), 10);
        click(By.cssSelector("#add-to-cart-button-72"));
        waitForElementVisible(By.cssSelector("span[title='Close']"), 10);
        click(By.cssSelector("span[title='Close']"));
    }
    public void addQuantityProductToCart(String quantity) {
        click(By.xpath("//input[contains(@onclick,'catalog/72/1/1')]"));
        waitForElementVisible(By.cssSelector("#add-to-cart-button-72"), 10);
        WebElement quantityInputComputer = driver.findElement(By.id("addtocart_72_EnteredQuantity"));
        quantityInputComputer.clear();
        quantityInputComputer.sendKeys(quantity);
        click(By.cssSelector("#add-to-cart-button-72"));
        waitForElementVisible(By.cssSelector("span[title='Close']"), 10);
        click(By.cssSelector("span[title='Close']"));

    }

    public void clearCart() {
        openCart();
        if(!isElementPresent(By.xpath("//div[contains(text(),'Your Shopping Cart is empty!')]"))) {
            List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@name='removefromcart']"));
            for (WebElement checkbox : checkboxes) {
                checkbox.click();
            }
            click(By.xpath("//input[@name='updatecart']"));
        }
        toHomePage();

    }

    public Product getComputer() {
        String secondProduct = driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(.,'Build your own cheap computer')]")).getText();
        Product computer = new Product(secondProduct);
        return computer;
    }

    public boolean isProductInCart(Product product) {
        List<WebElement> cartItems = driver.findElements(By.xpath("//a[@class='product-name']"));
        for (WebElement cartItem : cartItems) {
            System.out.println(cartItem.getText());
            if (cartItem.getText().equals(product.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public boolean isProductsInCart(List<Product> products) {
        List<WebElement> cartItems = driver.findElements(By.xpath("//a[@class='product-name']"));
        Set<String> cartItemTitles = cartItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toSet());

        Set<String> productTitles = products.stream()
                .map(Product::getTitle)
                .collect(Collectors.toSet());

        return cartItemTitles.containsAll(productTitles);
    }

    public void toHomePage() {
        click(By.cssSelector("a[href='/']"));
    }

    public boolean isWarningMessagePresent() {
        return isElementLocator(By.xpath("//p[.='Quantity should be positive']"));
    }

    public boolean isCartEmptyMessagePresent() {
        return isElementPresent(By.xpath("//div[contains(text(),'Your Shopping Cart is empty!')]"));
    }
}
