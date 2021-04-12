package carfinance.server.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A annual payment calculator. 
 */
public class Calculator {
	
 
	/**
	 * Computes and returns the annual payment for the given
	 * interest rate, loan amount and number of years of loan.
	 * @param r = annual interest rate
	 * @param P = loan after down payment
	 * @param n = years of loan
	 * @require number of years must be above 0, interest rate should not be negative.
	 * @return A = the annual payment amount with two decimals.
	 */
	public static double getAnnualPayment(double r, double p, int n) {	
		
		if (r != 0) {
			
			
			try {
			BigDecimal R = new BigDecimal(r);
			 
			BigDecimal P = new BigDecimal(p);
			BigDecimal Factor = R.add(new BigDecimal(1)).pow(n);
			BigDecimal Numerator = P.multiply(R).multiply(Factor);
			BigDecimal Denominator = Factor.subtract(new BigDecimal(1.00));
			return Numerator.divide(Denominator, 2, RoundingMode.HALF_UP).doubleValue();
			} catch (Exception e) {
				double factor = Math.pow(1+r, n);
				double a = p * r * factor/ (factor -1);
				return a * 100.0/100.0;
			}
		}
		else return Math.round(p / n *100.0)/100.0;
	}
}