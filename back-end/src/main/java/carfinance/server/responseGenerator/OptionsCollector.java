package carfinance.server.responseGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import carfinance.server.calculator.InterestRateRegulator;
import carfinance.server.offerGenerator.Catalogue;

/**
 * An options collector to return a map of categorical options for 
 * the client application.
 */
public class OptionsCollector{
	
	private Map<String, Set<String>> options; // maps category name to category values, i.e. from field names to field values.
	
	/**
	 * A custom constructor to generate a options collector
	 * @param catalogue: a catalogue storing the available channels and products.
	 * @param interestRateRegulator: a interest rate regulator monitoring certain interest rate factors from which required categorical inputs can be obtained.
	 */
	public OptionsCollector(Catalogue catalogue, InterestRateRegulator interestRateRegulator) {
		// Step 1: initialize the 'options' map.
		options = new HashMap<>();
		// Step 2: retrieve the categorical options from the interest rate regulator and store 
		// them in the map.
		for (String key : interestRateRegulator.getCategories()) {
			options.put(key, interestRateRegulator.getOptions(key));
		}
		// Step 3: retrieve the channel names from the catalogue and store them in the map.
		options.put("channels", catalogue.getChannels());
	}
	
	/**
	 * @return the categorical options to be selected by the user.
	 */
	public Map<String, Set<String>> getOptions() {
		return options;
	}
}
