package carfinance.server;

import java.util.Map;

public class CategorialInterestRegulator {
	
	private Map<String, Double> interestRateAdjustments;
	
	public CategorialInterestRegulator(Map<String, Double> interestRateAdjustments) {
		this.interestRateAdjustments = interestRateAdjustments;
	}
	
	public double getInterestRateAdjustment(String carType) {
		return interestRateAdjustments.get(carType);
	}
	
	public String[] getCatogries() {
		return (String[]) interestRateAdjustments.keySet().toArray();
	}
}
