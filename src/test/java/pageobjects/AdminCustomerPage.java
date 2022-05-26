package pageobjects;

import static org.testng.Assert.assertEquals;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Settings;

public class AdminCustomerPage extends LoadableComponent<AdminCustomerPage> {
	String expectedPageTitle = "Customers - Avactis Shopping Cart";
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(xpath = "//div[@class='table-responsive']/table//tr/td[3]")
	public List<WebElement> emailColumnList;

	@FindBy(xpath = "//div[@class='table-responsive']/table//tr/td[1]")
	public List<WebElement> checkBoxColumnList;

	@FindBy(xpath = "//tfoot//div[@id='DeleteButton2']")
	public WebElement deleteButton;

	@FindBy(xpath = "//a[@class='dropdown-toggle'][@title='Admin Info']")
	public WebElement adminTopLink;

	@FindBy(xpath = "//div[@class='top-menu']//a[@title='Sign Out']")
	public WebElement topMenuSignOut;

	@FindBy(xpath = "//div[@class='modal-footer']/button[text()='OK']")
	public WebElement deleteOk;

	public AdminCustomerPage(WebDriver driver, WebDriverWait wait) {
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

	public void deleteCustomerTestData() {
		String email = Settings.getCustomer().getEmail();
		
				for (int i = 0; i <= emailColumnList.size() - 1; i++) {
					WebElement e = emailColumnList.get(i);
					if (e.getText().equals(email)) {
						WebElement checkBx = checkBoxColumnList.get(i);
						if (checkBx.isSelected() != true) {
							checkBx.click();
							deleteButton.click();
							WebElement okButton = wait.until(ExpectedConditions.visibilityOf(deleteOk));
							okButton.click();
							wait.until(ExpectedConditions.stalenessOf(e));
						}
					}
				}
			
		Actions actions = new Actions(driver);
		actions.moveToElement(adminTopLink);
		actions.moveToElement(topMenuSignOut);
		actions.click();
		actions.perform();
	}
}
