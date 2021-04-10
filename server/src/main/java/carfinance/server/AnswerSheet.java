package carfinance.server;

public class AnswerSheet {
	private String channel;
	private String fuelType;
	private String carType;
	private int loan;
	private int term;
	private int totCost;
	private int downPayment;
	
	public AnswerSheet(String[] choices, int[] inputValues) {
		this.channel = choices[0];
		this.carType = choices[1];
		this.fuelType = choices[2];
		
		
		this.totCost = inputValues[0];
		this.downPayment = inputValues[1];
		this.loan = inputValues[0] - inputValues[1];
		this.term = inputValues[1];
	}
	
	public String getChannel() {
		return this.channel;
	}

	public String getFuelType() {
		return fuelType;
	}

	public String getCarType() {
		return carType;
	}

	public int getLoan() {
		return loan;
	}

	public int getTerm() {
		return term;
	}
	
	public int getTotCost() {
		return totCost;
	}
	
	public int getDownPayment() {
		return downPayment;
	}
}
