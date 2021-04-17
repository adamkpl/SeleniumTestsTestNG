package com.automationpractice;

import com.automationpractice.pageObjects.utils.BrowserDriverFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTestCase {

    protected WebDriver driver;
    protected Logger logger = Logger.getLogger(BaseTestCase.class);

    @Parameters({"browser"})
    @BeforeClass
    public void setupClass(@Optional("chrome") String browser) {
        DOMConfigurator.configure("log4j.xml");
        BrowserDriverFactory browserDriverFactory = new BrowserDriverFactory(browser);
        driver = browserDriverFactory.createDriver();
    }

    @BeforeMethod(alwaysRun = true)
    public void setupTest() {
        logger.trace("Deleting all cookies.");
        driver.manage().deleteAllCookies();
        logger.trace("All cookies deleted.");
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        if (driver != null) {
            /*
            This was commented out due to failures on Firefox.
            logger.trace("Closing the current window.");
            driver.close();
            logger.trace("Closed the current window.");*/
            logger.trace("Kill this driver. Closing every associated window.");
            driver.quit();
            logger.trace("Driver killed, closed every associated window.");
        }
    }

}