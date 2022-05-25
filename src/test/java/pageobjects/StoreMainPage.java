package pageobjects;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import dataproviders.ProductsListDP;
import pageobjects.elements.FixedTopMenu;
import pageobjects.extra.Product;
import utilities.Base;

public class StoreMainPage extends LoadableComponent<StoreMainPage> {
	public Base baseObj;
	private WebDriver driver;
	private String storeURL;
	private WebDriverWait wait;
	private String expectedPageTitle = "Avactis Demo Store";
	public static String loginEmail = "";
	public static String loginPassword = "";
	private String adminSiteUrl;
	// All common WebElements are defined in this file
	private FixedTopMenu topMenu = new FixedTopMenu();
  //  public static String customerListArr[];
    
	public StoreMainPage(Base.Browser brName) {
		baseObj = new Base(brName);
		this.driver = baseObj.getDriver();
		this.storeURL = baseObj.getAvactisStoreFrontURL();
		this.adminSiteUrl = baseObj.getAvactisAdminURL();
		this.wait = baseObj.explicitWait();
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
		SignInPage signInPage = new SignInPage(driver, wait);
		return signInPage;
	}

	// ADD CHOSEN PRODUCTS TO THE CART
	
	
	public void addItemsToTheCart() {
		try {
		Object[][] pList = new ProductsListDP().getProductsList();
		Product chosenItem;
		for(int i=0; i<= pList.length-1; i++) {
			 chosenItem = new Product((String)pList[i][0],(String) pList[i][1],(String)pList[i][2],(String)pList[i][3]);
			 Boolean success = clickProductLink(chosenItem);
			 if(success == true) {
				 System.out.println(chosenItem.name + "Added to the cart!");
			 } else {
				 System.err.println(chosenItem.name + " Add cart - Error");
			 }
		}
		}catch(IOException e) {
			System.out.println("Can't open Products list file");
		}
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
			//*******************COMPUTES - SUB MENU OPTIONS ************************
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
			//***********************END OF SUBMENU OPTIONS**************************
			actions.click();
			actions.perform();
			break;
		case "dvd":
			actions.moveToElement(FixedTopMenu.dvdMenu);
			//*******************DVD - SUB MENU OPTIONS *****************************
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
			//***********************END OF SUBMENU OPTIONS**************************
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

	public void deleteAllData() {
		driver.get(adminSiteUrl);
		AdminMainPage adminPage = new AdminMainPage(driver, wait);
		AdminMainPage.adminEmail = baseObj.getAdminUserEmail();
		AdminMainPage.adminPass = baseObj.getAdminPassword();
		adminPage.deleteCustomer();
	}
}
