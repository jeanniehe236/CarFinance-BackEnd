package carfinance.server.responseGenerator;

import java.util.Map;
import java.util.Set;

import carfinance.server.calculator.InterestRateRegulator;
import carfinance.server.database.Catalogue;

public class OptionsCollector{
	
	private Map<String, Set<String>> options;
	
	public OptionsCollector(Catalogue catalogue, InterestRateRegulator interestRateRegulator) {
		options = interestRateRegulator.getCategories();
		options.put("channels", catalogue.getChannels());
	}
	
	
	public Map<String, Set<String>> getOptions() {
		return options;
	}
	
	public Set<String> getOptions(String Key) {
		return options.get(Key);
	}
}
