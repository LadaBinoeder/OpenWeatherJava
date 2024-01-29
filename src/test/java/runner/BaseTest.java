package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private WebDriver driver;

    private WebDriverWait webDriverWait;

    @BeforeMethod
    protected void beforeMethod() {
       // ChromeOptions chromeOptions = new ChromeOptions();
        // chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        driver = BaseUtils.createDriver();

    }
    @AfterMethod
    protected void afterMethod() {
        driver.quit();
        webDriverWait = null;

    }
    protected WebDriver getDriver() {
        return driver;

    }
}
