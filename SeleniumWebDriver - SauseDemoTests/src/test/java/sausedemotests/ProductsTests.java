package sausedemotests;

import core.BaseTest;
import org.example.BrowserTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Formatter;
import java.util.Locale;

public class ProductsTests extends BaseTest {

    @BeforeAll
    public static void beforeAllTests(){
        driver = startBrowser(BrowserTypes.CHROME);

        // Configure wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Navigate to Google.com
        driver.get("https://www.saucedemo.com/");

        authenticateWithUser("standard_user", "secret_sauce");
    }

    @Test
    public void productAddedToShoppingCart_when_addToCart(){
        String backpackTitle = "Sauce Labs Backpack";
        String shirtTitle = "Sauce Labs Bolt T-Shirt";

        WebElement backpack = getProductByTitle(backpackTitle);
        backpack.findElement(By.className("btn_inventory")).click();

        var tshirt = getProductByTitle(shirtTitle);
        tshirt.findElement(By.className("btn_inventory")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        // Assert Items and Totals
        var items = driver.findElements(By.className("inventory_item_name"));

        Assertions.assertEquals(2, items.size(), "Items count not as expected");

        Assertions.assertEquals(backpackTitle, items.get(0).getText(), "Item title not as expected");
        Assertions.assertEquals(shirtTitle, items.get(1).getText(), "Item title not as expected");
    }


    @Test
    public void userDetailsAdded_when_checkoutWithValidInformation(){
        String backpackTitle = "Sauce Labs Backpack";
        String shirtTitle = "Sauce Labs Bolt T-Shirt";

        WebElement backpack = getProductByTitle(backpackTitle);
        backpack.findElement(By.className("btn_inventory")).click();

        var tshirt = getProductByTitle(shirtTitle);
        tshirt.findElement(By.className("btn_inventory")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        // Assert Items and Totals
        driver.findElement(By.id("checkout")).click();

        // fill form

        driver.findElement(By.id("first-name")).sendKeys("First Name");
        driver.findElement(By.id("last-name")).sendKeys("Last Name");
        driver.findElement(By.id("postal-code")).sendKeys("ZIP Code");
        //fillShippingDetails("Fname", "lname", "zip");

        driver.findElement(By.id("continue")).click();

        var items = driver.findElements(By.className("inventory_item_name"));
        var total = driver.findElement(By.className("summary_total_label")).getText();

        double expectedPrice = 29.99 + 15.99 + 3.68;


        String expectedTotal = String.valueOf(new Formatter(Locale.US).format("Total: $%.2f",expectedPrice));

        Assertions.assertEquals(2, items.size(), "Items count not as expected");
        Assertions.assertEquals(backpackTitle, items.get(0).getText(), "Item title not as expected");
        Assertions.assertEquals(shirtTitle, items.get(1).getText(), "Item title not as expected");
        Assertions.assertEquals(expectedTotal, total, "Items total price not as expected");
    }


    @Test
    public void orderCompleted_when_addProduct_and_checkout_withConfirm() {
        String backpackTitle = "Sauce Labs Backpack";
        String shirtTitle = "Sauce Labs Bolt T-Shirt";

        // Add Backpack and T-shirt to shopping cart
        WebElement backpack = getProductByTitle(backpackTitle);
        backpack.findElement(By.className("btn_inventory")).click();

        var tshirt = getProductByTitle(shirtTitle);
        tshirt.findElement(By.className("btn_inventory")).click();

        // Click on shopping Cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // Fill Contact Details
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("First Name");
        driver.findElement(By.id("last-name")).sendKeys("Last Name");
        driver.findElement(By.id("postal-code")).sendKeys("ZIP Code");

        // Complete Order (Assuming there's a button or action for this)
        driver.findElement(By.xpath("//input[@data-test='continue']"));

        // Assert Items removed from Shopping Cart (You may need to navigate back to the cart page)
        driver.get("https://www.saucedemo.com/cart");
        var cartItems = driver.findElements(By.xpath("//button[@id='finish']"));
        Assertions.assertEquals(0, cartItems.size(), "Cart should be empty after completing the order");
    }
}