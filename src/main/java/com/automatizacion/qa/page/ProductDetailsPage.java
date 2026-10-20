package com.automatizacion.qa.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage extends BasePage {

    private final By productTitle = By.className("inventory_details_name");
    private final By addToCartButton = By.xpath("//button[contains(text(),'Add to cart')]");
    private final By removeButton = By.xpath("//button[contains(text(),'Remove')]");

    public String getProductTitle() {
        return getText(productTitle);
    }

    public void addToCart() {
        WebElement button = waitForElementClickable(addToCartButton, 10);
        if (button != null) {
            button.click();
        } else {
            throw new RuntimeException("Botón Add to cart no disponible");
        }
    }

    public boolean waitForRemoveVisible(int timeoutSeconds) {
        WebElement element = waitForElement(removeButton, timeoutSeconds);
        return element != null && element.isDisplayed();
    }

    public boolean isRemoveVisible() {
        try {
            return driver.findElement(removeButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}