package carfinance.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import carfinance.server.TestUtils;
import carfinance.server.calculator.InterestRateRegulator;
import carfinance.server.offerGenerator.Catalogue;
import carfinance.server.offerGenerator.OfferGenerator;
import carfinance.server.preprocessor.Validator;
import carfinance.server.responseGenerator.OptionsCollector;
import carfinance.server.responseGenerator.ResponseGenerator;

class CalculatorRestControllerTest {
	private OptionsCollector optionsCollector;
	private Validator validator;
	private OfferGenerator offerGenerator;
	CalculatorRestController restController;
	
	public CalculatorRestControllerTest() {
		Catalogue catalogue = new Catalogue();
		InterestRateRegulator interestRateRegulator = new InterestRateRegulator();
		validator = new Validator(catalogue, interestRateRegulator);
		
		optionsCollector = new OptionsCollector(catalogue, interestRateRegulator);
		offerGenerator = new OfferGenerator(catalogue, interestRateRegulator);
		restController = new CalculatorRestController();
	}
	
	@Test
	void testGetOptionsInTermsOfCatagories() {
		assertTrue(optionsCollector.getOptions().keySet().equals(restController.getOptions().keySet()));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"car-types", "channels", "fuel-types"})
	void testGetOptionsForEachOptionCategories(String key) {
		assertEquals(optionsCollector.getOptions().get(key), restController.getOptions().get(key));
	}
	
	@Test
	@DisplayName("Test getResponse()")
	void testNotReturnNullFromGetResponse(){
		Map<String,String> params = Map.of("channel", "Dealer",
				"car-type", "New", "fuel-type", "Diesel",
				"tot-cost", "100000", "down-payment", "1000", "term", "10");
		
		assertNotEquals(null, restController.getResponse(params));
	}
	
	@Test
	@DisplayName("Test getResponse() in terms of answer sheet")
	void testGetResponseAnswerSheet(){
		Map<String,String> params = Map.of("channel", "Dealer",
				"car-type", "New", "fuel-type", "Diesel",
				"tot-cost", "100000", "down-payment", "1000", "term", "10");
		
		assertTrue(TestUtils.answerSheetsAreEqual(ResponseGenerator.generateResponse(params, validator, offerGenerator).getAnswerSheet(), 
				restController.getResponse(params).getAnswerSheet()));
	}
	
	@Test
	@DisplayName("Test getResponse() in terms of message")
	void testGetResponseMessage(){
		Map<String,String> params = Map.of("channel", "Dealer",
				"car-type", "New", "fuel-type", "Diesel",
				"tot-cost", "100000", "down-payment", "1000", "term", "10");
		
		assertEquals(ResponseGenerator.generateResponse(params, validator, offerGenerator).getMessage(), 
				restController.getResponse(params).getMessage());
	}
	
}
