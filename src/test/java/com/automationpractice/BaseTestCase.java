package com.automationpractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTestCase {

    protected WebDriver driver;
    protected Logger logger = Logger.getLogger(BaseTestCase.class);

    @BeforeClass
    public void setupClass() {
        DOMConfigurator.configure("log4j.xml");
        WebDriverManager.chromedriver().setup();
        logger.trace("Initializing driver.");
        driver = new ChromeDriver();
        logger.trace("Driver initialized.");
    }

    @BeforeMethod
    public void setupTest() {
        logger.trace("Deleting all cookies.");
        driver.manage().deleteAllCookies();
        logger.trace("All cookies deleted.");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            logger.trace("Closing the current window.");
            driver.close();
            logger.trace("Closed the current window.");
            logger.trace("Kill this driver. Closing every associated window.");
            driver.quit();
            logger.trace("Driver killed, closed every associated window.");
        }
    }

}