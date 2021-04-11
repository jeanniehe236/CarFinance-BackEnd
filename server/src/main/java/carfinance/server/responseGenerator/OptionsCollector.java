package carfinance.server.responseGenerator;

import java.util.Map;
import java.util.Set;

import carfinance.server.calculator.MasterInterestRateRegulator;
import carfinance.server.database.Catalogue;

public class OptionsCollector{
	
	private Map<String, Set<String>> options;
	
	
	public OptionsCollector() {
		options = new MasterInterestRateRegulator().getCategories();
		options.put("channels", new Catalogue().getChannels());
		
	}
	
	public OptionsCollector(Catalogue catalogue, MasterInterestRateRegulator interestRateRegulator) {
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
