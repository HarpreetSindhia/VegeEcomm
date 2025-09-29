package EcommVege.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	//driver.findElement(By.xpath("//input[@class='promoCode']")).sendKeys("rahulshettyacademy");
	
	@FindBy(xpath="//input[@class='promoCode']")
	WebElement promocodeLocator;
	
	//driver.findElement(By.xpath("//button[@class='promoBtn']")).click();
	
	@FindBy(xpath="//button[@class='promoBtn']")
	WebElement applyButton;
	
	//String	promocodeToast =	driver.findElement(By.xpath("//span[@class='promoInfo']")).getText();
	
	@FindBy(xpath="//span[@class='promoInfo']")
	WebElement promocodeToast;
	
	public String appyPromocode(String text)
	{
		promocodeLocator.sendKeys(text);
		applyButton.click();
		return promocodeToast.getText();
	}
	
	
	//driver.findElement(By.xpath("//button[text()='Place Order']")).click();
	
	@FindBy(xpath="//button[text()='Place Order']")
	WebElement placeOrder;
	
	public CheckoutPage placeOrder()
	{
		placeOrder.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
