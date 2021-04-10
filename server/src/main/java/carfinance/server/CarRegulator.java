package carfinance.server;

import java.util.Map;

public class CarRegulator {
	
	private Map<String, Double> interestRateAdjustments;
	
	public CarRegulator() {
		this.interestRateAdjustments = Map.of("New", 0.0, "Old", 0.01);
	}
	
	public CarRegulator(Map<String, Double> interestRateAdjustments) {
		this.interestRateAdjustments = interestRateAdjustments;
	}
	
	public double getInterestRateAdjustment(String carType) {
		return interestRateAdjustments.get(carType);
	}
	
	public String[] getCarTypes() {
		return (String[]) interestRateAdjustments.keySet().toArray();
	}
}
