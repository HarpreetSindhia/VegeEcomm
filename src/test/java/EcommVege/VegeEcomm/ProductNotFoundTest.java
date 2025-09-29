package EcommVege.VegeEcomm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import EcommVege.testComponents.BaseTest;
import EcommVege.testComponents.Retry;

public class ProductNotFoundTest extends BaseTest {


	@Test(dataProvider="getData" , retryAnalyzer = Retry.class)
	public void productNotFound(HashMap<String, Object> input) {

		productCatalogue.searchInput((String) input.get("searchProduct"));

		WebElement noResultElement = productCatalogue.getSearchResult();

		Assert.assertTrue(noResultElement.isDisplayed(), "List does not display any products");

		Assert.assertTrue(noResultElement.getText().equals("Sorry, no products matched your search!"));

	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String , Object>> data =	getJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\EcommVege\\data\\NoMatchProduct.json");
		
		return new Object[][] {{data.get(0)}};
	}

}
