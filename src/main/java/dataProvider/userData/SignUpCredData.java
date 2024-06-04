package dataProvider.userData;

import constants.FilePathConstants;
import dataProvider.DataObjectBuilder;
import entity.SignUpCred;
import org.testng.annotations.DataProvider;

import java.nio.file.Path;

public class SignUpCredData {
    @DataProvider(name = "signUpCredValidUser")
    public SignUpCred[] signUpCredValidUser() {

        Path loginCredDataPath = Path.of(FilePathConstants.SIGNUP_CRED_INVALID_USER_JSON);

        return DataObjectBuilder.buildDataObject(loginCredDataPath, SignUpCred[].class);
    }

    @DataProvider(name = "signUpCredInvalidUser")
    public SignUpCred[] signUpCredInvalidUser() {

        Path loginCredDataPath = Path.of(FilePathConstants.SIGNUP_CRED_INVALID_USER_JSON);

        return DataObjectBuilder.buildDataObject(loginCredDataPath, SignUpCred[].class);
    }
}
