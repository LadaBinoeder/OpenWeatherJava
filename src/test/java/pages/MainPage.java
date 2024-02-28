package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends FooterMenuPage {

    @FindBy(xpath = "//div[@class = 'mobile-padding main-page banner-content main-website']/h1/span")
    private WebElement header;

    @FindBy(xpath = "//div[@class = 'mobile-padding main-page banner-content main-website']/h2/span")
    private WebElement subtitle;

    @FindBy(xpath = "//span[@class = 'control-el owm-switch']")
    private WebElement differentWeatherButton;

    @FindBy(className = "pop-up-container")
    private WebElement differentWeatherPopup;

    @FindBy(xpath = "//div[@class = 'pop-up-container']/*[local-name() = 'svg']")
    private WebElement closeIconDifferentWeatherPopup;

    @FindBy(id = "selected")
    private WebElement selectedUnit;

    @FindBy(xpath = "//div[@class = 'switch-container']//*[text() = 'Metric: °C, m/s']")
    private WebElement celsiusUnit;

    @FindBy(xpath = "//div[@class = 'switch-container']//*[text() = 'Imperial: °F, mph']")
    private WebElement fahrenheitUnit;

    @FindBy(xpath = "//div[@class = 'current-temp']/span[@class = 'heading']")
    private WebElement currentTemperature;

    @FindBy(xpath = "//div[@class = 'search-container']//input")
    private WebElement searchBlockInput;

    @FindBy(xpath = "//div[@class = 'search']//button")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[@class = 'search-dropdown-menu']")
    private WebElement searchDropdownMenu;

    @FindBy(xpath = "//div[@class = 'section-content']//h2")
    private WebElement locationDisplayed;

    @FindBy(xpath = "//div[@class = 'search-block']/div[@class = 'sub not-found notFoundOpen']")
    private WebElement errorCityNotFound;

    @FindBy(xpath = "//div[@class = 'widget-notification']")
    private WebElement errorWidgetCityNotFound;

    @FindBy(xpath = "//div[@class = 'widget-notification']//*[local-name() = 'svg']")
    private WebElement errorWidgetCityNotFoundIconClose;

    @FindBy(xpath = "//div[@id = 'weather-widget']//div[@class = 'page-container']")
    private WebElement searchBlockContainer;

    @FindBy(xpath = "//div[@id = 'weather-widget']//div[@class = 'section-content']")
    private WebElement forecastSection;

    @FindBy(xpath = "//div[@class = 'day-list-values']/div/span")
    private List<WebElement> eightDayForecast;

    @FindBy(xpath = "//ul[@class = 'search-dropdown-menu']/li")
    private List<WebElement> searchDropdownList;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        if(header.getText().isEmpty()) {
            getWait10().until(ExpectedConditions.visibilityOf(header));
        }

        return header.getText();
    }

    public String getSubtitleText() {
        if(subtitle.getText().isEmpty()) {
            getWait10().until(ExpectedConditions.visibilityOf(subtitle));
        }

        return subtitle.getText();
    }

    public String getDisplayedLocationText() {
        if(locationDisplayed.getText().isEmpty()) {
            getWait10().until(ExpectedConditions.visibilityOf(locationDisplayed));
        }

        return locationDisplayed.getText();
    }

    public String getErrorCityNotFoundText() {
        if(errorCityNotFound.getText().isEmpty()) {
            getWait10().until(ExpectedConditions.visibilityOf(errorCityNotFound));
        }

        return errorCityNotFound.getText();
    }

    public String getErrorCityWidgetNotFoundText() {
        if(errorWidgetCityNotFound.getText().isEmpty()) {
            getWait10().until(ExpectedConditions.visibilityOf(errorWidgetCityNotFound));
        }

        return errorWidgetCityNotFound.getText();
    }

    public void putInValueSearchBlock(String value) {

        searchBlockInput.sendKeys(value);
    }

    public void enterValueSearchBlock(String value) {

        searchBlockInput.sendKeys(value);
        searchBlockInput.sendKeys(Keys.ENTER);
    }

    public void clickDifferentWeatherButton() {
        getWait5().until(ExpectedConditions.visibilityOfAllElements(differentWeatherButton));
        getWait5().until(ExpectedConditions.elementToBeClickable(differentWeatherButton)).click();

    }

    public void clickCloseIconDifferentWeatherPopup() {
        getWait5().until(ExpectedConditions.visibilityOfAllElements(closeIconDifferentWeatherPopup));
        getWait5().until(ExpectedConditions.elementToBeClickable(closeIconDifferentWeatherPopup)).click();

    }

    public void clickFahrenheitUnit() {
        getWait5().until(ExpectedConditions.visibilityOfAllElements(fahrenheitUnit));
        getWait5().until(ExpectedConditions.elementToBeClickable(fahrenheitUnit)).click();

    }

    public void clickSearchButton() {
        getWait5().until(ExpectedConditions.visibilityOfAllElements(searchButton));
        getWait5().until(ExpectedConditions.elementToBeClickable(searchButton)).click();

    }

    public void clickErrorWidgetCityNotFoundIconClose() {
        getWait5().until(ExpectedConditions.visibilityOfAllElements(errorWidgetCityNotFoundIconClose));
        getWait5().until(ExpectedConditions.elementToBeClickable(errorWidgetCityNotFoundIconClose)).click();

    }

    public boolean searchBlockContainerIsDisplayed() {

        return searchBlockContainer.isDisplayed();
    }

    public boolean forecastSectionIsDisplayed() {

        return forecastSection.isDisplayed();
    }

    public boolean differentWeatherPopupIsDisplayed() {

        return differentWeatherPopup.isDisplayed();
    }

    public void waitTillSearchDropdownMenuIsVisible()  {

        getWait10().until(ExpectedConditions.visibilityOf(searchDropdownMenu));
    }

    public void waitErrorWidgetCityNotFoundIsVisible()  {

        getWait10().until(ExpectedConditions.visibilityOf(errorWidgetCityNotFound));
    }

    public void waitTillDifferentWeatherPopupIsNotVisible()  {

        getWait10().until(ExpectedConditions.invisibilityOf(differentWeatherPopup));
    }

    public void waitTillCurrentTemperatureTextChanges(String text) {
        getWait5().until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(currentTemperature, text)));

    }

    public void waitTillDisplayedLocationTextChanges(String text) {
        getWait5().until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(locationDisplayed, text)));

    }

    public boolean differentWeatherPopupIsNotDisplayed() {
        try {
            differentWeatherPopup.isDisplayed();
            return true;

        } catch (NoSuchElementException e) {
            return false;

        }
    }

    public boolean searchDropdownIsNotDisplayed() {
        try {
            searchDropdownMenu.isDisplayed();
            return true;

        } catch (NoSuchElementException e) {
            return false;

        }
    }

    public boolean errorWidgetCityNotFoundIsNotDisplayed() {
        try {
            errorWidgetCityNotFound.isDisplayed();
            return true;

        } catch (NoSuchElementException e) {
            return false;

        }
    }

    public String getSelectedUnitAttribute(String attribute) {

        return selectedUnit.getAttribute(attribute);
    }

    public String getSearchBlockInputAttribute(String attribute) {

        return searchBlockInput.getAttribute(attribute);
    }

    public boolean verifyNeededUnitsDisplayed(String units) {
        String text = currentTemperature.getText();
        if(text.contains(units)){
            return true;

        };
        return false;

    }

    public int getCurrentTemperatureFigureFromText() {

        String currentTemp = currentTemperature.getText();
        char[] arrayCurrentTemp = currentTemp.toCharArray();
        currentTemp = "";

        for(int i = 0; i < arrayCurrentTemp.length - 2; i++) {
            currentTemp = currentTemp + arrayCurrentTemp[i];
        }
        return Integer.valueOf(currentTemp);

    }

    public boolean celsiusToFahrenheitConvertingCorresponds(int temperatureInCelsius, int temperatureInFahrenheit) {

        if(temperatureInFahrenheit == convertCelsiusToFahrenheit(temperatureInCelsius)
                || temperatureInFahrenheit == convertCelsiusToFahrenheit(temperatureInCelsius) + 1)
        {
            return true;

        }
        return false;

    }

    public boolean verifyEightDayForecastContainsNeededUnits(String units) {

        for(int i = 0; i < eightDayForecast.size(); i++) {
            if(eightDayForecast.get(i).getText().contains(units)) {
                return true;

            }
        }
        return false;

    }

    public boolean verifySearchDropdownListIsEmpty() {

        return searchDropdownList.isEmpty();
    }

    public void clickSearchDropdownListElement(int index) {

        searchDropdownList.get(index).click();
    }

    public boolean verifySearchDropdownListShowsCorrespondingResults(String cityName) {

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

        return cityNameCorresponds;
    }
}
