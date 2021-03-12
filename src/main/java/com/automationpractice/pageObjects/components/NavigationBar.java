package com.automationpractice.pageObjects.components;

import com.automationpractice.pageObjects.pages.AbstractPageObject;
import com.automationpractice.pageObjects.pages.AccountSignInPage;
import com.automationpractice.pageObjects.utils.WaitWrapper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavigationBar extends AbstractPageObject {

    @FindBy(partialLinkText = "Sign in")
    private WebElement signInLink;

    @FindBy(css = ".header_user_info > a")
    private WebElement username;

    public NavigationBar(WebDriver driver){
        super(driver);
    }

    public AccountSignInPage clickSignInLink(){
        WaitWrapper.waitForElement(driver, signInLink);
        signInLink.click();
        return new AccountSignInPage(driver);
    }

}
