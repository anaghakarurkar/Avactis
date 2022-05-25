package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class Base {
	public static  WebDriver driver;
	private  String driverPath;
	private  WebDriverWait wait;
	private  String avactisStorePath;
	private  String avactisAdminPath;
	private  String adminUsername;
	private  String adminPassword;
    
    
    public static enum Browser {
		CHROME, MICROSOFTEDGE
	};

	private static Browser browserName;

	public Base() {
		this(Browser.CHROME);
	}

	public Base(Browser brName) {
		browserName = brName;
		setProperties();
	}
	
	

	private void setProperties() {
		// get all properties from config.properties and initialise class data members

		File file = new File("src\\test\\resources\\properties\\config.properties");
		try {
			// Create FileInputStream object to read the file
			FileInputStream fileInput = new FileInputStream(file);

			// Create properties class object
			Properties prop = new Properties();

			// Load properties file in InputStream
			prop.load(fileInput);

			driverPath = prop.getProperty(browserName.toString().toLowerCase());
			avactisStorePath = prop.getProperty("storefrontlink");
			avactisAdminPath = prop.getProperty("adminlink");
			adminUsername = prop.getProperty("adminemail");
			adminPassword = prop.getProperty("adminpassword");
			
			launchBrowser();
			wait = new WebDriverWait(driver,
			Duration.ofSeconds(Long.parseLong(prop.getProperty("waitinseconds"))));

			fileInput.close();
		} catch (IOException e) {
			System.out.println("Can not find/load config.properties file");
		}
	}

	private void launchBrowser() {
		// This method create new instance of a driver in incognito mode
		switch (browserName) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", driverPath);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			driver = new ChromeDriver(chromeOptions);
			break;
		case MICROSOFTEDGE:
			System.setProperty("webdriver.edge.driver", driverPath);
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("InPrivate");
			driver = new EdgeDriver(edgeOptions);
			break;
		default:
			break;
		}
		driver.manage().window().maximize();
	}

		
	public  void setExplicitWait(Duration duration) {
		wait = new WebDriverWait(driver, duration);
	}

	public   WebDriverWait explicitWait() {
		return wait;
	}

	public  WebDriver getDriver() {
		return driver;
	}

	public  String getAvactisStoreFrontURL() {
		return avactisStorePath;
	}

	public  String getAvactisAdminURL() {
		return avactisAdminPath;
	}

	public  String getAdminUserEmail() {
		return adminUsername;
	}

	public  String getAdminPassword() {
		return adminPassword;
	}

	
	public  void closeBrowser() {
		driver.close();
	}

	public  void quitAllBrowserWindows() {
		driver.quit();
	}
	
	
	
}