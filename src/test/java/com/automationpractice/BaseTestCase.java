package com.automationpractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.*;

public class BaseTestCase {

    protected WebDriver driver;
    protected Logger logger = Logger.getLogger(BaseTestCase.class);

    @Parameters({"browser"})
    @BeforeClass
    public void setupClass(@Optional("chrome") String browser) {
        DOMConfigurator.configure("log4j.xml");

        switch (browser) {
            case "chrome":
                logger.trace("Initializing" + browser + "driver.");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                logger.trace("Driver " + browser + "initialized.");
                break;

            case "firefox":
                logger.trace("Initializing" + browser + "driver.");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                logger.trace("Driver " + browser + "initialized.");
                break;

            case "edge":
                logger.trace("Initializing" + browser + "driver.");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                logger.trace("Driver " + browser + "initialized.");
                break;

            case "ie":
                logger.trace("Initializing" + browser + "driver.");
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                logger.trace("Driver " + browser + "initialized.");
                break;

            case "opera":
                logger.trace("Initializing" + browser + "driver.");
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
                logger.trace("Driver " + browser + "initialized.");
                break;

            default:
                System.out.println("Do not know how to start: " + browser + ". Launching Chrome by default.");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                logger.trace("Driver " + browser + "initialized.");
                break;
        }
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