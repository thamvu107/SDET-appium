package testCaseV2.authentication;

import annotations.author.Author;
import base.BaseTestV2;
import dataProvider.signUp.SignUpCredData;
import entity.authen.SignUpCred;
import enums.DeviceUnderTestType;
import enums.PlatformType;
import io.appium.java_client.AppiumDriver;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testFlows.SignUpFlow;

import static constants.LoginScreenConstants.INVALID_EMAIL_MESSAGE;
import static constants.LoginScreenConstants.INVALID_PASSWORD_MESSAGE;
import static constants.SignUpScreenConstants.INCORRECT_REPEAT_PASSWORD_MESSAGE;
import static constants.SignUpScreenConstants.SIGN_UP_SUCCESS_MESSAGE;
import static constants.SignUpScreenConstants.SIGN_UP_SUCCESS_TITLE;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class SignUpTest extends BaseTestV2 {
  private AppiumDriver driver;
  private SignUpFlow signUpFlow;

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

    signUpFlow = new SignUpFlow(driver);
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

  @Test
  @Ignore
  public void ignoreTest() {
    System.out.println("Ignore test");
  }
}
