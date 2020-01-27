package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.BoardsPageHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import ru.stqa.selenium.pages.TheBoardPageHelper;
import ru.stqa.selenium.util.DataProviders;


import java.util.List;

public class CurrentBoardPageTests extends TestBase {
    public static final String BOARD = "New";
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardPage;
    TheBoardPageHelper theBoardPage;

    @BeforeMethod
    public void initTest(){

        homePage = PageFactory.initElements(driver,HomePageHelper.class);
        loginPage = PageFactory.initElements(driver,LoginPageHelper.class);
        boardPage = PageFactory.initElements(driver,BoardsPageHelper.class);
        theBoardPage = PageFactory.initElements(driver,TheBoardPageHelper.class);

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN,PASSWORD);
        boardPage.waitUntilPageIsLoaded();
    }

   @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderText")
    public void createNewList(String listName)  {
       boardPage.openBoard(BOARD);
       theBoardPage.waitUntilPageIsLoaded();
       int q = theBoardPage.quantityOfLists(); // quantity before added
       theBoardPage.addAnotherList(listName);
       int qEnd = theBoardPage.quantityOfLists(); // quantity after added
       Assert.assertEquals(q+1,qEnd);
    }
    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderText")
    public void  addFirstCardInNewList (String listName, String cardName)  {

        boardPage.openBoard("QA4Haifa");
        theBoardPage.waitUntilPageIsLoaded();
        int quantityAddAnotherButtonBeg = theBoardPage.quantityOfLists();
        theBoardPage.addAnotherList(listName);
        theBoardPage.waitUntilAdAnotherCardButtonIsVisible();
        theBoardPage.LastEmptyList(cardName);
        theBoardPage.waitUntilAdAnotherCardButtonIsVisible();
        int quantityAddAnotherButtonEnd = theBoardPage.quantityOfLists();
        Assert.assertEquals(quantityAddAnotherButtonBeg + 1, quantityAddAnotherButtonEnd);
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderText")
    public void deleteList(String listName) {
        boardPage.openBoard("New");
        theBoardPage.waitUntilPageIsLoaded();
        int counter =0;
        counter = theBoardPage.quantityOfLists();
        System.out.println("before  lists " + counter);
        if (counter==0){
            theBoardPage.addAnotherList(listName);
        }
        theBoardPage.deleteList();
        counter = theBoardPage.quantityOfLists();
        System.out.println("after  lists " + counter);
    }
    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderText")
    public void CopyList(String listName){
        boardPage.openBoard("New");
        theBoardPage.waitUntilPageIsLoaded();
        int q = theBoardPage.quantityOfLists();
        if (q==0){ theBoardPage.addAnotherList(listName); }
        q = theBoardPage.quantityOfLists();
        theBoardPage.copyList();
        int qEnd = theBoardPage.quantityOfLists();
        String name1 = theBoardPage.getTitleOfLists(0);
        String name2 = theBoardPage.getTitleOfLists(1);

        int count =0;
        if (name1.equals(name2)) count++;
        if (qEnd==q+1)count++;
        Assert.assertEquals(count,2, "There isn't copy of list");
    }

}
