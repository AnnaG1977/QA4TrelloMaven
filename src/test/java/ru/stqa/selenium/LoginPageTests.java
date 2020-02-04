package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.BoardsPageHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import ru.stqa.selenium.util.DataProviders;


public class LoginPageTests extends TestBase {

    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardPage;

    @BeforeMethod
    public void initTests(){

        homePage = PageFactory.initElements(driver,HomePageHelper.class);

        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);

        boardPage = PageFactory.initElements(driver,BoardsPageHelper.class);
    }
    @Test
    public void loginToTrelloPositive()  {
        log.startTestCase("loginToTrelloPositive");
        log.info("Open login page");
        homePage.openLoginPage();
        log.info("Wait until page is loaded");
        loginPage.waitUntilPageIsLoaded();
        log.info("Enter login/password as Atlassian - "  + LOGIN + "  /  " + PASSWORD);
        loginPage.loginToTrelloAsAtlassian(LOGIN,PASSWORD);
        log.info("Wait until boards page is loaded");
        boardPage.waitUntilPageIsLoaded();
        log.info("Verify: 'Boards icon is displayed' ");
        Assert.assertTrue(boardPage.verifyIfBoardsIconIsDisplayed());
        log.info("Verify: personal boards header is displayed ");
        Assert.assertTrue(boardPage.verifyIfPersonalBoardsHeaderIsDisplayed());
        log.endTestCase();
    }
    @Test(dataProviderClass =DataProviders.class, dataProvider = "dataProviderPasswordIncorrect")
    public void loginIncorrectPassNegative(String login, String psw)  {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
       // loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD+1);
        loginPage.loginToTrelloAsAtlassian(login, psw);
        loginPage.waitPasswordError();
        Assert.assertTrue(loginPage.verifyIfPasswordErrorIsCorrect(),"Error massage is not correct");
    }
    @Test (dataProviderClass = DataProviders.class, dataProvider = "dataProviderFirst")
    public void loginIncorrectLoginNegative(String login, String psw, String message)  {
        log.startTestCase("loginIncorrectLoginNegative");
        log.info("---------login: " + login +" / " + "password: " + psw + "-------------");
        log.info("---------message: " + message + "---------------");
        log.info("Open login page");
        homePage.openLoginPage();
        log.info("Wait until page is loaded");
        loginPage.waitUntilPageIsLoaded();
        //loginPage.enterAtlLogin("aqqqqq");
       // loginPage.enterAtlPassword(PASSWORD);
        log.info("Enter login");
        loginPage.enterAtlLogin(login);
        log.info("Enter password");
        loginPage.enterAtlPassword(psw);
        log.info("login: " + login +" / " + "password: " + psw );
        log.info("Wait login error with account");
        loginPage.waitLoginErrorWithAccount();
       // Assert.assertTrue(loginPage.verifyIfLoginErrorWithAccount());
        log.info("Verify that error message '" + message + " '");
        Assert.assertEquals(message,loginPage.getLoginError());
    }

}
