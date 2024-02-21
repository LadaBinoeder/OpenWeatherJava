import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.BaseTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainTest extends BaseTest {

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

    @Test
    public void testURLAndTitle() throws IOException {

        final String expectedResultURL = "https://openweathermap.org/";
        final String expectedResultTitle = "Сurrent weather and forecast - OpenWeatherMap";

        openBaseUrl();

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

        boolean logoIsDisplayed = elementIsDisplayed(LOGO);

        Assert.assertTrue(logoIsDisplayed);
    }

    @Test
    public void testPlaceholderIsDisplayed() {

        openBaseUrl();

        boolean placeholderIsDisplayed = elementIsDisplayed(PLACEHOLDER);

        Assert.assertTrue(placeholderIsDisplayed);
    }

    @Test
    public void testAllTheElementsInDesktopMenuAreDisplayed() {

        final int expectedResultNumber = 12;

        openBaseUrl();

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
        clickElement(LOGO);

        String actualResultURL = getDriver().getCurrentUrl();
        String actualResultHref = getAttribute(LOGO_LINK, "href");
        String actualResultImage = getAttribute(LOGO_IMAGE, "src");

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

        String actualResultLink = getAttribute(WEATHER_IN_YOUR_CITY_PLACEHOLDER_LINK,"action");
        String actualResultText = getAttribute(WEATHER_IN_YOUR_CITY_PLACEHOLDER_TEXT, "placeholder");

        clickElement(WEATHER_IN_YOUR_CITY_PLACEHOLDER_LINK);
        enterValue(WEATHER_IN_YOUR_CITY_PLACEHOLDER_TEXT, city);

        String actualResultPage = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertEquals(actualResultText, expectedResultText);
        Assert.assertEquals(actualResultPage, expectedResultPage);
    }

    @Test
    public void testGuideIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/guide";

        openBaseUrl();

        String actualResultLink = getAttribute(GUIDE_MENU, "href");

        clickElement(GUIDE_MENU);

        boolean newPageIsOpened = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpened);
    }

    @Test
    public void testApiIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/api";

        openBaseUrl();

        String actualResultLink = getAttribute(API_MENU, "href");

        clickElement(API_MENU);

        boolean newPageIsOpened = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpened);
    }

    @Test
    public void testDashboardIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/weather-dashboard";

        openBaseUrl();

        String actualResultLink = getAttribute(DASHBOARD_MENU, "href");

        clickElement(DASHBOARD_MENU);

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testMarketplaceIsClickable() {

        final String expectedResultLink = "https://home.openweathermap.org/marketplace";

        openBaseUrl();

        String actualResultLink = getAttribute(MARKETPLACE_MENU, "href");

        clickElement(MARKETPLACE_MENU);
        switchToSecondWindow();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testPricingIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/price";

        openBaseUrl();

        String actualResultLink = getAttribute(PRICING_MENU, "href");

        clickElement(PRICING_MENU);

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testMapsIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/weathermap";

        openBaseUrl();

        String actualResultLink = getAttribute(MAPS_MENU, "href");

        clickElement(MAPS_MENU);

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testOurInitiativesIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/our-initiatives";

        openBaseUrl();

        String actualResultLink = getAttribute(OUR_INITIATIVES_MENU, "href");

        clickElement(OUR_INITIATIVES_MENU);

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testPartnersIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/examples";

        openBaseUrl();

        String actualResultLink = getAttribute(PARTNERS_MENU, "href");

        clickElement(PARTNERS_MENU);

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testBlogIsClickable() {

        final String expectedResultLink = "https://openweather.co.uk/blog/category/weather";

        openBaseUrl();

        String actualResultLink = getAttribute(BLOG_MENU, "href");

        clickElement(BLOG_MENU);
        switchToSecondWindow();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testForBusinessIsClickable() {

        final String expectedResultLink = "https://openweather.co.uk/";

        openBaseUrl();

        String actualResultLink = getAttribute(FOR_BUSINESS_MENU, "href");

        clickElement(FOR_BUSINESS_MENU);
        switchToSecondWindow();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testSignInIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/home/sign_in";

        openBaseUrl();

        String actualResultLink = getAttribute(SIGN_IN_MENU, "href");

        clickElement(SIGN_IN_MENU);

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testSupportIsClickableAndHasThreeSubmenus() {

        final String expectedResultClass = "dropdown-menu dropdown-visible";
        final int expectedResultNumberOfSubmenus = 3;

        openBaseUrl();
        clickElement(SUPPORT_MENU);

        List<WebElement> submenus = getDriver().findElements(By.xpath("//ul[@class = \"dropdown-menu dropdown-visible\"]/li"));

        String actualResultClass = getAttribute(SUPPORT_DROPDOWN_MENU, "class");

        int actualResultNumberOfSubmenus = submenus.size();

        Assert.assertEquals(actualResultClass, expectedResultClass);
        Assert.assertEquals(actualResultNumberOfSubmenus, expectedResultNumberOfSubmenus);
    }

    @Test
    public void testFaqIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/faq";

        openBaseUrl();
        clickElement(SUPPORT_MENU);

        String actualResultLink = getAttribute(FAQ_SUBMENU, "href");

        clickElement(FAQ_SUBMENU);

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testHowToStartIsClickable() {

        final String expectedResultLink = "https://openweathermap.org/appid";

        openBaseUrl();
        clickElement(SUPPORT_MENU);

        String actualResultLink = getAttribute(HOW_TO_START_SUBMENU, "href");

        clickElement(HOW_TO_START_SUBMENU);

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testAskAQuestionIsClickable() {

        final String expectedResultLink = "https://home.openweathermap.org/questions";

        openBaseUrl();
        clickElement(SUPPORT_MENU);

        String actualResultLink = getAttribute(ASK_A_QUESTION_SUBMENU, "href");

        clickElement(ASK_A_QUESTION_SUBMENU);
        switchToSecondWindow();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualResultLink, expectedResultLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testVerifyHeader() {

        final String expectedResultHeader = "OpenWeather";

        openBaseUrl();

        String actualResultHeader = getText(HEADER);

        Assert.assertEquals(actualResultHeader, expectedResultHeader);
    }

    @Test
    public void testVerifySubtitle() {

        final String expectedResultSubtitle = "Weather forecasts, nowcasts and history in a fast and elegant way";

        openBaseUrl();

        String actualResultSubtitle = getText(SUBTITLE);

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
        clickElement(DIFFERENT_WEATHER_BUTTON);

        boolean isVisible = elementIsDisplayed(DIFFERENT_WEATHER_POPUP);

        clickElement(CLOSE_ICON_DIFFERENT_WEATHER_POPUP);
        getWait5().until(ExpectedConditions.invisibilityOfElementLocated(DIFFERENT_WEATHER_POPUP));

        boolean isNotVisible = elementIsNotDisplayed(DIFFERENT_WEATHER_POPUP);

        Assert.assertTrue(isVisible);
        Assert.assertFalse(isNotVisible);
    }

    @Test
    public void testUnitsSwitcher() {

        final String expectedResultCelsiusSelected = "left: 2pt;";
        final String expectedResultFahrenheitSelected = "slideRight";

        openBaseUrl();

        String actualResultCelsiusSelected = getAttribute(SELECTED_UNIT, "style");

        clickElement(FAHRENHEIT_UNIT);

        String actualResultFahrenheitSelected = getAttribute(SELECTED_UNIT, "className");

        Assert.assertEquals(actualResultCelsiusSelected, expectedResultCelsiusSelected);
        Assert.assertEquals(actualResultFahrenheitSelected, expectedResultFahrenheitSelected);
    }

    @Test
    public void testUnitsSwitcherChangesCurrentWeather() {

        openBaseUrl();

        boolean unitsDisplayedInCelsius = verifyNeededUnitsDisplayed(CURRENT_TEMPERATURE, "C");
        int valueDisplayedInCelsius = getTemperatureFigureFromText(CURRENT_TEMPERATURE);

        clickElement(FAHRENHEIT_UNIT);
        waitTillTextChanges(CURRENT_TEMPERATURE, String.valueOf(valueDisplayedInCelsius));

        boolean unitsDisplayedInFahrenheit = verifyNeededUnitsDisplayed(CURRENT_TEMPERATURE, "F");
        int valueDisplayedInFahrenheit = getTemperatureFigureFromText(CURRENT_TEMPERATURE);
        boolean temperatureHasChanged = celsiusToFahrenheitConvertingCorresponds(valueDisplayedInCelsius, valueDisplayedInFahrenheit);

        Assert.assertTrue(unitsDisplayedInCelsius);
        Assert.assertTrue(unitsDisplayedInFahrenheit);
        Assert.assertTrue(temperatureHasChanged);
    }

    @Test
    public void testUnitsSwitcherChangesEightDayForecast() {

        openBaseUrl();

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

        String actualResultPlaceholderText = getAttribute(SEARCH_BLOCK_INPUT, "placeholder");

        Assert.assertEquals(actualResultPlaceholderText, expectedResultPlaceholderText);
    }

    @Test
    public void testVerifySearchButtonIsClickable() {

        final String cityName = "Madrid";

        openBaseUrl();
        putInValue(SEARCH_BLOCK_INPUT, cityName);
        clickElement(SEARCH_BUTTON);
        waitTillElementIsVisible(SEARCH_DROPDOWN_MENU);

        List<WebElement> searchDropdownList = getDriver().findElements(By.xpath("//ul[@class = 'search-dropdown-menu']/li"));

        Assert.assertFalse(searchDropdownList.isEmpty());
    }

    @Test
    public void testVerifySearchButtonIsClickableByEnter() {

        final String cityName = "Madrid";

        openBaseUrl();
        enterValue(SEARCH_BLOCK_INPUT, cityName);
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

        String currentLocationDisplayed = getText(LOCATION_DISPLAYED);

        enterValue(SEARCH_BLOCK_INPUT, cityName);
        waitTillElementIsVisible(SEARCH_DROPDOWN_MENU);

        List<WebElement> searchDropdownList = getDriver().findElements(By.xpath("//ul[@class = 'search-dropdown-menu']/li"));

        clickListElement(searchDropdownList, 0);
        waitTillTextChanges(LOCATION_DISPLAYED, currentLocationDisplayed);

        String actualResultLocationDisplayed = getText(LOCATION_DISPLAYED);

        Assert.assertEquals(actualResultLocationDisplayed, expectedResultLocationDisplayed);
    }

    @Test
    public void testVerifySearchButtonEmptySearch() {

        openBaseUrl();
        clickElement(SEARCH_BUTTON);

        Assert.assertFalse(elementIsNotDisplayed(SEARCH_DROPDOWN_MENU));
    }

    @Test
    public void testVerifySearchButtonInvalidInput() {

        String invalidInput = "lmmslg";

        final String expectedResultErrorText = "Not found. To make search more precise put the city's name, comma, 2-letter country code (ISO3166).";
        final String expectedResultErrorWidgetText = "No results for " + invalidInput;

        openBaseUrl();
        enterValue(SEARCH_BLOCK_INPUT, invalidInput);
        waitTillElementIsVisible(ERROR_WIDGET_CITY_NOT_FOUND);

        String actualResultErrorText = getText(ERROR_CITY_NOT_FOUND);
        String actualResultErrorWidgetText = getText(ERROR_WIDGET_CITY_NOT_FOUND);

        Assert.assertEquals(actualResultErrorText, expectedResultErrorText);
        Assert.assertEquals(actualResultErrorWidgetText, expectedResultErrorWidgetText);
    }

    @Test
    public void testErrorWidgetCloses() {

        String invalidInput = "lmmslg";

        openBaseUrl();
        enterValue(SEARCH_BLOCK_INPUT, invalidInput);
        waitTillElementIsVisible(ERROR_WIDGET_CITY_NOT_FOUND);
        clickElement(ERROR_WIDGET_CITY_NOT_FOUND_ICON_CLOSE);

        Assert.assertFalse(elementIsNotDisplayed(ERROR_WIDGET_CITY_NOT_FOUND));
    }

    @Test
    public void testVerifySearchButtonShowsCorrespondingResults() {

        final String cityName = "Madrid";

        openBaseUrl();
        putInValue(SEARCH_BLOCK_INPUT, cityName);
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





