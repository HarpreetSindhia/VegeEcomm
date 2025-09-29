package EcommVege.VegeEcomm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import EcommVege.testComponents.BaseTest;

public class SearchProductTest extends BaseTest {

	@Test(dataProvider="getData" , groups={"ValidateSearchInput"})
	public void validateSearchAndAddToCart(HashMap<String , Object> input ) {

		
		productCatalogue.searchInput((String) input.get("searchProduct"));
		List<String> searchResult =	productCatalogue.mapWebElementToString();
		Assert.assertTrue(searchResult.contains((String) input.get("expectedProduct")) , "Search did not return expected product ");
		productCatalogue.addProductToCart((String) input.get("expectedProduct"));
		productCatalogue.clickCart();
		Boolean productMatch =	productCatalogue.matchProductByName((String) input.get("expectedProduct"));
		Assert.assertTrue(productMatch , "Expected product not found in cart");
		
			
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String , Object>> data =	getJsonToMap(System.getProperty("user.dir")+"\\src\\test\\java\\EcommVege\\data\\SearchProduct.json");
		
		return new Object[][] {{data.get(0)} , {data.get(1)}};
	}
	
	
	
}
