package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ReportUtils;
import utils.TestUtils;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.List;

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
    }

    public void waitTillGreyContainerDisappears() {
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

    }

    public void waitTillTextChanges(By by, String text) {
        getWait5().until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(by, text)));

    }

    public void waitTillElementIsVisible(By by)  {

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public String getAttribute(By by, String attribute) {

        return driver.findElement(by).getAttribute(attribute);
    }

    public String getText(By by) {
        return driver.findElement(by).getText();

    }

    public void clickElement(By by) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(by));
        getWait5().until(ExpectedConditions.elementToBeClickable(by)).click();

    }

    public void clickListElement(List<WebElement> elements, int index) {

        elements.get(index).click();
    }

    public void enterValue(By by, String value) {

        driver.findElement(by).sendKeys(value);
        driver.findElement(by).sendKeys(Keys.ENTER);
    }

    public void putInValue(By by, String value) {

        driver.findElement(by).sendKeys(value);
    }

    public boolean elementIsDisplayed(By by) {

        boolean isDisplayed = driver.findElement(by).isDisplayed();
        return isDisplayed;

    }

    public boolean elementIsNotDisplayed(By by) {
        try {
            driver.findElement(by);
            return true;

        } catch (NoSuchElementException e) {
            return false;

        }
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

    public boolean verifyNeededUnitsDisplayed(By by, String units) {
        String text = getText(by);
        if(text.contains(units)){
            return true;

        };
        return false;

    }

    public boolean verifyArrayContainsNeededUnits(List<WebElement> array, String units) {

        for(int i = 0; i < array.size(); i++) {
            if(array.get(i).getText().contains(units)) {
                return true;

            }
        }
        return false;

    }

    public int convertCelsiusToFahrenheit(int temperatureInCelsius) {

        int temperatureInFahrenheit = (int)(temperatureInCelsius * 1.8 + 32);
        return temperatureInFahrenheit;

    }

    public boolean celsiusToFahrenheitConvertingCorresponds(int temperatureInCelsius, int temperatureInFahrenheit) {

        if(temperatureInFahrenheit == convertCelsiusToFahrenheit(temperatureInCelsius)
                || temperatureInFahrenheit == convertCelsiusToFahrenheit(temperatureInCelsius) + 1)
        {
            return true;

        }
        return false;

    }

    public int getTemperatureFigureFromText(By by) {

        String currentTemp = getText(by);
        char[] arrayCurrentTemp = currentTemp.toCharArray();
        currentTemp = "";

        for(int i = 0; i < arrayCurrentTemp.length - 2; i++) {
            currentTemp = currentTemp + arrayCurrentTemp[i];
        }
        return Integer.valueOf(currentTemp);

    }
}
