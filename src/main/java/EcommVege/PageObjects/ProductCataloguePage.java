package EcommVege.PageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCataloguePage {
	
	WebDriver driver;
	
	public ProductCataloguePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public void goToPage()
	{
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	}
	
	//List<WebElement> webProducts =	driver.findElements(By.xpath("//div[@class='product']"));
	
	@FindBy(xpath="//div[@class='product']")
	List<WebElement> webProducts;
	
	public List<WebElement> getProductList()
	{
		return webProducts;
	}
	
	By productNameLocator =	By.xpath(".//h4");
	
	
	
	public List<WebElement> getProductsByName(List<String> groceryItem)

	{
		List<WebElement> matchProducts = new ArrayList<>();
		
		
		for(String item : groceryItem)
		{
			WebElement selectProduct =	getProductList().stream().filter(webP -> webP.findElement(productNameLocator).getText().split("-")[0].trim().equals(item)).findFirst().orElse(null);
			
			if(selectProduct != null)
			{
				matchProducts.add(selectProduct);
			}
			
		}
		
		return matchProducts;
	
	}
	
	By addProductButton =	By.xpath(".//div[@class='product-action']/button");
	
	
	public void addProductsToCart(List<String> groceryItem)
	{
		List<WebElement> matchProducts =	getProductsByName(groceryItem);
		
		for(WebElement product : matchProducts)
		{
			WebElement addProduct = 	product.findElement(addProductButton);
			
			addProduct.click();
		}
	
	}
	
	
	
	
	//WebElement cartIcon =	driver.findElement(By.xpath("//img[@alt='Cart']"));
	
	@FindBy(xpath="//img[@alt='Cart']")
	WebElement cartIcon;
	
	public void clickCart()
	{
		cartIcon.click();
	}
	
	
	//List<WebElement> cartProducts = driver.findElements(By.xpath("//ul[@class='cart-items']/li"));
	
	@FindBy(xpath="//ul[@class='cart-items']/li")
	List<WebElement> cartProducts;
	
	
	public List<WebElement> getCartProducts()
	{
		return cartProducts;
	}
	
	public boolean matchProductsByName(List<String> groceryItem)
	{
		for(String item : groceryItem)
		{
			Boolean matchProduct =	getCartProducts().stream().anyMatch(cartP -> cartP.findElement(By.xpath(".//p[@class='product-name']")).getText().split("-")[0].trim().equals(item));
		
			if(!matchProduct)
			{
				return false;
			}
		
		}
		
		return true;
		
	}
	
	//WebElement checkOut =	driver.findElement(By.xpath("//div[@class='action-block']/button"));
	
	@FindBy(xpath="//div[@class='action-block']/button")
	WebElement checkout;
	
	public CartPage proceedCheckout()
	{
		checkout.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		
	}
	
	
	
	//driver.findElement(By.xpath("//input[@type='search']")).sendKeys(searchProduct);
	
	@FindBy(xpath="//input[@type='search']")
	WebElement searchInput;
	
	public void searchInput(String searchProduct)
	{
		searchInput.sendKeys(searchProduct);
		
	}
	
	//List<WebElement> searchProducts =	driver.findElements(By.xpath("//div[@class='product']"));
	
	@FindBy(xpath="//div[@class='product']")
	List<WebElement> searchResults;
	
	public List<WebElement> getSearchProducts()
	{
		return searchResults;
	}
	
	public List<String> mapWebElementToString()
	{
		List<String> searchResult =	getSearchProducts().stream().map(sProd -> sProd.findElement(productNameLocator).getText().split("-")[0].trim()).collect(Collectors.toList());
		
		return searchResult;
	}
	
	public WebElement getProductByName(String expectedProduct)
	{
		
		WebElement selectProduct =	getSearchProducts().stream().filter(searchP -> searchP.findElement(productNameLocator).getText().split("-")[0].trim()
		.equalsIgnoreCase(expectedProduct)).findFirst().orElse(null);
		
		return selectProduct;
	}
	
	public void addProductToCart(String expectedProduct)
	{
			WebElement selectProduct =	getProductByName(expectedProduct);
					
					if(selectProduct != null)
					{
						WebElement addProduct =	selectProduct.findElement(addProductButton);
						
						addProduct.click();
						
						
					}	
	}
	
	
	
	
	
	//Boolean productMatch =	cartProducts.stream().anyMatch(cartP -> cartP.findElement(By.xpath(".//p[@class='product-name']")).getText().split("-")[0].trim().equalsIgnoreCase(expectedProduct));
	
	public Boolean matchProductByName(String expectedProduct)
	{
		Boolean productMatch =	getCartProducts().stream().anyMatch(cartP -> cartP.findElement(By.xpath(".//p[@class='product-name']")).getText().split("-")[0].trim().equalsIgnoreCase(expectedProduct));
		
		if(!productMatch)
		{
			return false;
		}
		
		return true;
	}
	
	
	
	//WebElement noResultElement =	driver.findElement(By.xpath("//div[@class='products-wrapper']//div[@class='no-results']//h2"));
	
	
	@FindBy(xpath="//div[@class='products-wrapper']//div[@class='no-results']//h2")
	WebElement noResultElement;
	
	public WebElement getSearchResult()
	{
		return	noResultElement;
	}
}
	
	
	

	
	
	
	
	/*for(String item : groceryItem)
	{
		WebElement selectProduct =	webProducts.stream().filter(webP -> webP.findElement(By.xpath(".//h4")).getText().split("-")[0].trim().equals(item)).findFirst().orElse(null);
		
		if(selectProduct != null)
		{
			WebElement addProduct =	selectProduct.findElement(By.xpath(".//div[@class='product-action']/button"));
			
			addProduct.click();
		}
	}*/