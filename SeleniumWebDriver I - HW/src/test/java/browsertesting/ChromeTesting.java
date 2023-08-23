package browsertesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class ChromeTesting {

    private static WebDriver driver;

    // Enum to represent different browser options
    public enum Browser {
        CHROME
    }

    // Choose the browser to use for testing
    private static final Browser chosenBrowser = Browser.CHROME;

    @BeforeAll
    public static void setUp() {
        switch (chosenBrowser) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + chosenBrowser);
        }

        //Timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to Google
        driver.get("https://www.google.com");

    }

    @Test
    public void testBingSearch() {

        // Accept cookies if the banner is present
        try {
            WebElement acceptCookiesButton = driver.findElement(By.xpath("//button[@id='L2AGLb']"));
            acceptCookiesButton.click();
        } catch (Exception e) {
            // Ignore if the accept button is not found
        }

        // Find the search input element and enter the search query
        WebElement searchInput = driver.findElement(By.xpath("//textarea[@type='search']"));
        searchInput.sendKeys("Telerik Academy Alpha");
        //Search
        searchInput.submit();

        //Click search button -
        //WebElement searchButton = driver.findElement(By.xpath("//label[@id=\"search_icon\"]"));
        //searchButton.click();
        //li[@id="sa_5005"]

        // Find the first search result's title element
        WebElement firstResultTitle = driver.findElement(By.xpath("//h3[@class='LC20lb MBeuO DKV0Md']"));

        // Get the text of the title element
        String firstResultTitleText = firstResultTitle.getText();

        // Assert the title of the first result
        Assertions.assertEquals(firstResultTitleText, "IT Career Start in 6 Months - Telerik Academy Alpha",
                    "Title of first result is incorrect");
    }
    @AfterEach
     public void tearDown() {
     //Close browser
        driver.quit();
    }
}