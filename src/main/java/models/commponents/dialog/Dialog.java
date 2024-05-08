package models.commponents.dialog;

public interface Dialog {

    void seeDialog();

    void verifyDialogTitle(String expectedTitle);

    void verifyDialogMessage(String expectedMessage);

    void clickOnOkButton();

    void verifyDialogDisappeared();

}
