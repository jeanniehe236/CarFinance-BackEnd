package carfinance.server.offerGenerator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class ProductTest {
	
	private Product testProduct = new Product("Lease", 0.01);
	@Test
	@DisplayName("Test getting the product name.")
	void testGetName() {
		assertEquals("Lease", testProduct.getName());
	}
	
	@Test
	@DisplayName("Test getting the base interest rate.")
	void testGetBaseInterest() {
		assertEquals(0.01, testProduct.getBaseInterestRate());
	}

}
