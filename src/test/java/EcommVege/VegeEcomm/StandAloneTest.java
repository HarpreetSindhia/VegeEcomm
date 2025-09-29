package EcommVege.VegeEcomm;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	
	List<String> groceryItem =	List.of("Cauliflower" , "Tomato" , "Carrot" , "Mushroom" , "Banana");

	@Test
	public void submitOrderTest() 
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		List<WebElement> webProducts =	driver.findElements(By.xpath("//div[@class='product']"));
		//Cucumber - 1 Kg
		for(String item : groceryItem)
		{
			WebElement selectProduct =	webProducts.stream().filter(webP -> webP.findElement(By.xpath(".//h4")).getText().split("-")[0].trim().equals(item)).findFirst().orElse(null);
			
			if(selectProduct != null)
			{
				WebElement addProduct =	selectProduct.findElement(By.xpath(".//div[@class='product-action']/button"));
				
				addProduct.click();
			}
		}
		
		driver.findElement(By.xpath("//img[@alt='Cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.xpath("//ul[@class='cart-items']/li"));
		
		for(String item : groceryItem)
		{
			Boolean matchProduct =	cartProducts.stream().anyMatch(cartP -> cartP.findElement(By.xpath(".//p[@class='product-name']")).getText().split("-")[0].trim().equals(item));
		
		
			Assert.assertTrue(matchProduct, "Product not exist");
		
		}
		
		
		WebElement checkOut =	driver.findElement(By.xpath("//div[@class='action-block']/button"));
		
		checkOut.click();
		
		driver.findElement(By.xpath("//input[@class='promoCode']")).sendKeys("rahulshettyacademy");
		
		driver.findElement(By.xpath("//button[@class='promoBtn']")).click();
		
		String	promocodeToast =	driver.findElement(By.xpath("//span[@class='promoInfo']")).getText();
		
		Assert.assertTrue(promocodeToast.equals("Code applied ..!"));
		
		driver.findElement(By.xpath("//button[text()='Place Order']")).click();
		
		WebElement staticDropDown =	driver.findElement(By.xpath("//select"));
		
		Select dropDown = new Select(staticDropDown);
		
		dropDown.selectByValue("Belgium");
		
		String dropDownText =	dropDown.getFirstSelectedOption().getText();
		
		Assert.assertTrue(dropDownText.equals("Belgium"));
		
		driver.findElement(By.xpath("//input[@type='checkbox']")).click();
		
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		
		driver.quit();
		
		
		

	}

}
