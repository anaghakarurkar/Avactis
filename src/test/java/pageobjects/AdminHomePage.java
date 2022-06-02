package pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminHomePage extends LoadableComponent<AdminHomePage> {
String expectedPageTitle = "Home - Avactis Shopping Cart";
	private WebDriver driver;
	private WebDriverWait wait;
	
@FindBy(xpath="//li[@id='menu-users']/a[@href='users.php']")
public WebElement customersLink;
@FindBy(xpath="//ul[@class='sub-menu']//span[text()='Customers']")
public WebElement customerSubMenuLink;

//div[@class='page-sidebar-wrapper']//a[@href='customers.php']

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
		Actions action = new Actions(driver);
		action.moveToElement(customersLink);
		action.moveToElement(customerSubMenuLink);
		action.click();
		action.perform();
		
		AdminCustomerPage custPage= new AdminCustomerPage(driver, wait);
		custPage.deleteCustomerTestData();
	}

}
