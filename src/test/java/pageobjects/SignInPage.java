package pageobjects;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.elements.FixedTopMenu;
import utilities.Customer;
import utilities.Settings;

public class SignInPage extends LoadableComponent<SignInPage> {
	private String expectedPageTitle = "Avactis Demo Store";
	private WebDriver driver;
	private Customer customer;
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

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		this.customer  = Settings.getCustomer();
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

	public boolean registerUser() {
		registerBtn.click();
		RegisterUserPage registerPageObj = new RegisterUserPage(driver);
		return registerPageObj.registerCustomer(customer);
	}

	public boolean login(String loginType) {
		String email = customer.getEmail();
		String passwd = customer.gePassword();

		signInemailTextBox.sendKeys(email);

		if (loginType.equals("Invalid Credentials")) {
			signInPasswordTextBox.sendKeys("invalid123");
		} else if (loginType.equals("Valid Credentials")) {
			signInPasswordTextBox.sendKeys(passwd);
		}

		if (!rememberMeCheckBox.isSelected())
			rememberMeCheckBox.click();
		signInsubmitBtn.click();
		MyAccountPage accPage = new MyAccountPage(driver);
		if (accPage.signInSuccess == true) {
			accPage.signOutLink.click();
			return true;
		} else {
			return false;
		}
	}

}
