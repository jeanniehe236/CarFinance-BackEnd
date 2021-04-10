package carfinance.server;

import java.util.Map;
import java.util.Set;

public class ChoiceCollector{
	
	public static Map<String, Set<String>> getChoices() {
		
		Map<String, Set<String>> res = new MasterInterestRateRegulator().getCategories();
		res.put("channels", new Catalogue().getChannels());
		return res;
	}
}
