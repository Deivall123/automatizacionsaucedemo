package com.automatizacion.qa.page;

import org.openqa.selenium.By;

public class CheckoutStepOnePage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By firstNameInput = By.id("first-name");
    private final By lastNameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");

    public boolean isCheckoutYourInformationVisible() {
        String title = getText(pageTitle);
        return title.equals("Checkout: Your Information");
    }

    public void fillCustomerInformation(String firstName, String lastName, String postalCode) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(postalCodeInput, postalCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void clickCancel() {
        click(cancelButton);
    }
}
