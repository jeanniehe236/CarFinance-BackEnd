/**
 * 
 */
package carfinance.server.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayName;

/**
 * Test Class for the calculator
 */
class CalculatorTest {

	@ParameterizedTest
	@ValueSource(ints = {20, 10, 5, 2, 1})
	@DisplayName("Check annual payment(A) for zero interest for different loan terms.")
    public void testhZeroInterestForDifferentTerms(int term) {
		int loan = 100000;
		assertEquals(loan/term, Calculator.getAnnualPayment(0.0,loan,term), "With zero interest rate, the annual payment should be A = P/n");
    }
	
	@ParameterizedTest
	@ValueSource(ints = {10000, 5000, 1000})
	@DisplayName("Check annual payment(A) for zero interest for different loan amounts.")
    public void testhZeroInterestForDifferentLoanAmounts(int loan) {
		int term = 5;
		assertEquals(loan/term, Calculator.getAnnualPayment(0.0,loan, term), "With zero interest rate, the annual payment should be A = P/n");
    }
	
	@ParameterizedTest
	@ValueSource(doubles = {0.0, 0.01, 0.02, 0.05})
	@DisplayName("Check annual payment(A) for zero loan amount for different interest rate.")
    public void testhZeroInterestForDifferentLoanAmounts(double r) {
		int term = 5;
		int loan = 0;
		assertEquals(loan, Calculator.getAnnualPayment(r, loan, term), "With zero interest rate, the annual payment should be A = P/n");
    }
	
	@ParameterizedTest
	@ValueSource(ints = {1, 5, 10})
	@DisplayName("Check annual payment(A) for zero loan amount for different loan terms.")
    public void testhZeroInterestForDifferentLoanTerms(int term) {
		int loan = 0;
		assertEquals(loan, Calculator.getAnnualPayment(0.02, loan, term), "With zero interest rate, the annual payment should be A = P/n");
    }
	
	@Test
	@DisplayName("Check annual payment for one year payment (n = 1).")
    public void testhOneYearPayment() {
        assertEquals(101.0, Calculator.getAnnualPayment(0.01,100,1), "One year loan should give A = PV * (1 + r) = 100 * (1 + 0.01) = 101");
    }
	
	@Test
	@DisplayName("Check annual payment for two years payment (n = 2)")
	public void testhtwoYearPayment() {
		assertEquals(50.75,Calculator.getAnnualPayment(0.01,100.0, 2), "Two year loan should give A = 50.75");
    }

}
