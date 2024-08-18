package testcaseV3.authen;

import annotations.author.Author;
import base.BaseTestV3;
import dataProvider.signIn.LoginCredData;
import entity.authen.LoginCred;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import testFlows.SignInFlow;

import static constants.SignInScreenConstants.SIGN_IN_ALERT_MESSAGE;
import static constants.SignInScreenConstants.SIGN_IN_ALERT_TITLE;
import static interfaces.IAuthor.THAM_VU;

@Slf4j
public class ReportableTestStatus extends BaseTestV3 {
  private SignInFlow signInFlow;

  @BeforeClass(alwaysRun = true)
  public void beforeClass() {

    log.atInfo().log("Before Class ReportableTestStatus get driver " + getDriver());

    signInFlow = new SignInFlow(getDriver());
  }

  @Author(THAM_VU)
  @Test(enabled = false, dataProvider = "loginCredValidUser", dataProviderClass = LoginCredData.class,
    groups = {"funcTest", "checkInTest"})
  public void skipTest(LoginCred loginCred) {

    signInFlow
      .signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }


  @Test(description = "Test case for purpose to show failure test",
    dataProvider = "loginCredInvalidEmail",
    dataProviderClass = LoginCredData.class,
    groups = {"brokenTests"})
  public void brokenTest(LoginCred loginCred) {
    signInFlow
      .signInAsValidCred(loginCred)
      .verifyAlertIsPresent(SIGN_IN_ALERT_TITLE, SIGN_IN_ALERT_MESSAGE);
  }
}
