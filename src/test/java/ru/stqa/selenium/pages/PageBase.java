package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.util.LogLog4j;

public abstract class PageBase {
    WebDriver driver;
    public static LogLog4j log = new LogLog4j();

    public PageBase (WebDriver driver){
        this.driver=driver;
    }
    public abstract void waitUntilPageIsLoaded();

    public void waitUntilElementIsClickable(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOfAllElements(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enterValueToTheField(WebElement field, String value) {

        field.click();
        field.clear();
        field.sendKeys(value);
    }
}
