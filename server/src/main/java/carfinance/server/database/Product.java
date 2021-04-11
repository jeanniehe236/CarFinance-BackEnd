package carfinance.server.database;

public class Product {
	
	private String name;
	private double baseInterestRate;
	
	public Product(String name, double baseInterestRate) {
		this.name = name;
		this.baseInterestRate = baseInterestRate;
	}
	
	public String getName() {
		return this.name;
	}
	public double getBaseInterestRate() {
		return this.baseInterestRate;
	}
}
