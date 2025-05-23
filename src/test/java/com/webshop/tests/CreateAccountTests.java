package com.webshop.tests;

import com.demowebshop.data.UserData;
import com.demowebshop.models.User;
import com.demowebshop.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void precondition() {
        if(!app.getUser().isLoginLinkPresent()){
            app.getUser().clickOnLogOutLink();
        }
    }

    @Test(enabled = false)
    public void newUserRegistrationPositiveTest() {
        app.getUser().clickOnRegisterLink();
        app.getUser().clickOnGender(app.getUser().userPositivTest);
        app.getUser().fillNameLastName(app.getUser().userPositivTest);
        app.getUser().fillRegisterLoginForm(app.getUser().userPositivTest);
        app.getUser().repeatPassword(app.getUser().userPositivTest);
        app.getUser().clickOnRegisterButton();
        Assert.assertTrue(app.getUser().isLogOutLinkPresent());
    }

    @Test(dataProvider = "createNewContactWithCsv", dataProviderClass = DataProviders.class)
    public void newUserRegistrationPositiveFromDataProviderWithCsvFileTest(User user) {
        app.getUser().clickOnRegisterLink();
        app.getUser().clickOnGender(user);
        app.getUser().fillNameLastName(user);
        app.getUser().fillRegisterLoginForm(user);
        app.getUser().repeatPassword(user);
        app.getUser().clickOnRegisterButton();
        Assert.assertTrue(app.getUser().isLogOutLinkPresent());
    }



    @Test
    public void existedUserRegistrationNegativeTest() {
        app.getUser().clickOnRegisterLink();
        app.getUser().clickOnGender(app.getUser().userPositivTest);
        app.getUser().fillNameLastName(app.getUser().userNegativeTest);
        app.getUser().fillRegisterLoginForm(app.getUser().userNegativeTest);
        app.getUser().repeatPassword(app.getUser().userNegativeTest);
        app.getUser().clickOnRegisterButton();

        softAssert.assertTrue(app.getUser().isErrorMessagePresent());
        softAssert.assertFalse(app.getUser().isLogOutLinkPresent());
        softAssert.assertAll();
    }


}
