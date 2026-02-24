package Tests;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.InventoryPage;
import Pages.LoginPage;
import utils.DataDriven;

public class InventoryTest extends BaseTest {

    JSONObject data = DataDriven.jsonReader();

    @Test
    public void verifyInventoryPage() {

        LoginPage login = new LoginPage(driver);
        login.enterUsername(data.getString("validUser"));
        login.enterPassword(data.getString("validPass"));
        login.clickLogin();

        InventoryPage inventory = new InventoryPage(driver);

        Assert.assertEquals(inventory.getPageTitle(), "Swag Labs");
        Assert.assertTrue(inventory.isCartDisplayed());
        Assert.assertEquals(inventory.getProductsCount(), 6);
    }
}
