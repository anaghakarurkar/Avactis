package storefront.pageobjects;
// this product will be added to the shopping cart
public class Product {
	public String ID;
	public String name;
	public String category;
	public String subCategory;
	public Boolean addedToTheCart;  // if product is added successfully to the cart or not
	public String productPageTitle;
	public Product(String id, String name, String category, String subCategory) {
		this.ID = id;
		this.name = name;
		this.category = category;
		this.subCategory = subCategory;
		this.addedToTheCart = false;
		this.productPageTitle = "";
	}
}
