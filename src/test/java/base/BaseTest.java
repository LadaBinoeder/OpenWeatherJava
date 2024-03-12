package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public abstract class BaseTest {

    private WebDriver driver;
    private WebDriverWait webDriverWait5;
    private WebDriverWait webDriverWait10;
    private WebDriverWait webDriverWait20;

    public final String BASE_URL = TestUtils.getBaseUrl();

    @BeforeSuite
    protected void beforeSuite(ITestContext context) {

        Reporter.log(ReportUtils.getReportHeader(context), true);
    }

    @BeforeMethod
    protected void beforeMethod(Method method, ITestResult result) throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setBrowserName("chrome");

        this.driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
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
    protected WebDriver getDriver() {
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

    public MainPage openBaseURL() {
        TestUtils.loadBaseUrlPage(getDriver(), getWait10());

        if (TestUtils.isH2HeaderExists(getDriver())) {
            Reporter.log("BaseURL page was loaded successfully ", true);
        } else {
            TestUtils.reLoadBaseUrlPage(getDriver(), getWait10());
        }
        return new MainPage(getDriver());

    }
}
