package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MainPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.testdata.TestData;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SignInNegativeTest extends BaseTestCase {

    @DataProvider(name = "credentialsData-provider")
    public Object[][] testData() {
        return new Object[][]{
                {TestData.ACCT_EMAIL_INVALID, TestData.ACCT_PASSWORD_INVALID}
                , {TestData.ACCT_EMAIL_INVALID, TestData.ACCT_PASSWORD_INVALID_LESS_THAN_MINIMUM_REQUIRED}
                , {TestData.ACCT_EMAIL, TestData.ACCT_PASSWORD_INVALID_EMPTY}
                , {TestData.ACCT_EMAIL_INVALID_EMPTY, TestData.ACCT_PASSWORD_VALID}
                , {TestData.ACCT_EMAIL_INVALID_EMPTY, TestData.ACCT_PASSWORD_INVALID_EMPTY}
                , {TestData.ACCT_EMAIL_UPPER_CASE, TestData.ACCT_PASSWORD_VALID_UPPER_CASE}};
    }

    @Test (dataProvider = "credentialsData-provider")
    public void shouldNotSignInToAccount(String username, String password) {
        AccountSignInPage accountSignInPage = new AccountSignInPage(driver);
        MyAccount myAccount = new MyAccount(driver);

        // Given
        accountSignInPage
                .navigateToAccountSignInPage()

                // When
                .loginToAccount()
                    .withUsername(username)
                    .withPassword(password)
                .clickSignInButton();

        // Then
        //TakeScreenshotWrapper.takeScreenshot(driver, "shouldNotSignInToAccount_" + username + "_" + password + ".png");
        assertTrue(myAccount.isAuthorizationErrorMessageDisplayed());
    }

}