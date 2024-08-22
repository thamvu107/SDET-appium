package testcaseV8.authen;

import annotations.author.Author;
import base.BaseTestV8;
import dataProvider.signIn.LoginCredData;
import entity.authen.LoginCred;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import testFlows.SignInFlow;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_TITLE;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class SignInTest extends BaseTestV8 {

  @Author(THAM_VU)
  @Test(dataProvider = "loginCredValidUser", dataProviderClass = LoginCredData.class,
    groups = {"funcTest", "checkInTest"})
  public void loginWithCorrectCredential(LoginCred loginCred) {
    System.out.println("loginWithCorrectCredential " + getDriver());

    SignInFlow signInFlow = signInFlow(getDriver());
    signInFlow.signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }

  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidUser", dataProviderClass = LoginCredData.class,
    groups = {"funcTest"})
  public void loginWithIncorrectCredentials(LoginCred loginCred) {
    System.out.println("loginWithIncorrectCredentials " + getDriver());

    SignInFlow signInFlow = signInFlow(getDriver());
    signInFlow.signInAsInvalidCred(loginCred)
      .verifyCredIsInvalid(INVALID_EMAIL_MESSAGE, INVALID_PASSWORD_MESSAGE);
  }

  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidEmail", dataProviderClass = LoginCredData.class,
    groups = {"funcTest", "checkInTest"})
  public void loginWithIncorrectEmail(LoginCred loginCred) {
    System.out.println("loginWithIncorrectEmail " + getDriver());

    SignInFlow signInFlow = signInFlow(getDriver());
    signInFlow.signInAsInvalidCred(loginCred)
      .verifyEmailIsInvalid(INVALID_EMAIL_MESSAGE);
  }


  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidPassword", dataProviderClass = LoginCredData.class,
    groups = {"funcTest"})
  public void loginWithIncorrectPassword(LoginCred loginCred) {
    SignInFlow signInFlow = signInFlow(getDriver());
    signInFlow.signInAsInvalidCred(loginCred)
      .verifyPasswordIsInvalid(INVALID_PASSWORD_MESSAGE);
  }


  private SignInFlow signInFlow(AppiumDriver driver) {
    return new SignInFlow(driver);
  }
}
