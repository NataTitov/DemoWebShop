package com.demowebshop.fw;

import com.demowebshop.data.UserData;
import com.demowebshop.models.Product;
import com.demowebshop.models.User;
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

    public Product giftCard() {
        String firstProductGiftCard = driver.findElement(By.xpath("//h2[@class='product-title']/a[contains(.,'$25 Virtual Gift Card')]")).getText();
        Product giftCard = new Product(firstProductGiftCard);
        return giftCard;
    }

    public void fillRecipientsNameEmailMessage(User user) {
        type(By.id("giftcard_2_RecipientName"), user.getName());
        type(By.id("giftcard_2_RecipientEmail"), user.getEmail());
        type(By.id("giftcard_2_Message"), user.getMessage());

    }

    public void openGiftCardPage() {
        click(By.xpath("//input[contains(@onclick,'catalog/2/1/1')]"));
        waitForElementVisible(By.cssSelector("#add-to-cart-button-2"), 10);


    }

    public void addGiftCardToCart() {
        click(By.id("add-to-cart-button-2"));
        waitForElementVisible(By.cssSelector("span[title='Close']"), 10);
        click(By.cssSelector("span[title='Close']"));
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
        if (!isElementPresent(By.xpath("//div[contains(text(),'Your Shopping Cart is empty!')]"))) {
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

    public boolean isWarningQuantityMessagePresent() {
        return isElementLocator(By.xpath("//p[.='Quantity should be positive']"));
    }

    public boolean isWarningGifCardRecipientNameMessagePresent() {
        return isElementLocator(By.xpath("//p[.='Enter valid recipient name']"));
    }

    public boolean isWarningGifCardRecipientEmailMessagePresent() {
        return isElementLocator(By.xpath("//p[.='Enter valid recipient email']"));

    }

    public boolean isWarningGifCardRecipientFieldsEmptyMessagePresent() {
        if (isElementLocator(By.xpath("//p[.='Enter valid recipient name']")) && isElementLocator(By.xpath("//p[.='Enter valid recipient email']"))) {
            return true;
        }
        return false;
    }

    public boolean isCartEmptyMessagePresent() {
        return isElementPresent(By.xpath("//div[contains(text(),'Your Shopping Cart is empty!')]"));
    }

    public boolean isFieldsWithLoginUserData() {
        String nameLoginGiftCard = driver.findElement(By.id("giftcard_2_SenderName")).getAttribute("value");
        String emailLoginGiftCard = driver.findElement(By.id("giftcard_2_SenderEmail")).getAttribute("value");
        String fieldYourNameInGiftCard = UserData.NAME_LOGIN + " " + UserData.LAST_NAME_LOGIN;
        return fieldYourNameInGiftCard.equals(nameLoginGiftCard) && UserData.EMAIL_LOGIN.equals(emailLoginGiftCard);
    }
}
