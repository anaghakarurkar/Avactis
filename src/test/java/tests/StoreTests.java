package tests;

import org.testng.annotations.Test;
import pageobjects.SignInPage;
import pageobjects.StoreMainPage;
import pageobjects.extra.Customer;
import pageobjects.listener.TestExecutionListener;
import utilities.Base;
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

@Listeners({ TestExecutionListener.class })
public class StoreTests {
	StoreMainPage mainPage;

	@Test(priority = 1, dataProviderClass = dataproviders.CustomerDP.class, dataProvider = "Registration")
	public void verifyRegisterUser(String userName, String password, String firstName, String lastName, String country,
			String state, String postCode, String city, String add1, String add2, String phone) {

		Boolean expectedResult = true;
		Customer customer = new Customer(userName, password, firstName, lastName, country, state, postCode, city, add1,
				add2, phone);
		SignInPage signInPage = mainPage.openSignInPage();
		Boolean actualResult = signInPage.registerUser(customer);

		assertEquals(actualResult, expectedResult);
	}

	@Test(priority = 2, dataProviderClass = dataproviders.CustomerDP.class, dataProvider = "Login")
	public void verifyLoginUser(String username, String password) {
		boolean expectedResult = true;
		boolean actualResult;
		SignInPage signInPage = mainPage.openSignInPage();
		actualResult = signInPage.login(username, password);

		assertEquals(actualResult, expectedResult);
	}

	@Test(priority = 3)
	public void verifyProductsAddedToCart() {
		Boolean actualResult = false;
		Boolean expectedResult = true;

		// login
		SignInPage loginPage = mainPage.openSignInPage();
		Boolean isSuccessful = loginPage.login(StoreMainPage.loginEmail, StoreMainPage.loginPassword);
		if (isSuccessful == true) {
			actualResult = true;
			mainPage.addItemsToTheCart();

		} else {
			actualResult = false;
		}

		mainPage.addItemsToTheCart();
		assertEquals(actualResult, expectedResult);
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@BeforeClass
	public void beforeClass() {
		mainPage = new StoreMainPage(Base.Browser.CHROME);
	}

	@AfterClass
	public void afterClass() {
		mainPage.deleteAllData();
		Base.driver.quit();
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
