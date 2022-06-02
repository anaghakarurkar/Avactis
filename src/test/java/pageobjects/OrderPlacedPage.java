package pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Log;
import utilities.Settings;

public class OrderPlacedPage extends LoadableComponent<OrderPlacedPage> {
	private WebDriver driver;
private String orderTotalValue;
	@FindBy(xpath="//div[contains(@class,'note-success')]")
	public WebElement succesMessage;
	
	@FindBy(xpath="//div[contains(@class,'shoppingcart_total')]/ul/li[3]")
	public WebElement orderTotalLi;
	
	public OrderPlacedPage(WebDriver driver, String orderTotalValue) {
		this.driver = driver;
		this.orderTotalValue = orderTotalValue;
		get();
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// Page is already loaded

	}

	@Override
	protected void isLoaded() throws Error {
		WebDriverWait wait = Settings.getBase().explicitWait();
		wait.until(ExpectedConditions.urlContains("order_placed.php"));
		assertEquals(driver.getTitle(), "Avactis Demo Store");
		Log.info("Order placed successfully.");
	}

	public boolean verifyOrderIDDisplayed() {
		if(succesMessage.getText().contains("Your order is placed. Order ID: #")) {
			Log.info("Order Id display verification done.");
			return true;
		} else {
			Log.error("Can't find Order Id in final order placed page.");
			return false;
		}
	}
	
	public boolean verifyCorrectTotalAmount() {
	if(orderTotalLi.getText().contains(orderTotalValue)) {
		Log.info("Total amount verification done.");
		return true;
	}else {
		Log.error("Total amount verification failed.");
		return false;
	}
		
	}
}
