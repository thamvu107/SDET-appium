package testcaseV5.authen;

import annotations.author.Author;
import base.BaseTestV5;
import dataProvider.signIn.LoginCredData;
import entity.authen.LoginCred;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testFlows.SignInFlow;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_TITLE;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class SignInTest extends BaseTestV5 {
  private AppiumDriver driver;

  private SignInFlow signInFlow;

  @BeforeMethod(alwaysRun = true)
//  @Parameters({"platformType", "deviceType", "configureFile"})
  public void beforeMethod() {

    System.out.println("Before method: getConfigureFile " + getDriverFactory().getConfigureFile());
    System.out.println("Before method: driver " + getDriverFactory().createDriver());
//    log.atInfo().log("Before method: driver " + getDriverFactory().getDriver());
    signInFlow = new SignInFlow(getDriver());
  }

  @AfterMethod()
  public void afterMethod() {
    signInFlow.closeAlert();

    getDriverFactory().quitDriver();

  }

  @Author(THAM_VU)
  @Test(dataProvider = "loginCredValidUser", dataProviderClass = LoginCredData.class,
    groups = {"funcTest", "checkInTest"})
  public void loginWithCorrectCredential(LoginCred loginCred) {


    signInFlow
      .signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }

  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidUser", dataProviderClass = LoginCredData.class,
    groups = {"funcTest"})
  public void loginWithIncorrectCredentials(LoginCred loginCred) {

    signInFlow
      .signInAsInvalidCred(loginCred)
      .verifyCredIsInvalid(INVALID_EMAIL_MESSAGE, INVALID_PASSWORD_MESSAGE);
  }

  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidEmail", dataProviderClass = LoginCredData.class,
    groups = {"funcTest", "checkInTest"})
  public void loginWithIncorrectEmail(LoginCred loginCred) {

    signInFlow.signInAsInvalidCred(loginCred)
      .verifyEmailIsInvalid(INVALID_EMAIL_MESSAGE);
  }


  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidPassword", dataProviderClass = LoginCredData.class,
    groups = {"funcTest"})
  public void loginWithIncorrectPassword(LoginCred loginCred) {

    signInFlow.signInAsInvalidCred(loginCred)
      .verifyPasswordIsInvalid(INVALID_PASSWORD_MESSAGE);
  }

//  @Test(description = "Test case for purpose to show failure test",
//    dataProvider = "loginCredInvalidEmail",
//    dataProviderClass = LoginCredData.class,
//    groups = {"brokenTests"})
//  public void methodToBeBrokenTest1(LoginCred loginCred) {
//    signInFlow
//      .signInAsValidCred(loginCred)
//      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
//  }

//  @Test(description = "Test case for purpose to show failure test",
//    dataProvider = "loginCredInvalidEmail",
//    dataProviderClass = LoginCredData.class,
//    groups = {"brokenTests"})
//  public void methodToBeBrokenTest2(LoginCred loginCred) {
//    signInFlow
//      .signInAsValidCred(loginCred)
//      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
//  }
}
