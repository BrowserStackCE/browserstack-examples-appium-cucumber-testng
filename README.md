![Logo](https://www.browserstack.com/images/static/header-logo.jpg)

# BrowserStack Examples Cucumber TestNG <a href="https://cucumber.io"><img src="https://brandslogos.com/wp-content/uploads/images/large/cucumber-logo.png" alt="Cucumber" height="22" /></a> <a href="https://testng.org/"><img src="https://e7.pngegg.com/pngimages/640/776/png-clipart-testng-logo-software-testing-software-framework-computer-icons-automation-testing-angle-text.png" alt="TestNG" height="22" /></a>

## Introduction

TestNG is a testing framework designed to simplify a broad range of testing needs, from unit testing (testing a class in isolation of the others) to integration testing (testing entire systems made of several classes, several packages and even several external frameworks, such as application servers). Cucumber is a software tool that supports behavior-driven development (BDD).

This BrowserStack Example repository demonstrates a Selenium test framework written in Cucumber and TestNG with parallel testing capabilties. The Selenium test scripts are written for the open source [BrowserStack Demo web application](https://bstackdemo.com) ([Github](https://github.com/browserstack/browserstack-demo-app)). This BrowserStack Demo App is an e-commerce web application which showcases multiple real-world user scenarios, written in Next and React. The app is bundled with offers data, orders data and products data that contains everything you need to start using the app and run tests out-of-the-box.

The Selenium tests are run on different platforms like on-prem, docker and BrowserStack using various run configurations and test capabilities.



---

## Repository setup

- Clone the repository

- Ensure you have the following dependencies installed on the machine
  - Java >= 8
  - Maven >= 3.1+
  - Gradle >= 5.0+

  Maven:
    ```sh
    mvn install
    ```

  Gradle:
    ```sh
    gradle build
    ```

## About the tests in this repository

This repository contains the following Selenium tests:

| Module   | Test name                          | Description |
  | ---   | ---                                   | --- |
| E2E      | End to End Scenario                | This test scenario verifies successful product purchase lifecycle end-to-end. It demonstrates the [Page Object Model design pattern](https://www.browserstack.com/guide/page-object-model-in-selenium) and is also the default test executed in all the single test run profiles. |
| Login    | Login with given username          | This test verifies the login workflow with different types of valid login users. |
| Login    | Login as Locked User               | This test verifies the login workflow error for a locked user. |
| Offers   | Offers for Mumbai location     | This test mocks the GPS location for Mumbai and verifies that the product offers applicable for the Mumbai location are shown.   |
| Product  | Apply Apple Vendor Filter          | This test verifies that 9 Apple products are only shown if the Apple vendor filter option is applied. |
| Product  | Apply Lowest to Highest Order By   | This test verifies that the product prices are in ascending order when the product sort "Lowest to Highest" is applied. |
| User     | Login as User with no image loaded | This test verifies that the product images load for user: "image_not_loading_user" on the e-commerce application. Since the images do not load, the test case assertion fails.|
| User     | Login as User with existing Orders |  This test verifies that existing orders are shown for user: "existing_orders_user"  |
  
---

---
# BrowserStack

[BrowserStack](https://browserstack.com) provides instant access to 2,000+ real mobile devices and browsers on a highly reliable cloud infrastructure that effortlessly scales as testing needs grow.

## Prerequisites

- Create a new [BrowserStack account](https://www.browserstack.com/users/sign_up) or use an existing one.
- Identify your BrowserStack username and access key from the [BrowserStack Automate Dashboard](https://automate.browserstack.com/) and export them as environment variables using the below commands.

  - For \*nix based and Mac machines:

  ```sh
  export BROWSERSTACK_USERNAME=<browserstack-username> &&
  export BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```

  - For Windows:

  ```shell
  set BROWSERSTACK_USERNAME=<browserstack-username>
  set BROWSERSTACK_ACCESS_KEY=<browserstack-access-key>
  ```

  Alternatively, you can also hardcode username and access_key objects in the [browserstack-android.yml & browserstack-ios.yml] file.

Note:
- We have configured a list of test capabilities in the [browserstack-android.yml & browserstack-ios.yml] file. You can certainly update them based on your device / browser test requirements.
- The exact test capability values can be easily identified using the [Browserstack Capability Generator](https://browserstack.com/automate/capabilities)


## Running Your Tests

### Run a specific test on BrowserStack

In this section, we will run a single test on Chrome browser on Browserstack. To change test capabilities for this configuration, please refer to the [browserstack-android.yml & browserstack-ios.yml] file.

- How to run the test?

  - To run the default test scenario (e.g. End to End Scenario) on your own machine, use the following command:

  Maven:
  - For \*nix based and Mac machines:

  ```sh
  mvn test -P bstack-single -Dbrowserstack.config="browserstack-ios.yml" //For IOS app
  mvn test -P bstack-single -Dbrowserstack.config="browserstack-android.yml" //For Android App
  ```
  - For Windows:

  ```sh
  mvn test -P bstack-single -Dbrowserstack.config="browserstack-ios.yml" //For IOS app
  mvn test -P bstack-single -Dbrowserstack.config="browserstack-android.yml" //For Android app
  ```


  Gradle:
  - For \*nix based and Mac machines:

  ```sh
  gradle bstack-single -Dbrowserstack.config="browserstack-ios.yml"
  ```
  - For Windows:

  ```sh
  gradle bstack-single -Dbrowserstack.config="browserstack-ios.yml"
  ```

- Output

  This run profile executes a single test on a single browser on BrowserStack. Please refer to your [BrowserStack dashboard](https://automate.browserstack.com/) for test results.


### Run the entire test suite in parallel

In this section, we will run the tests in parallel on a single browser on Browserstack. Refer to [browserstack-android.yml & browserstack-ios.yml] file to change test capabilities for this configuration.

- How to run the test?

  To run the entire test suite in parallel on a single BrowserStack browser, use the following command:

    Maven:
  - For \*nix based and Mac machines:

  ```sh
  mvn test -P bstack-parallel -Dbrowserstack.config="browserstack-ios.yml" //For IOS app
  mvn test -P bstack-parallel -Dbrowserstack.config="browserstack-android.yml" 
  ```
  - For Windows:

  ```sh
  mvn test -P bstack-parallel -Dbrowserstack.config="browserstack-ios.yml" //For IOS app
  mvn test -P bstack-parallel -Dbrowserstack.config="browserstack-andriod.yml" //for Android App
  ```


  Gradle:
  - For \*nix based and Mac machines:
  ```sh
  rm -f -- browserstack.yml & ln src/test/resources/conf/browserstack-parallel.yml browserstack.yml & gradle bstack-parallel
  ```
  - For Windows:

  ```sh
  del /f "browserstack.yml" && copy /y .\src\test\resources\conf\browserstack-parallel.yml browserstack.yml & gradle bstack-parallel
  ```



- Output

  This run profile executes the entire test suite in parallel on a single BrowserStack browser. Please refer to your [BrowserStack dashboard](https://automate.browserstack.com/) for test results.



## Generating Allure Reports

  In this section, we will generate and serve allure reports for maven test runs.

- Generate Report using the following command: `mvn io.qameta.allure:allure-maven:report`
- Serve the Allure report on a server: `mvn io.qameta.allure:allure-maven:serve`

## Additional Resources

- View your test results on the [BrowserStack Automate dashboard](https://www.browserstack.com/automate)
- Documentation for writing [Automate test scripts in Java](https://www.browserstack.com/automate/java)
- Customizing your tests capabilities on BrowserStack using our [test capability generator](https://www.browserstack.com/automate/capabilities)
- [List of Browsers & mobile devices](https://www.browserstack.com/list-of-browsers-and-platforms?product=automate) for automation testing on BrowserStack
- [Using Automate REST API](https://www.browserstack.com/automate/rest-api) to access information about your tests via the command-line interface
- Understand how many parallel sessions you need by using our [Parallel Test Calculator](https://www.browserstack.com/automate/parallel-calculator?ref=github)
- For testing public web applications behind IP restriction, [Inbound IP Whitelisting](https://www.browserstack.com/local-testing/inbound-ip-whitelisting) can be enabled with the [BrowserStack Enterprise](https://www.browserstack.com/enterprise) offering
