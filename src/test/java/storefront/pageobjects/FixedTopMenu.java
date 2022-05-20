package storefront.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//All common WebElements are defined in this file

public final class FixedTopMenu {
	@FindBy(xpath = "//div[@class='pre-header']//a[text()='Sign In']")
	public static WebElement signInLink;

	@FindBy(xpath = "//div[@class='pre-header']//a[text()='My Account']")
	public static WebElement myAccountLink;

	@FindBy(xpath = "//div[@class='pre-header']//a[text()='My cart']")
	public static WebElement myCartLink;

	@FindBy(xpath = "//div[@class='pre-header']//a[text()='Checkout']")
	public static WebElement checkoutLink;

	@FindBy(xpath = "//div[@class = 'header-navigation']//a[text()='Apparel']")
	public static WebElement apparelMenu;

	@FindBy(xpath = "//a[@class='dropdown-toggle'][text()='Computers']")
	public static WebElement computersMenu;

	@FindBy(xpath = "//a[@class='dropdown-toggle'][text()='DVD']")
	public static WebElement dvdMenu;

	@FindBy(xpath = "//div[@class='header-navigation']//a[text()='Furniture']")
	public static WebElement furnitureMenu;

	@FindBy(xpath = "//div[@class='header-navigation']//a[text()='Sport']")
	public static WebElement sportMenu;

	@FindBy(xpath = "//div[@class='header-navigation']//a[text()='Digital Distribution']")
	public static WebElement digitalDistriMenu;

	@FindBy(className = "top-cart-info-count")
	public static WebElement cartItemCountLink;

	@FindBy(className = "top-cart-info-value")
	public static WebElement cartItemsTotalAmountLink;
	
	@FindBy(xpath = "//div[@class ='header-navigation']//a[text()='Notebooks']")
	public static WebElement notebooksMenuItem;
	
	@FindBy(xpath="//div[@class ='header-navigation']//a[text()='Desktops']")
	public static WebElement desktopsMenuItem;
	
	@FindBy(xpath = "//div[@class='header-navigation']//a[text()='Classic Films']")
	public static WebElement classicFilmsMenuItem;

	@FindBy(xpath = "//div[@class='header-navigation']//a[text()='Kids DVDs']")
	public static WebElement kidsDvdsMenuItem;
	
	@FindBy(xpath = "//div[@class='header-navigation']//a[text()='TV on DVD']")
	public static WebElement tvOnDvdMenuItem;
	
	
}
