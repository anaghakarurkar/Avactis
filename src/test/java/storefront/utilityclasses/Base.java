package storefront.utilityclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class Base {
	private static WebDriver driver;
	private static String driverPath;
	private static WebDriverWait wait;
	private static String avactisStorePath;
	private static String avactisAdminPath;
	private static String adminUsername;
	private static String adminPassword;
    
    
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
		// This method create new instance of a driver
		switch (browserName) {
		case CHROME:
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			break;
		case MICROSOFTEDGE:
			System.setProperty("webdriver.edge.driver", driverPath);
			driver = new EdgeDriver();
			break;
		default:
			break;
		}
		driver.manage().window().maximize();
	}

		
	public static void setExplicitWait(Duration duration) {
		wait = new WebDriverWait(driver, duration);
	}

	public static  WebDriverWait explicitWait() {
		return wait;
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static String getAvactisStoreFrontURL() {
		return avactisStorePath;
	}

	public static String getAvactisAdminURL() {
		return avactisAdminPath;
	}

	public static String getAdminUserEmail() {
		return adminUsername;
	}

	public static String getAdminPassword() {
		return adminPassword;
	}

	
	public static void closeBrowser() {
		driver.close();
	}

	public static void quitAllBrowserWindows() {
		driver.quit();
	}
	
	
	
}