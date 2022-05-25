package pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminHomePage extends LoadableComponent<AdminHomePage> {
String expectedPageTitle = "Home - Avactis Shopping Cart";
	private WebDriver driver;
	private WebDriverWait wait;

@FindBy(xpath="//li[@id='menu-users']/a")
public WebElement customersLink;



public AdminHomePage(WebDriver driver, WebDriverWait wait) {
	this.driver = driver;
	this.wait = wait;
	get();
	PageFactory.initElements(driver, this);
}

@Override
	protected void load() {
		//Page is already loaded
		
	}

	@Override
	protected void isLoaded() throws Error {
		assertEquals(driver.getTitle(), expectedPageTitle);
	}
	
	public void chooseCustomerMenuOption() {
		customersLink.click();
		AdminCustomerPage custPage= new AdminCustomerPage(driver, wait);
		custPage.deleteCustomerTestData();
	}

}
