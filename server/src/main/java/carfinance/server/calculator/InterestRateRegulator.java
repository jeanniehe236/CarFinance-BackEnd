package carfinance.server.calculator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author jeanniehe
 *
 */
public class InterestRateRegulator {
	
	private Map<String, Map<String, Double>> interestRateAdjustments;
	
	/**
	 * The default constructor creating a map of two default interest rate adjustment categories:
	 * Car type with + 0.01 interest rate for old cars, 0.0 for new cars
	 * Fuel type with - 0.01 interest rate for electronic and hybrid, 0.01 for diesel and gas.
	 */
	public InterestRateRegulator() {
		this.interestRateAdjustments = Map.of(
			"carTypes", Map.of("Used", 0.01, "New", 0.0),
			"fuelTypes", Map.of("Petrol", 0.0, "Diesel", 0.0, "Electrical", -0.02, "Hybrid", -0.02));
	}
	
	/**
	 * A constructor creating a interest rate regulator given a map with interest rate adjustment 
	 * categories.
	 * @param interestRateAdjustments: maps interest rate adjustment categories along with the
	 * associated category values to an interest rate adjustment.
	 */
	public InterestRateRegulator(Map<String, Map<String, Double>> interestRateAdjustments) {
		
		this.interestRateAdjustments = interestRateAdjustments;
	}
	
	/**
	 * 
	 * @param categoryType
	 * @param category
	 * @return
	 */
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
		for (String key : interestRateAdjustments.keySet()) {
			categories.put(key, getCategories(key));
		}
		return categories;
	}
	
}
