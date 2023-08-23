package browsertesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;

public class BingTesting {

    private static WebDriver driver;

    // Enum to represent different browser options
    public enum Browser {
        CHROME, FIREFOX
    }
    // Choose the browser to use for testing - Browser.FIREFOX or Browser.CHROME
    // *Note - the first time when you run with Firefox it may give you an error message for the Assertion
    // (I have no idea why, but it slaps the expected and the actual result from the page), but
    // run it again and the tests will pass - magic :)
    private static final Browser chosenBrowser = Browser.CHROME;

    @BeforeAll
    public static void setUp() {
        switch (chosenBrowser) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + chosenBrowser);
        }

        //Timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to Bing
        driver.get("https://www.bing.com");

    }

    @Test
    public void testBingSearch() {

        // Accept cookies if the banner is present
        try {
            WebElement acceptCookiesButton = driver.findElement(By.xpath("//button[@id='bnp_btn_accept']"));
            acceptCookiesButton.click();
        } catch (Exception e) {
            // Ignores if the accept button is not found
        }

        // Find the search input element and enter the search query
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='sb_form_q']"));
        searchInput.sendKeys("Telerik Academy Alpha");
        //Search
        searchInput.submit();

        //Click search button
        //WebElement searchButton = driver.findElement(By.xpath("//label[@id=\"search_icon\"]"));
        //searchButton.click();
        //li[@id="sa_5005"]


        if (chosenBrowser == Browser.CHROME) {
            // Find the first search result's title element
            WebElement firstResultTitle = driver.findElement(By.xpath("//h2[@class=' b_topTitle']"));

            // Get the text of the title element
            String firstResultTitleText = firstResultTitle.getText();

            // Assert the title of the first result
            Assertions.assertEquals(firstResultTitleText, "IT Career Start in 6 Months - Telerik Academy Alpha",
                    "Title of first result is incorrect");
        } else {
            // Find the first search result's title element
            WebElement firstResultTitle = driver.findElement(By.cssSelector("h2 > a[href^='https://www.bing.com']"));

            // Get the text of the title element
            String firstResultTitleText = firstResultTitle.getText();

            // Assert the title of the first result
            Assertions.assertEquals(firstResultTitleText, "IT Career Start in 6 Months - Telerik Academy Alpha",
                    "Title of first result is incorrect");
        }
    }
    @AfterEach
    public void tearDown() {
        //Close browser
        driver.quit();
    }
}
