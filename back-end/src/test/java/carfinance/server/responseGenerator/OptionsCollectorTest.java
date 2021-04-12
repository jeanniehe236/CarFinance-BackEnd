package carfinance.server.responseGenerator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import carfinance.server.calculator.InterestRateRegulator;
import carfinance.server.offerGenerator.Catalogue;

import org.junit.jupiter.params.ParameterizedTest;

class OptionsCollectorTest {

	private Catalogue catalogue;
	private InterestRateRegulator interestRegulator;
	private OptionsCollector optionsCollector;
	
	public OptionsCollectorTest() {
		catalogue = new Catalogue();
		interestRegulator = new InterestRateRegulator();
		optionsCollector = new OptionsCollector(new Catalogue(), new InterestRateRegulator());
		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"carTypes", "fuelTypes"})
	void testGetInterestRateFactorOptions(String key) {
		assertEquals(optionsCollector.getOptions().get(key), interestRegulator.getOptions(key),
				String.format("%s should be the same as the ones monitored by the interest regulator", key));
		
	}
	
	@Test
	@DisplayName("Test channel collection")
	void testGetChannelOptions() {
		assertEquals(optionsCollector.getOptions().get("channels"), catalogue.getChannels(),
				"Channels should be those in the catalogue");
		
	}

}
