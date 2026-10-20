package com.automatizacion.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage {

    private final By cartItem = By.className("cart_item");
    private final By checkoutButton = By.id("checkout");
    private final By cartTitle = By.className("title");

    public boolean isProductInCart(String productName) {
        try {
            // Buscar todos los elementos del carrito
            for (WebElement item : driver.findElements(cartItem)) {
                String text = item.getText();
                if (text.contains(productName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public CheckoutStepOnePage clickCheckout() {
        click(checkoutButton);
        return new CheckoutStepOnePage();
    }

    public String getCartTitle() {
        return getText(cartTitle);
    }
}
