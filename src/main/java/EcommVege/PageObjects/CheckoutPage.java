package EcommVege.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//WebElement staticDropDown =	driver.findElement(By.xpath("//select"));
	
	@FindBy(xpath="//select")
	WebElement staticDropDown;
	
	
	public String selectCountry(String country)
	{
		Select dropDown = new Select(staticDropDown);
		
		dropDown.selectByValue(country);
		
		String dropDownText = dropDown.getFirstSelectedOption().getText();
		
		return dropDownText;
		
		
	}
	
	
	//driver.findElement(By.xpath("//input[@type='checkbox']")).click();
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement termsConditions;
	
	public void acceptTerms()
	{
		termsConditions.click();
	}
	
	
	//driver.findElement(By.xpath("//button[text()='Proceed']")).click();
	
	@FindBy(xpath="//button[text()='Proceed']")
	WebElement proceed;
	
	public void confirmOrder()
	{
		proceed.click();
	}
	
}
	
	/*Select dropDown = new Select(staticDropDown);
	
	dropDown.selectByValue("Belgium");
	
	String dropDownText =	dropDown.getFirstSelectedOption().getText();
	
}*/
