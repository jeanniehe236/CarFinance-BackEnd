package carfinance.server;

public class FuelRegulator {
	
	private String[] fuelTypes = new String[]{"Diesel", "Gas", "Electronic", "Hybrid"};
	
	protected static double getInterestRateAdjustment(int fuelType) {
		System.out.println("Getting interest adjustment for fuel type");
		if (fuelType > 1) return -0.02;
		else return 0;
	}
	
	protected String[] getFuelTypes() {
		return fuelTypes.clone();
	}
	
	protected String getFuelType(int i) {
		return fuelTypes[i];
	}
	
	protected int getNumOfFuelTypes() {
		return fuelTypes.length;
	}
}
