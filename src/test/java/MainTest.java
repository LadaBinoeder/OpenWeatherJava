import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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
    private final By WEATHER_IN_YOUR_CITY_PLACEHOLDER_LINK = By.xpath("//li[@id = 'desktop-menu']/form");
    private final By WEATHER_IN_YOUR_CITY_PLACEHOLDER_TEXT = By.xpath("//li[@id = 'desktop-menu']/form/input[@type = 'text']");
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
    private final By HEADER = By.xpath("//div[@class = 'mobile-padding main-page banner-content main-website']/h1/span");
    private final By SUBTITLE = By.xpath("//div[@class = 'mobile-padding main-page banner-content main-website']/h2/span");
    private final By PLACEHOLDER = By.xpath("//li[@id = 'desktop-menu']//input[@name = 'q']");
    private final By DIFFERENT_WEATHER_BUTTON = By.xpath("//span[@class = 'control-el owm-switch']");
    private final By DIFFERENT_WEATHER_POPUP = By.className("pop-up-container");
    private final By CLOSE_ICON_DIFFERENT_WEATHER_POPUP = By.xpath("//div[@class = 'pop-up-container']/*[local-name() = 'svg']");
    private final By SELECTED_UNIT = By.id("selected");
    private final By CELSIUS_UNIT = By.xpath("//div[@class = 'switch-container']//*[text() = 'Metric: °C, m/s']");
    private final By FAHRENHEIT_UNIT = By.xpath("//div[@class = 'switch-container']//*[text() = 'Imperial: °F, mph']");
    private final By CURRENT_TEMPERATURE = By.xpath("//div[@class = 'current-temp']/span[@class = 'heading']");
    private final By SEARCH_BLOCK_INPUT = By.xpath("//div[@class = 'search-container']//input");
    private final By SEARCH_BUTTON = By.xpath("//div[@class = 'search']//button");
    private final By SEARCH_DROPDOWN_MENU = By.xpath("//ul[@class = 'search-dropdown-menu']");
    private final By LOCATION_DISPLAYED = By.xpath("//div[@class = 'section-content']//h2");
    private final By ERROR_CITY_NOT_FOUND = By.xpath("//div[@class = 'search-block']/div[@class = 'sub not-found notFoundOpen']");
    private final By ERROR_WIDGET_CITY_NOT_FOUND = By.xpath("//div[@class = 'widget-notification']");
    private final By ERROR_WIDGET_CITY_NOT_FOUND_ICON_CLOSE = By.xpath("//div[@class = 'widget-notification']//*[local-name() = 'svg']");

    private void openBaseUrl() {
        getDriver().get(BASE_URL);

    }

    private void waitTillGreyContainerDisappears() {
        getWait10().until(ExpectedConditions.invisibilityOfElementLocated(By.className("owm-loader-container")));

    }

    private void waitTillTextChanges(By by, String text) {
        getWait5().until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(by, text)));

    }

    private void waitTillElementIsVisible(By by) {

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(by));
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

    private void clickListElement(List<WebElement> elements, int index) {

        elements.get(index).click();
    }

    private void enterValue(By by, String value, ChromeDriver driver) {

        driver.findElement(by).sendKeys(value);
        driver.findElement(by).sendKeys(Keys.ENTER);
    }

    private void putInValue(By by, String value, ChromeDriver driver) {

        driver.findElement(by).sendKeys(value);
    }
    private boolean elementIsDisplayed(By by, ChromeDriver driver) {

        boolean isDisplayed = driver.findElement(by).isDisplayed();
        return isDisplayed;

    }

    private boolean elementIsNotDisplayed(By by, ChromeDriver driver) {
        try {
            driver.findElement(by);
            return true;

        } catch (NoSuchElementException e) {
            return false;

        }
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

    private boolean verifyNeededUnitsDisplayed(By by, String units, ChromeDriver driver) {
        String text = getText(by, driver);
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

    private int convertCelsiusToFahrenheit(int temperatureInCelsius) {

        int temperatureInFahrenheit = (int)(temperatureInCelsius * 1.8 + 32);
        return temperatureInFahrenheit;

    }

    private boolean celsiusToFahrenheitConvertingCorresponds(int temperatureInCelsius, int temperatureInFahrenheit) {

        if(temperatureInFahrenheit == convertCelsiusToFahrenheit(temperatureInCelsius)
                || temperatureInFahrenheit == convertCelsiusToFahrenheit(temperatureInCelsius) + 1)
        {
            return true;

        }
        return false;

    }

    public int getTemperatureFigureFromText(By by) {

        String currentTemp = getText(by, getDriver());
        char[] arrayCurrentTemp = currentTemp.toCharArray();
        currentTemp = "";

        for(int i = 0; i < arrayCurrentTemp.length - 2; i++) {
            currentTemp = currentTemp + arrayCurrentTemp[i];
        }
        return Integer.valueOf(currentTemp);

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

        boolean logoIsDisplayed = elementIsDisplayed(LOGO, getDriver());

        Assert.assertTrue(logoIsDisplayed);
    }

    @Test
    public void testPlaceholderIsDisplayed() {

        getDriver().get(BASE_URL);
        waitTillGreyContainerDisappears();

        boolean placeholderIsDisplayed = elementIsDisplayed(PLACEHOLDER, getDriver());

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

        String actualResultLink = getAttribute(WEATHER_IN_YOUR_CITY_PLACEHOLDER_LINK,"action", getDriver());
        String actualResultText = getAttribute(WEATHER_IN_YOUR_CITY_PLACEHOLDER_TEXT, "placeholder", getDriver());

        clickElement(WEATHER_IN_YOUR_CITY_PLACEHOLDER_LINK);
        enterValue(WEATHER_IN_YOUR_CITY_PLACEHOLDER_TEXT, city, getDriver());

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

    @Test
    public void testDifferentWeatherPopupOpensAndCloses() throws NoSuchElementException {

        openBaseUrl();
        waitTillGreyContainerDisappears();
        clickElement(DIFFERENT_WEATHER_BUTTON);

        boolean isVisible = elementIsDisplayed(DIFFERENT_WEATHER_POPUP, getDriver());

        clickElement(CLOSE_ICON_DIFFERENT_WEATHER_POPUP);
        getWait5().until(ExpectedConditions.invisibilityOfElementLocated(DIFFERENT_WEATHER_POPUP));

        boolean isNotVisible = elementIsNotDisplayed(DIFFERENT_WEATHER_POPUP, getDriver());

        Assert.assertTrue(isVisible);
        Assert.assertFalse(isNotVisible);
    }

    @Test
    public void testUnitsSwitcher() {

        final String expectedResultCelsiusSelected = "left: 2pt;";
        final String expectedResultFahrenheitSelected = "slideRight";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultCelsiusSelected = getAttribute(SELECTED_UNIT, "style", getDriver());

        clickElement(FAHRENHEIT_UNIT);

        String actualResultFahrenheitSelected = getAttribute(SELECTED_UNIT, "className",
                getDriver());

        Assert.assertEquals(actualResultCelsiusSelected, expectedResultCelsiusSelected);
        Assert.assertEquals(actualResultFahrenheitSelected, expectedResultFahrenheitSelected);
    }

    @Test
    public void testUnitsSwitcherChangesCurrentWeather() {

        openBaseUrl();
        waitTillGreyContainerDisappears();

        boolean unitsDisplayedInCelsius = verifyNeededUnitsDisplayed(CURRENT_TEMPERATURE, "C", getDriver());
        int valueDisplayedInCelsius = getTemperatureFigureFromText(CURRENT_TEMPERATURE);

        clickElement(FAHRENHEIT_UNIT);
        waitTillTextChanges(CURRENT_TEMPERATURE, String.valueOf(valueDisplayedInCelsius));

        boolean unitsDisplayedInFahrenheit = verifyNeededUnitsDisplayed(CURRENT_TEMPERATURE, "F", getDriver());
        int valueDisplayedInFahrenheit = getTemperatureFigureFromText(CURRENT_TEMPERATURE);
        boolean temperatureHasChanged = celsiusToFahrenheitConvertingCorresponds(valueDisplayedInCelsius, valueDisplayedInFahrenheit);

        Assert.assertTrue(unitsDisplayedInCelsius);
        Assert.assertTrue(unitsDisplayedInFahrenheit);
        Assert.assertTrue(temperatureHasChanged);
    }

    @Test
    public void testUnitsSwitcherChangesEightDayForecast() {

        openBaseUrl();
        waitTillGreyContainerDisappears();

        List<WebElement> eightDayForecast = getDriver().findElements(By.xpath("//div[@class = 'day-list-values']/div/span"));

        boolean unitsDisplayedInCelsius = verifyArrayContainsNeededUnits(eightDayForecast, "C");

        clickElement(FAHRENHEIT_UNIT);

        boolean unitsDisplayedInFahrenheit = verifyArrayContainsNeededUnits(eightDayForecast, "F");;

        Assert.assertTrue(unitsDisplayedInCelsius);
        Assert.assertTrue(unitsDisplayedInFahrenheit);
    }

    @Test
    public void testVerifySearchBlockPlaceholder() {

        final String expectedResultPlaceholderText = "Search city";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String actualResultPlaceholderText = getAttribute(SEARCH_BLOCK_INPUT, "placeholder", getDriver());

        Assert.assertEquals(actualResultPlaceholderText, expectedResultPlaceholderText);
    }

    @Test
    public void testVerifySearchButtonIsClickable() {

        final String cityName = "Madrid";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        putInValue(SEARCH_BLOCK_INPUT, cityName, getDriver());
        clickElement(SEARCH_BUTTON);

        waitTillElementIsVisible(SEARCH_DROPDOWN_MENU);

        List<WebElement> searchDropdownList = getDriver().findElements(By.xpath("//ul[@class = 'search-dropdown-menu']/li"));

        Assert.assertFalse(searchDropdownList.isEmpty());
    }

    @Test
    public void testVerifySearchButtonIsClickableByEnter() {

        final String cityName = "Madrid";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        enterValue(SEARCH_BLOCK_INPUT, cityName, getDriver());
        clickElement(SEARCH_BUTTON);

        waitTillElementIsVisible(SEARCH_DROPDOWN_MENU);

        List<WebElement> searchDropdownList = getDriver().findElements(By.xpath("//ul[@class = 'search-dropdown-menu']/li"));

        Assert.assertFalse(searchDropdownList.isEmpty());
    }

    @Test
    public void testVerifySearchButtonChangesTheLocationShown() {

        final String cityName = "Paris";
        final String expectedResultLocationDisplayed = "Paris, FR";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        String currentLocationDisplayed = getText(LOCATION_DISPLAYED, getDriver());

        enterValue(SEARCH_BLOCK_INPUT, cityName, getDriver());
        waitTillElementIsVisible(SEARCH_DROPDOWN_MENU);

        List<WebElement> searchDropdownList = getDriver().findElements(By.xpath("//ul[@class = 'search-dropdown-menu']/li"));

        clickListElement(searchDropdownList, 0);
        waitTillTextChanges(LOCATION_DISPLAYED, currentLocationDisplayed);

        String actualResultLocationDisplayed = getText(LOCATION_DISPLAYED, getDriver());

        Assert.assertEquals(actualResultLocationDisplayed, expectedResultLocationDisplayed);
    }

    @Test
    public void testVerifySearchButtonEmptySearch() {

        openBaseUrl();
        waitTillGreyContainerDisappears();

        clickElement(SEARCH_BUTTON);

        Assert.assertFalse(elementIsNotDisplayed(SEARCH_DROPDOWN_MENU, getDriver()));
    }

    @Test
    public void testVerifySearchButtonInvalidInput() {

        String invalidInput = "lmmslg";

        final String expectedResultErrorText = "Not found. To make search more precise put the city's name, comma, 2-letter country code (ISO3166).";
        final String expectedResultErrorWidgetText = "No results for " + invalidInput;

        openBaseUrl();
        waitTillGreyContainerDisappears();

        enterValue(SEARCH_BLOCK_INPUT, invalidInput, getDriver());

        waitTillElementIsVisible(ERROR_WIDGET_CITY_NOT_FOUND);

        String actualResultErrorText = getText(ERROR_CITY_NOT_FOUND, getDriver());
        String actualResultErrorWidgetText = getText(ERROR_WIDGET_CITY_NOT_FOUND, getDriver());

        Assert.assertEquals(actualResultErrorText, expectedResultErrorText);
        Assert.assertEquals(actualResultErrorWidgetText, expectedResultErrorWidgetText);
    }

    @Test
    public void testErrorWidgetCloses() {

        String invalidInput = "lmmslg";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        enterValue(SEARCH_BLOCK_INPUT, invalidInput, getDriver());
        waitTillElementIsVisible(ERROR_WIDGET_CITY_NOT_FOUND);
        clickElement(ERROR_WIDGET_CITY_NOT_FOUND_ICON_CLOSE);

        Assert.assertFalse(elementIsNotDisplayed(ERROR_WIDGET_CITY_NOT_FOUND, getDriver()));
    }

    @Test
    public void testVerifySearchButtonShowsCorrespondingResults() {

        final String cityName = "Madrid";

        openBaseUrl();
        waitTillGreyContainerDisappears();

        putInValue(SEARCH_BLOCK_INPUT, cityName, getDriver());
        clickElement(SEARCH_BUTTON);

        waitTillElementIsVisible(SEARCH_DROPDOWN_MENU);

        List<WebElement> searchDropdownList = getDriver().findElements(By.xpath("//ul[@class = 'search-dropdown-menu']/li"));

        int correspondingCityNameCount = 0;

        for(int i = 0; i < searchDropdownList.size(); i++) {
            if(searchDropdownList.get(i).getText().contains(cityName)) {
                correspondingCityNameCount++;
            }
        }

        boolean cityNameCorresponds = false;

        if(correspondingCityNameCount == searchDropdownList.size()) {
            cityNameCorresponds = true;
        }

        Assert.assertTrue(cityNameCorresponds);
    }
}





