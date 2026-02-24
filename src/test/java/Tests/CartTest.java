package Tests;

import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import base.BaseTest;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DataDriven;

public class CartTest extends BaseTest {

    JSONObject data = DataDriven.jsonReader();

    // ===============================
    // Reusable Login Method
    // ===============================
    private void login() {
        LoginPage login = new LoginPage(driver);
        login.enterUsername(data.getString("validUser"));
        login.enterPassword(data.getString("validPass"));
        login.clickLogin();
    }

    // ===============================
    // Helper: Switch To New Window
    // ===============================
    private void switchToNewWindow(String mainWindow) {

        for (int i = 0; i < 5; i++) {  // small retry loop
            for (String window : driver.getWindowHandles()) {
                if (!window.equals(mainWindow)) {
                    driver.switchTo().window(window);
                    return;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean waitForNewWindow(String mainWindow) {

        for (int i = 0; i < 5; i++) {
            if (driver.getWindowHandles().size() > 1) {
                for (String window : driver.getWindowHandles()) {
                    if (!window.equals(mainWindow)) {
                        driver.switchTo().window(window);
                        return true;
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    private void closeNewWindowAndReturn(String mainWindow) {
        driver.close();
        driver.switchTo().window(mainWindow);
    }
    // ===============================
    // 1️⃣ Verify Social Links
    // ===============================
    @Test

    public void verifySocialLinks() {

        login();
        InventoryPage inventory = new InventoryPage(driver);

        String mainWindow = driver.getWindowHandle();

        // ===== LinkedIn =====
        inventory.clickLinkedIn();
        Assert.assertTrue(waitForNewWindow(mainWindow));
        closeNewWindowAndReturn(mainWindow);

        // ===== Facebook =====
        inventory.clickFacebook();
        Assert.assertTrue(waitForNewWindow(mainWindow));
        closeNewWindowAndReturn(mainWindow);

        // ===== Twitter =====
        inventory.clickTwitter();
        Assert.assertTrue(waitForNewWindow(mainWindow));
        closeNewWindowAndReturn(mainWindow);
    }
    // ===============================
    // 2️⃣ Verify Cart Is Empty
    // ===============================
    @Test
    public void verifyCartIsEmpty() {

        login();

        InventoryPage inventory = new InventoryPage(driver);
        inventory.openCart();

        CartPage cart = new CartPage(driver);
        Assert.assertEquals(cart.getCartItemsCount(), 0);
    }

    // ===============================
    // 3️⃣ Add 3 Specific Products
    // ===============================
    @Test
    public void addThreeProducts() {

        login();

        InventoryPage inventory = new InventoryPage(driver);

        inventory.addProductByName("Sauce Labs Backpack");
        inventory.addProductByName("Sauce Labs Bolt T-Shirt");
        inventory.addProductByName("Sauce Labs Onesie");

        inventory.openCart();

        CartPage cart = new CartPage(driver);
        Assert.assertEquals(cart.getCartItemsCount(), 3);
    }

    // ===============================
    // 4️⃣ Remove One Product
    // ===============================
    @Test
    public void removeOneProduct() {

        login();

        InventoryPage inventory = new InventoryPage(driver);

        inventory.addProductByName("Sauce Labs Backpack");
        inventory.addProductByName("Sauce Labs Bolt T-Shirt");
        inventory.addProductByName("Sauce Labs Onesie");

        inventory.openCart();

        CartPage cart = new CartPage(driver);
        cart.removeBoltTShirt();

        cart.clickContinueShopping();

        Assert.assertEquals(
                inventory.getButtonText("Sauce Labs Bolt T-Shirt"),
                "Add to cart"
        );

        Assert.assertEquals(
                inventory.getButtonText("Sauce Labs Backpack"),
                "Remove"
        );

        Assert.assertEquals(
                inventory.getButtonText("Sauce Labs Onesie"),
                "Remove"
        );
    }
}