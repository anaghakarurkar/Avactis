package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Customer {
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	
	private String country;
	private String state;
	private String postcode;		
	private String city;	
	private String address1;
	private String address2;
	

	private String phone;

	public Customer() {
		setCustomerData();
	}

	public String getEmail() {
		return userName;
	}

	public String gePassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String[] getAddress() {
		String[] address = new String[6];
		address[0] = country;
		address[1] = state;
		address[2] = postcode;
		address[3] = city;
		address[4] =address1;
		address[5] = address2;
		return address;
	}

	public String getPhoneNumber() {
		return phone;
	}

	private void  setCustomerData() {
		// get all properties from customer.properties and initialise class data members

				File file = new File("src\\test\\resources\\properties\\customer.properties");
				try {
					// Create FileInputStream object to read the file
					FileInputStream fileInput = new FileInputStream(file);

					// Create properties class object
					Properties prop = new Properties();

					// Load properties file in InputStream
					prop.load(fileInput);
					
					userName = prop.getProperty("USERNAME");
					password = prop.getProperty("PASSWORD");

					firstName = prop.getProperty("FIRSTNAME");
					lastName = prop.getProperty("LASTNAME");
					
					country = prop.getProperty("COUNTRY");
					state = prop.getProperty("STATE");
					postcode = prop.getProperty("POSTCODE");
					city = prop.getProperty("CITY");
					address1 =prop.getProperty("ADDRESS1");
					address2 = prop.getProperty("ADDRESS2");
					
					phone = prop.getProperty("PHONE");
					
					fileInput.close();
				} catch (IOException e) {
					System.out.println("Can not find/load customer.properties file");
				}
	}
}
