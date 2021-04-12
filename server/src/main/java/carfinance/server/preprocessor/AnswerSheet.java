package carfinance.server.preprocessor;

/**
 * An answer sheet storing the parsed user inputs
 */
public class AnswerSheet {
	
	private String channel; // the channel through which the clients want to purchase a financial product.
	private String fuelType; // the fuel type of the concerning car.
	private String carType; // the car type of the concerning car.
	private int loan; // the loan amount after down payment.
	private int term; // the loan term as the number of years of loan.
	private int totCost; // the total cost of the car.
	private int downPayment; // the user's down payment.
	
	/**
	 * A custom constructor to create an answer sheet using the parsed user inputs.
	 * @param choices: the categorical options selected by the user.
	 * @param inputValues: the integer inputs provided by the user.
	 */
	public AnswerSheet(String[] choices, int[] inputValues) {
		this.channel = choices[0];
		this.carType = choices[1];
		this.fuelType = choices[2];
		
		this.totCost = inputValues[0];
		this.downPayment = inputValues[1];
		this.loan = inputValues[0] - inputValues[1];
		this.term = inputValues[2];
	}
	
	/**
	 * @return the channel selected by the user.
	 */
	public String getChannel() {
		return this.channel;
	}

	/**
	 * @return the car type selected by the user.
	 */
	public String getCarType() {
		return carType;
	}
	
	/**
	 * @return the fuel type selected by the user.
	 */
	public String getFuelType() {
		return fuelType;
	}

	/**
	 * @return the loan amount after down payment requested by the user.
	 */
	public int getLoan() {
		return loan;
	}

	/**
	 * @return the loan term (years of loan).
	 */
	public int getTerm() {
		return term;
	}
	
	/**
	 * @return the total price of the car
	 */
	public int getTotCost() {
		return totCost;
	}
	
	/**
	 * @return the amount of down payment suggested by the user.
	 */
	public int getDownPayment() {
		return downPayment;
	}
}
