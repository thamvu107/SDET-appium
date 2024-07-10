package testFlows;

import entity.authen.LoginCred;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import screens.alert.SignInAlertScreen;
import screens.login.LoginScreen;
import screens.login.SignInScreen;
import utils.AlertHelper;

public class SignInFlow extends BaseFlow {
  private final LoginScreen loginScreen;
  private final SignInScreen signInScreen;
  private LoginCred loginCred;
  private SignInAlertScreen alertScreen;

  private final AlertHelper alertHelper;

  public SignInFlow(AppiumDriver driver) {
    super(driver);
    loginScreen = openLoginScreen();
    signInScreen = loginScreen.openSignInForm();
    alertHelper = new AlertHelper(driver);
  }

  public SignInFlow closeAlert() {
    alertHelper.closeAlertIfPresent();

    return this;
  }

  public SignInFlow signInAsValidCred(LoginCred loginCred) {

//    signInAsValidCred(loginCred);
    signInScreen.signInWithCred(loginCred);
    alertScreen = new SignInAlertScreen(driver);

    return this;
  }

  public SignInFlow signInAsInvalidCred(LoginCred loginCred) {
    signInScreen.signInWithCred(loginCred);

    return this;
  }

  public SignInFlow verifyAlertIsPresent(String alertTitle, String alertMessage) {
    Assert.assertTrue(alertScreen.isAlertPresent(), "Alert is not present");
    Assert.assertEquals(alertScreen.getAlertTitle(), alertTitle, "Alert title is not correct");
    Assert.assertEquals(alertScreen.getAlertMessage(), alertMessage, "Alert title is not correct");

    return this;
  }

  public SignInFlow verityEmailIsInvalid(String email) {
    Assert.assertEquals(signInScreen.getInvalidEmailMessage(), email, "Invalid email message is not correct");

    return this;
  }

  public SignInFlow verityPasswordIsInvalid(String password) {
    Assert.assertEquals(signInScreen.getInvalidPasswordMessage(), password, "Invalid password message is not correct");

    return this;
  }

  public SignInFlow verifyCredIsInvalid(String email, String password) {
    verityEmailIsInvalid(email);
    verityPasswordIsInvalid(password);

    return this;
  }

}
