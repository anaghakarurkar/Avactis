package pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminMainPage extends LoadableComponent<AdminMainPage> {
	private WebDriver driver;
	private WebDriverWait wait;
	private String expectedPageTitle = "Avactis Shopping Cart";
	public static String adminEmail;
	public static String adminPass;

	@FindBy(xpath = "//input[@name='AdminEmail'][@type='text']")
	public WebElement emailTbx;

	@FindBy(xpath = "//input[@name='Password'][@type='password']")
	public WebElement passwordTbx;

	@FindBy(xpath = "//input[@name='RememberEmail'][@type='checkbox']")
	public WebElement rememberMeCbx;

	@FindBy(xpath = "//button[@type='submit']")
	public WebElement submitBtn;

	public AdminMainPage(WebDriver driver, WebDriverWait wait) {
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

	public void login() {
		emailTbx.sendKeys(adminEmail);
		passwordTbx.sendKeys(adminPass);

		if (rememberMeCbx.isSelected()) {
			rememberMeCbx.click();
		}
		submitBtn.click();
	}
	
	public void deleteCustomer() {
		login();
		AdminHomePage homePage = new AdminHomePage(driver, wait);
		homePage.chooseCustomerMenuOption();
	}
}
