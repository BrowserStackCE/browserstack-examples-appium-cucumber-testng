package com.browserstack.examples.stepdefs;

import com.browserstack.app.pages.*;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BStackDemoSteps {
  public AppiumDriver<?> driver;
  WebDriverWait wait;
  HomePage homePage;
  CartPage cartPage;
  CheckoutPage checkoutPage;
  OrdersPage ordersPage;
  OffersPage offersPage;
  LoginPage loginPage;

  @Before
  public void setUp() throws MalformedURLException {
    MutableCapabilities capabilities = new MutableCapabilities();
    HashMap<String, String> bstackOptions = new HashMap<>();
    capabilities.setCapability("bstack:options", bstackOptions);
    driver =
      new AppiumDriver<>(
        new URL("https://hub.browserstack.com/wd/hub"),
        capabilities
      );

    wait = new WebDriverWait(driver, 10l);
  }

  @And("I add two products to cart")
  public void iAddProductsToCart() {
    homePage.addProductToCart("12").addProductToCart("16");
  }

  @And("I click on Buy Button")
  public void iClickOnBuyButton() {
    cartPage = homePage.openCart();
    checkoutPage = cartPage.proceedToCheckout();
  }

  @And("I press the Apple Vendor Filter")
  public void iPressTheAppleVendorFilter() {}

  @And("I order by lowest to highest")
  public void iOrderByLowestToHighest() {}

  @Then("I should see user {string} logged in")
  public void iShouldUserLoggedIn(String user) {}

  @Then("I should see no image loaded")
  public void iShouldSeeNoImageLoaded() {}

  @Then("I should see {int} items in the list")
  public void iShouldSeeItemsInTheList(int productCount) {}

  @Then("I should see prices in ascending order")
  public void iShouldSeePricesInAscendingOrder() {}

  @And("I press Log In Button")
  public void iPressLogin() {
    loginPage.login();
  }

  @Then("I should see {string} as Login Error Message")
  public void iShouldSeeAsLoginErrorMessage(String expectedMessage) {}

  @And("I SignIn as {string} with {string} password")
  public void iSignInAsWithPassword(String username, String password) {
    homePage.navigateToSignIn().loginWith(username, password);
  }

  @Given("I navigate to Demo Native App")
  public void iNavigateToApp() {
    homePage = new HomePage(driver);
  }

  @And("I click on {string} link")
  public void iClickOnLink(String linkText) {
    if (linkText.equals("Orders")) {
      ordersPage = homePage.navigateToOrders();
    } else if (linkText.equals("Offers")) {
      offersPage = homePage.navigateToOffers();
    }
  }

  @And("I type {string} in {string}")
  public void iTypeIn(String text, String text2) {
    loginPage = homePage.navigateToSignIn().typeDataInField(text, text2);
  }

  @And("I type {string} in Post Code")
  public void iTypeInPostCode(String postCode) {}

  @And("I click on Checkout Button")
  public void iClickOnCheckoutButton() {}

  @And(
    "I enter shipping details {string}, {string}, {string}, {string} and {string}"
  )
  public void iEnterShippingDetailsAnd(
    String first,
    String last,
    String address,
    String province,
    String postCode
  ) {
    checkoutPage
      .enterShippingDetails(
        "firstname",
        "lastname",
        "address",
        "state",
        "12345"
      )
      .continueShopping();
  }

  @Then("I should see Offer elements")
  public void iShouldSeeOfferElements() {
    offersPage.getOffers();
  }

  @Then("I should see elements in list")
  public void iShouldSeeElementsInList() {
    Assert.assertEquals(ordersPage.getItemsFromOrder(), 2);
  }

  @After
  public void teardown(Scenario scenario) throws Exception {
    Thread.sleep(5000);
    driver.quit();
  }
}
