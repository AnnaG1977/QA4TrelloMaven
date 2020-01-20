package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase {

    @FindBy(id = "user")
    WebElement userField;

    @FindBy(id="login")
    WebElement loginButton;

    @FindBy(xpath = "//button[@id='login-submit']//span[contains(text(),'Log in')]")
    WebElement theSecondLoginButton;

    @FindBy(xpath = "//input[@class='button account-button button-green btn btn-success']")
    WebElement LoginButtonIfLoginNegative;

    @FindBy(xpath = "//button[@id='login-submit']//span[contains(text(),'Continue')]")
    WebElement continueButton;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(xpath = "//div[@id = 'login-error']/span")
    WebElement passwordError;
    @FindBy(xpath = "//p[@class='error-message']")
    WebElement loginErrorWithAccount;

    public LoginPageHelper(WebDriver driver){
        super(driver);
    }
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(loginButton, 30);
    }

    public void enterAtlLogin(String login) {
        enterValueToTheField(userField,login);
    }

    public void clickLoginWithAtlassian() {
        waitUntilElementIsClickable(loginButton,10);
        loginButton.click();
    }
    public void clickContinueButton() {
        waitUntilElementIsClickable(continueButton,30);
        continueButton.click();
    }
    public void enterAtlPasswordAndLogin(String password) {
        waitUntilElementIsClickable(passwordField,30);
        waitUntilElementIsClickable(theSecondLoginButton,30);
        enterValueToTheField(passwordField,password);
        theSecondLoginButton.click();
    }

    public void loginToTrelloAsAtlassian(String login, String password){
        this.enterAtlLogin(login);
        this.clickLoginWithAtlassian();
        this.clickContinueButton();
        this.enterAtlPasswordAndLogin(password);
    }

    public void waitPasswordError() {
        waitUntilElementIsVisible(passwordError,10);
    }
    public boolean verifyIfPasswordErrorIsCorrect(){
        return passwordError.getText()
                .contains("Incorrect email address and / or password.\n" +
                        "Do you need help logging in?");
    }
    public void waitLoginErrorWithAccount(){
        waitUntilElementIsVisible(loginErrorWithAccount,20);
    }
    public boolean verifyIfLoginErrorWithAccount(){
        return loginErrorWithAccount.getText()
                .contains("There isn't an account for this username");
    }

    public void enterAtlPassword(String password) {
        waitUntilElementIsClickable(passwordField,10);
        waitUntilElementIsClickable(LoginButtonIfLoginNegative, 10);
        enterValueToTheField(passwordField,password);
        loginButton.click();
    }
}
