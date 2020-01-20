package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardsPageHelper extends PageBase {

    @FindBy(xpath = "//button[@data-test-id='header-boards-menu-button']")
    WebElement iconBoards;
    @FindBy( xpath = "//h3[@class = 'boards-page-board-section-header-name']")
    WebElement boardsPageHeaderName;

    public BoardsPageHelper(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(iconBoards, 50 );
    }
    public boolean verifyIfBoardsIconIsDisplayed(){
        return iconBoards.isDisplayed();
    }
    public boolean verifyIfPersonalBoardsHeaderIsDisplayed(){

        return boardsPageHeaderName.getText().equals("Personal Boards");

    }
    public void openBoard(String board){
        driver.findElement(By.xpath("//div[@title='"+board+"']/..")).click();
    }
}
