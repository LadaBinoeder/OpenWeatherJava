package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public void clickLogo() {

        clickElement(logo);
    }

    public void clickWeatherInYourCityPlaceholder() {

        clickElement(weatherInYourCityPlaceholderLink);
    }

    public void clickGuideMenu() {

        clickElement(guideMenu);
    }

    public void clickAPIMenu() {

        clickElement(apiMenu);
    }

    public void clickDashboardMenu() {

        clickElement(dashboardMenu);
    }

    public void clickMarketplaceMenu() {

        clickElement(marketplaceMenu);
    }

    public void clickPricingMenu() {

        clickElement(pricingMenu);
    }

    public void clickMapsMenu() {

        clickElement(mapsMenu);
    }

    public void clickOurInitiativesMenu() {

        clickElement(ourInitiativesMenu);
    }

    public void clickPartnersMenu() {

        clickElement(partnersMenu);
    }

    public void clickBlogMenu() {

        clickElement(blogMenu);
    }

    public void clickForBusinessMenu() {

        clickElement(forBusinessMenu);
    }

    public void clickSignInMenu() {

        clickElement(signInMenu);
    }

    public void clickSupportMenu() {

        clickElement(supportMenu);
    }

    public void clickFAQSubmenu() {

        clickElement(faqSubmenu);
    }

    public void clickHowToStartSubmenu() {

        clickElement(howToStartSubmenu);
    }

    public void clickAskAQuestionSubmenu() {

        clickElement(askAQuestionSubmenu);
    }

    public void enterCityNameInWeatherInYourCityPlaceholder(String city) {

        enterValue(weatherInYourCityPlaceholderText, city);
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
}
