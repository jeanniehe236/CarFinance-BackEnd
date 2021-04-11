/**
 * 
 */
package carfinance.server.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

/**
 * @author jeanniehe
 * 
 *
 */
class CalculatorTest {

	@Test
	@DisplayName("Check annual payment(A) for zero interest(r = 0).")
    public void testhZeroInterest() {
		assertEquals(100.0, Calculator.getAnnualPayment(0,100,1), "n = 1 should give A = PV = 100");
        assertEquals(50.0, Calculator.getAnnualPayment(0,100,2), "n = 2, PV = 100 should give A = 50.");
    }
	
	@Test
	@DisplayName("Check annual payment for zero present value(PV) regardless interest rate(r), loan term(n).")
    public void testhZeroPresentValue() {
        assertEquals(0.0, Calculator.getAnnualPayment(0.0,0,1), "PV, r, n = 0 should give zero annual payment");
        assertEquals(0.0, Calculator.getAnnualPayment(0.1,0,1), "PV, n = 0, r != 0 should give zero annual payment");
	}
	
	@Test
	@DisplayName("Check annual payment for one year payment (n = 1).")
    public void testhOneYearPayment() {
        assertEquals(101.0, Calculator.getAnnualPayment(0.01,100,1), "One year loan should give A = PV * (1 + r) = 100 * (1 + 0.01) = 101");
    }
	
	@Test
	@DisplayName("Check annual payment for two years payment (n = 2)")
	public void testhtwoYearPayment() {
        assertEquals(50.75, Calculator.getAnnualPayment(0.01,100,2), "Two year loan should give A = 50.75");
    }

}
