package com.automationpractice;

import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.pages.MainPage;
import com.automationpractice.pageObjects.pages.MyAccount;
import com.automationpractice.pageObjects.utils.TakeScreenshotWrapper;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CreateAccountPositiveTest extends BaseTestCase {

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
        //TakeScreenshotWrapper.takeScreenshot(driver, "RegisterAccountMinimum_Success.png");
        assertTrue(myAccount.isWelcomeMessageDisplayed());
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
        //TakeScreenshotWrapper.takeScreenshot(driver, "shouldRegisterAccountWithAllFieldsFilledWithValidInputData.png");
        assertTrue(myAccount.isWelcomeMessageDisplayed());
    }

}