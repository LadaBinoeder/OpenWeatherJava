import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
    private final By LOGO = By.xpath("//ul[@id = 'first-level-nav']/li[@class = 'logo']");
    private final By LOGO_LINK = By.xpath("//ul[@id = 'first-level-nav']/li[@class = 'logo']/a");
    private final By LOGO_IMAGE = By.xpath("//ul[@id = 'first-level-nav']/li[@class = 'logo']/a/img");
    private final By PLACEHOLDER_LINK = By.xpath("//li[@id = 'desktop-menu']/form");
    private final By PLACEHOLDER_TEXT = By.xpath("//li[@id = 'desktop-menu']/form/input[@type = 'text']");
    private final By GUIDE_MENU = By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Guide']");
    private final By API_MENU = By.xpath("//li[@id = 'desktop-menu']//a[text() = 'API']");
    private final By DASHBOARD_MENU = By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Dashboard']");
    private final By MARKETPLACE_MENU = By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Marketplace']");
    private final By PRICING_MENU = By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Pricing']");
    private final By OUR_INITIATIVES_MENU = By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Our Initiatives']");
    private final By MAPS_MENU = By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Maps']");
    private final By PARTNERS_MENU = By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Partners']");
    private final By BLOG_MENU = By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Blog']");
    private final By FOR_BUSINESS_MENU = By.xpath("//li[@id = 'desktop-menu']//a[text() = 'For Business']");
    private final By SIGN_IN_MENU = By.xpath("//li[@id = 'desktop-menu']//a[text() = 'Sign in']");
    private final By SUPPORT_MENU = By.id("support-dropdown");
    private final By SUPPORT_DROPDOWN_MENU = By.id("support-dropdown-menu");
    private final By FAQ_SUBMENU = By.linkText("FAQ");
    private final By HOW_TO_START_SUBMENU = By.linkText("How to start");
    private final By ASK_A_QUESTION_SUBMENU = By.linkText("Ask a question");
    private final By HEADER = By.xpath("//div[@class = 'mobile-padding main-page']/h1/span");
    private final By SUBTITLE = By.xpath("//div[@class = 'mobile-padding main-page']/h2/span");
    private final By PLACEHOLDER = By.xpath("//li[@id = 'desktop-menu']//input[@name = 'q']");
    private void openBaseUrl() {
        getDriver().get(BASE_URL);

    }
    private void waitTillGreyContainerDisappears() {
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

    }
    private String getAttribute(By by, String attribute, ChromeDriver driver) {

        return driver.findElement(by).getAttribute(attribute);
    }
    private String getText(By by, ChromeDriver driver) {
        return driver.findElement(by).getText();

    }

    private void clickElement(By by) {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(by));
        getWait5().until(ExpectedConditions.elementToBeClickable(by)).click();

    }

    private void enterValue(By by, String value, ChromeDriver driver) {

        driver.findElement(by).sendKeys(value);
        driver.findElement(by).sendKeys(Keys.ENTER);
    }
    private boolean isDisplayed(By by, ChromeDriver driver) {

        boolean isDisplayed = driver.findElement(by).isDisplayed();
        return isDisplayed;

    }
    private boolean verifyNewPageOpen(ChromeDriver driver) {
        boolean newPageIsOpened = true;
        if (getDriver().getCurrentUrl().equals(BASE_URL)) {
            newPageIsOpened = false;

        }
        return newPageIsOpened;

    }

    private void switchToSecondWindow(ChromeDriver driver) {
        String handle = getDriver().getWindowHandles().toArray()[1].toString();
        getDriver().switchTo().window(handle);

    }

    @Test
    public void testURLAndTitle() throws IOException {

        final String expectedResultURL = "https://openweathermap.org/";
        final String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";

        openBaseUrl();
        waitTillGreyContainerDisappears();

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

        openBaseUrl();
        waitTillGreyContainerDisappears();

        boolean logoIsDisplayed = isDisplayed(LOGO, getDriver());

        Assert.assertTrue(logoIsDisplayed);
    }

    @Test
    public void testPlaceholderIsDisplayed() {

        getDriver().get(BASE_URL);
        waitTillGreyContainerDisappears();

        boolean placeholderIsDisplayed = isDisplayed(PLACEHOLDER, getDriver());

        Assert.assertTrue(placeholderIsDisplayed);
    }

    @Test
    public void testAllTheElementsInDesktopMenuAreDisplayed() {

        final int expectedResultNumber = 12;

        openBaseUrl();
        waitTillGreyContainerDisappears();

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

        openBaseUrl();
        waitTillGreyContainerDisappears();

        List<WebElement> desktopMenuElements = getDriver().findElements(By.xpath("//li[@id = 'desktop-menu']/ul/li"));
        List<String> actualDesktopMenuNames = new ArrayList<>();

        for (int i = 0; i < desktopMenuElements.size(); i++) {
            actualDesktopMenuNames.add(desktopMenuElements.get(i).getText());
        }

        Assert.assertEquals(actualDesktopMenuNames, expectedDesktopMenuNames);
    }

    @Test
    public void testLogoIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/";
        final String expectedResultImage = "https://openweathermap.org/themes/openweathermap/assets/img/logo_white_cropped.png";

        openBaseUrl();
        waitTillGreyContainerDisappears();
        clickElement(LOGO);

        String actualResultURL = getDriver().getCurrentUrl();
        String actualResultHref = getAttribute(LOGO_LINK, "href", getDriver());
        String actualResultImage = getAttribute(LOGO_IMAGE, "src", getDriver());

        Assert.assertEquals(actualResultURL, BASE_URL);
        Assert.assertEquals(actualResultHref, expectedResultLink);
        Assert.assertEquals(actualResultImage, expectedResultImage);
    }

    @Test
    public void testPlaceholderIsClickable() {

        final String city = "Rome";
        final String expectedResultLink = "https://openweathermap.org/find";
        final String expectedResultText = "Weather in your city";
        final String expectedResultPage = expectedResultLink + "?q=" + city;

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultLink = getAttribute(PLACEHOLDER_LINK,"action", getDriver());
        String actualResultText = getAttribute(PLACEHOLDER_TEXT, "placeholder", getDriver());

        clickElement(PLACEHOLDER_LINK);
        enterValue(PLACEHOLDER_TEXT, city, getDriver());

        String actualResultPage = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertEquals(actualResultText, expectedResultText);
        Assert.assertEquals(actualResultPage, expectedResultPage);
    }

    @Test
    public void testGuideIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/guide";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultLink = getAttribute(GUIDE_MENU, "href", getDriver());

        clickElement(GUIDE_MENU);

        boolean newPageIsOpened = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpened);
    }

    @Test
    public void testApiIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/api";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultLink = getAttribute(API_MENU, "href", getDriver());

        clickElement(API_MENU);

        boolean newPageIsOpened = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpened);
    }

    @Test
    public void testDashboardIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/weather-dashboard";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultLink = getAttribute(DASHBOARD_MENU, "href", getDriver());

        clickElement(DASHBOARD_MENU);

        boolean newPageIsOpen = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testMarketplaceIsClickable() {

        final String expectedResultLink = "https://home.openweathermap.org/marketplace";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultLink = getAttribute(MARKETPLACE_MENU, "href", getDriver());

        clickElement(MARKETPLACE_MENU);
        switchToSecondWindow(getDriver());

        boolean newPageIsOpen = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testPricingIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/price";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultLink = getAttribute(PRICING_MENU, "href", getDriver());

        clickElement(PRICING_MENU);

        boolean newPageIsOpen = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testMapsIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/weathermap";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultLink = getAttribute(MAPS_MENU, "href", getDriver());

        clickElement(MAPS_MENU);

        boolean newPageIsOpen = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testOurInitiativesIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/our-initiatives";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultLink = getAttribute(OUR_INITIATIVES_MENU, "href", getDriver());

        clickElement(OUR_INITIATIVES_MENU);

        boolean newPageIsOpen = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testPartnersIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/examples";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultLink = getAttribute(PARTNERS_MENU, "href", getDriver());

        clickElement(PARTNERS_MENU);

        boolean newPageIsOpen = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testBlogIsClickable() {

        final String expectedResultLink = "https://openweather.co.uk/blog/category/weather";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultLink = getAttribute(BLOG_MENU, "href", getDriver());

        clickElement(BLOG_MENU);
        switchToSecondWindow(getDriver());

        boolean newPageIsOpen = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testForBusinessIsClickable() {

        final String expectedResultLink = "https://openweather.co.uk/";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultLink = getAttribute(FOR_BUSINESS_MENU, "href", getDriver());

        clickElement(FOR_BUSINESS_MENU);
        switchToSecondWindow(getDriver());

        boolean newPageIsOpen = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testSignInIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/home/sign_in";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultLink = getAttribute(SIGN_IN_MENU, "href", getDriver());

        clickElement(SIGN_IN_MENU);

        boolean newPageIsOpen = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testSupportIsClickableAndHasThreeSubmenus() {

        final String expectedResultClass = "dropdown-menu dropdown-visible";
        final int expectedResultNumberOfSubmenus = 3;

        openBaseUrl();
        waitTillGreyContainerDisappears();

        clickElement(SUPPORT_MENU);

        List<WebElement> submenus = getDriver().findElements(By.xpath("//ul[@class = \"dropdown-menu dropdown-visible\"]/li"));

        String actualResultClass = getAttribute(SUPPORT_DROPDOWN_MENU, "class", getDriver());

        int actualResultNumberOfSubmenus = submenus.size();

        Assert.assertEquals(actualResultClass, expectedResultClass);
        Assert.assertEquals(actualResultNumberOfSubmenus, expectedResultNumberOfSubmenus);
    }

    @Test
    public void testFaqIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/faq";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        clickElement(SUPPORT_MENU);

        String actualResultLink = getAttribute(FAQ_SUBMENU, "href", getDriver());

        clickElement(FAQ_SUBMENU);

        boolean newPageIsOpen = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testHowToStartIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/appid";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        clickElement(SUPPORT_MENU);

        String actualResultLink = getAttribute(HOW_TO_START_SUBMENU, "href", getDriver());

        clickElement(HOW_TO_START_SUBMENU);

        boolean newPageIsOpen = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testAskAQuestionIsClickable() {

        final String expectedResultLink = "https://home.openweathermap.org/questions";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        clickElement(SUPPORT_MENU);

        String actualResultLink = getAttribute(ASK_A_QUESTION_SUBMENU, "href", getDriver());

        clickElement(ASK_A_QUESTION_SUBMENU);
        switchToSecondWindow(getDriver());

        boolean newPageIsOpen = verifyNewPageOpen(getDriver());

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testVerifyHeader() {

        final String expectedResultHeader = "OpenWeather";

        openBaseUrl();

        String actualResultHeader = getText(HEADER, getDriver());

        Assert.assertEquals(actualResultHeader, expectedResultHeader);
    }

    @Test
    public void testVerifySubtitle() {

        final String expectedResultSubtitle = "Weather forecasts, nowcasts and history in a fast and elegant way";

        openBaseUrl();

        String actualResultSubtitle = getText(SUBTITLE, getDriver());

        Assert.assertEquals(actualResultSubtitle, expectedResultSubtitle);
    }

    @Test
    public void testVerifyGreyContainerIsDisplayed() {

        openBaseUrl();

        WebElement greyContainer = getDriver().findElement(By.xpath("//div[@id = 'weather-widget']//div[@class = 'page-container']"));

        Assert.assertTrue(greyContainer.isDisplayed());
    }

    @Test
    public void testVerifySectionContentIsDisplayed() {

        openBaseUrl();

        WebElement sectionContent = getDriver().findElement(By.xpath("//div[@id = 'weather-widget']//div[@class = 'section-content']"));

        Assert.assertTrue(sectionContent.isDisplayed());
    }
}




