package tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import io.qameta.allure.Description;
import pageobjects.CheckoutPage;
import pageobjects.MyAccountPage;
import pageobjects.OrderPlacedPage;
import pageobjects.SignInPage;
import pageobjects.StoreMainPage;
import pageobjects.listener.TestExecutionListener;
import utilities.Base;
import utilities.Log;
import utilities.Settings;

@Listeners({ TestExecutionListener.class })
public class StoreTests {
	StoreMainPage mainPage;
	Base base;

	@Description("Register new customer")
	@Test(priority = 1)
	public void verifyRegisterUser() {
		Boolean expectedResult = true;
		SignInPage signInPage = mainPage.openSignInPage();
		Log.info("Registering new customer...");
		Boolean actualResult = signInPage.registerUser();
		assertEquals(actualResult, expectedResult);

	}

	@Description("Re - registering customer")
	@Test(priority = 2)
	public void verifyReRegisterUser() {
		Boolean expectedResult = true;
		SignInPage signInPage = mainPage.openSignInPage();
		Log.info("Re-registering customer.");
		Boolean actualResult = signInPage.registerUser();
		assertEquals(actualResult, expectedResult);
	}

	@Description("Log in using valid email and valid password")
	@Test(priority = 3)
	public void verifyLogin() {
		boolean expectedResult = true;
		SignInPage signInPage = mainPage.openSignInPage();
		MyAccountPage myAccount = signInPage.login("Valid Credentials");
		myAccount.customerSignOut();
		assertEquals(myAccount.signInSuccess, expectedResult);
		Log.info("Log in using valid email and valid password successful!");
	}

	@Description("Log in using valid email and invalid password")
	@Test(priority = 4)
	public void verifyInvalidLogin() {
		boolean expectedResult = true;
		SignInPage signInPage = mainPage.openSignInPage();
		MyAccountPage myAccount = signInPage.login("Invalid Credentials");
		assertEquals(myAccount.signInSuccess, expectedResult);
	}

	@Test(priority = 5)
	public void verifyProductsAddedToCart() {
		// login
		boolean actualResult = false;
		boolean expectedResult = true;
		SignInPage loginPage = mainPage.openSignInPage();
		MyAccountPage myAccount = loginPage.login("Valid Credentials");
		actualResult = myAccount.signInSuccess;
		assertEquals(actualResult, expectedResult);
		Log.info("Log In Successful!");

		// Add all products chosen to the cart
		actualResult = mainPage.addItemsToTheCart();
		assertEquals(actualResult, expectedResult);

		// verify cart for products added, price and total price
		actualResult = mainPage.verifyCart();
		assertEquals(actualResult, expectedResult);
		Log.info("All products added to the cart! Verification Successful!");
		// Verify address
		CheckoutPage checkout = mainPage.checkout("REGISTERED");
		actualResult = checkout.verifyAddress();
		assertEquals(actualResult, expectedResult);

		// choose shipping option
		checkout.chooseShippingOption();

		// verify total amount
		actualResult = checkout.verifyTotalAmount();
		assertEquals(actualResult, expectedResult);

		// verify order id displayed
		OrderPlacedPage orderPlacedPage = checkout.placeOrder();

		actualResult = orderPlacedPage.verifyOrderIDDisplayed();
		assertEquals(actualResult, expectedResult);

//		// verify total amount in final order place page.
//		actualResult = orderPlacedPage.verifyCorrectTotalAmount();
//		assertEquals(actualResult, expectedResult);
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass
	public void beforeClass() {
		// Load project settings
		Settings.loadSettings();
		// launch browser
		base = Settings.getBase();
		base.launchBrowser();
		// open store main page
		mainPage = new StoreMainPage(base);
	}

	@AfterClass
	public void afterClass() {
		// DELETE ALL REGISTRATION DATA FROM DATABASE
		mainPage.deleteAllData();
		// CLOSE BROWSER
		base.quitAllBrowserWindows();
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
