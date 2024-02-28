package base;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.MainPage;
import utils.ReportUtils;
import utils.TestUtils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

import static base.BaseUtils.isElementExists;

public abstract class BaseTest {

    private ChromeDriver driver;
    private WebDriverWait webDriverWait5;
    private WebDriverWait webDriverWait10;
    private WebDriverWait webDriverWait20;

    public final String BASE_URL = TestUtils.getBaseUrl();

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {

        Reporter.log(ReportUtils.getReportHeader(context), true);
    }

    @BeforeMethod
    protected void beforeMethod(Method method, ITestResult result) {

        driver = BaseUtils.createDriver();
        Reporter.log(ReportUtils.END_LINE, true);
        Reporter.log("TEST RUN", true);
        Reporter.log(ReportUtils.getClassNameTestName(method, result), true);

    }
    @AfterMethod
    protected void afterMethod(Method method, ITestResult result) {
        Reporter.log(ReportUtils.getTestStatistics(method, result), true);

        driver.quit();
        webDriverWait5 = null;
        webDriverWait10 = null;
        webDriverWait20  = null;

    }
    protected ChromeDriver getDriver() {
        return driver;

    }

    protected WebDriverWait getWait5() {
        if(webDriverWait5 == null) {
            webDriverWait5 = new WebDriverWait(driver, Duration.ofSeconds(5));
        }
        return webDriverWait5;

    }
    protected WebDriverWait getWait10() {
        if(webDriverWait10 == null) {
            webDriverWait10 = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return webDriverWait10;

    }

    protected WebDriverWait getWait20() {
        if(webDriverWait20 == null) {
            webDriverWait20 = new WebDriverWait(driver, Duration.ofSeconds(20));
        }
        return webDriverWait20;

    }

    public void openBaseUrl() {
        driver.get(BASE_URL);
        waitTillGreyContainerDisappears();

        if(reloadPageIfElementNoFound(By.xpath("//div[id = 'weather-widget']//h2"))) {
            Reporter.log("Base URL page was loaded successfully");
        } else {
            Reporter.log("!!!!! ERROR !!!!! Base URL page was NOT loaded. \n"
                    + "Cancel current run and rerun jobs\n", true);
        }
    }

    public MainPage openBaseUrl_ReturnMainPage() {
        driver.get(BASE_URL);
        waitTillGreyContainerDisappears();

        if(reloadPageIfElementNoFound(By.xpath("//div[id = 'weather-widget']//h2"))) {
            Reporter.log("Base URL page was loaded successfully");
        } else {
            Reporter.log("!!!!! ERROR !!!!! Base URL page was NOT loaded. \n"
                    + "Cancel current run and rerun jobs\n", true);
        }

        return new MainPage(getDriver());
    }

    private boolean reloadPageIfElementNoFound(By by) {

        int count = 0;

        while(count <= 3 && !(isElementExists(driver, by))) {
            getDriver().navigate().refresh();
            Reporter.log("Reloading BaseURL page", true);
            waitTillGreyContainerDisappears();
            count++;
        }

        return isElementExists(driver, by);
    }

    public void waitTillGreyContainerDisappears() {
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

    }

    public boolean verifyNewPageOpen() {
        boolean newPageIsOpened = true;
        if (driver.getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpened = false;

        }
        return newPageIsOpened;

    }

    public void switchToSecondWindow() {
        String handle = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(handle);

    }
}
