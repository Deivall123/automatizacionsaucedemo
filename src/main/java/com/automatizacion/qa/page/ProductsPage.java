package com.automatizacion.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage extends BasePage {

    private final By productNames = By.className("inventory_item_name");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartButton = By.className("shopping_cart_link");

    public ProductDetailsPage openProductByName(String name) {
        List<WebElement> products = driver.findElements(productNames);

        for (WebElement product : products) {
            if (product.getText().trim().equalsIgnoreCase(name)) {
                product.click();
                return new ProductDetailsPage();
            }
        }

        throw new RuntimeException("Producto no encontrado: " + name);
    }

    public int getCartQuantity() {
        try {
            String value = driver.findElement(cartBadge).getText();
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean waitForCartQuantity(int expectedQty, int timeoutSeconds) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                    .until(d -> {
                        try {
                            String text = d.findElement(cartBadge).getText();
                            return Integer.parseInt(text) == expectedQty;
                        } catch (Exception ex) {
                            return false;
                        }
                    });
        } catch (TimeoutException e) {
            return false;
        }
    }
    public CartPage openCart() {
        click(cartButton);
        return new CartPage();
    }

}