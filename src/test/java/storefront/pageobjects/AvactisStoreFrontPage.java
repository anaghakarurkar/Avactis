package storefront.pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import storefront.utilityclasses.Base;

public class AvactisStoreFrontPage extends LoadableComponent<AvactisStoreFrontPage> {
	public Base baseObj;
	private WebDriver driver;
	private String storeURL;
	private WebDriverWait wait;
	private String expectedPageTitle = "Avactis Demo Store";

	// All common WebElements are defined in this file
	private FixedTopMenu topMenu = new FixedTopMenu();

	public AvactisStoreFrontPage(Base.Browser brName) {
		baseObj = new Base(brName);
		this.driver = baseObj.getDriver();
		this.storeURL = baseObj.getAvactisStoreFrontURL();
		this.wait = baseObj.explicitWait();
		get();
		PageFactory.initElements(driver, topMenu);
	}

	@Override
	protected void load() {
		driver.get(storeURL);
	}

	@Override
	protected void isLoaded() throws Error {
		assertEquals(driver.getTitle(), expectedPageTitle);
	}

	public Boolean addItemToTheCart(Product product) {
		ProductsPage commonProductsPage;
		Actions actions = new Actions(driver);

		switch (product.category.toLowerCase()) {
		case "apparel":
			FixedTopMenu.apparelMenu.click();
			product.productPageTitle = "Apparel - Avactis Demo Store";
			break;
		case "computers":
				actions.moveToElement(FixedTopMenu.computersMenu);
				switch (product.subCategory.toLowerCase()) {
				case "none":
					product.productPageTitle = "Computers - Avactis Demo Store";
					break;
				case "notebooks":
					product.productPageTitle = "Notebooks - Avactis Demo Store";
					actions.moveToElement(FixedTopMenu.notebooksMenuItem);
					break;
				case "desktops":
					product.productPageTitle = "Desktops - Avactis Demo Store";
					actions.moveToElement(FixedTopMenu.desktopsMenuItem);
					break;
				default:
					break;
				}
			actions.click();
			actions.perform();
			break;
		case "dvd":
				actions.moveToElement(FixedTopMenu.dvdMenu);
					switch (product.subCategory.toLowerCase()) {
					case "tv on dvd":
						product.productPageTitle = "TV on DVD - Avactis Demo Store";
						actions.moveToElement(FixedTopMenu.tvOnDvdMenuItem);
						break;
					case "kids dvds":
						product.productPageTitle = "Kids DVDs - Avactis Demo Store";
						actions.moveToElement(FixedTopMenu.kidsDvdsMenuItem);
						break;
					case "classic films":
						product.productPageTitle = "Classic Films - Avactis Demo Store";
						actions.moveToElement(FixedTopMenu.classicFilmsMenuItem);
						break;
					default:
						break;
					}
				actions.click();
				actions.perform();
			break;
		case "furniture":
			product.productPageTitle = "Furniture - Avactis Demo Store";
			FixedTopMenu.furnitureMenu.click();
			break;
		case "sports":
			product.productPageTitle = "Sport and Travel - Avactis Demo Store";
			FixedTopMenu.sportMenu.click();
			break;

		case "digital distribution":
			product.productPageTitle = "Digital Distribution - Avactis Demo Store";
			FixedTopMenu.digitalDistriMenu.click();
		default:
			break;
		}
		commonProductsPage = new ProductsPage(driver, wait, product);
		product.addedToTheCart = commonProductsPage.addToCart();

		return product.addedToTheCart;
	}

}
