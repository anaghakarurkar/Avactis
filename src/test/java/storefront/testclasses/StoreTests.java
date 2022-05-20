package storefront.testclasses;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import storefront.pageobjects.AvactisStoreFrontPage;
import storefront.pageobjects.Product;
import storefront.utilityclasses.Base;

public class StoreTests {
	AvactisStoreFrontPage mainPage;

	@Test(dataProviderClass = storefront.dataproviders.ProductsShoppingList.class, dataProvider = "ProductList")
	public void addProductsToCart(String id, String name, String category,
			String subCategory, String chosenForAutomation) {
		
		Product chosenItem = new Product(id, name,category, subCategory);
		Boolean success = mainPage.addItemToTheCart(chosenItem);
		
	assertEquals(success, true);
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass
	public void beforeClass() {
		mainPage = new AvactisStoreFrontPage(Base.Browser.CHROME);
	}

	@AfterClass
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {
	}

}
