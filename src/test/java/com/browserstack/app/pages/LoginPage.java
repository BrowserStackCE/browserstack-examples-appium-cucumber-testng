package com.browserstack.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class LoginPage extends BasePage {
  @AndroidFindBy(accessibility = "username-input")
  @iOSXCUITFindBy(accessibility = "username-input")
  private MobileElement usernameInput;

  @AndroidFindBy(accessibility = "password-input")
  @iOSXCUITFindBy(accessibility = "password-input")
  private MobileElement passwordInput;

  @AndroidFindBy(accessibility = "login-btn")
  @iOSXCUITFindBy(accessibility = "login-btn")
  private MobileElement logInButton;

  public LoginPage(AppiumDriver<?> driver) {
    super(driver);
  }

  public LoginPage typeDataInField(String data, String field) {
    if (field.equals("username")) {
      usernameInput.click();
      mobileHelper.selectFromPickerWheel(
        "//XCUIElementTypePickerWheel[@value='Accepted usernames are']",
        data
      );
    } else if (field.equals("password")) {
      passwordInput.click();
      mobileHelper.selectFromPickerWheel(
        "//XCUIElementTypePickerWheel[@value='Password for all users']",
        data
      );
    }
    return new LoginPage(driver);
  }

  public HomePage login() {
    logInButton.click();
    return new HomePage(driver);
  }

  public HomePage loginWith(String username, String password) {
    usernameInput.click();
    mobileHelper.selectFromPickerWheel(
      "//XCUIElementTypePickerWheel[@value='Accepted usernames are']",
      username
    );

    passwordInput.click();
    mobileHelper.selectFromPickerWheel(
      "//XCUIElementTypePickerWheel[@value='Password for all users']",
      password
    );
    logInButton.click();
    return new HomePage(driver);
  }
}
