package storefront.pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends LoadableComponent<LoginPage> {
	String expectedPageTitle = "Avactis Demo Store";
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//a/button[text()='Register']")
	public WebElement registerBtn;

	
	//existing user sign in page elements
	@FindBy(xpath = "//input[@id='account_sign_in_form_email_id']")
	public WebElement signInemailTextBox;
	
	@FindBy(xpath="//input[@id='account_sign_in_form_passwd_id']")
	public WebElement signInPasswordTextBox;
	
	@FindBy(xpath="//input[@class='input_checkbox'][@name='remember_me']")
	public WebElement rememberMeCheckBox;
	
	@FindBy(xpath="//input[@value='Sign In']")
	public WebElement signInsubmitBtn;

	public LoginPage(WebDriver driver, WebDriverWait wait) {
		this.driver = driver;
		this.wait = wait;
		get();
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// Page is already loaded

	}

	@Override
	protected void isLoaded() throws Error {
		assertEquals(driver.getTitle(), expectedPageTitle);
	}

}
