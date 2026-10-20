Feature: Comprar mochila Sauce Labs

  Scenario: Usuario inicia sesión y agrega Sauce Labs Backpack al carrito
    Given I open the login page
    When I login as "standard_user" with password "secret_sauce"
    And I open the product "Sauce Labs Backpack"
    Then I should see the product page with title "Sauce Labs Backpack"
    When I add the product to the cart
    Then the cart quantity should be 1