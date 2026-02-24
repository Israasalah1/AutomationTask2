package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By cartItems = By.className("cart_item");
    By removeBoltShirt = By.id("remove-sauce-labs-bolt-t-shirt");
    By continueShoppingBtn = By.id("continue-shopping");

    // Actions

    public int getCartItemsCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeBoltTShirt() {
        driver.findElement(removeBoltShirt).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingBtn).click();
    }
}