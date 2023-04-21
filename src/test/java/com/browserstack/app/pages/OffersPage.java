package com.browserstack.app.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import java.util.List;

public class OffersPage extends BasePage {

  public OffersPage(AppiumDriver<?> driver) {
    super(driver);
  }

  public OffersPage getOffers() {
    // TBD
    return this;
  }
}
