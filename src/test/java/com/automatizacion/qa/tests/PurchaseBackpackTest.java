package com.automatizacion.qa.tests;

import com.automatizacion.qa.page.CartPage;
import com.automatizacion.qa.page.CheckoutStepOnePage;
import com.automatizacion.qa.page.LoginPage;
import com.automatizacion.qa.page.ProductDetailsPage;
import com.automatizacion.qa.page.ProductsPage;
import com.automatizacion.qa.utils.DriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class PurchaseBackpackTest {

    static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = DriverFactory.getDriver();
    }

    @Test
    public void addBackpackToCartAndGoToCheckout() {
        String username = "standard_user";
        String password = "secret_sauce";
        String productName = "Sauce Labs Backpack";

        LoginPage loginPage = new LoginPage();
        loginPage.open();
        pauseForSeconds(2);

        ProductsPage productsPage = loginPage.loginValidUser(username, password);
        pauseForSeconds(2);

        ProductDetailsPage detail = productsPage.openProductByName(productName);
        pauseForSeconds(2);

        String title = detail.getProductTitle();
        Assertions.assertEquals(productName, title, "El título del producto no coincide");

        detail.addToCart();
        pauseForSeconds(2);

        CartPage cartPage = productsPage.openCart();
        pauseForSeconds(2);

        boolean itemPresent = cartPage.isProductInCart(productName);
        Assertions.assertTrue(itemPresent, "El producto no está en el carrito");

        CheckoutStepOnePage checkoutPage = cartPage.clickCheckout();
        pauseForSeconds(2);

        boolean correctPage = checkoutPage.isCheckoutYourInformationVisible();
        Assertions.assertTrue(correctPage, "No se llegó a Checkout: Your Information");
    }

    @AfterAll
    public static void tearDown() {
        DriverFactory.quitDriver();
    }

    private void pauseForSeconds(int seconds) {
        if (Boolean.getBoolean("debugPause")) {
            try {
                Thread.sleep(seconds * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}