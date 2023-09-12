package com.telerikacademy.testframework;

import org.asynchttpclient.util.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




import java.time.Duration;
import java.util.Properties;

public class UserActionNonImplemented {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserActionNonImplemented.class);
    private final Driver driver;

    public UserActionNonImplemented(Driver driver) {
        this.driver = driver;
    }

    public void hoverElement(String key, Object... arguments) {
        // 1. Get locator value from properties by key
        Properties uiMappings = PropertiesManager.PropertiesManagerEnum.INSTANCE.getUiMappings();
        String locator = uiMappings.getProperty(key);

        // 2. Add Log entry for the action to be performed
        // Add your logging logic here
        // For example:
        System.out.println("Hovering over element with key: " + key);

        // 3. Perform a hover Action
        WebElement element = driver.findElement(By.xpath(locator)); // Use the appropriate locator strategy (e.g., XPath)
        Actions actions = new Actions(driver.getWebDriver()); // Initialize Actions with your WebDriver instance
        actions.moveToElement(element).build().perform();
    }

    public void switchToIFrame(String iframe) {
        // TODO: Implement the method
        // 1. Get iframe locator value from properties by key
        // 2. Add Log entry for the action to be performed
        // 3. Switch to the frame

        // Get iframe locator value from properties by key
        Properties uiMappings = PropertiesManager.PropertiesManagerEnum.INSTANCE.getUiMappings();
        String iframeLocator = uiMappings.getProperty(iframe);

        // Add Log entry for the action to be performed
        System.out.println("Switching to iframe with key: " + iframe);

        // Switch to the frame
        driver.switchTo().frame(iframeLocator);
    }

    public boolean isElementPresent(String locator, Object... arguments) {
        // TODO: Implement the method
        // 1. Get default timeout from properties
        // 2. Initialize Wait utility
        // 3. Try to wait for element present
        // 4. return true/false if the element is/not present
        // Get default timeout from properties
        // Get default timeout from properties
        int defaultTimeoutSeconds = Integer.parseInt(PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("defaultTimeoutSeconds"));

        // Convert it to Duration
        Duration defaultTimeout = Duration.ofSeconds(defaultTimeoutSeconds);

        // Initialize Wait utility
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);

        // Try to wait for element present
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))); // Use the appropriate locator strategy
            return true; // Element is present
        } catch (TimeoutException e) {
            return false; // Element is not present within the specified timeout
        }
    }

    public boolean isElementVisible(String locator, Object... arguments) {
        // TODO: Implement the method
        // 1. Get default timeout from properties
        // 2. Initialize Wait utility
        // 3. Try to wait for element visible
        // 4. return true/false if the element is/not visible
        // Get default timeout from properties
        int defaultTimeoutSeconds = Integer.parseInt(PropertiesManager.PropertiesManagerEnum.INSTANCE.getConfigProperties().getProperty("defaultTimeoutSeconds"));

        // Convert it to Duration
        Duration defaultTimeout = Duration.ofSeconds(defaultTimeoutSeconds);

        // Initialize Wait utility
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);

        // Try to wait for element to be visible
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))); // Use the appropriate locator strategy
            return element.isDisplayed(); // Check if the element is visible
        } catch (TimeoutException e) {
            return false; // Element is not visible within the specified timeout
        }
    }

    public void waitFor(long timeOutMilliseconds) {
        try {
            Thread.sleep(timeOutMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //############# ASSERTS #########
    public void assertNavigatedUrl(String urlKey) {
        // TODO: Implement the method
        // 1. Get Current URL
        // 2. Get expected url by urlKey from Properties

        // Get Current URL
        // Get Current URL
        // Get Current URL
        // Get Current URL
        String currentUrl = driver.getCurrentUrl();

        // Get expected URL by `urlKey` from Properties
        String expectedUrl = PropertiesManager.PropertiesManagerEnum.INSTANCE.getUiMappings().getProperty(urlKey);

        // Check if the current URL matches the expected URL
        if (currentUrl.equals(expectedUrl)) {
            LOGGER.info("URL Assertion Passed: Current URL matches the expected URL.");
        } else {
            LOGGER.error("URL Assertion Failed: Current URL does not match the expected URL.");
            throw new AssertionError("URL Assertion Failed: Current URL does not match the expected URL.");
        }
    }

    public void pressKey(Keys key) {
        // TODO: Implement the method
        // Initialize Actions
        Actions actions = new Actions(driver);

        // Perform key press
        actions.sendKeys(key).perform();
    }

    public String getElementAttribute(String locator, String attributeName) {
        // TODO: Implement the method
        // 1. Find Element using the locator value from Properties
        // 2. Get the element attribute
        // 3. Return the expected value
        // Find Element using the locator value from Properties
        WebElement element = driver.findElement(By.xpath(locator)); // Use the appropriate locator strategy (e.g., XPath)

        // Return the expected value directly
        return element.getAttribute(attributeName);
    }
}
