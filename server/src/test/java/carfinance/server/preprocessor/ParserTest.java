package carfinance.server.preprocessor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest {

	Parser parser;
	Map<String, String> validAnswers;
	AnswerSheet answerSheetFromValidAnswers;
	
	ParserTest(){
		parser = new Parser();
		validAnswers = Map.of("channel", "Dealer",
				"car-type", "New", "fuel-type", "Diesel",
				"tot-cost", "10000", "down-payment", "1000", "term", "10");
		answerSheetFromValidAnswers = parser.parse(validAnswers);
	}
	
	@Test
	@DisplayName("Test car-type-parsing for valid answers")
	void testParseCarType() {
		assertEquals(validAnswers.get("car-type"), answerSheetFromValidAnswers.getCarType());
	}
	
	@Test
	@DisplayName("Test fuel-type-parsing for valid answers")
	void testParseFuelType() {
		assertEquals(validAnswers.get("fuel-type"), answerSheetFromValidAnswers.getFuelType());
	}
	
	@Test
	@DisplayName("Test tot-cost-parsing for valid answers")
	void testParseTotCost() {
		assertEquals(getValidIntegerAnswer("tot-cost"), answerSheetFromValidAnswers.getTotCost());
	}
	
	@Test
	@DisplayName("Test down-payment-parsing for valid answers")
	void testParseDownPayment() {
		assertEquals(getValidIntegerAnswer("down-payment"), answerSheetFromValidAnswers.getDownPayment());
	}
	
	@Test
	@DisplayName("Test term-parsing for valid answers")
	void testParseTerm() {
		assertEquals(getValidIntegerAnswer("term"), answerSheetFromValidAnswers.getTerm());
	}
	
	@Test
	@DisplayName("Test getLoan() for valid answers")
	void testParseLoan() {
		assertEquals(getValidIntegerAnswer("tot-cost") - getValidIntegerAnswer("down-payment"), answerSheetFromValidAnswers.getLoan());
	
	}
	
	@Test
	@DisplayName("Test returning null for answers with invalid headers")
	void testReturnNullForInvalidHeaders(){
		Map<String, String> answers = Map.of("Wrong", "0");
		assertEquals(null, parser.parse(answers));
	}
		
	@Test
	@DisplayName("Test accepting answers with more elements than requested")
	void testAcceptingNonNeccessaryAnswers(){
		Map<String, String> invalidAnswers = new HashMap<String, String>();
		invalidAnswers.putAll(validAnswers);
		invalidAnswers.put("loan-type","SMS");
		assertNotEquals(null, parser.parse(invalidAnswers));
	}
	
	@Test
	@DisplayName("Test return null for a non-integer value in a field where integer is required.")
	void testReturnNullForUnacceptableValues(){
		Map<String, String> invalidAnswers = new HashMap<String, String>();
		invalidAnswers.putAll(validAnswers);
		invalidAnswers.put("tot-cost","SMS");
		assertEquals(null, parser.parse(invalidAnswers));
	}
	
	private int getValidIntegerAnswer(String key){
		return Integer.parseInt(validAnswers.get(key));
	}
	
}
