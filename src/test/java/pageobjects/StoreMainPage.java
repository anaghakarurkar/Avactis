package pageobjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.elements.FixedTopMenu;
import utilities.Base;
import utilities.Product;
import utilities.ProductsList;
import utilities.Settings;
import utilities.Log;


public class StoreMainPage extends LoadableComponent<StoreMainPage> {
	private Base base;
	private WebDriver driver;
	private String storeURL;
	private WebDriverWait wait;
	private String expectedPageTitle = "Avactis Demo Store";
	public static String loginEmail = "";
	public static String loginPassword = "";
	private String adminSiteUrl;
	
	// All common WebElements are defined in this file
	private FixedTopMenu topMenu = new FixedTopMenu();
	// public static String customerListArr[];

	public StoreMainPage(Base base) {
		this.base = base;
		this.driver = base.getDriver();
		this.storeURL = base.getAvactisStoreFrontURL();
		this.adminSiteUrl = base.getAvactisAdminURL();
		this.wait = base.explicitWait();
		get();
		PageFactory.initElements(driver, topMenu);
	}

	// SCENARIO - GO TO STORE
	@Override
	protected void load() {
		driver.get(storeURL);
	}

	@Override
	protected void isLoaded() throws Error {
		assertEquals(driver.getTitle(), expectedPageTitle);
	}

	public SignInPage openSignInPage() {
		FixedTopMenu.signInLink.click();
		SignInPage signInPage = new SignInPage(driver);
		return signInPage;
	}

	// ADD CHOSEN PRODUCTS TO THE CART
	public Boolean addItemsToTheCart() {
		Boolean allItemsAdded = true;

		try {
			Object[][] pList = new ProductsList().getProductsList();
			Product chosenItem;
			Settings.chosenProducts = new Product[pList.length];

			for (int i = 0; i <= pList.length - 1; i++) {
				chosenItem = new Product(Integer.parseInt((String) pList[i][0]) , (String) pList[i][1], (String) pList[i][2],
						(String) pList[i][3], Integer.parseInt((String)pList[i][5]) ,Float.valueOf((String) pList[i][6]));

				System.out.println(chosenItem.name);
				Boolean success = clickProductLink(chosenItem);
				Settings.chosenProducts[i] = chosenItem;

				// check if  product is added  to the cart successfully
				if (success == true) {
					Log.info(chosenItem.name + " Added to the Cart!");

				} else {
					Log.error(chosenItem.name + " - problem adding to the Cart.");
					allItemsAdded = false;
				}
			}
		} catch (IOException e) {
			Log.error("Can't open Products list file");
			allItemsAdded = false;
		}
		return allItemsAdded;
	}

	public Boolean clickProductLink(Product product) {
		ProductsPage commonProductsPage;
		Actions actions = new Actions(driver);

		switch (product.category.toLowerCase()) {
		case "apparel":
			FixedTopMenu.apparelMenu.click();
			break;
		case "computers":
			actions.moveToElement(FixedTopMenu.computersMenu);
			// *******************COMPUTES - SUB MENU OPTIONS ************************
			switch (product.subCategory.toLowerCase()) {
			case "notebooks":
				actions.moveToElement(FixedTopMenu.notebooksMenuItem);
				break;
			case "desktops":
				actions.moveToElement(FixedTopMenu.desktopsMenuItem);
				break;
			default:
				break;
			}
			// ***********************END OF SUBMENU OPTIONS**************************
			actions.click();
			actions.perform();
			break;
		case "dvd":
			actions.moveToElement(FixedTopMenu.dvdMenu);
			// *******************DVD - SUB MENU OPTIONS *****************************
			switch (product.subCategory.toLowerCase()) {
			case "tv on dvd":
				actions.moveToElement(FixedTopMenu.tvOnDvdMenuItem);
				break;
			case "kids dvds":
				actions.moveToElement(FixedTopMenu.kidsDvdsMenuItem);
				break;
			case "classic films":
				actions.moveToElement(FixedTopMenu.classicFilmsMenuItem);
				break;
			default:
				break;
			}
			// ***********************END OF SUBMENU OPTIONS**************************
			actions.click();
			actions.perform();
			break;
		case "furniture":
			FixedTopMenu.furnitureMenu.click();
			break;
		case "sports":
			FixedTopMenu.sportMenu.click();
			break;
		case "digital distribution":
			FixedTopMenu.digitalDistriMenu.click();
		default:
			break;
		}
		commonProductsPage = new ProductsPage(driver, wait, product);
		product.addedToTheCart = commonProductsPage.addToCart();
		return product.addedToTheCart;
	}

	public CheckoutPage checkout(String userType) {
		FixedTopMenu.checkoutLink.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver, wait);
		if (userType.endsWith("GUEST")) {
			checkoutPage.checkoutAsGuest();
		} else if (userType.equals("REGISTERED")) {
			checkoutPage.checkoutAsCustomer();
		}
		return checkoutPage;
	}

	public void deleteAllData() {
		driver.get(adminSiteUrl);
		AdminMainPage adminPage = new AdminMainPage(driver, wait);
		AdminMainPage.adminEmail = base.getAdminUserEmail();
		AdminMainPage.adminPass = base.getAdminPassword();
		adminPage.deleteCustomer();
	}

	public boolean verifyCart() {	
		FixedTopMenu.myCartLink.click();
		MyCartPage cartPage = new MyCartPage(driver);
		Boolean status = cartPage.verifyCart(Settings.chosenProducts);
		return status;
	}
}
