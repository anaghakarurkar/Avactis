package utilities;
// this product will be added to the shopping cart
public class Product {
	public int ID;
	public String name;
	public String category;
	public String subCategory;
	public int quantity;
	public float productPrice;
	public Boolean addedToTheCart;  // if product is added successfully to the cart or not
	public String productPageTitle;
	public float totalPrice;
	
	
	public Product(int id, String name, String category, String subCategory, int quantity, float productPrice) {
		this.ID = id;
		this.name = name;
		this.category = category;
		this.subCategory = subCategory;
		this.quantity = quantity;
		this.productPrice = productPrice;
		this.addedToTheCart = false;
		this.productPageTitle = "";
		this.totalPrice = (float) quantity * productPrice;
	}
}
