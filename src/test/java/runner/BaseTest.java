package runner;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTest {

    private ChromeDriver driver;
    private WebDriverWait webDriverWait;

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
        webDriverWait = null;

    }
    protected ChromeDriver getDriver() {
        return driver;

    }

    protected WebDriverWait getWait5() {
        if(webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }
        return webDriverWait;

    }
    protected WebDriverWait getWait10() {
        if(webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return webDriverWait;

    }

    protected WebDriverWait getWait20() {
        if(webDriverWait == null) {
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        }
        return webDriverWait;

    }
}
