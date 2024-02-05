import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class MainTestWithoutBeforeTest {
    private final static String BASE_URL = "https://openweathermap.org/";

    @Test
    public void testLocationButtonGeoForbidden() {

        String expectedResultNotificationMessage = "Location unavailable. Displaying default location: London";
        String expectedResultCityCountry = "London, GB";

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.managed_default_content_settings.geolocation", 2);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get(BASE_URL);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement cityCountryName = driver.findElement(By.xpath("//div[@class = 'section-content']//h2"));

        WebElement locationButton = driver.findElement(By.className("icon-current-location"));
        locationButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'widget-notification']/span")));

        WebElement notification = driver.findElement(By.xpath("//div[@class = 'widget-notification']/span"));
        String actualResultCityCountry = cityCountryName.getText();
        String actualResultNotificationMessage = notification.getText();

        Assert.assertEquals(actualResultCityCountry, expectedResultCityCountry);
        Assert.assertEquals(actualResultNotificationMessage, expectedResultNotificationMessage);

        driver.quit();
    }
}
