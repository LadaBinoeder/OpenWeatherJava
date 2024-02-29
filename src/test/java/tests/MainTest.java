package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainTest extends BaseTest {

    @Test
    public void testURLAndTitle() throws IOException {

        final String expectedURL = "https://openweathermap.org/";
        final String expectedTitle = "Сurrent weather and forecast - OpenWeatherMap";

        openBaseUrl();

        String actualURL = getDriver().getCurrentUrl();
        String actualTitle = getDriver().getTitle();

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

        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertTrue(pageIsNotEmpty);
        Assert.assertTrue(noErrors);
    }

    @Test
    public void testVerifyHeader() {

        final String expectedHeader = "OpenWeather";

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        String actualHeader = mainPage.getHeaderText();

        Assert.assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testVerifySubtitle() {

        final String expectedSubtitle = "Weather forecasts, nowcasts and history in a fast and elegant way";

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        String actualSubtitle = mainPage.getSubtitleText();

        Assert.assertEquals(actualSubtitle, expectedSubtitle);
    }

    @Test
    public void testVerifySearchBlockIsDisplayed() {

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        Assert.assertTrue(mainPage.searchBlockContainerIsDisplayed());
    }

    @Test
    public void testVerifyForecastSectionIsDisplayed() {

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        Assert.assertTrue(mainPage.forecastSectionIsDisplayed());
    }

    @Test
    public void testDifferentWeatherPopupOpensAndCloses() throws NoSuchElementException {

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickDifferentWeatherButton();

        boolean isVisible = mainPage.differentWeatherPopupIsDisplayed();

        mainPage.clickCloseIconDifferentWeatherPopup();
        mainPage.waitTillDifferentWeatherPopupIsNotVisible();

        boolean isNotVisible = mainPage.differentWeatherPopupIsNotDisplayed();

        Assert.assertTrue(isVisible);
        Assert.assertFalse(isNotVisible);
    }

    @Test
    public void testUnitsSwitcher() {

        final String expectedCelsiusSelected = "left: 2pt;";
        final String expectedFahrenheitSelected = "slideRight";

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        String actualCelsiusSelected = mainPage.getSelectedUnitAttribute("style");

        mainPage.clickFahrenheitUnit();

        String actualFahrenheitSelected = mainPage.getSelectedUnitAttribute("className");

        Assert.assertEquals(actualCelsiusSelected, expectedCelsiusSelected);
        Assert.assertEquals(actualFahrenheitSelected, expectedFahrenheitSelected);
    }

    @Test
    public void testUnitsSwitcherChangesCurrentWeather() {

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        boolean unitsDisplayedInCelsius = mainPage.verifyNeededUnitsDisplayed( "C");
        int valueDisplayedInCelsius = mainPage.getCurrentTemperatureFigureFromText();

        mainPage.clickFahrenheitUnit();
        mainPage.waitTillCurrentTemperatureTextChanges(String.valueOf(valueDisplayedInCelsius));

        boolean unitsDisplayedInFahrenheit = mainPage.verifyNeededUnitsDisplayed("F");
        int valueDisplayedInFahrenheit = mainPage.getCurrentTemperatureFigureFromText();
        boolean temperatureHasChanged = mainPage.celsiusToFahrenheitConvertingCorresponds(valueDisplayedInCelsius, valueDisplayedInFahrenheit);

        Assert.assertTrue(unitsDisplayedInCelsius);
        Assert.assertTrue(unitsDisplayedInFahrenheit);
        Assert.assertTrue(temperatureHasChanged);
    }

    @Test
    public void testUnitsSwitcherChangesEightDayForecast() {

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        boolean unitsDisplayedInCelsius = mainPage.verifyEightDayForecastContainsNeededUnits("C");

        mainPage.clickFahrenheitUnit();

        boolean unitsDisplayedInFahrenheit = mainPage.verifyEightDayForecastContainsNeededUnits("F");;

        Assert.assertTrue(unitsDisplayedInCelsius);
        Assert.assertTrue(unitsDisplayedInFahrenheit);
    }

    @Test
    public void testVerifySearchBlockPlaceholder() {

        final String expectedPlaceholderText = "Search city";

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        String actualPlaceholderText = mainPage.getSearchBlockInputAttribute("placeholder");

        Assert.assertEquals(actualPlaceholderText, expectedPlaceholderText);
    }

    @Test
    public void testVerifySearchButtonIsClickable() {

        final String cityName = "Madrid";

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.putInValueSearchBlock(cityName);
        mainPage.clickSearchButton();
        mainPage.waitTillSearchDropdownMenuIsVisible();

        Assert.assertFalse(mainPage.verifySearchDropdownListIsEmpty());
    }

    @Test
    public void testVerifySearchButtonIsClickableByEnter() {

        final String cityName = "Madrid";

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.enterValueSearchBlock(cityName);
        mainPage.waitTillSearchDropdownMenuIsVisible();

        Assert.assertFalse(mainPage.verifySearchDropdownListIsEmpty());
    }

    @Test
    public void testVerifySearchButtonChangesTheLocationShown() {

        final String cityName = "Paris";
        final String expectedLocationDisplayed = "Paris, FR";

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        String currentLocationDisplayed = mainPage.getDisplayedLocationText();

        mainPage.enterValueSearchBlock(cityName);
        mainPage.waitTillSearchDropdownMenuIsVisible();
        mainPage.clickSearchDropdownListElement(0);
        mainPage.waitTillDisplayedLocationTextChanges(currentLocationDisplayed);

        String actualLocationDisplayed = mainPage.getDisplayedLocationText();

        Assert.assertEquals(actualLocationDisplayed, expectedLocationDisplayed);
    }

    @Test
    public void testVerifySearchButtonEmptySearch() {

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.clickSearchButton();

        Assert.assertFalse(mainPage.searchDropdownIsNotDisplayed());
    }

    @Test
    public void testVerifySearchButtonInvalidInput() {

        String invalidInput = "lmmslg";

        final String expectedErrorText = "Not found. To make search more precise put the city's name, comma, 2-letter country code (ISO3166).";
        final String expectedErrorWidgetText = "No results for " + invalidInput;

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.enterValueSearchBlock(invalidInput);
        mainPage.waitErrorWidgetCityNotFoundIsVisible();

        String actualErrorText = mainPage.getErrorCityNotFoundText();
        String actualErrorWidgetText = mainPage.getErrorCityWidgetNotFoundText();

        Assert.assertEquals(actualErrorText, expectedErrorText);
        Assert.assertEquals(actualErrorWidgetText, expectedErrorWidgetText);
    }

    @Test
    public void testErrorWidgetCloses() {

        String invalidInput = "lmmslg";

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.enterValueSearchBlock(invalidInput);
        mainPage.waitErrorWidgetCityNotFoundIsVisible();
        mainPage.clickErrorWidgetCityNotFoundIconClose();

        Assert.assertFalse(mainPage.errorWidgetCityNotFoundIsNotDisplayed());
    }

    @Test
    public void testVerifySearchButtonShowsCorrespondingResults() {

        final String cityName = "Madrid";

        openBaseUrl();

        MainPage mainPage = new MainPage(getDriver());

        mainPage.putInValueSearchBlock(cityName);
        mainPage.clickSearchButton();
        mainPage.waitTillSearchDropdownMenuIsVisible();

        Assert.assertTrue(mainPage.verifySearchDropdownListShowsCorrespondingResults(cityName));
    }
}
