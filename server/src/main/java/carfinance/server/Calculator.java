package carfinance.server;


public class Calculator {
	
	public static double getAnnualPayment(double interestRate, int presentValue, int term) {
		return (presentValue)* interestRate/(1 + Math.pow((1 + interestRate), - term));
	
	}
	 
}