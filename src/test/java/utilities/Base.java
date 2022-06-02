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
	private static  WebDriver driver;
	private static String driverPath;
	private static WebDriverWait wait;
	private static String avactisStorePath;
	private static String avactisAdminPath;
	private static String adminUsername;
	private static String adminPassword;
    private static String chosenBrowser;
    private static long waitSeconds;
  
	public Base() {
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
		
			avactisStorePath = prop.getProperty("storefrontlink");
			avactisAdminPath = prop.getProperty("adminlink");

			adminUsername = prop.getProperty("adminemail");
			adminPassword = prop.getProperty("adminpassword");
			
			chosenBrowser = prop.getProperty("chosenbrowser");
			driverPath = prop.getProperty(chosenBrowser);
			waitSeconds =Long.parseLong(prop.getProperty("waitinseconds"));
			
			fileInput.close();
		} catch (IOException e) {
			System.out.println("Can not find/load config.properties file");
		}
	}

	public  void launchBrowser() {
		// This method create new instance of a driver in incognito mode
		switch (chosenBrowser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", driverPath);
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--incognito");
			driver = new ChromeDriver(chromeOptions);
			break;
		case "microsoftedge":
			System.setProperty("webdriver.edge.driver", driverPath);
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("InPrivate");
			driver = new EdgeDriver(edgeOptions);
			break;
		default:
			break;
		}
		
		wait = new WebDriverWait(driver,
				Duration.ofSeconds(waitSeconds));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}


	public WebDriverWait explicitWait() {
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