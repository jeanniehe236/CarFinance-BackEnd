package carfinance.server.preprocessor;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AnswerSheetTest {

	int totCost;
	int loan;
	int downPayment;
	int term;
	String carType;
	String fuelType;
	String channel;
	
	AnswerSheetTest(){
		totCost = 100000;
		downPayment = 1000;
		term = 5;
		carType = "New";
		fuelType = "Diesel";
		channel = "End_Customer";
	}

	@ParameterizedTest
	@ValueSource(strings = {"Dealer", "End_Customer"})
	@DisplayName("Test getChannel()")
	void testGetChannel(String currentChannel) {
		AnswerSheet answerSheet = new AnswerSheet(
				new String[]{currentChannel, carType, fuelType}, 
				new int[] {totCost, downPayment, term});
		assertEquals(currentChannel, answerSheet.getChannel(), String.format("The channel should be %s.", currentChannel));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"New", "Used"})
	void testGetCarType(String carType) {
		AnswerSheet answerSheet = new AnswerSheet(
				new String[]{channel, carType, fuelType}, 
				new int[] {totCost, downPayment, term});
		assertEquals(carType, answerSheet.getCarType(), String.format("The channel should be %s.", carType));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Hybrid", "Diesel"})
	void testGetFuelType(String fuelType) {
		AnswerSheet answerSheet = new AnswerSheet(
				new String[]{channel, carType, fuelType}, 
				new int[] {totCost, downPayment, term});
		assertEquals(fuelType, answerSheet.getFuelType(), String.format("The channel should be %s.", fuelType));
	}

	@ParameterizedTest
	@ValueSource(ints = {100000, 10000, 500})
	void testGetTotCost(int totCost) {
		AnswerSheet answerSheet = new AnswerSheet(
				new String[]{channel, carType, fuelType}, 
				new int[] {totCost, downPayment, term});
		assertEquals(totCost, answerSheet.getTotCost(), String.format("The total cost should be %s.",String.valueOf(totCost)));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1000, 100, 500})
	void testGetDownPayment(int downPayment) {
		AnswerSheet answerSheet = new AnswerSheet(
				new String[]{channel, carType, fuelType}, 
				new int[] {totCost, downPayment, term});
		assertEquals(downPayment, answerSheet.getDownPayment(), String.format("The total cost should be %s.",String.valueOf(downPayment)));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {5000, 10000, 2000})
	void testGetLoanAmount(int loan) {
		AnswerSheet answerSheet = new AnswerSheet(
				new String[]{channel, carType, fuelType}, 
				new int[] {totCost, totCost-loan, term});
		assertEquals(loan, answerSheet.getLoan(), String.format("The total cost should be %s.",String.valueOf(loan)));
	}
	
	@ParameterizedTest
	@ValueSource(ints = {5, 10, 20})
	void testGetTerm(int term) {
		AnswerSheet answerSheet = new AnswerSheet(
				new String[]{channel, carType, fuelType}, 
				new int[] {totCost, downPayment, term});
		assertEquals(term, answerSheet.getTerm(), String.format("The total cost should be %s.",String.valueOf(term)));
	}
}
