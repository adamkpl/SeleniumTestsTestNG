package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MainPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.testdata.TestData;
import com.automationpractice.pageObjects.utils.TakeScreenshotWrapper;
import com.automationpractice.pageObjects.utils.Url;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

/**
 * Create an account
 * Page Object Pattern with "Fluent API"
 * @since 2020-02-07
 * @author Adam K.
 */

public class CreateAccountTest extends BaseTestCase {

    @Test
    public void shouldRegisterAccountWithRequiredFieldsFilledOnlyWithValidInputData() {
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
                .createAnAccount()
                .setRandomEmailAddress()
                .clickCreateAccountButton()
                    .setRandomFirstName()
                    .setRandomLastName()
                    .setRandomPassword()
                    .setRandomAddress()
                    .setRandomCity()
                    .selectRandomState()
                    .setRandomPostcode()
                    .selectRandomCountry()
                    .setRandomMobilePhoneNumber()
                    .setRandomAddressAlias()
                    .clickRegisterButton();

        // Then
        myAccount
                .getWelcomeMessage();
                TakeScreenshotWrapper.takeScreenshot(driver, "RegisterAccountMinimum_Success.png");
                AssertJUnit.assertEquals("URL = myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());
    }

    @Test
    public void shouldRegisterAccountWithAllFieldsFilledWithValidInputData() {
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
                .createAnAccount()
                .setRandomEmailAddress()
                .clickCreateAccountButton()
                    .setRandomGender()
                    .setRandomFirstName()
                    .setRandomLastName()
                    .setRandomPassword()
                    .selectRandomDayOfBirth()
                    .selectRandomMonthOfBirth()
                    .selectRandomYearOfBirth()
                    .checkNewsletter()
                    .checkSpecialOffers()
                    .setRandomCompany()
                    .setRandomAddress()
                    .setRandomCity()
                    .selectRandomState()
                    .setRandomPostcode()
                    .selectRandomCountry()
                    .setRandomAdditionalInformation()
                    .setRandomPhoneNumber()
                    .setRandomMobilePhoneNumber()
                    .setRandomAddressAlias()
                    .clickRegisterButton();

        // Then
        myAccount
                .getWelcomeMessage();
                TakeScreenshotWrapper.takeScreenshot(driver, "RegisterAccountMaximum_Success.png");
                AssertJUnit.assertEquals("URL = myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());
    }

    @Test
    public void shouldNotRegisterAccountForAnExistingAccount() {
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
                .createAnAccount()
                .setEmailAddress(TestData.ACCT_EMAIL)
                .clickCreateAccountButton();

        // Then
        myAccount
                .getAuthErrorMessage();
                TakeScreenshotWrapper.takeScreenshot(driver, "CreateAccountFailAnExistingAccount.png");
                AssertJUnit.assertNotSame("URL != myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());
    }


    @Test
    public void shouldNotRegisterAccountForAnInvalidEmailAddress() {
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
                .createAnAccount()
                .setEmailAddress(TestData.ACCT_EMAIL_INVALID)
                .clickCreateAccountButton();

        // Then
        myAccount
                .getAuthErrorMessage();
                TakeScreenshotWrapper.takeScreenshot(driver, "CreateAccountFailInvalidEmailAddress.png");
                AssertJUnit.assertNotSame("URL != myAccount", Url.MY_ACCOUNT, driver.getCurrentUrl());
    }

}