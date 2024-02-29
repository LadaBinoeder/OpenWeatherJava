package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TopMenuPage;

import java.util.List;

public class TopMenuTest extends BaseTest {

    @Test
    public void testLogoIsDisplayed() {

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        boolean logoIsDisplayed = topMenuPage.logoIsDisplayed();

        Assert.assertTrue(logoIsDisplayed);
    }

    @Test
    public void testPlaceholderIsDisplayed() {

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        boolean placeholderIsDisplayed = topMenuPage.placeholderIsDisplayed();

        Assert.assertTrue(placeholderIsDisplayed);
    }

    @Test
    public void testAllTheElementsInDesktopMenuAreDisplayed() {

        final int expectedResult = 12;

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        int actualResult = topMenuPage.getDesktopMenuSize();

        Assert.assertEquals(actualResult, expectedResult);
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

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        List<String> actualDesktopMenuNames = topMenuPage.getDesktopMenuText();

        Assert.assertEquals(actualDesktopMenuNames, expectedDesktopMenuNames);
    }

    @Test
    public void testLogoIsClickable() {

        final String expectedLink = "https://openweathermap.org/";
        final String expectedImage = "https://openweathermap.org/themes/openweathermap/assets/img/logo_white_cropped.png";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        topMenuPage.clickLogo();

        String actualURL = getDriver().getCurrentUrl();
        String actualLink = topMenuPage.getLogoLink();
        String actualImage = topMenuPage.getLogoImage();

        Assert.assertEquals(actualURL, BASE_URL);
        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertEquals(actualImage, expectedImage);
    }

    @Test
    public void testPlaceholderIsClickable() {

        final String city = "Rome";
        final String expectedLink = "https://openweathermap.org/find";
        final String expectedText = "Weather in your city";
        final String expectedPage = expectedLink + "?q=" + city;

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        String actualResultLink = topMenuPage.getWeatherInYourCityPlaceholderLink();
        String actualResultText = topMenuPage.getWeatherInYourCityPlaceholderText();

        topMenuPage.clickWeatherInYourCityPlaceholder();
        topMenuPage.enterCityNameInWeatherInYourCityPlaceholder(city);

        String actualResultPage = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResultLink, expectedLink);
        Assert.assertEquals(actualResultText, expectedText);
        Assert.assertEquals(actualResultPage, expectedPage);
    }

    @Test
    public void testGuideIsClickable() {

        final String expectedLink = "https://openweathermap.org/guide";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        String actualLink = topMenuPage.getGuideMenuLink();

        topMenuPage.clickGuideMenu();

        boolean newPageIsOpened = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpened);
    }

    @Test
    public void testApiIsClickable() {

        final String expectedLink = "https://openweathermap.org/api";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        String actualLink = topMenuPage.getAPIMenuLink();

        topMenuPage.clickAPIMenu();

        boolean newPageIsOpened = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpened);
    }

    @Test
    public void testDashboardIsClickable() {

        final String expectedLink = "https://openweathermap.org/weather-dashboard";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        String actualLink = topMenuPage.getDashboardMenuLink();

        topMenuPage.clickDashboardMenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testMarketplaceIsClickable() {

        final String expectedLink = "https://home.openweathermap.org/marketplace";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        String actualLink = topMenuPage.getMarketplaceMenuLink();

        topMenuPage.clickMarketplaceMenu();
        switchToSecondWindow();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testPricingIsClickable() {

        final String expectedLink = "https://openweathermap.org/price";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        String actualLink = topMenuPage.getPricingMenuLink();

        topMenuPage.clickPricingMenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testMapsIsClickable() {

        final String expectedLink = "https://openweathermap.org/weathermap";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        String actualLink = topMenuPage.getMapsMenuLink();

        topMenuPage.clickMapsMenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testOurInitiativesIsClickable() {

        final String expectedLink = "https://openweathermap.org/our-initiatives";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        String actualLink = topMenuPage.getOurInitiativesMenuLink();

        topMenuPage.clickOurInitiativesMenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testPartnersIsClickable() {

        final String expectedLink = "https://openweathermap.org/examples";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        String actualLink = topMenuPage.getPartnersMenuLink();

        topMenuPage.clickPartnersMenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testBlogIsClickable() {

        final String expectedLink = "https://openweather.co.uk/blog/category/weather";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        String actualLink = topMenuPage.getBlogMenuLink();

        topMenuPage.clickBlogMenu();
        switchToSecondWindow();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testForBusinessIsClickable() {

        final String expectedLink = "https://openweather.co.uk/";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        String actualLink = topMenuPage.getForBusinessMenuLink();

        topMenuPage.clickForBusinessMenu();
        switchToSecondWindow();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testSignInIsClickable() {

        final String expectedLink = "https://openweathermap.org/home/sign_in";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        String actualLink = topMenuPage.getSignInMenuLink();

        topMenuPage.clickSignInMenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testSupportIsClickableAndHasThreeSubmenus() {

        final String expectedClass = "dropdown-menu dropdown-visible";
        final int expectedNumberOfSubmenus = 3;

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        topMenuPage.clickSupportMenu();

        String actualClass = topMenuPage.getSupportDropdownMenuClass();
        int actualNumberOfSubmenus = topMenuPage.getSubmenusSize();

        Assert.assertEquals(actualClass, expectedClass);
        Assert.assertEquals(actualNumberOfSubmenus, expectedNumberOfSubmenus);
    }

    @Test
    public void testFaqIsClickable() {

        final String expectedLink = "https://openweathermap.org/faq";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        topMenuPage.clickSupportMenu();

        String actualLink = topMenuPage.getFAQSubmenuLink();

        topMenuPage.clickFAQSubmenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testHowToStartIsClickable() {

        final String expectedLink = "https://openweathermap.org/appid";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        topMenuPage.clickSupportMenu();

        String actualLink = topMenuPage.getHowToStartSubmenuLink();

        topMenuPage.clickHowToStartSubmenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testAskAQuestionIsClickable() {

        final String expectedLink = "https://home.openweathermap.org/questions";

        openBaseUrl();

        TopMenuPage topMenuPage = new TopMenuPage(getDriver());

        topMenuPage.clickSupportMenu();

        String actualLink = topMenuPage.getAskAQuestionSubmenuLink();

        topMenuPage.clickAskAQuestionSubmenu();
        switchToSecondWindow();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }
}
