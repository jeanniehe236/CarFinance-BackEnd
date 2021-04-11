package carfinance.server.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import carfinance.server.database.Catalogue;
import carfinance.server.offerGenerator.OfferGenerator;
import carfinance.server.preprocessor.Validator;
import carfinance.server.responseGenerator.OptionsCollector;

class InterestRateRegulatorTest {
	
	private InterestRateRegulator interestRateRegulator;
	private String car;
	private String fuel;
	
	public InterestRateRegulatorTest() {
		this.interestRateRegulator = new InterestRateRegulator();
		car = "carTypes";
		fuel = "fuelTypes";
	}
	
	@Test
	@DisplayName("Check new car interest adjustment.")
	void testNewCar() {
		assertEquals(0.00, interestRateRegulator.getInterestRateAdjustment("carTypes", "New"), "New car shoud get 0% adjustment");
	}
	
	@Test
	@DisplayName("Check used car interest adjustment.")
	void testUsedCar() {
		assertEquals(0.01, interestRateRegulator.getInterestRateAdjustment("carTypes", "Used"), "Used Car should get 1% extra rate");
	}
	
	@Test
	@DisplayName("Check petrol car interest adjustment.")
	void testPetrol() {
		assertEquals(0.00, interestRateRegulator.getInterestRateAdjustment("fuelTypes", "Petrol"), "Petrol shoud get 0% adjustment");
	}
	
	@Test
	@DisplayName("Check diesel car interest adjustment.")
	void testDiesel() {
		assertEquals(0.00, interestRateRegulator.getInterestRateAdjustment("fuelTypes", "Diesel"), "Diesel should get 0% adjustment");
	}
	
	@Test
	@DisplayName("Check hybrid car interest adjustment.")
	void testHybrid() {
		assertEquals(-0.02, interestRateRegulator.getInterestRateAdjustment("fuelTypes", "Hybrid"), "Hybrid should give -2% adjustment");
	}

}
