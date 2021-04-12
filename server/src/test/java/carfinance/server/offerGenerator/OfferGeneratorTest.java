package carfinance.server.offerGenerator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import carfinance.server.preprocessor.AnswerSheet;
import carfinance.server.calculator.InterestRateRegulator;
import carfinance.server.TestUtils;
class OfferGeneratorTest {

	AnswerSheet dealerAnswerSheet;
	AnswerSheet endCustomerAnswerSheet;
	OfferGenerator offerGenerator;
	OfferGeneratorTest(){
		offerGenerator = new OfferGenerator(new Catalogue(), new InterestRateRegulator());
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"End_Customer", "Dealer"})
	@DisplayName("Test the number of offers generated.")
	void testNumOfOffersForDealer(String channel) {
		AnswerSheet answerSheet = new AnswerSheet(
				new String[]{"Dealer", "New", "Diesel"}, new int[] {10000, 100, 900, 10});
		assertEquals(new Catalogue().getProducts(channel).length,
				offerGenerator.getOffers(answerSheet).length,
				String.format("The offers generated for %ss should be as many as the number of available products for them.", channel.toLowerCase()));
		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"End_Customer", "Dealer"})
	@DisplayName("Test if the offers are generated based on the available product in the corresponding channel.")
	void testOffersGeneratedForADealer(String channel) {
		AnswerSheet answerSheet = new AnswerSheet(
				new String[]{channel, "New", "Diesel"}, new int[] {10000, 100, 900, 10});
		assertTrue(TestUtils.areBasedOn( 
				offerGenerator.getOffers(answerSheet),
				new Catalogue().getProducts(channel)),
				"The offer generator should generate offers based on the available products for the corresponding client.");
		
	}
	
	@ParameterizedTest
	@ValueSource(ints = {10000, 5000, 1000}) // total price
	@DisplayName("Test if the offers are sorted by annual payment for different loan amounts.")
	void testSortedOffersForDifferentLoanAmounts(int totalCost) {
		AnswerSheet answerSheet = new AnswerSheet(
				new String[]{"Dealer", "New", "Diesel"}, new int[] {totalCost, 100, totalCost-100, 10});
		
		OfferGenerator offerGenerator = new OfferGenerator(
				new Catalogue(
				Map.of("Dealer", new Product[]{new Product("SMS Loan", 0.05),
						new Product("Hire_Purchase", 0.03),
						new Product("Lease", 0.04)
				})), new InterestRateRegulator());

		assertEquals("Hire_Purchase", offerGenerator.getOffers(answerSheet)[0].getName(),
				"The first offer should be a hire purchase.");
		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Dealer", "End_Customer"}) // total term
	@DisplayName("Test if the offers are sorted by annual payment for different customers.")
	void testSortedOffersForDifferentCustomers(String channel) {
		AnswerSheet answerSheet = new AnswerSheet(
				new String[]{channel, "New", "Diesel"}, new int[] {10000, 100, 900, 10});
		
		OfferGenerator offerGenerator = new OfferGenerator(
				new Catalogue(
				Map.of("Dealer", new Product[]{new Product("SMS Loan", 0.05),
						new Product("Hire_Purchase", 0.03),
						new Product("Lease", 0.04)
				},"End_Customer", new Product[]{new Product("SMS Loan", 0.06),
						new Product("Hire_Purchase", 0.04),
						new Product("Lease", 0.05)
				})), new InterestRateRegulator());

		assertEquals("Hire_Purchase", offerGenerator.getOffers(answerSheet)[0].getName(),
				"The first offer should be a hire purchase.");
		
	}
}
