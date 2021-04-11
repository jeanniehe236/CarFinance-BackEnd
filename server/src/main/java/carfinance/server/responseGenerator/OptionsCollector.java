package carfinance.server.responseGenerator;

import java.util.Map;
import java.util.Set;

import carfinance.server.calculator.MasterInterestRateRegulator;
import carfinance.server.database.Catalogue;

public class OptionsCollector{
	
	public static Map<String, Set<String>> getOptions() {
		
		Map<String, Set<String>> res = new MasterInterestRateRegulator().getCategories();
		res.put("channels", new Catalogue().getChannels());
		return res;
	}
}
