package carfinance.server;

public class Offer extends Product implements Comparable<Offer>{
	
	private double interestRate;
	private double annualPayment;
	
	public Offer(String name, double baseInterestRate) {
		super(name, baseInterestRate);
	}
	

	public Offer(Product product, double interestAdjustment, int presentValue, int term){
		super(product.getName(), product.getBaseInterestRate());
		this.interestRate = product.getBaseInterestRate() + interestAdjustment;
		this.annualPayment = Calculator.getAnnualPayment(this.interestRate, presentValue, term);
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	
	public double getAnnualPayment() {
		return annualPayment;
	}
	

	@Override
	public int compareTo(Offer o) {
		return (int) Math.round(getAnnualPayment() - o.getAnnualPayment());
	}
	
}
