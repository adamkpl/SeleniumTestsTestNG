package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.testdata.TestData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CreateAccountNegativeTest extends BaseTestCase {

    @DataProvider(name = "usernameData-provider")
    public Object[][] testData() {
        return new Object[][]{
                {TestData.ACCT_EMAIL}
                , {TestData.ACCT_EMAIL_UPPER_CASE}
                , {TestData.ACCT_EMAIL_INVALID}
                , {TestData.ACCT_EMAIL_INVALID_EMPTY}};
    }

    @Test (dataProvider = "usernameData-provider")
    public void shouldNotRegisterAccount(String username) {
        AccountSignInPage accountSignInPage = new AccountSignInPage(driver);
        MyAccount myAccount = new MyAccount(driver);

        // Given
        accountSignInPage
                .navigateToAccountSignInPage()

        // When
                .createAnAccount()
                    .setEmailAddress(username)
                .clickCreateAccountButton();

        // Then
        //TakeScreenshotWrapper.takeScreenshot(driver, "shouldNotRegisterAccount_" + username + "_.png");
        assertTrue(myAccount.isAuthorizationErrorMessageDisplayed());
    }

}