package carfinance.server.offerGenerator;

import carfinance.server.calculator.Calculator;

/**
 * An offer to store a product along with the computed interest rate and
 * annual payment for a certain customer in a certain context. Can be
 * sorted by annual payment.
 */
public class Offer extends Product implements Comparable<Offer>{
	
	private double interestRate;
	private double annualPayment;
	
	/**
	 * A constructor same as an product.
	 */
	public Offer(String name, double baseInterestRate) {
		super(name, baseInterestRate);
	}
	
	/**
	 * An extended constructor to produce an offer based on a given product, interest
	 * rate adjustment, loan amount and loan term.
	 * @param product: the product upon which the offer is to be based
	 * @param interestAdjustment: the interest adjustment for the client receiving this offer.
	 * @param loan: the loan amount after down payment
	 * @param term: the number of years of loan.
	 */
	public Offer(Product product, double interestAdjustment, int loan, int term){
		super(product.getName(), product.getBaseInterestRate());
		this.interestRate = product.getBaseInterestRate() + interestAdjustment;
		this.annualPayment = Calculator.getAnnualPayment(this.interestRate, loan, term);
	}
	
	/**
	 * @return the final interest rate
	 */
	public double getInterestRate() {
		return interestRate;
	}
	
	/**
	 * @return the annual payment for taking this offer.
	 */
	public double getAnnualPayment() {
		return annualPayment;
	}
	
	/**
	 * Allows comparing the offers by annual payment. Returns an integer corresponding
	 * 100 x the difference between the current annual payment and the reference object's
	 * annual payment. The multiplication by 100 is used to ensure precision at 2 decimals.
	 * 
	 */
	@Override
	public int compareTo(Offer o) {
		return (int) Math.round((getAnnualPayment() - o.getAnnualPayment())*100);
	}
	
}
