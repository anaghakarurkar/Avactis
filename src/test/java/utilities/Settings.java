package utilities;


public final class Settings {
	private static Customer customerObj;
	private static Base baseObj;
	public static Product[] chosenProducts;

	private Settings() {}
	
	public static void loadSettings() {
		baseObj = new Base();	
		customerObj = new Customer();
		chosenProducts = null;
	}
	public static Base getBase() {
		return baseObj;
	}
	public static Customer getCustomer() {
		return customerObj;
	}
	public static Product[] getChosenProducts() {
		return chosenProducts;
	}
	public static float getTotalProductPrice() {
		float finalTotalPrice = 0.0f;
		for(int i=0; i<= chosenProducts.length-1;i++) {
			finalTotalPrice += chosenProducts[i].totalPrice;
		}
		return finalTotalPrice;
	}
}
