package Tests;

import base.BaseTest;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import Pages.LoginPage;
import utils.DataDriven;

public class LoginTest extends BaseTest {

    JSONObject data = DataDriven.jsonReader();

    @Test
    public void successfulLogin() {
        LoginPage login = new LoginPage(driver);

        login.enterUsername(data.getString("validUser"));
        login.enterPassword(data.getString("validPass"));
        login.clickLogin();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    public void invalidLogin() {
        LoginPage login = new LoginPage(driver);

        login.enterUsername(data.getString("invalidUser"));
        login.enterPassword(data.getString("invalidPass"));
        login.clickLogin();

        Assert.assertTrue(login.getErrorMessage()
                .contains("Username and password do not match"));
    }

    @Test
    public void loginWithoutPassword() {
        LoginPage login = new LoginPage(driver);

        login.enterUsername(data.getString("validUser"));
        login.clickLogin();

        Assert.assertTrue(login.getErrorMessage()
                .contains("Password is required"));
    }
}
