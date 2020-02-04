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
        log.info("Start method waitUntilPageIsLoaded ");
        log.info("Wait until login button is clickable");
        waitUntilElementIsClickable(loginButton, 30);
    }

    public void enterAtlLogin(String login) {
        log.info("Start method enterAtlLogin");
        log.info("Enter login");
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
        log.info("Start method loginToTrelloAsAtlassian " + login + "/" + password);
        log.info("Enter login  - " + login);
        this.enterAtlLogin(login);
        log.info("Click  on 'Login with Atlassian' button ");
        this.clickLoginWithAtlassian();
        log.info("Click on 'Continue' button");
        this.clickContinueButton();
        log.info("Enter password - " + password);
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
        log.info("Start method waitLoginErrorWithAccount");
        log.info("Wait until error message is visible");
        waitUntilElementIsVisible(loginErrorWithAccount,20);
    }
    public boolean verifyIfLoginErrorWithAccount(){
        return loginErrorWithAccount.getText()
                .contains("There isn't an account for this username");
    }

    public void enterAtlPassword(String password) {
        log.info("Start method enterAtlPassword");
        log.info("Wait until password field is clickable");
        waitUntilElementIsClickable(passwordField,10);
        log.info("Wait until button of login is clickable");
        waitUntilElementIsClickable(LoginButtonIfLoginNegative, 10);
        log.info("Enter password");
        enterValueToTheField(passwordField,password);
        log.info("Click on 'Login' button");
        loginButton.click();
    }
    public String getLoginError(){
        log.info("Start method 'getLoginError'");
        log.info("Get error text " + loginErrorWithAccount.getText());
        return loginErrorWithAccount.getText();
    }
}
