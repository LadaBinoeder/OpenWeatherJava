import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainTest extends BaseTest {

    private final static String BASE_URL = "https://openweathermap.org/";

    @Test
    public void testURLAndTitle() throws IOException {

        final String expectedResultURL = "https://openweathermap.org/";
        final String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        String actualResultURL = getDriver().getCurrentUrl();
        String actualResultTitle = getDriver().getTitle();

        WebElement anyElement = getDriver().findElement(By.xpath("//*"));
        boolean pageIsNotEmpty = false;
        if (anyElement != null) {
            pageIsNotEmpty = true;
        }

        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        boolean noErrors = true;
        if (responseCode >= 400 && responseCode < 600) {
            noErrors = false;
        }

        Assert.assertEquals(actualResultURL, expectedResultURL);
        Assert.assertEquals(actualResultTitle, expectedResultTitle);
        Assert.assertTrue(pageIsNotEmpty);
        Assert.assertTrue(noErrors);
    }

    @Test
    public void testLogoIsDisplayed() {

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement logo = getDriver().findElement(By.xpath("//ul[@id = 'first-level-nav']/li[@class = 'logo']"));
        boolean logoIsDisplayed = logo.isDisplayed();

        Assert.assertTrue(logoIsDisplayed);
    }

    @Test
    public void testPlaceholderIsDisplayed() {

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement placeholder = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']//input[@name = 'q']"));
        boolean placeholderIsDisplayed = placeholder.isDisplayed();

        Assert.assertTrue(placeholderIsDisplayed);
    }

    @Test
    public void testAllTheElementsInDesktopMenuAreDisplayed() {

        final int expectedResultNumber = 12;

        getDriver().get(BASE_URL);
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        List<WebElement> desktopMenuElements = getDriver().findElements(By.xpath("//li[@id = 'desktop-menu']/ul/li"));

        int actualResultNumber = desktopMenuElements.size();

        Assert.assertEquals(actualResultNumber, expectedResultNumber);
    }

    @Test
    public void testNamesOfElementsInDesktopMenu() {

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
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        List<WebElement> desktopMenuElements = getDriver().findElements(By.xpath("//li[@id = 'desktop-menu']/ul/li"));
        List<String> actualDesktopMenuNames = new ArrayList<>();

        for (int i = 0; i < desktopMenuElements.size(); i++) {
            actualDesktopMenuNames.add(desktopMenuElements.get(i).getText());
        }

        Assert.assertEquals(actualDesktopMenuNames, expectedDesktopMenuNames);
    }

    @Test
    public void testLogoIsClickable() {

        String expectedResultLink = "https://openweathermap.org/";
        String expectedResultImage = "https://openweathermap.org/themes/openweathermap/assets/img/logo_white_cropped.png";

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement logo = getDriver().findElement(By.xpath("//ul[@id = 'first-level-nav']/li[@class = 'logo']"));
        logo.click();

        WebElement logoLink = getDriver().findElement(By.xpath("//ul[@id = 'first-level-nav']/li[@class = 'logo']/a"));
        WebElement logoImage = getDriver().findElement(By.xpath("//ul[@id = 'first-level-nav']/li[@class = 'logo']/a/img"));

        String actualResultURL = getDriver().getCurrentUrl();
        String actualResultHref = logoLink.getAttribute("href");
        String actualResultImage = logoImage.getAttribute("src");

        Assert.assertEquals(actualResultURL, BASE_URL);
        Assert.assertEquals(actualResultHref, expectedResultLink);
        Assert.assertEquals(actualResultImage, expectedResultImage);
    }

    @Test
    public void testPlaceholderIsClickable() {

        final String city = "Rome";
        String expectedResultLink = "https://openweathermap.org/find";
        String expectedResultText = "Weather in your city";
        String expectedResultPage = expectedResultLink + "?q=" + city;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement placeholderLink = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']/form"));
        WebElement placeholderText = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']/form/input[@type = 'text']"));

        String actualResultLink = placeholderLink.getAttribute("action");
        String actualResultText = placeholderText.getAttribute("placeholder");

        placeholderLink.click();
        placeholderText.sendKeys(city);
        placeholderText.sendKeys(Keys.ENTER);

        String actualResultPage = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertEquals(actualResultText, expectedResultText);
        Assert.assertEquals(actualResultPage, expectedResultPage);
    }

    @Test
    public void testGuideIsClickable() {

        String expectedResultLink = "https://openweathermap.org/guide";
        boolean newPageIsOpened = true;

        getDriver().get(BASE_URL);
        getWait20().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement guideMenu = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']//a[text() = \"Guide\"]"));

        String actualResultLink = guideMenu.getAttribute("href");

        guideMenu.click();

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpened = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpened);
    }

    @Test
    public void testApiIsClickable() {

        String expectedResultLink = "https://openweathermap.org/api";
        boolean newPageisOpened = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement apiMenu = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']//a[text() = \"API\"]"));

        String actualResultLink = apiMenu.getAttribute("href");

        apiMenu.click();

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageisOpened = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageisOpened);
    }

    @Test
    public void testDashboardIsClickable() {

        String expectedResultLink = "https://openweathermap.org/weather-dashboard";
        boolean newPageIsOpen = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement dashboardMenu = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']//a[text() = \"Dashboard\"]"));

        String actualResultLink = dashboardMenu.getAttribute("href");

        dashboardMenu.click();

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpen = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testMarketplaceIsClickable() {

        String expectedResultLink = "https://home.openweathermap.org/marketplace";
        boolean newPageIsOpen = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement marketplaceMenu = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Marketplace']"));

        String actualResultLink = marketplaceMenu.getAttribute("href");

        marketplaceMenu.click();

        String handle = getDriver().getWindowHandles().toArray()[1].toString();
        getDriver().switchTo().window(handle);

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpen = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testPricingIsClickable() {

        String expectedResultLink = "https://openweathermap.org/price";
        boolean newPageOpen = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement pricingMenu = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']//a[text() = \"Pricing\"]"));

        String actualResultLink = pricingMenu.getAttribute("href");

        pricingMenu.click();

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageOpen = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageOpen);
    }

    @Test
    public void testMapsIsClickable() {

        String expectedResultLink = "https://openweathermap.org/weathermap";
        boolean newPageIsOpen = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement mapsMenu = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']//a[text() = \"Maps\"]"));

        String actualResultLink = mapsMenu.getAttribute("href");

        mapsMenu.click();

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpen = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testOurInitiativesIsClickable() {

        String expectedResultLink = "https://openweathermap.org/our-initiatives";
        boolean newPageIsOpen = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement ourInitiativesMenu = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Our Initiatives']"));

        String actualResultLink = ourInitiativesMenu.getAttribute("href");

        ourInitiativesMenu.click();

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpen = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testPartnersIsClickable() {

        String expectedResultLink = "https://openweathermap.org/examples";
        boolean newPageIsOpen = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement partnersMenu = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Partners']"));

        String actualResultLink = partnersMenu.getAttribute("href");

        partnersMenu.click();

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpen = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testBlogIsClickable() {

        String expectedResultLink = "https://openweather.co.uk/blog/category/weather";
        boolean newPageIsOpen = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement blogMenu = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Blog']"));

        String actualResultLink = blogMenu.getAttribute("href");

        blogMenu.click();

        String handle = getDriver().getWindowHandles().toArray()[1].toString();
        getDriver().switchTo().window(handle);

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpen = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testForBusinessIsClickable() {

        String expectedResultLink = "https://openweather.co.uk/";
        boolean newPageIsOpen = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement forBusinessMenu = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']//a[text() = 'For Business']"));

        String actualResultLink = forBusinessMenu.getAttribute("href");

        forBusinessMenu.click();

        String handle = getDriver().getWindowHandles().toArray()[1].toString();
        getDriver().switchTo().window(handle);

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpen = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testSignInIsClickable() {

        String expectedResultLink = "https://openweathermap.org/home/sign_in";
        boolean newPageIsOpen = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement signInMenu = getDriver().findElement(By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Sign in']"));

        String actualResultLink = signInMenu.getAttribute("href");

        signInMenu.click();

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpen = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testSupportIsClickableAndHasThreeSubmenus() {

        String expectedResultClass = "dropdown-menu dropdown-visible";
        int expectedResultNumberOfSubmenus = 3;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement supportMenu = getDriver().findElement(By.id("support-dropdown"));
        WebElement supportDropdownMenu = getDriver().findElement(By.id("support-dropdown-menu"));
        supportMenu.click();

        List<WebElement> submenus = getDriver().findElements(By.xpath("//ul[@class = \"dropdown-menu dropdown-visible\"]/li"));

        String actualResultClass = supportDropdownMenu.getAttribute("class");
        int actualResultNumberOfSubmenus = submenus.size();

        Assert.assertEquals(actualResultClass, expectedResultClass);
        Assert.assertEquals(actualResultNumberOfSubmenus, expectedResultNumberOfSubmenus);
    }

    @Test
    public void testFaqIsClickable() {

        String expectedResultLink = "https://openweathermap.org/faq";
        boolean newPageIsOpen = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement supportMenu = getDriver().findElement(By.id("support-dropdown"));
        supportMenu.click();

        WebElement faqSubmenu = getDriver().findElement(By.linkText("FAQ"));

        String actualResultLink = faqSubmenu.getAttribute("href");

        faqSubmenu.click();

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpen = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testHowToStartIsClickable() {

        String expectedResultLink = "https://openweathermap.org/appid";
        boolean newPageIsOpen = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement supportMenu = getDriver().findElement(By.id("support-dropdown"));
        supportMenu.click();

        WebElement howToStartSubmenu = getDriver().findElement(By.linkText("How to start"));

        String actualResultLink = howToStartSubmenu.getAttribute("href");

        howToStartSubmenu.click();

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpen = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testAskAQuestionIsClickable() {

        String expectedResultLink = "https://home.openweathermap.org/questions";
        boolean newPageIsOpen = true;

        getDriver().get(BASE_URL);
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

        WebElement supportMenu = getDriver().findElement(By.id("support-dropdown"));
        supportMenu.click();

        WebElement askAQuestionSubmenu = getDriver().findElement(By.linkText("Ask a question"));

        String actualResultLink = askAQuestionSubmenu.getAttribute("href");

        askAQuestionSubmenu.click();

        String handle = getDriver().getWindowHandles().toArray()[1].toString();
        getDriver().switchTo().window(handle);

        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpen = false;
        }

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testVerifyHeader() {

        String expectedResultHeader = "OpenWeather";

        getDriver().get(BASE_URL);

        WebElement header = getDriver().findElement(By.xpath("//div[@class = 'mobile-padding main-page']/h1/span"));

        String actualResultHeader = header.getText();

        Assert.assertEquals(actualResultHeader, expectedResultHeader);
    }

    @Test
    public void testVerifySubtitle() {

        String expectedResultSurtitle = "Weather forecasts, nowcasts and history in a fast and elegant way";

        getDriver().get(BASE_URL);

        WebElement header = getDriver().findElement(By.xpath("//div[@class = 'mobile-padding main-page']/h2/span"));

        String actualResultSubtitle = header.getText();

        Assert.assertEquals(actualResultSubtitle, expectedResultSurtitle);
    }

    @Test
    public void testVerifyGreyContainerIsDisplayed() {

        getDriver().get(BASE_URL);

        WebElement greyContainer = getDriver().findElement(By.xpath("//div[@id = 'weather-widget']//div[@class = 'page-container']"));

        Assert.assertTrue(greyContainer.isDisplayed());
    }

    @Test
    public void testVerifySectionContentIsDisplayed() {

        getDriver().get(BASE_URL);

        WebElement sectionContent = getDriver().findElement(By.xpath("//div[@id = 'weather-widget']//div[@class = 'section-content']"));

        Assert.assertTrue(sectionContent.isDisplayed());
    }
}




