package pageobjects;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.elements.FixedTopMenu;
import utilities.Customer;
import utilities.Log;
import utilities.Settings;

public class RegisterUserPage extends LoadableComponent<RegisterUserPage> {
	WebDriver driver;
	WebDriverWait wait;
	@FindBy(className = "registration_form")
	public WebElement registrationFrm;

	@FindBy(name = "customer_info[Customer][Email]")
	public WebElement emailTbx;

	@FindBy(name = "customer_info[Customer][Password]")
	public WebElement passwordTbx;

	@FindBy(name = "customer_info[Customer][RePassword]")
	public WebElement rePasswordTbx;

	@FindBy(name = "customer_info[Customer][FirstName]")
	public WebElement firstNameTbx;

	@FindBy(name = "customer_info[Customer][LastName]")
	public WebElement lastNameTbx;

	@FindBy(name = "customer_info[Customer][Country]")
	public WebElement countryDropDown;

	@FindBy(name = "customer_info[Customer][State]")
	public WebElement stateDropDown;

	@FindBy(name = "customer_info[Customer][ZipCode]")
	public WebElement postCodeTbx;

	@FindBy(name = "customer_info[Customer][City]")
	public WebElement cityTbx;

	@FindBy(name = "customer_info[Customer][Streetline1]")
	public WebElement address1Tbx;

	@FindBy(name = "customer_info[Customer][Streetline2]")
	public WebElement address2Tbx;

	@FindBy(name = "customer_info[Customer][Phone]")
	public WebElement phoneTbx;

	@FindBy(xpath = "//input[@type='submit'][@value='Register']")
	public WebElement registerBtn;

	@FindBy(xpath = "//div[text()='Account created successfully. You are now registered.']")
	public WebElement successMessage;

	FixedTopMenu topMenu = new FixedTopMenu();

	public RegisterUserPage(WebDriver driver) {
		this.driver = driver;
		this.wait = Settings.getBase().explicitWait();
		get();
		PageFactory.initElements(driver, this);
		PageFactory.initElements(driver, topMenu);
	}

	public boolean registerCustomer(Customer customer) {
		//FILL THE REGISTRATION FORM

		String[] address = customer.getAddress();
		
		emailTbx.sendKeys(customer.getEmail());
		passwordTbx.sendKeys(customer.gePassword());
		rePasswordTbx.sendKeys(customer.gePassword());
		firstNameTbx.sendKeys(customer.getFirstName());
		lastNameTbx.sendKeys(customer.getLastName());
		
		countryDropDown.click();
		Select countryselect = new Select(countryDropDown);
		countryselect.selectByVisibleText(address[0]);

		Select stateSelect = new Select(stateDropDown);
		stateDropDown.click();
		stateSelect.selectByVisibleText(address[1]);
		
		postCodeTbx.sendKeys(address[2]);
		cityTbx.sendKeys(address[3]);
		address1Tbx.sendKeys(address[4]);
		address2Tbx.sendKeys(address[5]);
		
		phoneTbx.sendKeys(customer.getPhoneNumber());

		String oldUrl = driver.getCurrentUrl();

		registerBtn.submit();
		//CHECK FOR SUCCESS
		try {
			wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));
		} catch (Exception e) {

		}
		if (driver.getCurrentUrl().equals(oldUrl)) {
			FixedTopMenu.signInLink.click();
			Log.error("Registration Failed.");
			return false;
		} else {
			MyAccountPage accPage = new MyAccountPage(driver);
			Log.info("Registration Successful!");
			accPage.customerSignOut();
			return true;
		}
	}

	@Override
	protected void load() {
		// Page is already loaded
	}

	@Override
	protected void isLoaded() throws Error {
		assertEquals(driver.getTitle(), "Avactis Demo Store");
	}
}
