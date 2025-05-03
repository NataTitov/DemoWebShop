package com.webshop.tests;

import com.demowebshop.data.UserData;
import com.demowebshop.fw.ProductHelper;
import com.demowebshop.models.Product;
import com.demowebshop.models.User;
import com.demowebshop.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddGiftCardToCartTest extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void precondition() {
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnLogOutLink();
        }
        app.getUser().clickOnLoginLink();
        app.getUser().fillRegisterLoginForm(app.getUser().userLogin);
        app.getUser().clickOnLoginButton();
    }
    @Test
    public void isFieldsGiftCardWithLoginUserDataTest() {
        app.getProduct().clearCart();
        app.getProduct().openGiftCardPage();
        Assert.assertTrue(app.getProduct().isFieldsWithLoginUserData());

    }


    @Test(dataProvider = "addGiftCardToCartPositiveTest", dataProviderClass = DataProviders.class)
    public void addGiftCardToCartPositiveTest(String name, String mail, String message) {
        app.getProduct().clearCart();
        Product giftCard = app.getProduct().giftCard();
        app.getProduct().openGiftCardPage();
        app.getProduct().fillRecipientsNameEmailMessage(new User().setName(name).setEmail(mail).setMessage(message));
        app.getProduct().addGiftCardToCart();
        app.getProduct().openCart();
        Assert.assertTrue(app.getProduct().isProductInCart(giftCard));
    }

    @Test(dataProvider = "addGiftCardToCartNegativeTest", dataProviderClass = DataProviders.class)
    public void addGiftCardToCartNegativeTest(String name, String mail, String message) {
        app.getProduct().clearCart();
        app.getProduct().openGiftCardPage();
        app.getProduct().fillRecipientsNameEmailMessage(new User().setName(name).setEmail(mail).setMessage(message));
        app.getProduct().addGiftCardToCart();
        if (name.trim().isEmpty() && mail.trim().isEmpty()){
            softAssert.assertTrue(app.getProduct().isWarningGifCardRecipientFieldsEmptyMessagePresent());
        } else if (mail.trim().isEmpty()){
            softAssert.assertTrue(app.getProduct().isWarningGifCardRecipientEmailMessagePresent());
        } else if (name.trim().isEmpty()){
            softAssert.assertTrue(app.getProduct().isWarningGifCardRecipientNameMessagePresent());
        }
        app.getProduct().openCart();
        softAssert.assertTrue(app.getProduct().isCartEmptyMessagePresent());
        softAssert.assertAll();
    }

}
