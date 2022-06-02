package pageobjects;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import utilities.Product;

public class MyCartPage extends LoadableComponent<MyCartPage> {
	WebDriver driver;

	@FindBy(xpath = "//form[@id='Product_Quan']/table//tr")
	public List<WebElement> tableRows;

	@FindBy(xpath = "//table//td[@class='goods-page-description']//a")
	public List<WebElement> descriptionColumn;

	@FindBy(xpath = "//table//td//select[contains(@name,'quantity_in_cart')]")
	public List<WebElement> quantitiyColumn;

	@FindBy(xpath = "//table//tr//td[@class='goods-page-price']/strong")
	public List<WebElement> priceColumn;

	@FindBy(xpath = "//table//tr//td[@class='goods-page-total']/strong")
	public List<WebElement> totalPriceColumn;

	public MyCartPage(WebDriver driver) {
		this.driver = driver;
		get();
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		// Page is loaded already
	}

	@Override
	protected void isLoaded() throws Error {
		assertEquals(driver.getTitle(), "Avactis Demo Store");
	}

	public boolean verifyCart(Product[] productList) {
		int count = 0;

		for (int i = 0; i <= descriptionColumn.size() - 1; i++) {

			for (int j = 0; j <= productList.length - 1; j++) {
				if (productList[j].name.equals(descriptionColumn.get(i).getText())) {
					Select select = new Select(quantitiyColumn.get(i));
					
					if (productList[j].quantity == Float.valueOf(select.getFirstSelectedOption().getText())) {
						if (productList[j].productPrice == Float.valueOf(priceColumn.get(i).getText().substring(1))) {
							if (productList[j].totalPrice == Float
									.valueOf(totalPriceColumn.get(i).getText().substring(1))) {
								count += 1;
								break;
							}
						}
					}
				}		

			}

		}
		if (count == descriptionColumn.size()) {
			return true;
		} else {
			return false;
		}

	}
}
