package testcaseV3.authen;

import annotations.author.Author;
import base.BaseTestV3;
import dataProvider.signIn.LoginCredData;
import entity.authen.LoginCred;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testFlows.SignInFlow;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_TITLE;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class SignInTest extends BaseTestV3 {
  private AppiumDriver driver;

  private SignInFlow signInFlow;

  @BeforeClass(alwaysRun = true)
  public void beforeClass() {

    System.out.println("BeforeClass SignInTest");

    System.out.println("Before Class: driver " + getDriver());
    log.atInfo().log("Before Class: driver " + getDriver());

    signInFlow = new SignInFlow(getDriver());
  }

  @AfterClass
  public void afterClass() {

    System.out.println("After Class");
    log.atInfo().log("After Class: driver " + getDriver());

  }

  @AfterMethod()
  public void afterMethod() {
    signInFlow.closeAlert();
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

}
