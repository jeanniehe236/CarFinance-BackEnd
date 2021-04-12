package carfinance.server.offerGenerator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import carfinance.server.TestUtils;

class CatalogueTest {
	Catalogue catalogue;
	Product[] endCustomerProducts;
	Product[] dealerProducts;
	
	public CatalogueTest() {
		
		this.endCustomerProducts = new Product[]{
				new Product("Hire_Purchase", 0.04),
				new Product("Lease", 0.05)
		};
		
		this.dealerProducts = new Product[]{
				new Product("Hire_Purchase", 0.03),
				new Product("Lease", 0.04)
		};
		this.catalogue = new Catalogue(Map.of("End_Customer", 
				endCustomerProducts, "Dealer",dealerProducts));
	}
	
	
	@Test
	@DisplayName("Test getting the channel names")
	void testGetChannels() {
		assertEquals(
				Set.of("End_Customer", "Dealer"), 
				catalogue.getChannels(), "getChannels() should return Private_Consumer and Dealer.");
	}
	
	
	@Test
	@DisplayName("Test if getProducts() returns the product for dealers as constructed.")
	void testGetDealerProductsInTermsOfProductsName() {
		assertTrue(TestUtils.equals(dealerProducts, catalogue.getProducts("Dealer")));
	}
	
	
	@Test
	@DisplayName("Test if getProducts() returns the products for end customers as constructed.")
	void testGetEndCustomerProducts() {
		assertTrue(TestUtils.equals(endCustomerProducts, catalogue.getProducts("End_Customer")));
		
	}
	
	
	@Test
	@DisplayName("Test default constructor in terms of end customer products.")
	void testDefaultEndCustomerProducts() {
		assertTrue(TestUtils.equals(endCustomerProducts, new Catalogue().getProducts("End_Customer")));
		
	}
	
	@Test
	@DisplayName("Test default constructor in terms of dealer products.")
	void testDefaultDealerProducts() {
		assertTrue(TestUtils.equals(dealerProducts, new Catalogue().getProducts("Dealer")));
		
	}
	
	@Test
	@DisplayName("Test changing products for end customers from outside the catalogue.")
	void testChangingPrivateCustomerProductFromOutsideTheCatalogue() {
		Product[] products = catalogue.getProducts("Dealer");
		products[0] = null;
		assertNotEquals(null, catalogue.getProducts("End_Customer")[0], "Setting a product to null should not be possible from outside the catalogue.");
	}
	
	@Test
	@DisplayName("Test changing channels outside the catalogue.")
	void testAddingAChannelFromOutsideTheCatalogue() {
		Set<String> channels = catalogue.getChannels();
		assertThrows(Exception.class, () -> {
			channels.add("Hacker");
		}, "Adding channels to a catalogue should not be possible outside the catalogue.");
	}
	
	@Test
	@DisplayName("Test changing channels outside the catalogue.")
	void testClearingChannelsFromOutsideTheCatalogue() {
		Set<String> channels = catalogue.getChannels();
		assertThrows(Exception.class, () -> {
			channels.clear();
		}, "Adding channels to a catalogue should not be possible outside the catalogue.");
	}
}
