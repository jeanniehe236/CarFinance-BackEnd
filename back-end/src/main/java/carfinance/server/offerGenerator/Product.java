package carfinance.server.offerGenerator;

/**
 * A product with a name and a base interest rate
 */
public class Product {
	
	private String name; // the name of the product
	private double baseInterestRate; // the base interest rate of the product
	
	/**
	 * A constructor for creating a product with the given name and base interest.
	 * @param name: the name of the product
	 * @param baseInterestRate: the base interest rate of the product
	 */
	public Product(String name, double baseInterestRate) {
		this.name = name;
		this.baseInterestRate = baseInterestRate;
	}
	
	/**
	 * @return the name of the product
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the base interest rate of the product.
	 */
	public double getBaseInterestRate() {
		return this.baseInterestRate;
	}
}
