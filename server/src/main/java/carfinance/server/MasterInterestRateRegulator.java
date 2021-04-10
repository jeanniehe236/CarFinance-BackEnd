package carfinance.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MasterInterestRateRegulator {
	
	private Map<String, Map<String, Double>> interestRateAdjustments;
	
	public MasterInterestRateRegulator(Map<String, Map<String, Double>> interestRateAdjustments) {
		this.interestRateAdjustments = interestRateAdjustments;
	}
	
	public MasterInterestRateRegulator() {
		this.interestRateAdjustments = Map.of(
			"carTypes", Map.of("Old", 0.01, "New", 0.0),
			"fuelTypes", Map.of("Diesel", 0.0, "Gas", 0.0, "Electronic", -0.02, "Hybrid", -0.02));
	}
	
	public double getInterestRateAdjustment(String categoryType, String category) {
		return interestRateAdjustments.get(categoryType).get(category);
	}
	
	public Set<String> getCategories(String categoryType) {
		return interestRateAdjustments.get(categoryType).keySet();
	}
	
	public double getInterestRateAdjustment(Map<String, String> categories) {
		double res = 0;
		for (Iterator<Entry<String, String>> iterator = categories.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> entry = iterator.next();
			res += interestRateAdjustments.get(entry.getKey()).get(entry.getValue());
		}
		return res;
	}
	
	public Map<String, Set<String>> getCategories(){
		Map<String, Set<String>> categories = new HashMap<>();
		
		for (Iterator<Entry<String, Map<String, Double>>> iterator = interestRateAdjustments.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Map<String, Double>> entry = iterator.next();
			categories.put((String) entry.getKey(), getCategories((String) entry.getKey()));
		}
		return categories;
		
	}
	
}
