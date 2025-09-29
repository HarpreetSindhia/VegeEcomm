package EcommVege.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import EcommVege.PageObjects.ProductCataloguePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public ProductCataloguePage productCatalogue;

	public WebDriver initializeDriver() throws IOException
	{
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\EcommVege\\resources\\GlobalData.properties");
		Properties properties = new Properties();
		properties.load(fis);
		String browserName =	System.getProperty("browser")!= null ? System.getProperty("browser") : properties.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		
		return driver;
		
	}
	
	public List<HashMap<String , Object>> getJsonToMap(String filePath) throws IOException
	{
		//Json File To String
		
		String jsonContent =	FileUtils.readFileToString(new File(filePath) , StandardCharsets.UTF_8);
		
		//String to List<HashMap>
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<HashMap<String,Object>> data =	mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,Object>>> () {});
		
		return data;
	}
	
	
	public String getScreenShot(String testCaseName , WebDriver driver) throws IOException
	{
		TakesScreenshot ts =	(TakesScreenshot)driver;
		File src =	ts.getScreenshotAs(OutputType.FILE);
		File dest =	new File(System.getProperty("user.dir") + "//reports" + testCaseName + "//.png");
		
		FileUtils.copyFile(src, dest);
		
		return System.getProperty("user.dir")+"//reports"+testCaseName+"//.png";
	}
	
	
	
	
	@BeforeMethod(alwaysRun=true)
	public ProductCataloguePage launchApplication() throws IOException
	{
		driver = initializeDriver();
		productCatalogue = new ProductCataloguePage(driver);
		productCatalogue.goToPage();
		return productCatalogue;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeDriver()
	{
		driver.quit();
	}
}
