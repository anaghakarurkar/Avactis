package pageobjects;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.elements.FixedTopMenu;
import pageobjects.extra.Customer;

public class SignInPage extends LoadableComponent<SignInPage> {
	String expectedPageTitle = "Avactis Demo Store";
	WebDriver driver;
	WebDriverWait wait;
	String[] customerData;

	private FixedTopMenu topMenu = new FixedTopMenu();

	@FindBy(xpath = "//a/button[text()='Register']")
	public WebElement registerBtn;

	@FindBy(xpath = "//div/h2[text()='Sign in or create a new account']")
	public WebElement myAccountLabel;

	// existing user sign in page elements
	@FindBy(xpath = "//input[@id='account_sign_in_form_email_id']")
	public WebElement signInemailTextBox;

	@FindBy(xpath = "//input[@id='account_sign_in_form_passwd_id']")
	public WebElement signInPasswordTextBox;

	@FindBy(xpath = "//input[@class='input_checkbox'][@name='remember_me']")
	public WebElement rememberMeCheckBox;

	@FindBy(xpath = "//input[@value='Sign In']")
	public WebElement signInsubmitBtn;

	public SignInPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		get();
		PageFactory.initElements(driver, this);
		PageFactory.initElements(driver, topMenu);
	}

	@Override
	protected void load() {
		// Page is already loaded
	}

	@Override
	protected void isLoaded() throws Error {
		assertEquals(driver.getTitle(), expectedPageTitle);
	}

	public boolean registerUser(Customer customer) {
		registerBtn.click();
		RegisterUserPage registerPageObj = new RegisterUserPage(driver, wait);
		return registerPageObj.registerCustomer(customer);
	}

	public boolean login(String userName, String password) {
 		signInemailTextBox.sendKeys(userName);
		signInPasswordTextBox.sendKeys(password);
		
		if(!rememberMeCheckBox.isSelected())
			rememberMeCheckBox.click();
		signInsubmitBtn.click();
		MyAccountPage accPage = new MyAccountPage(driver, wait);
		if (accPage.signInSuccess == true)
		{
			accPage.signOutLink.click();
		return true;
		}else {
			return false;
		}
	}

	public boolean login() {
		return true;
	}
}
