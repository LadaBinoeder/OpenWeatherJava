package tests;

import base.BaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import java.io.IOException;

public class MainTest extends BaseTest {

    @Test
    public void testURLAndTitle() throws IOException {

        final String expectedURL = "https://openweathermap.org/";
        final String expectedTitle = "Сurrent weather and forecast - OpenWeatherMap";

        MainPage mainPage = openBaseURL();

        String actualURL = getDriver().getCurrentUrl();
        String actualTitle = getDriver().getTitle();

        Assert.assertEquals(actualURL, expectedURL);
        Assert.assertEquals(actualTitle, expectedTitle);
        Assert.assertTrue(mainPage.verifyPageIsNotEmpty());
        Assert.assertTrue(mainPage.verifyErrorsAtPage());
    }

    @Test
    public void testVerifyHeader() {

        final String expectedHeader = "OpenWeather";

        MainPage mainPage = openBaseURL();
        String actualHeader = mainPage.getHeaderText();

        Assert.assertEquals(actualHeader, expectedHeader);
    }

    @Test
    public void testVerifySubtitle() {

        final String expectedSubtitle = "Weather forecasts, nowcasts and history in a fast and elegant way";

        MainPage mainPage = openBaseURL();

        String actualSubtitle = mainPage.getSubtitleText();

        Assert.assertEquals(actualSubtitle, expectedSubtitle);
    }

    @Test
    public void testVerifySearchBlockIsDisplayed() {

        MainPage mainPage = openBaseURL();;

        Assert.assertTrue(mainPage.searchBlockContainerIsDisplayed());
    }

    @Test
    public void testVerifyForecastSectionIsDisplayed() {

        MainPage mainPage = openBaseURL();

        Assert.assertTrue(mainPage.forecastSectionIsDisplayed());
    }

    @Test
    public void testDifferentWeatherPopupOpensAndCloses() throws NoSuchElementException {

        MainPage mainPage = openBaseURL();

        boolean isVisible = mainPage.clickDifferentWeatherButton()
                                    .differentWeatherPopupIsDisplayed();

        boolean isNotVisible = mainPage.clickCloseIconDifferentWeatherPopup()
                                       .waitTillDifferentWeatherPopupIsNotVisible()
                                       .differentWeatherPopupIsNotDisplayed();

        Assert.assertTrue(isVisible);
        Assert.assertFalse(isNotVisible);
    }

    @Test
    public void testUnitsSwitcher() {

        final String expectedCelsiusSelected = "left: 2pt;";
        final String expectedFahrenheitSelected = "slideRight";

        MainPage mainPage = openBaseURL();

        String actualCelsiusSelected = mainPage.getSelectedUnitAttribute("style");

        String actualFahrenheitSelected = mainPage.clickFahrenheitUnit()
                                                  .getSelectedUnitAttribute("className");

        Assert.assertEquals(actualCelsiusSelected, expectedCelsiusSelected);
        Assert.assertEquals(actualFahrenheitSelected, expectedFahrenheitSelected);
    }

    @Test
    public void testUnitsSwitcherChangesCurrentWeather() {

        MainPage mainPage = openBaseURL();

        boolean unitsDisplayedInCelsius = mainPage.verifyNeededUnitsDisplayed( "C");
        int valueDisplayedInCelsius = mainPage.getCurrentTemperatureFigureFromText();

        mainPage.clickFahrenheitUnit()
                .waitTillCurrentTemperatureTextChanges(String.valueOf(valueDisplayedInCelsius));

        boolean unitsDisplayedInFahrenheit = mainPage.verifyNeededUnitsDisplayed("F");
        int valueDisplayedInFahrenheit = mainPage.getCurrentTemperatureFigureFromText();
        boolean temperatureHasChanged = mainPage.celsiusToFahrenheitConvertingCorresponds(valueDisplayedInCelsius, valueDisplayedInFahrenheit);

        Assert.assertTrue(unitsDisplayedInCelsius);
        Assert.assertTrue(unitsDisplayedInFahrenheit);
        Assert.assertTrue(temperatureHasChanged);
    }

    @Test
    public void testUnitsSwitcherChangesEightDayForecast() {

        MainPage mainPage = openBaseURL();

        boolean unitsDisplayedInCelsius = mainPage.verifyEightDayForecastContainsNeededUnits("C");

        boolean unitsDisplayedInFahrenheit = mainPage.clickFahrenheitUnit()
                                                     .verifyEightDayForecastContainsNeededUnits("F");;

        Assert.assertTrue(unitsDisplayedInCelsius);
        Assert.assertTrue(unitsDisplayedInFahrenheit);
    }

    @Test
    public void testVerifySearchBlockPlaceholder() {

        final String expectedPlaceholderText = "Search city";

        MainPage mainPage = openBaseURL();

        String actualPlaceholderText = mainPage.getSearchBlockInputAttribute("placeholder");

        Assert.assertEquals(actualPlaceholderText, expectedPlaceholderText);
    }

    @Test
    public void testVerifySearchButtonIsClickable() {

        final String cityName = "Madrid";

        MainPage mainPage = openBaseURL();

        mainPage.putInValueSearchBlock(cityName)
                .clickSearchButton()
                .waitTillSearchDropdownMenuIsVisible();

        Assert.assertFalse(mainPage.verifySearchDropdownListIsEmpty());
    }

    @Test
    public void testVerifySearchButtonIsClickableByEnter() {

        final String cityName = "Madrid";

        MainPage mainPage = openBaseURL();

        mainPage.enterValueSearchBlock(cityName)
                .waitTillSearchDropdownMenuIsVisible();

        Assert.assertFalse(mainPage.verifySearchDropdownListIsEmpty());
    }

    @Test
    public void testVerifySearchButtonChangesTheLocationShown() {

        final String cityName = "Paris";
        final String expectedLocationDisplayed = "Paris, FR";

        MainPage mainPage = openBaseURL();

        String currentLocationDisplayed = mainPage.getDisplayedLocationText();

        String actualLocationDisplayed = mainPage.enterValueSearchBlock(cityName)
                .waitTillSearchDropdownMenuIsVisible()
                .clickSearchDropdownListElement(0)
                .waitTillDisplayedLocationTextChanges(currentLocationDisplayed)
                .getDisplayedLocationText();

        Assert.assertEquals(actualLocationDisplayed, expectedLocationDisplayed);
    }

    @Test
    public void testVerifySearchButtonEmptySearch() {

        MainPage mainPage = openBaseURL();

        mainPage.clickSearchButton();

        Assert.assertFalse(mainPage.searchDropdownIsNotDisplayed());
    }

    @Test
    public void testVerifySearchButtonInvalidInput() {

        String invalidInput = "lmmslg";

        final String expectedErrorText = "Not found. To make search more precise put the city's name, comma, 2-letter country code (ISO3166).";
        final String expectedErrorWidgetText = "No results for " + invalidInput;

        MainPage mainPage = openBaseURL();

        String actualErrorText = mainPage.enterValueSearchBlock(invalidInput)
                                         .waitErrorWidgetCityNotFoundIsVisible()
                                         .getErrorCityNotFoundText();
        String actualErrorWidgetText = mainPage.getErrorCityWidgetNotFoundText();

        Assert.assertEquals(actualErrorText, expectedErrorText);
        Assert.assertEquals(actualErrorWidgetText, expectedErrorWidgetText);
    }

    @Test
    public void testErrorWidgetCloses() {

        String invalidInput = "lmmslg";

        MainPage mainPage = openBaseURL();

        mainPage.enterValueSearchBlock(invalidInput)
                .waitErrorWidgetCityNotFoundIsVisible()
                .clickErrorWidgetCityNotFoundIconClose();

        Assert.assertFalse(mainPage.errorWidgetCityNotFoundIsNotDisplayed());
    }

    @Test
    public void testVerifySearchButtonShowsCorrespondingResults() {

        final String cityName = "Madrid";

        MainPage mainPage = openBaseURL();

        mainPage.putInValueSearchBlock(cityName)
                .clickSearchButton()
                .waitTillSearchDropdownMenuIsVisible();

        Assert.assertTrue(mainPage.verifySearchDropdownListShowsCorrespondingResults(cityName));
    }
}
