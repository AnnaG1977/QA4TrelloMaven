package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TheBoardPageHelper extends PageBase {
    @FindBy(css= ".placeholder")
    WebElement addAnotherList;
    @FindBy(css = ".list-name-input")
    WebElement enterListTitle;
    @FindBy(xpath = "//input[@type='submit']")
    WebElement addListButton;
   @FindBy(xpath = "//span[@class='js-add-a-card']")
   List<WebElement> listAddCardButton;

    @FindBy(xpath = "//input[@class='primary confirm mod-compact js-add-card']")
    WebElement addCardButton;

    @FindBy(css = "a.js-cancel-edit")
    WebElement cancelAddList;

    @FindBy(xpath ="//textarea[@placeholder='Enter a title for this card…']")
    WebElement enterTitleForThisCard;

    @FindBy(css = "a.js-cancel")
    WebElement cancelAddCard;

    @FindBy(xpath = "//h2")
    List<WebElement> listHeader;
    @FindBy(xpath =  "//span[@class= 'js-add-another-card']")
    WebElement addAnotherCardButton;

    @FindBy(xpath = "//a[@class='list-header-extras-menu dark-hover js-open-list-menu icon-sm icon-overflow-menu-horizontal']")
    WebElement dots;
    @FindBy(xpath = "//a[@class='js-close-list']")
    WebElement archiveThisList;

    @FindBy(css = ".js-copy-list")
    WebElement copyList;

    @FindBy(xpath = "//input[@class='primary wide js-submit']")
    WebElement createList;

    @FindBy(xpath = "//textarea[@class='list-header-name mod-list-name js-list-name-input']")
    List<WebElement> titleList;

    public TheBoardPageHelper(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(addAnotherList,20);
    }

    public void waitUntilCopyListIsClickable(){
        waitUntilElementIsClickable(copyList,10);
    }

    public void waitUntilCreateListIsClickable(){
        waitUntilElementIsClickable(createList,10);
    }

    public void waitUntilAddListButtonIsVisible(){
        waitUntilElementIsVisible(addListButton,20);
    }
    public void waitUntilAddAnotherCardButtonISVisible(){
        waitUntilElementIsVisible(addAnotherCardButton,10);
    }
    public void waitUntilAdAnotherCardButtonIsVisible(){
        waitUntilElementIsVisible(addAnotherCardButton,20);
    }
    public void waitUntilArchiveThisListIsClickable(){
        waitUntilElementIsClickable(archiveThisList,10);
    }

    public void addAnotherList(){
        addAnotherList.click();
        enterValueToTheField(enterListTitle,"New List Anna34");
        addListButton.click();
        waitUntilElementIsClickable(addListButton,10);
        cancelAddList.click();
    }
    public int quantityOfLists(){
      return listHeader.size();
    }
    public int quantityOfAddCard(){
        return listAddCardButton.size();
    }

    public  void enterTitleForThisCard(){

        waitUntilElementIsClickable( addCardButton,10);
        enterValueToTheField(enterTitleForThisCard,"new card");
        addCardButton.click();
        waitUntilElementIsClickable(cancelAddCard,10);
        cancelAddCard.click();
    }
    public void LastEmptyList() {
        waitUntilAllElementsAreVisible(addAnotherList,15);
        int sizeLstAddCardButtons = listAddCardButton.size();
        WebElement lastAddCardButton = listAddCardButton.get(sizeLstAddCardButtons - 1);

        //----Add a first card for any new list
        lastAddCardButton.click();
        enterTitleForThisCard.sendKeys("text");
        addCardButton.click();
    }

    public void deleteList() {
        dots.click();
        waitUntilArchiveThisListIsClickable();
        archiveThisList.click();
    }
    public void copyList() {
        dots.click();
        waitUntilCopyListIsClickable();
        copyList.click();
        waitUntilCreateListIsClickable();
        createList.click();
        waitUntilAdAnotherCardButtonIsVisible();
    }
    public  String getTitleOfLists(int i){
        return titleList.get(i).getText();
    }
}
