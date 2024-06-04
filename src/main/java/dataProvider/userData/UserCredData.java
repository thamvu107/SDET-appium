package dataProvider.userData;

import dataProvider.DataObjectBuilder;
import entity.User;
import org.testng.annotations.DataProvider;

import java.nio.file.Path;

public class UserCredData {
    @DataProvider(name = "loginCredValidData")
    public static User[] loginCredValidData() {

        Path loginCredDataPath = Path.of("src/test/resources/data/authen/LoginCredValidUser.json");

        return DataObjectBuilder.buildDataObject(loginCredDataPath, User[].class);
    }
}
