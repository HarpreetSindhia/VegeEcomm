package EcommVege.VegeEcomm;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import EcommVege.PageObjects.CartPage;
import EcommVege.PageObjects.CheckoutPage;
import EcommVege.testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

//Comments added by Harpreet Sindhia

	@Test(dataProvider = "getData")
	public void submitOrderTest(HashMap<String, Object> input) throws IOException {

		productCatalogue.addProductsToCart((List<String>) input.get("groceryItem"));
		productCatalogue.clickCart();
		Boolean matchProductFound = productCatalogue.matchProductsByName((List<String>) input.get("groceryItem"));
		Assert.assertTrue(matchProductFound, "Product not exist");
		CartPage cartPage = productCatalogue.proceedCheckout();
		String promocodeToast = cartPage.appyPromocode((String) input.get("code"));
		Assert.assertTrue(promocodeToast.equals("Code applied ..!"));
		CheckoutPage checkoutPage = cartPage.placeOrder();
		String dropDownText = checkoutPage.selectCountry((String) input.get("country"));
		Assert.assertTrue(dropDownText.equals((String) input.get("country")));
		checkoutPage.acceptTerms();
		checkoutPage.confirmOrder();

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, Object>> data = getJsonToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\EcommVege\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
