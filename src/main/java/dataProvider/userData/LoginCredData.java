package dataProvider.userData;

import constants.FilePathConstants;
import dataProvider.DataObjectBuilder;
import entity.LoginCred;
import org.testng.annotations.DataProvider;

import java.nio.file.Path;

public class LoginCredData {
    @DataProvider(name = "loginCredValidUser")
    public LoginCred[] loginCredValidUser() {

        Path loginCredDataPath = Path.of(FilePathConstants.LOGIN_CRED_VALID_USER_JSON);

        return DataObjectBuilder.buildDataObject(loginCredDataPath, LoginCred[].class);
    }

    @DataProvider(name = "loginCredInvalidUser")
    public LoginCred[] loginCredInvalidUser() {

        Path loginCredDataPath = Path.of(FilePathConstants.LOGIN_CRED_INVALID_USER_JSON);

        return DataObjectBuilder.buildDataObject(loginCredDataPath, LoginCred[].class);
    }
}
