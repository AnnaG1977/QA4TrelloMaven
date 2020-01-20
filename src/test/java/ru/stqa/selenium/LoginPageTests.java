package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.BoardsPageHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;


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
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN,PASSWORD);
        boardPage.waitUntilPageIsLoaded();
        Assert.assertTrue(boardPage.verifyIfBoardsIconIsDisplayed());
        Assert.assertTrue(boardPage.verifyIfPersonalBoardsHeaderIsDisplayed());
    }
    @Test
    public void loginIncorrectPassNegative()  {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN,PASSWORD+1);
        loginPage.waitPasswordError();
        Assert.assertTrue(loginPage.verifyIfPasswordErrorIsCorrect(),"Error massage is not correct");
    }
    @Test
    public void loginIncorrectLoginNegative()  {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.enterAtlLogin("aqqqqq");
        loginPage.enterAtlPassword(PASSWORD);
        loginPage.waitLoginErrorWithAccount();
        Assert.assertTrue(loginPage.verifyIfLoginErrorWithAccount());
    }

}
