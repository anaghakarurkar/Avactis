package pageobjects.extra;

public class Customer {
	public String userName;
	public String password;
	public String firstName;
	public String lastName;
	public String country;
	public String state;
	public String postCode;
	public String city;
	public String add1;
	public String add2;
	public String phone;
	
	
	public Customer(String userName,String	password, String firstName,
			String lastName,String country, String	state, String postCode,
			String city, String	add1, String add2,String phone) {
		this.userName = userName;
		this.password = password;
		this.firstName= firstName;
		this.lastName = lastName;
		this.country = country;
		this.state = state;
		this.postCode = postCode;
		this.city = city;
		this.add1 = add1;
		this.add2 = add2;
		this.phone = phone;
	}
}
