import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import javax.swing.plaf.TableHeaderUI;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainTest extends BaseTest {

    private final static String BASE_URL = "https://openweathermap.org/";

    @Test
    public void testURLAndTitle() throws InterruptedException, IOException {

        final String expectedResultURL = "https://openweathermap.org/";
        final String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";

        getDriver().get(BASE_URL);
        Thread.sleep(3000);
        String actualResultURL = getDriver().getCurrentUrl();
        String actualResultTitle = getDriver().getTitle();

        WebElement anyElement = getDriver().findElement(By.xpath("//*"));
        boolean pageIsNotEmpty = false;
        if(anyElement != null) {
            pageIsNotEmpty = true;
        }

        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        boolean noErrors = true;
        if(responseCode >= 400 && responseCode < 600) {
           noErrors = false;
       }

        Assert.assertEquals(actualResultURL, expectedResultURL);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
        Assert.assertTrue(pageIsNotEmpty);
        Assert.assertTrue(noErrors);
    }

    @Test
    public void testLogoIsDisplayed() throws InterruptedException {

        getDriver().get(BASE_URL);
        Thread.sleep(3000);

        WebElement logo = getDriver().findElement(By.xpath("//ul[@id = 'first-level-nav']/li[@class = 'logo']"));
        boolean logoIsDisplayed = logo.isDisplayed();

        Assert.assertTrue(logoIsDisplayed);
    }

    @Test
    public void testPlaceholderIsDisplayed() throws InterruptedException {

        getDriver().get(BASE_URL);
        Thread.sleep(3000);

        WebElement placeholder = getDriver().findElement(By.xpath("//div[@id = 'desktop-menu']//input[@name = 'q']"));
        boolean placeholderIsDisplayed = placeholder.isDisplayed();

        Assert.assertTrue(placeholderIsDisplayed);
    }

    @Test
    public void testAllTheElementsInDesktopMenuAreDisplayed() throws InterruptedException {

        final int expectedResultNumber = 12;

        getDriver().get(BASE_URL);
        Thread.sleep(3000);

        List<WebElement> desktopMenuElements = getDriver().findElements(By.xpath("//div[@id = 'desktop-menu']/ul/li"));

        int actualResultNumber = desktopMenuElements.size();

        Assert.assertEquals(actualResultNumber, expectedResultNumber);
    }

    @Test
    public void testNamesOfElementsInDesktopMenu() throws InterruptedException {

        final List<String> expectedDesktopMenuNames = List.of(
                "Guide",
                "API",
                "Dashboard",
                "Marketplace",
                "Pricing",
                "Maps",
                "Our Initiatives",
                "Partners",
                "Blog",
                "For Business",
                "Sign in",
                "Support"
        );

        getDriver().get(BASE_URL);
        Thread.sleep(3000);

        List<WebElement> desktopMenuElements = getDriver().findElements(By.xpath("//div[@id = 'desktop-menu']/ul/li"));
        List<String> actualDesktopMenuNames = new ArrayList<>();

        for(int i = 0; i < desktopMenuElements.size(); i++) {
            actualDesktopMenuNames.add(desktopMenuElements.get(i).getText());
        }

        Assert.assertEquals(actualDesktopMenuNames, expectedDesktopMenuNames);
    }

    @Test
    public void testLogoIsClickable() throws InterruptedException {

        String expectedResultHref = "https://openweathermap.org/";
        String expectedResultImage = "https://openweathermap.org/themes/openweathermap/assets/img/logo_white_cropped.png";

        getDriver().get(BASE_URL);
        Thread.sleep(5000);

        WebElement logo = getDriver().findElement(By.xpath("//ul[@id = 'first-level-nav']/li[@class = 'logo']"));
        logo.click();

        WebElement logoLink = getDriver().findElement(By.xpath("//ul[@id = 'first-level-nav']/li[@class = 'logo']/a"));
        WebElement logoImage = getDriver().findElement(By.xpath("//ul[@id = 'first-level-nav']/li[@class = 'logo']/a/img"));

        String actualResultURL = getDriver().getCurrentUrl();
        String actualResultHref = logoLink.getAttribute("href");
        String actualResultImage = logoImage.getAttribute("src");

        Assert.assertEquals(actualResultURL, BASE_URL);
        Assert.assertEquals(actualResultHref, expectedResultHref);
        Assert.assertEquals(actualResultImage, expectedResultImage);
    }
}

