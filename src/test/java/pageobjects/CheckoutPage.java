package pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends LoadableComponent<CheckoutPage> {
	WebDriver driver;
	WebDriverWait wait;
	private String expectedPageTitle = "Avactis Demo Store";

	@FindBy(xpath = "//form[@id='checkout_1']//input[@value= 'Continue Checkout']")
	public WebElement checkoutButton;

	@FindBy(name = "billingInfo[Firstname]")
	public WebElement firstNameTxt;

	@FindBy(name = "billingInfo[Lastname]]")
	public WebElement lastNameTxt;

	@FindBy(name = "billingInfo[Email]")
	public WebElement emailTxt;

	@FindBy(name = "billingInfo[Country]")
	public WebElement countrySelect;

	@FindBy(name = "billingInfo[Postcode]")
	public WebElement zipcodeTxt;

	@FindBy(name = "billingInfo[Statemenu]")
	public WebElement stateSelect;

	@FindBy(name = "billingInfo[City]")
	public WebElement cityTxt;

	@FindBy(name = "billingInfo[Streetline1]")
	public WebElement address1Txt;

	@FindBy(name = "billingInfo[Streetline2]")
	public WebElement address2Txt;

	@FindBy(name = "billingInfo[Phone]")
	public WebElement phoneTxt;

	@FindBy(name = "checkbox_shipping_same_as_billing]")
	public WebElement sameShipAddCbx;

	public CheckoutPage(WebDriver driver, WebDriverWait wait) {
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

	public void checkoutAsGuest() {
		firstNameTxt.sendKeys("Rose");
		lastNameTxt.sendKeys("Evans");
		emailTxt.sendKeys("roseevans@gmail.com");

		countrySelect.click();
		Select select = new Select(countrySelect);
		select.selectByValue("Ireland");

		zipcodeTxt.sendKeys("D01");

		stateSelect.click();
		select = new Select(stateSelect);
		select.selectByValue("Dublin");

		cityTxt.sendKeys("Leinster");
		address1Txt.sendKeys("2");
		address2Txt.sendKeys("Abbey street lower");
		phoneTxt.sendKeys("834027127");

		if (sameShipAddCbx.isSelected() == false)
			sameShipAddCbx.click();

		checkoutButton.click();
	}

	public void checkoutAsCustomer() {
		checkoutButton.click();

	}

	public void verifyAddress() {
		// Verify one or two lines of address

	}

	public void verifyAmount() {
		// Verify the amount is matching with
		// 1. product pricing and
		// 2. tax and
		// 3. shipping charges
	}
}
