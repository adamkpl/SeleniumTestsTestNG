package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MainPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.testdata.TestData;
import com.automationpractice.pageObjects.utils.TakeScreenshotWrapper;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SignInTest extends BaseTestCase {

    @Test
    public void shouldLoginToAccount() {
        MainPage mainPage = new MainPage(driver);
        AccountSignInPage accountSignInPage = new AccountSignInPage(driver);
        MyAccount myAccount = new MyAccount(driver);

        // Given
        mainPage
                .navigateToMainPage()
                .selectSignInLink()
                .clickSignInLink();

        // When
        accountSignInPage
                .loginToAccount()
                    .withUsername(TestData.ACCT_EMAIL)
                    .withPassword(TestData.ACCT_PASSWORD_VALID)
                .clickSignInButton();

        // Then
        //TakeScreenshotWrapper.takeScreenshot(driver, "shouldLoginToAccount.png");
        assertTrue(myAccount.isWelcomeMessageDisplayed());
    }

    @Test
    public void shouldFailToLoginToAnExistingAccountWithInvalidPassword() {
        MainPage mainPage = new MainPage(driver);
        AccountSignInPage accountSignInPage = new AccountSignInPage(driver);
        MyAccount myAccount = new MyAccount(driver);

        // Given
        mainPage
                .navigateToMainPage()
                .selectSignInLink()
                .clickSignInLink();

        // When
        accountSignInPage
                .loginToAccount()
                    .withUsername(TestData.ACCT_EMAIL)
                    .withPassword(TestData.ACCT_PASSWORD_INVALID)
                .clickSignInButton();

        // Then
        //TakeScreenshotWrapper.takeScreenshot(driver, "shouldFailToLoginToAnExistingAccountWithInvalidPassword.png");
        assertTrue(myAccount.isAuthorizationErrorMessageDisplayed());
    }

}