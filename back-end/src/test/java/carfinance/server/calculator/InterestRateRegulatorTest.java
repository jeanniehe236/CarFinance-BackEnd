package carfinance.server.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InterestRateRegulatorTest {
	
	private InterestRateRegulator interestRateRegulator;
				
	public InterestRateRegulatorTest() {
		this.interestRateRegulator = 
				new InterestRateRegulator(Map.of("carTypes", 
						Map.of("Used", 0.01, "New", 0.0), "fuelTypes", 
						Map.of("Petrol", 0.0, "Diesel", 0.0, "Hybrid", -0.02, "Electrical", -0.02)));
	}
	
	
	@Test
	@DisplayName("Check new petrol car interest adjustment.")
	void testPetrolNew() {
		assertEquals(0.0, interestRateRegulator.getInterestRateAdjustment(
				Map.of("fuelTypes", "Petrol", "carTypes", "New")),
				"A new petrol car should give 0% adjustment");
	}
	
	@Test
	@DisplayName("Check used petrol car interest adjustment.")
	void testPetrolUsed() {
		assertEquals(0.01, interestRateRegulator.getInterestRateAdjustment(
				Map.of("fuelTypes", "Petrol", "carTypes", "Used")),
				"An used petrol car should give 1% extra interest rate");
	}
	
	@Test
	@DisplayName("Check new electrical car interest adjustment.")
	void testDieselNew() {
		assertEquals(0.00, interestRateRegulator.getInterestRateAdjustment(
				Map.of("fuelTypes", "Diesel", "carTypes", "New")
				), "A new diesel should give 0% adjustment");
	}
	
	@Test
	@DisplayName("Check used electrical car interest adjustment.")
	void testDieselUsed() {
		assertEquals(0.01, interestRateRegulator.getInterestRateAdjustment(
				Map.of("fuelTypes", "Diesel", "carTypes", "Used")
				), "An used diesel car should get 1% extra interest rate");
	}
	
	@Test
	@DisplayName("Check used hybrid car interest adjustment.")
	void testEletricalNew() {
		assertEquals(-0.02, interestRateRegulator.getInterestRateAdjustment(
				Map.of("fuelTypes", "Electrical", "carTypes", "New")
				), "A new eletrical car should give -2% discount");
	}
	
	@Test
	@DisplayName("Check used hybrid car interest adjustment.")
	void testEletricalUsed() {
		assertEquals(-0.01, interestRateRegulator.getInterestRateAdjustment(
				Map.of("fuelTypes", "Electrical", "carTypes", "Used")
				), "An used electrical car should give -1% discount");
	}
	
	@Test
	@DisplayName("Check new hybrid car interest adjustment.")
	void testHybridNew() {
		assertEquals(-0.02, interestRateRegulator.getInterestRateAdjustment(
				Map.of("fuelTypes", "Hybrid", "carTypes", "New")
				), "A new hybrid car should give -2% adjustment");
	}
	
	@Test
	@DisplayName("Check used hybrid car interest adjustment.")
	void testHybridUsed() {
		assertEquals(-0.01, interestRateRegulator.getInterestRateAdjustment(
				Map.of("fuelTypes", "Hybrid", "carTypes", "Used")
				), "Hybrid should give -1% adjustment");
	}
	
	@Test
	@DisplayName("Test getting interest rate factor categories.")
	void testGetCategories() {
		assertEquals(
				Set.of("carTypes", "fuelTypes"), 
				interestRateRegulator.getCategories(), "getCategories() should return all interest-rate-affecting carTypes as a set.");
	}
	
	@Test
	@DisplayName("Test getting fuel options")
	void testGetFuelOptions() {
		assertEquals(
				Set.of("Petrol", "Diesel", "Hybrid", "Electrical"), 
				interestRateRegulator.getOptions("fuelTypes"));
	}
	
	@Test
	@DisplayName("Test getting car options")
	void testGetCarOptions() {
		assertEquals(
				Set.of("New", "Used"), 
				interestRateRegulator.getOptions("carTypes"));
	}
	
	@Test
	@DisplayName("Check default constructor in terms of categories.")
	void testDefaultConstructorInTermsOfCategories() {
		assertEquals(interestRateRegulator.getCategories(), new InterestRateRegulator().getCategories());
	}
	
	@Test
	@DisplayName("Check default constructor in terms of car options.")
	void testDefaultConstructorInTermsOfCarOptions() {
		assertEquals(interestRateRegulator.getOptions("carTypes"), new InterestRateRegulator().getOptions("carTypes"));
	}
	
	@Test
	@DisplayName("Check default constructor in terms of fuel options.")
	void testDefaultConstructorInTermsOfFuelOptions() {
		assertEquals(interestRateRegulator.getOptions("fuelTypes"), new InterestRateRegulator().getOptions("fuelTypes"));
	}

}
