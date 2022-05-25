package pageobjects;

import static org.testng.Assert.assertEquals;

import java.util.HashSet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.elements.FixedTopMenu;
import pageobjects.extra.Customer;
import pageobjects.extra.SuccessfulRegList;

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

	public RegisterUserPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		get();
		PageFactory.initElements(driver, this);
		PageFactory.initElements(driver, topMenu);
	}

	public boolean registerCustomer(Customer customerData) {
		//FILL THE REGISTRATION FORM
		emailTbx.sendKeys(customerData.userName);
		passwordTbx.sendKeys(customerData.password);
		rePasswordTbx.sendKeys(customerData.password);
		firstNameTbx.sendKeys(customerData.firstName);
		lastNameTbx.sendKeys(customerData.lastName);
		
		countryDropDown.click();
		Select countryselect = new Select(countryDropDown);
		countryselect.selectByVisibleText(customerData.country);

		Select stateSelect = new Select(stateDropDown);
		stateDropDown.click();
		stateSelect.selectByVisibleText(customerData.state);

		postCodeTbx.sendKeys(customerData.postCode);
		cityTbx.sendKeys(customerData.city);
		address1Tbx.sendKeys(customerData.add1);
		address2Tbx.sendKeys(customerData.add2);
		phoneTbx.sendKeys(customerData.phone);

		String oldUrl = driver.getCurrentUrl();

		registerBtn.submit();
		//CHECK FOR SUCCESS
		try {
			wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));
		} catch (Exception e) {

		}
		if (driver.getCurrentUrl().equals(oldUrl)) {
			FixedTopMenu.signInLink.click();
			return false;
		} else {
			MyAccountPage accPage = new MyAccountPage(driver, wait);
			if(SuccessfulRegList.emailList== null)
			{
				SuccessfulRegList.emailList = new HashSet<String>();
			}
			SuccessfulRegList.emailList.add(customerData.userName);
			
			StoreMainPage.loginEmail = customerData.userName;
			StoreMainPage.loginPassword = customerData.password;
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
