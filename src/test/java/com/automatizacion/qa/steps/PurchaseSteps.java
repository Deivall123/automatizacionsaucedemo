package com.automatizacion.qa.steps;

import com.automatizacion.qa.page.LoginPage;
import com.automatizacion.qa.page.ProductDetailsPage;
import com.automatizacion.qa.page.ProductsPage;
import com.automatizacion.qa.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;

public class PurchaseSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private ProductDetailsPage productDetails;

    @Before
    public void setUp() {
        driver = DriverFactory.getDriver();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Given("I open the login page")
    public void openLoginPage() {
        loginPage = new LoginPage();
        loginPage.open();
    }

    @When("I login as {string} with password {string}")
    public void login(String username, String password) {
        productsPage = loginPage.loginValidUser(username, password);
    }

    @When("I open the product {string}")
    public void openProduct(String productName) {
        productDetails = productsPage.openProductByName(productName);
    }

    @Then("I should see the product page with title {string}")
    public void verifyProductTitle(String expectedTitle) {
        String actual = productDetails.getProductTitle();
        Assertions.assertEquals(expectedTitle, actual);
    }

    @When("I add the product to the cart")
    public void addToCart() {
        productDetails.addToCart();
        boolean removedVisible = productDetails.waitForRemoveVisible(8);
        Assertions.assertTrue(removedVisible, "Remove button did not appear after adding to cart");
    }

    @Then("the cart quantity should be {int}")
    public void verifyCartQuantity(Integer expectedQty) {
        // volver a listado de productos y verificar badge
        driver.get("https://www.saucedemo.com/inventory.html");
        boolean ok = productsPage.waitForCartQuantity(expectedQty, 8);
        Assertions.assertTrue(ok, "Cart quantity is not " + expectedQty);
    }
}
