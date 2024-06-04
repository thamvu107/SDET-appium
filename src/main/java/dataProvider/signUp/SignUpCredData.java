package dataProvider.signUp;

import constants.filePaths.SignUpCredPathConstants;
import entity.SignUpCred;
import helpers.DataObjectBuilderHelper;
import org.testng.annotations.DataProvider;

import java.nio.file.Path;

public class SignUpCredData {
    @DataProvider(name = "signUpCredValidUser")
    public SignUpCred[] signUpCredValidUser() {

        Path loginCredDataPath = Path.of(SignUpCredPathConstants.SIGNUP_CRED_VALID_USER_JSON);

        return DataObjectBuilderHelper.buildDataObject(loginCredDataPath, SignUpCred[].class);
    }

    @DataProvider(name = "signUpCredInvalidUser")
    public SignUpCred[] signUpCredInvalidUser() {

        Path loginCredDataPath = Path.of(SignUpCredPathConstants.SIGNUP_CRED_INVALID_USER_JSON);

        return DataObjectBuilderHelper.buildDataObject(loginCredDataPath, SignUpCred[].class);
    }

    @DataProvider(name = "signUpCredInvalidRepeatPassword")
    public SignUpCred[] signUpCredInvalidRepeatPassword() {

        Path loginCredDataPath = Path.of(SignUpCredPathConstants.SIGNUP_CRED_INVALID_REPEAT_PASSWORD_JSON);

        return DataObjectBuilderHelper.buildDataObject(loginCredDataPath, SignUpCred[].class);
    }
}
