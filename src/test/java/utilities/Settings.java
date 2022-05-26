package utilities;


public final class Settings {
	private static Customer customerObj;
	private static Base baseObj;

	private Settings() {}
	
	public static void loadSettings() {
		baseObj = new Base();	
		customerObj = new Customer();
	}
	public static Base getBase() {
		return baseObj;
	}
	public static Customer getCustomer() {
		return customerObj;
	}
}
