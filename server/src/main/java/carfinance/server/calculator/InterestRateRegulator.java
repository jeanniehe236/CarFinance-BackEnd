package carfinance.server.calculator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * The interest rate adjustment calculator converting a map of interest
 * rat factor options to an interest rate adjustment.
 */
public class InterestRateRegulator {
	
	private Map<String, Map<String, Double>> interestRateAdjustments;
	
	/**
	 * The default constructor creating a map of two default interest rate adjustment categories:
	 * Car type with + 0.01 interest rate for old cars, 0.0 for new cars
	 * Fuel type with - 0.01 interest rate for electronic and hybrid, 0.01 for diesel and petrol.
	 */
	public InterestRateRegulator() {
		this.interestRateAdjustments = Map.of(
			"carTypes", Map.of("Used", 0.01, "New", 0.0),
			"fuelTypes", Map.of("Petrol", 0.0, "Diesel", 0.0, "Hybrid", -0.02, "Electrical", -0.02));
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
	 * Computes and returns the interest rate adjustment for the given user inputs provided
	 * as a map of categorical options.
	 * @param selectedOptions: a map of category options mapping the interest rate factor category to the
	 * 		  categorical option.
	 * @return the corresponding interest rate adjustment.
	 */
	public double getInterestRateAdjustment(Map<String, String> selectedOptions) {
		double res = 0;
		for (Iterator<Entry<String, String>> iterator = selectedOptions.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, String> selectedOption = iterator.next();
			res += interestRateAdjustments.get(selectedOption.getKey()).get(selectedOption.getValue());
		}
		return res;
	}
	
	/**
	 * Returns a set of strings showing the name of the interest rate factor categories.
	 */
	public Set<String> getCategories(){
		return interestRateAdjustments.keySet();
	}
	
	/**
	 * Fetches and returns a set of strings showing the available options within the 
	 * given interest rate factor category.
	 * @param category: the name of the interest rate factor category.
	 * @return the names of the interest rate factor options within the requested category.
	 */
	public Set<String> getOptions(String category) {
		return interestRateAdjustments.get(category).keySet();
	}
	
	
}
