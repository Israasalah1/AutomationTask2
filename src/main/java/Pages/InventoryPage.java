package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // ======================
    // Locators
    // ======================

    By cartIcon = By.className("shopping_cart_link");
    By products = By.className("inventory_item");

    // Social Media Icons
    By linkedInIcon = By.cssSelector("a[data-test='social-linkedin']");
    By facebookIcon = By.cssSelector("a[data-test='social-facebook']");
    By twitterIcon = By.cssSelector("a[data-test='social-twitter']");


    // ======================
    // Existing Methods (Part 1)
    // ======================

    public boolean isCartDisplayed() {
        return driver.findElement(cartIcon).isDisplayed();
    }

    public int getProductsCount() {
        return driver.findElements(products).size();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }


    // ======================
    // Social Media Methods (Part 2)
    // ======================

    public void clickLinkedIn() {
        driver.findElement(linkedInIcon).click();
    }

    public void clickFacebook() {
        driver.findElement(facebookIcon).click();
    }

    public void clickTwitter() {
        driver.findElement(twitterIcon).click();
    }


    // ======================
    // Product Methods (Dynamic)
    // ======================

    public void addProductByName(String productName) {
        driver.findElement(
                By.xpath("//div[text()='" + productName +
                        "']/ancestor::div[@class='inventory_item']//button")
        ).click();
    }

    public String getButtonText(String productName) {
        return driver.findElement(
                By.xpath("//div[text()='" + productName +
                        "']/ancestor::div[@class='inventory_item']//button")
        ).getText();
    }
}