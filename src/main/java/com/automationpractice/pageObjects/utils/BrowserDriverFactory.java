package com.automationpractice.pageObjects.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.apache.log4j.Logger;

public class BrowserDriverFactory {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private String browser;
    private Logger logger = Logger.getLogger(BrowserDriverFactory.class);

    public BrowserDriverFactory(String browser) {
        this.browser = browser.toLowerCase();
    }

    public WebDriver createDriver() {
        switch (browser) {
            case "chrome":
                logger.trace("Initializing" + browser + "driver.");
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                logger.trace("Driver " + browser + "initialized.");
                break;

            case "firefox":
                logger.trace("Initializing" + browser + "driver.");
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                logger.trace("Driver " + browser + "initialized.");
                break;

            case "edge":
                logger.trace("Initializing" + browser + "driver.");
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                logger.trace("Driver " + browser + "initialized.");
                break;

            case "ie":
                logger.trace("Initializing" + browser + "driver.");
                WebDriverManager.iedriver().setup();
                driver.set(new InternetExplorerDriver());
                logger.trace("Driver " + browser + "initialized.");
                break;

            case "opera":
                logger.trace("Initializing" + browser + "driver.");
                WebDriverManager.operadriver().setup();
                driver.set(new OperaDriver());
                logger.trace("Driver " + browser + "initialized.");
                break;

            default:
                System.out.println("Do not know how to start: " + browser + ". Launching Chrome by default.");
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                logger.trace("Driver " + browser + "initialized.");
                break;
        }
        return driver.get();
    }
}
