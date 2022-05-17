package avactis.utilityclasses;

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
	private WebDriver driver;
	private String driverPath;
	private WebDriverWait wait;
	private String avactisStorePath;
	private String avactisAdminPath;
	private String adminUsername;
	private String adminPassword;

	public static enum Browser {
		CHROME, MICROSOFTEDGE, OPERA
	};

	private Browser browserName;

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

			this.driverPath = prop.getProperty(browserName.toString().toLowerCase());
			this.avactisStorePath = prop.getProperty("storefrontlink");
			this.avactisAdminPath = prop.getProperty("adminlink");
			this.adminUsername = prop.getProperty("adminemail");
			this.adminPassword = prop.getProperty("adminpassword");
			this.wait = new WebDriverWait(driver,
					Duration.ofSeconds(Long.parseLong(prop.getProperty("waitinseconds"))));

			launchBrowser();

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
		case OPERA:
			System.setProperty("webdriver.opera.driver", driverPath);
			driver = new OperaDriver();
			break;
		default:
			break;
		}
		driver.manage().window().maximize();
	}

		
	public void setExplicitWait(Duration duration) {
		wait = new WebDriverWait(driver, duration);
	}

	public WebDriverWait explicitWait() {
		return wait;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public String getAvactisStoreFrontURL() {
		return avactisStorePath;
	}

	public String getAvactisAdminURL() {
		return avactisAdminPath;
	}

	public String getAdminUserEmail() {
		return adminUsername;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	
	public void closeBrowser() {
		driver.close();
	}

	public void quitAllBrowserWindows() {
		driver.quit();
	}
}