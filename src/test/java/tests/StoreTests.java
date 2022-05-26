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
import pageobjects.SignInPage;
import pageobjects.StoreMainPage;
import pageobjects.listener.TestExecutionListener;
import utilities.Base;
import utilities.Settings;


@Listeners({ TestExecutionListener.class })
public class StoreTests {
	StoreMainPage mainPage;
	Base base;

	@Description("Register user")
	@Test(priority = 1)
	public void verifyRegisterUser() {
		Boolean expectedResult = true;
		SignInPage signInPage = mainPage.openSignInPage();
		Boolean actualResult = signInPage.registerUser();
		assertEquals(actualResult, expectedResult);
	}

	@Description("Re - registering same user")
	@Test(priority = 2)
	public void verifyReRegisterUser() {
		Boolean expectedResult = true;
		SignInPage signInPage = mainPage.openSignInPage();
		Boolean actualResult = signInPage.registerUser();
		assertEquals(actualResult, expectedResult);
	}

	@Description("Valid email - Valid password")
	@Test(priority = 3)
	public void verifyLogin() {
		boolean expectedResult = true;
		SignInPage signInPage = mainPage.openSignInPage();
		boolean actualResult = signInPage.login("Valid Credentials");
		assertEquals(actualResult, expectedResult);
	}

	@Description("Valid email - Invalid password")
	@Test(priority = 4)
	public void verifyInvalidLogin() {
		boolean expectedResult = true;
		SignInPage signInPage = mainPage.openSignInPage();
		boolean actualResult = signInPage.login("Invalid Credentials");
		assertEquals(actualResult, expectedResult);
	}

//	@Test(priority = 3)
//	public void verifyProductsAddedToCart() {
//		Boolean actualResult = false;
//		Boolean expectedResult = true;
//
//		// login
//		SignInPage loginPage = mainPage.openSignInPage();
//		Boolean isSuccessful = loginPage.login(StoreMainPage.loginEmail, StoreMainPage.loginPassword);
//		if (isSuccessful == true) {
//			actualResult = true;
//			mainPage.addItemsToTheCart();
//
//		} else {
//			actualResult = false;
//		}
//
//		mainPage.addItemsToTheCart();
//		assertEquals(actualResult, expectedResult);
//	}

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
		//DELETE ALL REGISTRATION DATA FROM DATABASE
		mainPage.deleteAllData();
		//CLOSE BROWSER
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
