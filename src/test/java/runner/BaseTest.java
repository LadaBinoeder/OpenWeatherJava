package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    private WebDriver driver;

    @BeforeMethod
    protected void beforeMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*", "--headless", "--window-size=1920,1080");

        driver = new ChromeDriver(chromeOptions);

    }
    @AfterMethod
    protected void afterMethod() {
        driver.quit();

    }
    protected WebDriver getDriver() {
        return driver;

    }
}
