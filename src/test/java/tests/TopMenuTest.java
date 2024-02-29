package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.TopMenuPage;

import java.util.List;

public class TopMenuTest extends BaseTest {

    @Test
    public void testLogoIsDisplayed() {

        boolean logoIsDisplayed = openBaseURL()
                .logoIsDisplayed();

        Assert.assertTrue(logoIsDisplayed);
    }

    @Test
    public void testPlaceholderIsDisplayed() {

        boolean placeholderIsDisplayed = openBaseURL()
                .placeholderIsDisplayed();

        Assert.assertTrue(placeholderIsDisplayed);
    }

    @Test
    public void testAllTheElementsInDesktopMenuAreDisplayed() {

        final int expectedResult = 12;

        int actualResult = openBaseURL()
                .getDesktopMenuSize();

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

        List<String> actualDesktopMenuNames = openBaseURL().getDesktopMenuText();

        Assert.assertEquals(actualDesktopMenuNames, expectedDesktopMenuNames);
    }

    @Test
    public void testLogoIsClickable() {

        final String expectedLink = "https://openweathermap.org/";
        final String expectedImage = "https://openweathermap.org/themes/openweathermap/assets/img/logo_white_cropped.png";

        TopMenuPage topMenuPage = openBaseURL();

        String actualURL = getDriver().getCurrentUrl();
        String actualLink = topMenuPage.clickLogo()
                                       .getLogoLink();
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

        TopMenuPage topMenuPage = openBaseURL();

        String actualResultLink = topMenuPage.getWeatherInYourCityPlaceholderLink();
        String actualResultText = topMenuPage.getWeatherInYourCityPlaceholderText();

        topMenuPage.clickWeatherInYourCityPlaceholder()
                   .enterCityNameInWeatherInYourCityPlaceholder(city);

        String actualResultPage = getDriver().getCurrentUrl();

        Assert.assertEquals(actualResultLink, expectedLink);
        Assert.assertEquals(actualResultText, expectedText);
        Assert.assertEquals(actualResultPage, expectedPage);
    }

    @Test
    public void testGuideIsClickable() {

        final String expectedLink = "https://openweathermap.org/guide";

        TopMenuPage topMenuPage = openBaseURL();

        String actualLink = topMenuPage.getGuideMenuLink();

        topMenuPage.clickGuideMenu();

        boolean newPageIsOpened = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpened);
    }

    @Test
    public void testApiIsClickable() {

        final String expectedLink = "https://openweathermap.org/api";

        TopMenuPage topMenuPage = openBaseURL();

        String actualLink = topMenuPage.getAPIMenuLink();

        topMenuPage.clickAPIMenu();

        boolean newPageIsOpened = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpened);
    }

    @Test
    public void testDashboardIsClickable() {

        final String expectedLink = "https://openweathermap.org/weather-dashboard";

        TopMenuPage topMenuPage = openBaseURL();

        String actualLink = topMenuPage.getDashboardMenuLink();

        topMenuPage.clickDashboardMenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testMarketplaceIsClickable() {

        final String expectedLink = "https://home.openweathermap.org/marketplace";

        TopMenuPage topMenuPage = openBaseURL();

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

        TopMenuPage topMenuPage = openBaseURL();

        String actualLink = topMenuPage.getPricingMenuLink();

        topMenuPage.clickPricingMenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }


    @Test
    public void testOurInitiativesIsClickable() {

        final String expectedLink = "https://openweathermap.org/our-initiatives";

        TopMenuPage topMenuPage = openBaseURL();

        String actualLink = topMenuPage.getOurInitiativesMenuLink();

        topMenuPage.clickOurInitiativesMenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testPartnersIsClickable() {

        final String expectedLink = "https://openweathermap.org/examples";

        TopMenuPage topMenuPage = openBaseURL();

        String actualLink = topMenuPage.getPartnersMenuLink();

        topMenuPage.clickPartnersMenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testBlogIsClickable() {

        final String expectedLink = "https://openweather.co.uk/blog/category/weather";

        TopMenuPage topMenuPage = openBaseURL();

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

        TopMenuPage topMenuPage = openBaseURL();

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

        TopMenuPage topMenuPage = openBaseURL();

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

        TopMenuPage topMenuPage = openBaseURL();

        String actualClass = topMenuPage.clickSupportMenu()
                                        .getSupportDropdownMenuClass();
        int actualNumberOfSubmenus = topMenuPage.getSubmenusSize();

        Assert.assertEquals(actualClass, expectedClass);
        Assert.assertEquals(actualNumberOfSubmenus, expectedNumberOfSubmenus);
    }

    @Test
    public void testFaqIsClickable() {

        final String expectedLink = "https://openweathermap.org/faq";

        TopMenuPage topMenuPage = openBaseURL();

        String actualLink = topMenuPage.clickSupportMenu()
                                       .getFAQSubmenuLink();

        topMenuPage.clickFAQSubmenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }

    @Test
    public void testHowToStartIsClickable() {

        final String expectedLink = "https://openweathermap.org/appid";

        TopMenuPage topMenuPage = openBaseURL();

        String actualLink = topMenuPage.clickSupportMenu()
                                       .getHowToStartSubmenuLink();

        topMenuPage.clickHowToStartSubmenu();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }
    @Test
    public void testAskAQuestionIsClickable() {

        final String expectedLink = "https://home.openweathermap.org/questions";

        TopMenuPage topMenuPage = openBaseURL();

        String actualLink = topMenuPage.clickSupportMenu()
                                       .getAskAQuestionSubmenuLink();

        topMenuPage.clickAskAQuestionSubmenu();
        switchToSecondWindow();

        boolean newPageIsOpen = verifyNewPageOpen();

        Assert.assertEquals(actualLink, expectedLink);
        Assert.assertTrue(newPageIsOpen);
    }
}
