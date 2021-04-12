package carfinance.server.responseGenerator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import carfinance.server.TestUtils;
import carfinance.server.preprocessor.AnswerSheet;

class ResponseTest {
	
	@ParameterizedTest
	@ValueSource(strings = {"Dealer", "End_Customer"})
	@DisplayName("Test return the correct answer sheet for different channels.")
	void testGetAnswerSheetWithDifferentChannels(String channel) {
		AnswerSheet answerSheet = new AnswerSheet(new String[]{channel, "New", "Diesel"}, 
				new int[] {1000000, 1000, 10});
		assertTrue(TestUtils.answerSheetsAreEqual(new Response(answerSheet, "").getAnswerSheet(), answerSheet), 
				String.format("getAnswerSheet() should return the answer sheet passed into the response with channel = %s", channel));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"New", "Used"})
	@DisplayName("Test return the correct answer sheet for different car types.")
	void testGetAnswerSheetWithDifferentCarTypes(String carType) {
		AnswerSheet answerSheet = new AnswerSheet(new String[]{"Dealer", carType, "Diesel"}, 
				new int[] {1000000, 1000, 10});
		assertTrue(TestUtils.answerSheetsAreEqual(new Response(answerSheet, "").getAnswerSheet(), answerSheet), 
				String.format("getAnswerSheet() should return the answer sheet passed into the response with car type = %s", carType));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Diesel", "Petrol", "Electronical", "Hybrid"})
	@DisplayName("Test return the correct answer sheet for different fuel types.")
	void testGetAnswerSheetWithDifferentFuelTypes(String fuelType) {
		AnswerSheet answerSheet = new AnswerSheet(new String[]{"Dealer", "Used", fuelType}, 
				new int[] {1000000, 1000, 10});
		assertTrue(TestUtils.answerSheetsAreEqual(new Response(answerSheet, "").getAnswerSheet(), answerSheet), 
				String.format("getAnswerSheet() should return the answer sheet passed into the response with fuel type = %s", fuelType));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {100000, 10000, 5000})
	@DisplayName("Test return the correct answer sheet for different total costs.")
	void testGetAnswerSheetWithDifferentTotCosts(int totCost) {
		AnswerSheet answerSheet = new AnswerSheet(new String[]{"Dealer", "Used", "Hybrid"}, 
				new int[] {totCost, 1000, 10});
		assertTrue(TestUtils.answerSheetsAreEqual(new Response(answerSheet, "").getAnswerSheet(), answerSheet), 
				String.format("getAnswerSheet() should return the answer sheet passed into the response with total cost = %s", String.valueOf(totCost)));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1000, 500, 100})
	@DisplayName("Test return the correct answer sheet for different down payments.")
	void testGetAnswerSheetWithDifferentDownPayments(int downPayment) {
		AnswerSheet answerSheet = new AnswerSheet(new String[]{"Dealer", "Used", "Hybrid"}, 
				new int[] {10000, downPayment, 10});
		assertTrue(TestUtils.answerSheetsAreEqual(new Response(answerSheet, "").getAnswerSheet(), answerSheet), 
				String.format("getAnswerSheet() should return the answer sheet passed into the response with down payment = %s", String.valueOf(downPayment)));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {10, 5, 1})
	@DisplayName("Test return correct answer sheet for differnt terms.")
	void testGetAnswerSheetWithDifferentTerms(int term) {
		AnswerSheet answerSheet = new AnswerSheet(new String[]{"Dealer", "Used", "Hybrid"}, 
				new int[] {100000, 1000, term});
		assertTrue(TestUtils.answerSheetsAreEqual(new Response(answerSheet, "").getAnswerSheet(), answerSheet), 
				String.format("getAnswerSheet() should return the answer sheet passed into the response with term = %s", String.valueOf(term)));
	}
	
	@Test
	@DisplayName("Test return the message used to produce the response.")
	void testGetMessage() {
		AnswerSheet answerSheet = new AnswerSheet(new String[]{"Dealer", "New", "Diesel"}, 
				new int[] {1000000, 1000, 10});
		String message =  "A response has been generated";
		assertEquals(message, new Response(answerSheet, message).getMessage(), 
				String.format("getAnswerSheet() should return %s.", message));
	}

}
