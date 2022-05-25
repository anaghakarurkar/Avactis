package pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.elements.FixedTopMenu;


public class MyAccountPage extends LoadableComponent<MyAccountPage> {
	WebDriver driver;
	WebDriverWait wait;
	FixedTopMenu topMenu = new FixedTopMenu();
	String pageUrl = "http://localhost/Avactis/home.php";
	public Boolean signInSuccess = false;
	
	@FindBy(xpath="//div[@class='pre-header']//a[text()='Sign Out']")
	public WebElement signOutLink;
	
	@FindBy(xpath="//div[contains(@class,\"note-success\")][text()='Account created successfully. You are now registered.']")
	public WebElement accCreatedDiv;
	
	public MyAccountPage(WebDriver driver, WebDriverWait wait) {
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
		assertEquals(driver.getCurrentUrl(), pageUrl);
		signInSuccess = true;
	}

	public void  customerSignOut() {
		signOutLink.click();
	}
}
