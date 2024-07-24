package testCaseV2.authentication;

import annotations.author.Author;
import base.BaseTestV2;
import dataProvider.signIn.LoginCredData;
import entity.authen.LoginCred;
import enums.DeviceUnderTestType;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testFlows.SignInFlow;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_TITLE;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class SignInTest extends BaseTestV2 {
  private AppiumDriver driver;

  private SignInFlow signInFlow;

  @BeforeClass(alwaysRun = true)
  @Parameters({"platformType", "deviceType", "configureFile"})
  public void beforeClass(String platformType, String deviceType, String configureFile) {
    baseTestPlatformType = PlatformType.valueOf(platformType);
    baseTestDeviceType = DeviceUnderTestType.valueOf(deviceType);
    baseTestConfigureFile = configureFile;

    System.out.println("platform:  " + baseTestPlatformType);
    System.out.println("deviceType:  " + baseTestDeviceType);
    System.out.println("configureFile:  " + baseTestConfigureFile);

    driver = getDriver(baseTestPlatformType, baseTestDeviceType, baseTestConfigureFile);

    System.out.println("Before Class: driver " + driver);
    log.atInfo().log("Before Class: driver " + driver);
    signInFlow = new SignInFlow(driver);
  }

  @AfterClass
  public void afterClass() {

    System.out.println("After Class");
    log.atInfo().log("After Class: driver " + driver);

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

  @Test(description = "Test case for purpose to show failure test",
    dataProvider = "loginCredInvalidEmail",
    dataProviderClass = LoginCredData.class,
    groups = {"brokenTests"})
  public void methodToBeBrokenTest1(LoginCred loginCred) {
    signInFlow
      .signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }

  @Test(description = "Test case for purpose to show failure test",
    dataProvider = "loginCredInvalidEmail",
    dataProviderClass = LoginCredData.class,
    groups = {"brokenTests"})
  public void methodToBeBrokenTest2(LoginCred loginCred) {
    signInFlow
      .signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }
}
