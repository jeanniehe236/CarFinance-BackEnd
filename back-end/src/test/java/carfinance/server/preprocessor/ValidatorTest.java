package carfinance.server.preprocessor;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import carfinance.server.TestUtils;
import carfinance.server.calculator.InterestRateRegulator;
import carfinance.server.offerGenerator.Catalogue;

class ValidatorTest {

	private Validator validator;
	private String[] stringInputs;
	private int[] integerInputs;
	private String[] headersForStringInputs;
	private String[] headersForIntegerInputs;
	
	public ValidatorTest() {
		validator = new Validator(new Catalogue(), new InterestRateRegulator());
		stringInputs = new String[]{"Dealer", "New", "Diesel"};
		integerInputs = new int[] {1000000, 1000, 10};
		headersForStringInputs = new String[] {"channel", "car type", "fuel type"};	
		headersForIntegerInputs = new String[] {"total cost", "down payment", "term"};	

	}
	
	@Test
	@DisplayName("Check returning null for an undefined answer sheet.") // channel
	void testReturnNullForNullInput() {
		
		assertEquals(null, validator.getValidatedAnswerSheet(null), 
				"The validator should return null for an undefined answer sheet");
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2}) 
	void testReturnNullForInvalidOptions(int index) {
		
		String[] invalidStringInputs = stringInputs.clone();
		
		invalidStringInputs[index] = "Invalid";
		assertEquals(null, validator.getValidatedAnswerSheet(new AnswerSheet(invalidStringInputs, integerInputs)), 
				"The validator should return null for an answer sheet with " + headersForStringInputs[index]);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {0, 1, 2}) 
	void testReturnNullForNegativeNumbers(int index) {
		int[] invalidIntegerInputs = integerInputs.clone();
		invalidIntegerInputs[index] = -1;
		assertEquals(null, validator.getValidatedAnswerSheet(new AnswerSheet(stringInputs, invalidIntegerInputs)), 
				"The validator should return null for an answer sheet with negative " + headersForIntegerInputs[index]);
	}
	
	@Test
	@DisplayName("Test return the original answer sheet if the answer sheet is valid.")
	void testGetValidatedAnswerSheetForValidAnswers() {
		AnswerSheet answerSheet = new AnswerSheet(stringInputs, integerInputs);
		assertTrue(TestUtils.answerSheetsAreEqual(validator.getValidatedAnswerSheet(answerSheet), answerSheet),  "The validator should return a valid answer sheet as it is.");
	}
	
	
	@ParameterizedTest
	@DisplayName("Test Liskov substitution principle fulfillness in terms of returning interest rate options.") 
	@ValueSource(strings = {"carTypes", "fuelTypes"}) // six numbers
	void testGetInterestRateFactorOptions(String key) {
		assertEquals(validator.getOptions().get(key), new InterestRateRegulator().getOptions(key),
				String.format("%s should be the same as the ones monitored by the interest regulator", key));
		
	}
	
	@Test
	@DisplayName("Test Liskov substitution principle fulfillness in terms of returning catalogue options.") 
	void testGetChannelOptions() {
		assertEquals(validator.getOptions().get("channels"), new Catalogue().getChannels(),
				"Channels should be those in the catalogue");
		
	}
}
