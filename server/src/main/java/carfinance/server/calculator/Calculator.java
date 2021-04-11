package carfinance.server.calculator;


/**
 * @author jeanniehe
 *
 */
public class Calculator {
	
 
	/**
	 * @param r = Interest rate per period
	 * @param P = Loan after down payment
	 * @param n = Periods of loan
	 * @require n > 0, 
	 * @return A = the annual payment amount with two decimals.
	 */
	public static double getAnnualPayment(double r, int p, int n) {
		
		double a;
		if (r > 0) {
			/*
			BigDecimal R = new BigDecimal(r);
			
			BigDecimal P = new BigDecimal(p);
			BigDecimal Factor = R.multiply(new BigDecimal(1));
			Factor = Factor.pow(n);
			BigDecimal A = P.multiply(R);
			A = A.multiply(Factor);
			A = A.divide(Factor.subtract(new BigDecimal(1)));
			A = A.setScale(2);
			a = A.doubleValue();
			*/
			double factor = Math.pow(1+r, n);
			a = p * r * factor/ (factor -1);
		}
		else a = p/n;
		//double a= PV * r /(1 -  Math.pow(1 + r, -n));
		//double a = P * (1 - Math.pow(1+r, -n))/ (Math.pow(1+r,n)-1);
		
		a = Math.round(a*100.0)/100.0;
		return a;
	}
	 
}