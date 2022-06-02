package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ShippingOptions {
	private static String method;
	private static String cost;

	public ShippingOptions() {
		loadShippingOption();
	}

	private static void loadShippingOption() {
		try {
			File file = new File("src\\test\\resources\\properties\\shippingOptions.properties");
			FileInputStream inputStream = new FileInputStream(file);
			Properties property = new Properties();
			property.load(inputStream);
			
			String chosenOpt = property.getProperty("chosen");
			
		method = property.getProperty(chosenOpt);
		
		cost = property.getProperty(chosenOpt + "C")  ;
			
		inputStream.close();
		} catch (IOException e) {
			System.out.println("Can not load shippingOptions.properties file");
		}
	}
	
	public static String  getShippingOptionName() {
		return method;
	}
	
	public static String getOptionCost() {
		return cost;
	}
}
