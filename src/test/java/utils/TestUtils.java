package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;

public class TestUtils {

    private final static String BASE_URL = "https://openweathermap.org/";

    private final static By H2_HEADER = By.xpath("//div[@id = 'weather-widget']//h2");

    public static String getBaseUrl() {

        return BASE_URL;
 
    }

    public static void waitForPageLoaded(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }

    public static void loadBaseUrlPage(WebDriver driver, WebDriverWait wait) {
        driver.get(getBaseUrl());
        waitForPageLoaded(driver);
        wait.until(ExpectedConditions.invisibilityOfElementLocated((By.className("owm-loader-container"))));
    }

    public static void reLoadBaseUrlPage(WebDriver driver, WebDriverWait wait) {
        int count = 0;
        while (count <= 3 && !(isH2HeaderExists(driver))) {
            loadBaseUrlPage(driver, wait);
            count++;
        }

        if (count == 3 && !isH2HeaderExists(driver)) {
            Reporter.log("!!!!! Error !!!!! BaseURL page was NOT loaded. Re-Run jobs\n", true);
        }
    }

    public static boolean isH2HeaderExists(WebDriver driver) {
        boolean isExists = true;
        try {
            driver.findElement(H2_HEADER);
        } catch (NoSuchElementException e) {
            isExists = false;
        }

        return isExists;
    }
}
