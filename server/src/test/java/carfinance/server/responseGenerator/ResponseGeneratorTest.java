package carfinance.server.responseGenerator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import carfinance.server.TestUtils;
import carfinance.server.calculator.InterestRateRegulator;
import carfinance.server.offerGenerator.Catalogue;
import carfinance.server.offerGenerator.OfferGenerator;
import carfinance.server.offerGenerator.Product;
import carfinance.server.preprocessor.Validator;

class ResponseGeneratorTest {
	
	private Map<String, String> validAnswers;
	
	private Validator validator;
	private OfferGenerator offerGenerator;
	
	ResponseGeneratorTest() {
		
		Catalogue catalogue = new Catalogue();
		InterestRateRegulator interestRateRegulator = new InterestRateRegulator();
		
		validator = new Validator(catalogue, interestRateRegulator);
		offerGenerator = new OfferGenerator(catalogue, interestRateRegulator);
		
		validAnswers = Map.of("channel", "Dealer",
				"car-type", "New", "fuel-type", "Diesel",
				"tot-cost", "100000", "down-payment", "1000", "term", "10");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Dealer", "End_Customer"})
	@DisplayName("Test return correct offers for valid input through different channels")
	void testNotReturnNullForValidInputsThroughDifferentChannels(String channel) {
		Map<String, String> newInputs = new HashMap<String, String>();
		newInputs.putAll(validAnswers);
		newInputs.put("channel", channel);
		assertNotEquals(null, ResponseGenerator.generateResponse(validAnswers, validator, offerGenerator),
		String.format("The response generator should not return null for valid inputs through channel: %s.", channel));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"New", "Used"})
	@DisplayName("Test not returning null for valid input with different car types")
	void testNotReturnNullForValidInputsForDifferentCarTypes(String carType) {
		Map<String, String> newInputs = new HashMap<String, String>();
		newInputs.putAll(validAnswers);
		newInputs.put("car-type", carType);
		assertNotEquals(null, ResponseGenerator.generateResponse(validAnswers, validator, offerGenerator),
		String.format("The response generator should not return null for valid inputs with car type: %s.", carType));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Diesel", "Petrol", "Hybrid", "Electronical"})
	@DisplayName("Test not returning null for valid input with different fuel types")
	void testNotReturnNullForValidInputsForDifferentFuelTypes(String fuelType) {
		Map<String, String> newInputs = new HashMap<String, String>();
		newInputs.putAll(validAnswers);
		newInputs.put("fuel-type", fuelType);
		assertNotEquals(null, ResponseGenerator.generateResponse(validAnswers, validator, offerGenerator),
		String.format("The response generator should not return null for valid inputs with fuel type %s.", fuelType));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"channel", "car-type", "fuel-type"})
	@DisplayName("Test return a response warning for invalid input upon receiving invalid options")
	void testWarnForInvalidOptions(String key) {
		Map<String, String> newInputs = new HashMap<String, String>();
		newInputs.putAll(validAnswers);
		newInputs.put(key, "invalid");
		assertTrue(ResponseGenerator.generateResponse(newInputs, validator, offerGenerator).getMessage().contains("invalid"),
		"The message should contain the word invalid for insvalid options");
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"tot-cost", "down-payment", "down-payment"})
	@DisplayName("Test return a response warning for invalid input upon receiving negative number values")
	void testWarnForNegativeNumbers(String key) {
		Map<String, String> newInputs = new HashMap<String, String>();
		newInputs.putAll(validAnswers);
		newInputs.put(key, "-1");
		assertTrue(ResponseGenerator.generateResponse(newInputs, validator, offerGenerator).getMessage().contains("invalid"),
		"The message should contain the word invalid for negative number inputs");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1000, 100})
	@DisplayName("Test return a response warning for invalid input upon receiving a request with <= 0 loan amount.")
	void testWarnForNegativeLoanAmountNumbers(int totCost) {
		Map<String, String> newInputs = new HashMap<String, String>();
		newInputs.putAll(validAnswers);
		newInputs.put("tot-cost", String.valueOf(totCost));
		assertTrue(ResponseGenerator.generateResponse(newInputs, validator, offerGenerator).getMessage().contains("invalid"),
		"The message should contain the word invalid for " + (totCost == 1000 ? "zero loan amount." : "negative loan amount."));
	}
	
	@Test
	@DisplayName("Test return a response warning upon receiving insufficient inputs.")
	void testWarnForInSufficientInputs() {
		assertTrue(ResponseGenerator.generateResponse(Map.of("channel", "Dealer"), validator, offerGenerator).getMessage().contains("invalid"),
		"The message should contain the word invalid for insufficient inputs");
	}
	
	@Test
	@DisplayName("Test return a response stating no offer if there is no offer available.")
	void testWarnForNoOffer() {
		OfferGenerator newOfferGenerator = new OfferGenerator(new Catalogue(Map.of("End_Customer", new Product[0], "Dealer",
		new Product[0])), new InterestRateRegulator());
		assertTrue(ResponseGenerator.generateResponse(validAnswers, validator, newOfferGenerator).getMessage().contains("no offer"),
		"The message should contain the words 'no offer' as there should exist no offer for the user");
	}
	
	@Test
	@DisplayName("Test return a response with no offer if there is no product available.")
	void testReturnResponseWithoutOfferWhenThereAreNoProducts() {
		OfferGenerator newOfferGenerator = new OfferGenerator(new Catalogue(Map.of("End_Customer", new Product[0], "Dealer",
		new Product[0])), new InterestRateRegulator());
		assertThrows(Exception.class, () -> { ((ResponseWithOffer) ResponseGenerator.generateResponse(
				validAnswers, validator, newOfferGenerator)).getOffers();
		});
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Dealer", "End_Customer"})
	@DisplayName("Test return a response with no offer if there is no offer available.")
	void testReturnResponseWithoutOfferWhenThereAreNoProducts(String channel) {
		Map<String, String> answers = Map.of("channel", channel,
				"car-type", "New", "fuel-type", "Diesel",
				"tot-cost", "100000", "down-payment", "1000", "term", "10");
		assertTrue(TestUtils.areBasedOn(
				((ResponseWithOffer) ResponseGenerator.generateResponse(answers, validator, offerGenerator)).getOffers(),
				new Catalogue().getProducts(channel)));
	}

}
