package runner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod() {
        driver = BaseUtils.createDriver();
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.addArguments("--remote-allow-origins=*", "--headless=new", "--window-size=1920,1080",
                "--disable-dev-shm-usage");

    }
    @AfterMethod
    protected void afterMethod() {
        driver.quit();

    }
    protected WebDriver getDriver() {
        return driver;

    }
}
