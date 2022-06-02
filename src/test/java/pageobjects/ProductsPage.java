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

import pageobjects.elements.FixedTopMenu;
import utilities.Product;

public class ProductsPage extends LoadableComponent<ProductsPage> {
	private WebDriver driver;
	private WebDriverWait wait;
	private Product product;
	private FixedTopMenu topMenu = new FixedTopMenu();

	String itemStr;
	private By item;

	@FindBy(xpath = "//input[@type='submit'][@value='Add To Cart']")
	public WebElement addToCartButton;

	@FindBy(xpath = "//div[@class='product-quantity']/select[@name='quantity_in_cart']")
	public WebElement quantitySelect;

	//@FindBy(xpath = "//div[@class='price']/strong[@class='product_sale_price']")
	
	@FindBy(xpath ="div[@class='price']//span")
	public WebElement productPriceLabel;

	@Override
	protected void load() {
		// page is loaded already
	}

	public ProductsPage(WebDriver driver, WebDriverWait wait, Product product) {
		this.driver = driver;
		this.wait = wait;
		this.product = product;
		this.product.productPageTitle = getProductPageTitle();
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
		// get total cart product count before adding new item.
		wait.until(ExpectedConditions
				.visibilityOf(driver.findElement(By.cssSelector("div.product_list.category_products.productlist"))));

		String cartItemCount = FixedTopMenu.cartItemCountLink.getText().replaceAll(" items", "").trim();

		int count = Integer.parseInt(cartItemCount) + product.quantity;

		WebElement element = driver.findElement(item);
		element.click();

		// add new product item to the cart.
		wait.until(ExpectedConditions.visibilityOf(addToCartButton));

		Select select = new Select(quantitySelect);
		select.selectByValue(String.valueOf(product.quantity));
		//product.productPrice = Float.parseFloat(productPriceLabel.getText().substring(1));
		System.out.println(product.productPrice);
		addToCartButton.click();

		// check if cart product count has increased by 1
		Boolean result = wait
				.until(ExpectedConditions.textToBePresentInElement(FixedTopMenu.cartItemCountLink, (count + " items")));

		return result;
	}

	public String getProductPageTitle() {
		String pCategory = product.category.toLowerCase();
		String pSubCategory = product.subCategory.toLowerCase();
		String tempStr = "";

		switch (pCategory) {
		case "apparel":
			tempStr = "Apparel - Avactis Demo Store";
			break;
		case "computers":
			switch (pSubCategory) {
			case "none":
				tempStr = "Computers - Avactis Demo Store";
				break;
			case "notebooks":
				tempStr = "Notebooks - Avactis Demo Store";
				break;
			case "desktops":
				tempStr = "Desktops - Avactis Demo Store";
				break;
			default:
				break;
			}
			break;
		case "dvd":
			switch (pSubCategory) {
			case "tv on dvd":
				tempStr = "TV on DVD - Avactis Demo Store";
				break;
			case "kids dvds":
				tempStr = "Kids DVDs - Avactis Demo Store";
				break;
			case "classic films":
				tempStr = "Classic Films - Avactis Demo Store";
				break;
			default:
				break;
			}
			break;
		case "furniture":
			tempStr = "Furniture - Avactis Demo Store";
			break;
		case "sports":
			tempStr = "Sport and Travel - Avactis Demo Store";
			break;
		case "digital distribution":
			tempStr = "Digital Distribution - Avactis Demo Store";
			break;
		default:
			break;
		}

		return tempStr;
	}
}
