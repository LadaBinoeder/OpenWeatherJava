package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BreadcrumbPage extends FooterMenuPage {

    @FindBy(className = "breadcrumb-title")
    private WebElement header;

    @FindBy(xpath = "//ol[@class = 'breadcrumb pull-right hidden-xs']/li/a")
    private WebElement homeLink;

    @FindBy(className = "breadcrumb__leaf")
    private WebElement breadcrumbLeaf;

    public BreadcrumbPage(WebDriver driver) {
        super(driver);
    }
}
