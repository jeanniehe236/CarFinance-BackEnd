package carfinance.server.responseGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import carfinance.server.calculator.InterestRateRegulator;
import carfinance.server.offerGenerator.Catalogue;

public class OptionsCollector{
	
	private Map<String, Set<String>> options;
	
	public OptionsCollector(Catalogue catalogue, InterestRateRegulator interestRateRegulator) {
		options = new HashMap<>();
		for (String key : interestRateRegulator.getCategories()) {
			options.put(key, interestRateRegulator.getOptions(key));
		}
		options.put("channels", catalogue.getChannels());
	}
	
	public Map<String, Set<String>> getOptions() {
		return options;
	}
}
