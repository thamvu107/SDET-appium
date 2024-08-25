package testcaseV9.authen;

import annotations.author.Author;
import base.BaseTestV9;
import dataProvider.signIn.LoginCredData;
import driver.ThreadSafeDriver;
import entity.authen.LoginCred;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import testFlows.SignInFlow;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_TITLE;
import static interfaces.IAuthor.THAM_VU;
import static io.qameta.allure.SeverityLevel.CRITICAL;
import static io.qameta.allure.SeverityLevel.NORMAL;

@Slf4j
@Epic("Authentication")
@Feature("Sign In")
public class SignInTest extends BaseTestV9 {

  @Test(dataProvider = "loginCredValidUser", dataProviderClass = LoginCredData.class,
    groups = {"funcTest", "checkInTest"})
  @Severity(CRITICAL)
  @Description("Login with correct credential")
  @Story("As a user, I want to login with correct credential")
  @Author(THAM_VU)

  public void loginWithCorrectCredential(LoginCred loginCred) {
    System.out.println("loginWithCorrectCredential " + ThreadSafeDriver.getDriver());

    SignInFlow signInFlow = signInFlow();
    signInFlow.signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }

  @Test(dataProvider = "loginCredInvalidEmail", dataProviderClass = LoginCredData.class,
    groups = {"funcTest", "checkInTest"})
  @Owner(THAM_VU)
  @Severity(NORMAL)
  @Issue("AUTH-123")
  @TmsLink("TMS-123")
  @Description("Login with incorrect email")
  public void loginWithIncorrectEmail(LoginCred loginCred) {
    System.out.println("loginWithIncorrectEmail " + ThreadSafeDriver.getDriver());

    SignInFlow signInFlow = signInFlow();
    signInFlow.signInAsInvalidCred(loginCred)
      .verifyEmailIsInvalid(INVALID_EMAIL_MESSAGE);
  }

  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidUser", dataProviderClass = LoginCredData.class,
    groups = {"funcTest"})
  public void loginWithIncorrectCredentials(LoginCred loginCred) {
    System.out.println("loginWithIncorrectCredentials " + ThreadSafeDriver.getDriver());

    SignInFlow signInFlow = signInFlow();
    signInFlow.signInAsInvalidCred(loginCred)
      .verifyCredIsInvalid(INVALID_EMAIL_MESSAGE, INVALID_PASSWORD_MESSAGE);
  }


  @Author(THAM_VU)
  @Test(dataProvider = "loginCredInvalidPassword", dataProviderClass = LoginCredData.class,
    groups = {"funcTest"})
  public void loginWithIncorrectPassword(LoginCred loginCred) {
    SignInFlow signInFlow = signInFlow();
    signInFlow.signInAsInvalidCred(loginCred)
      .verifyPasswordIsInvalid(INVALID_PASSWORD_MESSAGE);
  }


}
