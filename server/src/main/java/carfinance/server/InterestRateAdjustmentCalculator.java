package carfinance.server;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InterestRateAdjustmentCalculator{
	
	private String[] adjustmentCategories;
	private List<Map<String, Double>> interestRateAdjustments;
	
	public InterestRateAdjustmentCalculator(String[] interestAdjustmentCategories, List<Map<String, Double>> interestAdjustments) {
		this.adjustmentCategories = interestAdjustmentCategories;
		this.interestRateAdjustments = interestAdjustments;
	}
	
	public InterestRateAdjustmentCalculator() {
		this.adjustmentCategories = new String[]{"carType", "fuelType"};
		Map<String, Double> adjustmentDueToCar = Map.of("Old",  0.01, "New", 0.0);
		Map<String, Double> adjustmentDueToFuel = Map.of("Diesel", 0.0, "Gas", 0.0, "Electronic", -0.02, "Hybrid", -0.02);
		this.interestRateAdjustments = Arrays.asList(adjustmentDueToCar, adjustmentDueToFuel);
	}
	
	public double getInterestRateAdjustment(int categoryType, String category) {
		return interestRateAdjustments.get(categoryType).get(category);
	}
	
	public String[] getChoices(int i) {
		return (String[]) (interestRateAdjustments.get(i).keySet().toArray());
	}
	
	public double getInterestRateAdjustment(String[] categories) {
		double res = 0;
		for (int i = 0; i < adjustmentCategories.length; i++) {
			res += interestRateAdjustments.get(i).get(categories[i]);
		}
		return res;
	}
	
	public int getNumOfAdjustmentCategories(){
		return adjustmentCategories.length;
	}
	
	
	
}
