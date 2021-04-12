package carfinance.server.responseGenerator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import carfinance.server.TestUtils;
import carfinance.server.offerGenerator.Catalogue;
import carfinance.server.offerGenerator.Offer;
import carfinance.server.offerGenerator.Product;
import carfinance.server.preprocessor.AnswerSheet;

class ResponseWithOfferTest {

	AnswerSheet answerSheet;
	
	ResponseWithOfferTest(){
		answerSheet = new AnswerSheet(new String[]{"Dealer", "New", "Diesel"}, 
				new int[] {1000000, 1000, 10});
	}
	
	@Test
	@DisplayName("Test Liskov substitution fulfillness by returning the correct answer sheet when constructed using the constructor of its parent.")
	void testGetAnswerSheet() {
		assertTrue(TestUtils.answerSheetsAreEqual(new ResponseWithOffer(answerSheet, "").getAnswerSheet(), answerSheet), 
				"getAnswerSheet() should return the answer sheet with the same content as it was when the answer sheet was passed into the response.");
	}
	
	@Test
	@DisplayName("Test Liskov substitution fulfillness by returning the correct message when constructed using the constructor of its parent.")
	void testGetMessage() {
		String message =  "A response has been generated";
		assertEquals(message, new ResponseWithOffer(answerSheet, message).getMessage(), 
				String.format("getAnswerSheet() should return %s.", message));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 5})
	@DisplayName("Test return the correct offers for different number of duplicates.")
	void testGetOffers(int numOfOffers) {
		int totCost = 10000;
		int downPayment = 100;
		int term = 5;
		Product baseProduct = new Product("Lease", 0.04);
		Offer[] offers = new Offer[numOfOffers];
		
		for (int i = 0; i < numOfOffers; i++) {
		
				offers[i] = new Offer(baseProduct, -0.02, totCost-downPayment, term);
		}
		
		assertTrue(TestUtils.equals(new ResponseWithOffer(answerSheet, offers).getOffers(), offers), 
				"getOffers() should return the offers");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Dealer", "End_Customer"})
	@DisplayName("Test return the correct offers using the default catalogue through different channels.")
	void testGetOffers(String channel) {
		int totCost = 10000;
		int downPayment = 100;
		int term = 5;
		AnswerSheet answerSheet = new AnswerSheet(new String[]{channel, "New", "Diesel"}, 
				new int[] {totCost, downPayment, term});
		Product[] baseProducts = new Catalogue().getProducts(channel);
		Offer[] offers = new Offer[baseProducts.length];
		
		for (int i = 0; i < baseProducts.length; i++) {
				offers[i] = new Offer(baseProducts[i], -0.02, totCost-downPayment, term);
		}
		
		assertTrue(TestUtils.equals(new ResponseWithOffer(answerSheet, offers).getOffers(), offers), 
				"getOffers() should return the offers");
	}
}
