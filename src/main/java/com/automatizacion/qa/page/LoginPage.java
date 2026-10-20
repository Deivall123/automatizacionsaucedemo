package com.automatizacion.qa.page;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage open() {
        driver.get("https://www.saucedemo.com/");
        return this;
    }

    public ProductsPage loginValidUser(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
        return new ProductsPage();
    }
}
