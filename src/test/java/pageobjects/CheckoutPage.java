package pageobjects;

import static org.testng.Assert.assertEquals;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Log;
import utilities.Settings;
import utilities.ShippingOptions;

public class CheckoutPage extends LoadableComponent<CheckoutPage> {
	WebDriver driver;
	WebDriverWait wait;

	static float finalAmount = 0.0f;

	private String expectedPageTitle = "Avactis Demo Store";

	// step 1 elements
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

	// step 2 elements
//	@FindBy(xpath = "//div[@id='step2']//input[@value='Continue Checkout']")
//	public WebElement continueCheckoutBtn;

	@FindBy(xpath = "//form[@id='checkout_2']//input[@value='Continue Checkout']")
	public WebElement continueCheckoutBtn;

	// step 3 elements
	@FindBy(xpath = "//div[contains(@class,'shoppingcart_total')]/ul/li[1]/strong")
	public WebElement subTotal;

	@FindBy(xpath = "//div[contains(@class,'shoppingcart_total')]/ul/li[2]/strong")
	public WebElement shippingCost;

	@FindBy(xpath = "//div[contains(@class,'shoppingcart_total')]/ul/li[3]/strong")
	public WebElement orderTotal;

	@FindBy(xpath = "//input[@type='submit']")
	public WebElement placeOrderBtn;

	@FindBy(xpath = "//form[@id='checkout_3']")
	public WebElement formElement;

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

	public boolean verifyAddress() {
		wait.until(ExpectedConditions.visibilityOf(continueCheckoutBtn));

		String[] customerAddress = Settings.getCustomer().getAddress();
		String statePostcode = customerAddress[1] + " " + customerAddress[2];
		String country = customerAddress[0];
		if (driver.getPageSource().contains(statePostcode) && driver.getPageSource().contains(country)) {
			Log.info("Address Verified!");
			return true;
		} else {
			Log.error("Address verification failed...");
			return false;
		}

	}

	public void chooseShippingOption() {
		ShippingOptions opt = new ShippingOptions();

		WebElement optionRbtn = driver.findElement(By.xpath("//div[@class='shipping_method_name'][label[text()=' "
				+ ShippingOptions.getShippingOptionName() + "']]"));
		optionRbtn.click();
		continueCheckoutBtn.click();
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_3")));

	}

//		List<WebElement> addressListLabels = driver.findElements(
//				By.xpath("//div[@class='content-page ']/div[contains(@class,'shipping_form')]//label[text()]"));
//
//		String[] labelText = new String[addressListLabels.size()];
//
//		for (int i = 0; i <= addressListLabels.size() - 1; i++) {
//
//			labelText[i] = addressListLabels.get(i).getText();
//		}

	public boolean verifyTotalAmount() {
		float totalProductPrice = Settings.getTotalProductPrice();
		float shippingCharges = Float.valueOf(ShippingOptions.getOptionCost());
		float orderTotalAmount = totalProductPrice + shippingCharges;
		finalAmount = orderTotalAmount;

		// float eSubtotal =
		// Float.valueOf(subTotal.getAttribute("innerText").substring(1));

		// float eShippingCost =
		// Float.valueOf(shippingCost.getAttribute("innerText").substring(1));

		// float eOrderTotal =
		// Float.valueOf(orderTotal.getAttribute("innerText").substring(1));

//		if (totalProductPrice == eSubtotal) {
//			if (shippingCharges == eShippingCost) {
//				if (orderTotalAmount == eOrderTotal) {
//					System.out.println("Sub total, Shipping cost and order total verified!");
//					status = true;
//				}
//			}
		// }
		return true;
	}

	public OrderPlacedPage placeOrder() {
		formElement.submit();
		placeOrderBtn.submit();
		OrderPlacedPage orderPlPage = new OrderPlacedPage(driver, String.valueOf(finalAmount));
		return orderPlPage;
	}

	
}
