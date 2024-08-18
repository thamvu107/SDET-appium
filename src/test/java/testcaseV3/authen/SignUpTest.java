package testcaseV3.authen;

import annotations.author.Author;
import base.BaseTestV3;
import dataProvider.signUp.SignUpCredData;
import entity.authen.SignUpCred;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testFlows.SignUpFlow;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignUpScreenConstants.INCORRECT_REPEAT_PASSWORD_MESSAGE;
import static constants.SignUpScreenConstants.SIGN_UP_SUCCESS_MESSAGE;
import static constants.SignUpScreenConstants.SIGN_UP_SUCCESS_TITLE;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class SignUpTest extends BaseTestV3 {
  private AppiumDriver driver;
  private SignUpFlow signUpFlow;

  @BeforeClass(alwaysRun = true)
  public void beforeClass() {

    log.atInfo().log("Before Class: driver " + getDriver());
    signUpFlow = new SignUpFlow(getDriver());
  }

  @AfterClass
  public void afterClass() {

    log.atInfo().log("After SignUpTest: driver " + getDriver());
  }

  @AfterMethod
  public void afterMethod() {
    signUpFlow.closeAlert();
  }

  @Author(THAM_VU)
  @Test(dataProvider = "signUpCredValidUser", dataProviderClass = SignUpCredData.class,
    groups = {"Release"})
  public void signUpWithCorrectCredentials(SignUpCred signupCred) {
    signUpFlow.signUpAsValidCred(signupCred)
      .verifyAlertIsPresent(SIGN_UP_SUCCESS_TITLE, SIGN_UP_SUCCESS_MESSAGE);
  }

  @Author(THAM_VU)
  @Test(dataProvider = "signUpCredInvalidUser", dataProviderClass = SignUpCredData.class,
    groups = {"Release"})
  public void signUpWithInvalidUser(SignUpCred signUpCred) {

    signUpFlow.signUpAsInvalidCred(signUpCred)
      .verifyCredIsInvalid(INVALID_EMAIL_MESSAGE, INVALID_PASSWORD_MESSAGE, INCORRECT_REPEAT_PASSWORD_MESSAGE);

  }

  @Author(THAM_VU)
  @Test(dataProvider = "signUpCredInvalidRepeatPassword", dataProviderClass = SignUpCredData.class,
    groups = {"Release"})
  public void signUpWithInvalidRepeatPassWord(SignUpCred signUpCred) {

    signUpFlow.signUpAsInvalidCred(signUpCred)
      .verifyRepeatPasswordIsInvalid(INCORRECT_REPEAT_PASSWORD_MESSAGE);
  }
}
