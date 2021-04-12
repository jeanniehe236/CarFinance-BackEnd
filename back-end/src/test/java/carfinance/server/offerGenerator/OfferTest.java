package carfinance.server.offerGenerator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import carfinance.server.calculator.Calculator;

class OfferTest{

	private Offer baseOffer = new Offer("Lease", 0.04);
	private Offer advancedOffer = new Offer(baseOffer, 0.01, 100000, 10);
	
	@Test
	@DisplayName("Test getting the name of an offer constructed the same way as its parent class - the product class.")
	void testBaseOfferGetName() {
		assertEquals("Lease", baseOffer.getName());
	}
	
	@Test
	@DisplayName("Test getting the name of an offer constructed using the product, interest adjustment, price and term.")
	void testAdvancedOfferGetName() {
		assertEquals("Lease", advancedOffer.getName());
	}
	
	@Test
	@DisplayName("Test getting the base interest rate of an offer constucted the same way as its parent class - the product class.")
	void testGetBaseOfferBaseInterest() {
		assertEquals(0.04, baseOffer.getBaseInterestRate());
	}
	
	@Test
	@DisplayName("Test if an offer inherits the base interes rate of the product upon which it is based")
	void testGetAdvancedOfferBaseInterest() {
		assertEquals(baseOffer.getBaseInterestRate(), advancedOffer.getBaseInterestRate());
	}
	
	@Test
	@DisplayName("Test getting the interest rate of an offer with 1% extra interest rate")
	void testGetAdvancedOfferInterestWithExtraInterest() {
		assertEquals(baseOffer.getBaseInterestRate() + 0.01, advancedOffer.getInterestRate(), "The interest rate for an offer created with 1% extra interest based on a product with 1% base interest rate should be 3%.");
	}
	
	@Test
	@DisplayName("Test getting the interest rate of an offer with 1% discount")
	void testGetAdvancedOfferInterestWithInterestDiscount() {
		assertEquals(baseOffer.getBaseInterestRate()-0.01, new Offer((Product) baseOffer, -0.01, 100000, 10).getInterestRate(),
				"The interest rate for an offer created with 1% interest discount based on a product with 1% base interest rate should be 1%.");
	}
	
	@Test
	@DisplayName("Test getting the interest rate of an offer without interest adjustment")
	void testGetAdvancedOfferInterestWithoutInterestAdjustment() {	
		assertEquals(baseOffer.getBaseInterestRate(), new Offer((Product) baseOffer, 0.00, 100000, 10).getInterestRate(),
				"The interest rate of the offer should be the same as its base interest rate of the product upon which it is created given that there is no interest adjustment.");
	}
	
	
	@Test
	@DisplayName("Test getting the annual payment")
	void testGettingAnnualPayment() {
		int loan = 100000;
		int term = 10;
		Offer newOffer = new Offer(baseOffer, 0.01, loan, term);
		double annualPayment = Calculator.getAnnualPayment(newOffer.getInterestRate(), loan, term);
		
		assertEquals(annualPayment, 
				newOffer.getAnnualPayment(),
				"The offer annual payment should be:" + String.valueOf(annualPayment));
	}
	
	@Test
	@DisplayName("Test the offers' comparable interface.")
	void testSortingTwoOffers() {
		Offer[] offers = new Offer[] {
			new Offer(new Product("Lease", 0.03), -0.02, 10000, 10),
			new Offer(new Product("Hire Product", 0.04), -0.02, 10000, 10),
			new Offer(new Product("SMS Loan", 0.03), 0.00, 100, 10),
		};
		Arrays.sort(offers);
		assertEquals("SMS Loan", offers[0].getName(), "The offers should have implemented compareTo to allow them be sorted in increasing order by annual payment");
	}

}
