package ru.stqa.selenium;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.util.LogLog4j;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  //protected WebDriver driver;
  protected EventFiringWebDriver driver;

  HomePageHelper homePage;
  public static final String LOGIN = "annagorodetskaya@yandex.ru";
  public static final String PASSWORD = "repmrf04042003";
  public static LogLog4j log = new LogLog4j();

  public static class Mylistener extends AbstractWebDriverEventListener{
    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      //super.beforeFindBy(by, element, driver);
      System.out.println("find element " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
      //super.afterFindBy(by, element, driver);
      System.out.println("Element " + by + "was found ");

    }
  }

  @BeforeSuite
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--lang=" + "en");

    //driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    driver = new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities));

   // driver = new ChromeDriver(options);
    driver.register(new Mylistener());

    homePage = PageFactory.initElements(driver,HomePageHelper.class);

    //===========Enter to Trello====
    driver.get(baseUrl);
    driver.manage().window().maximize();
    homePage.waitUntilPageIsLoaded();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();

      driver.quit();
  }

}
