package storefront.pageobjects;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends LoadableComponent<ProductsPage> {
	private WebDriver driver;
	private WebDriverWait wait;
	private Product product;
	private FixedTopMenu topMenu = new FixedTopMenu();

	String itemStr;
	private By item;

	@FindBy(xpath = "//input[@type='submit'][@value='Add To Cart']")
	public WebElement addToCartButton;

	@Override
	protected void load() {
		// page is loaded already
	}

	public ProductsPage(WebDriver driver, WebDriverWait wait, Product product) {
		this.driver = driver;
		this.wait = wait;
		this.product = product;
		get();
		PageFactory.initElements(driver, this);
		PageFactory.initElements(driver, topMenu);
		itemStr = "//img[@class='img-responsive'][contains(@alt,'" + product.name + "')]";
		item = By.xpath(itemStr);
	}

	@Override
	protected void isLoaded() throws Error {

		assertEquals(driver.getTitle(), product.productPageTitle);
	}


	public Boolean addToCart() {
		//get total cart product count before adding new item.
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.cssSelector("div.product_list.category_products.productlist"))));
		
		String cartItemCount = FixedTopMenu.cartItemCountLink.getText().replaceAll(" items", "").trim();
		
		
		int count = Integer.parseInt(cartItemCount) + 1;

		WebElement element = driver.findElement(item);
		element.click();
		
		//add new product item to the cart.
		wait.until(ExpectedConditions.visibilityOf(addToCartButton));
		addToCartButton.click();
		
		//check if cart product count has increased by 1
		Boolean result = wait
				.until(ExpectedConditions.textToBePresentInElement(FixedTopMenu.cartItemCountLink, (count + " items")));
		return result;
	}
}
