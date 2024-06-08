package dataProvider.signIn;

import Utils.DataObjectBuilderUtil;
import constants.filePaths.LoginCredPathConstants;
import entity.LoginCred;
import org.testng.annotations.DataProvider;

import java.nio.file.Path;

public class LoginCredData {
    @DataProvider(name = "loginCredValidUser")
    public LoginCred[] loginCredValidUser() {

        Path loginCredDataPath = Path.of(LoginCredPathConstants.LOGIN_CRED_VALID_USER_JSON);

        return DataObjectBuilderUtil.buildDataObject(loginCredDataPath, LoginCred[].class);
    }

    @DataProvider(name = "loginCredInvalidUser")
    public LoginCred[] loginCredInvalidUser() {

        Path loginCredDataPath = Path.of(LoginCredPathConstants.LOGIN_CRED_INVALID_USER_JSON);

        return DataObjectBuilderUtil.buildDataObject(loginCredDataPath, LoginCred[].class);
    }

    @DataProvider(name = "loginCredInvalidEmail")
    public LoginCred[] loginCredInvalidEmail() {

        Path loginCredDataPath = Path.of(LoginCredPathConstants.LOGIN_CRED_INVALID_EMAIL_JSON);

        return DataObjectBuilderUtil.buildDataObject(loginCredDataPath, LoginCred[].class);
    }

    @DataProvider(name = "loginCredInvalidPassword")
    public LoginCred[] loginCredInvalidPassword() {

        Path loginCredDataPath = Path.of(LoginCredPathConstants.LOGIN_CRED_INVALID_PASSWORD_JSON);

        return DataObjectBuilderUtil.buildDataObject(loginCredDataPath, LoginCred[].class);
    }

}
