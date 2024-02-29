package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.TestUtils;

import java.util.List;

public class TopMenuPage extends BasePage {

    @FindBy(xpath = "//ul[@id = 'first-level-nav']/li[@class = 'logo']")
    private WebElement logo;

    @FindBy(xpath = "//ul[@id = 'first-level-nav']/li[@class = 'logo']/a")
    private WebElement logoLink;

    @FindBy(xpath = "//ul[@id = 'first-level-nav']/li[@class = 'logo']/a/img")
    private WebElement logoImage;

    @FindBy(xpath = "//li[@id = 'desktop-menu']/form")
    private WebElement weatherInYourCityPlaceholderLink;

    @FindBy(xpath = "//li[@id = 'desktop-menu']/form/input[@type = 'text']")
    private WebElement weatherInYourCityPlaceholderText;

    @FindBy(xpath = "//li[@id = 'desktop-menu']//a[text() = 'Guide']")
    private WebElement guideMenu;

    @FindBy(xpath = "//li[@id = 'desktop-menu']//a[text() = 'API']")
    private WebElement apiMenu;

    @FindBy(xpath = "//li[@id = 'desktop-menu']//a[text() = 'Dashboard']")
    private WebElement dashboardMenu;

    @FindBy(xpath = "//li[@id = 'desktop-menu']//a[text() = 'Marketplace']")
    private WebElement marketplaceMenu;

    @FindBy(xpath = "//li[@id = 'desktop-menu']//a[text() = 'Pricing']")
    private WebElement pricingMenu;

    @FindBy(xpath = "//li[@id = 'desktop-menu']//a[text() = 'Our Initiatives']")
    private WebElement ourInitiativesMenu;

    @FindBy(xpath = "//li[@id = 'desktop-menu']//a[text() = 'Maps']")
    private WebElement mapsMenu;

    @FindBy(xpath = "//li[@id = 'desktop-menu']//a[text() = 'Partners']")
    private WebElement partnersMenu;

    @FindBy(xpath = "//li[@id = 'desktop-menu']//a[text() = 'Blog']")
    private WebElement blogMenu;

    @FindBy(xpath = "//li[@id = 'desktop-menu']//a[text() = 'For Business']")
    private WebElement forBusinessMenu;

    @FindBy(xpath = "//li[@id = 'desktop-menu']//a[text() = 'Sign in']")
    private WebElement signInMenu;

    @FindBy(id = "support-dropdown")
    private WebElement supportMenu;

    @FindBy(id = "support-dropdown-menu")
    private WebElement supportDropdownMenu;

    @FindBy(linkText = "FAQ")
    private WebElement faqSubmenu;

    @FindBy(linkText = "How to start")
    private WebElement howToStartSubmenu;

    @FindBy(linkText = "Ask a question")
    private WebElement askAQuestionSubmenu;

    @FindBy(xpath = "//li[@id = 'desktop-menu']//input[@name = 'q']")
    private WebElement weatherInYourCityPlaceholder;

    @FindBy(xpath = "//li[@id = 'desktop-menu']/ul/li")
    private List<WebElement> desktopMenuElements;

    @FindBy(xpath = "//ul[@class = 'dropdown-menu dropdown-visible']/li")
    private List<WebElement> submenus;

    public TopMenuPage(WebDriver driver) {
        super(driver);
    }

    public boolean logoIsDisplayed() {

        return elementIsDisplayed(logo);
    }

    public boolean placeholderIsDisplayed() {

        return elementIsDisplayed(weatherInYourCityPlaceholder);
    }

    public int getDesktopMenuSize() {

        return getListSize(desktopMenuElements);
    }

    public int getSubmenusSize() {

        return getListSize(submenus);
    }


    public List<String> getDesktopMenuText() {

        return getListTexts(desktopMenuElements);
    }

    public TopMenuPage clickLogo() {

        clickElement(logo);
        return this;

    }

    public TopMenuPage clickWeatherInYourCityPlaceholder() {

        clickElement(weatherInYourCityPlaceholderLink);
        return this;

    }

    public TopMenuPage clickGuideMenu() {

        clickElement(guideMenu);
        return new GuidePage(getDriver());

    }

    public TopMenuPage clickAPIMenu() {

        clickElement(apiMenu);
        return new ApiPage(getDriver());

    }

    public TopMenuPage clickDashboardMenu() {

        clickElement(dashboardMenu);
        return new DashboardPage(getDriver());

    }

    public TopMenuPage clickMarketplaceMenu() {

        clickElement(marketplaceMenu);
        return this;

    }

    public TopMenuPage clickPricingMenu() {

        clickElement(pricingMenu);
        return new PricePage(getDriver());

    }

    public TopMenuPage clickMapsMenu() {

        clickElement(mapsMenu);
        return new MapsPage(getDriver());

    }

    public TopMenuPage clickOurInitiativesMenu() {

        clickElement(ourInitiativesMenu);
        return new OurInitiativesPage(getDriver());

    }

    public TopMenuPage clickPartnersMenu() {

        clickElement(partnersMenu);
        return new PartnersPage(getDriver());

    }

    public TopMenuPage clickBlogMenu() {

        clickElement(blogMenu);
        return this;

    }

    public TopMenuPage clickForBusinessMenu() {

        clickElement(forBusinessMenu);
        return this;

    }

    public TopMenuPage clickSignInMenu() {

        clickElement(signInMenu);
        return this;

    }

    public TopMenuPage clickSupportMenu() {

        clickElement(supportMenu);
        return this;

    }

    public TopMenuPage clickFAQSubmenu() {

        clickElement(faqSubmenu);
        return new FaqPage(getDriver());

    }

    public TopMenuPage clickHowToStartSubmenu() {

        clickElement(howToStartSubmenu);
        return new HowToStartPage(getDriver());

    }

    public TopMenuPage clickAskAQuestionSubmenu() {

        clickElement(askAQuestionSubmenu);
        return this;

    }

    public TopMenuPage enterCityNameInWeatherInYourCityPlaceholder(String city) {

        enterValue(weatherInYourCityPlaceholderText, city);
        return this;

    }

    public String getLogoLink() {

        return getAttribute(logoLink, "href");
    }

    public String getLogoImage() {

        return getAttribute(logoImage, "src");
    }

    public String getWeatherInYourCityPlaceholderLink() {

        return getAttribute(weatherInYourCityPlaceholderLink, "action");
    }

    public String getWeatherInYourCityPlaceholderText() {

        return getAttribute(weatherInYourCityPlaceholderText, "placeholder");
    }

    public String getGuideMenuLink() {

        return getAttribute(guideMenu, "href");
    }

    public String getAPIMenuLink() {

        return getAttribute(apiMenu, "href");
    }

    public String getDashboardMenuLink() {

        return getAttribute(dashboardMenu, "href");
    }

    public String getMarketplaceMenuLink() {

        return getAttribute(marketplaceMenu, "href");
    }

    public String getPricingMenuLink() {

        return getAttribute(pricingMenu, "href");
    }

    public String getMapsMenuLink() {

        return getAttribute(mapsMenu, "href");
    }

    public String getOurInitiativesMenuLink() {

        return getAttribute(ourInitiativesMenu, "href");
    }

    public String getPartnersMenuLink() {

        return getAttribute(partnersMenu, "href");
    }

    public String getBlogMenuLink() {

        return getAttribute(blogMenu, "href");
    }

    public String getForBusinessMenuLink() {

        return getAttribute(forBusinessMenu, "href");
    }

    public String getSignInMenuLink() {

        return getAttribute(signInMenu, "href");
    }

    public String getSupportDropdownMenuClass() {

        return getAttribute(supportDropdownMenu, "class");
    }

    public String getFAQSubmenuLink() {

        return getAttribute(faqSubmenu, "href");
    }

    public String getHowToStartSubmenuLink() {

        return getAttribute(howToStartSubmenu, "href");
    }

    public String getAskAQuestionSubmenuLink() {

        return getAttribute(askAQuestionSubmenu, "href");
    }

    public boolean verifyNewPageOpen() {
        boolean newPageIsOpened = true;
        if (getDriver().getCurrentUrl().equals(TestUtils.getBaseUrl())) {
            newPageIsOpened = false;

        }
        return newPageIsOpened;

    }

    public TopMenuPage switchToSecondWindowTopMenu() {
        String handle = getDriver().getWindowHandles().toArray()[1].toString();
        getDriver().switchTo().window(handle);
        return this;

    }
}
