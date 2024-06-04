package dataProvider.userData;

import constants.FilePathConstants;
import dataProvider.DataObjectBuilder;
import entity.User;
import org.testng.annotations.DataProvider;

import java.nio.file.Path;

public class UserCredData {
    @DataProvider(name = "loginCredValidUser")
    public User[] loginCredValidUser() {

        Path loginCredDataPath = Path.of(FilePathConstants.LOGIN_CRED_VALID_USER_JSON);

        return DataObjectBuilder.buildDataObject(loginCredDataPath, User[].class);
    }

    @DataProvider(name = "loginCredInvalidUser")
    public User[] loginCredInvalidUser() {

        Path loginCredDataPath = Path.of(FilePathConstants.LOGIN_CRED_INVALID_USER_JSON);

        return DataObjectBuilder.buildDataObject(loginCredDataPath, User[].class);
    }
}
