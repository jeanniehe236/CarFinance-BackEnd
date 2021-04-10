package carfinance.server;

public class Validator {
	
	AnswerSheet answers;
	
	public Validator(AnswerSheet answers) {
		this.answers = answers;
	}
	
	public AnswerSheet getValidatedAnswerSheet() {
		if (channelIsValid() && carIsValid() && fuelIsValid() && numbersAreValid()) return this.answers;
		else return null;
	}
	
	private boolean channelIsValid() {
		return (new Catalogue().getChannels().contains(answers.getChannel()));
	}
	
	private boolean carIsValid() {
		return (new MasterInterestRateRegulator().getCategories("carTypes").contains(answers.getCarType()));
	}
	
	private boolean fuelIsValid() {
		return new MasterInterestRateRegulator().getCategories("fuelTypes").contains(answers.getFuelType());
	}
	
	private boolean numbersAreValid() {
		return answers.getLoan() > 0 && answers.getTerm() > 0 & answers.getDownPayment() > 0 & answers.getTotCost() > 0;
	}
}

