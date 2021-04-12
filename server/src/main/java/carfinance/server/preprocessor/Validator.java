package carfinance.server.preprocessor;

import carfinance.server.calculator.InterestRateRegulator;
import carfinance.server.offerGenerator.Catalogue;
import carfinance.server.responseGenerator.OptionsCollector;

public class Validator extends OptionsCollector{

	public Validator(Catalogue catalogue, InterestRateRegulator interestRateRegulator) {
		super(catalogue, interestRateRegulator);
	}

	public AnswerSheet getValidatedAnswerSheet(AnswerSheet answers) {
		if (answers == null) return null;
		if (channelIsValid(answers) && carIsValid(answers) && fuelIsValid(answers) && numbersAreValid(answers)) return answers;
		else return null;
	}
	
	private boolean channelIsValid(AnswerSheet answers) {
		return getOptions().get("channels").contains(answers.getChannel());
	}
	
	private boolean carIsValid(AnswerSheet answers) {
		return getOptions().get("carTypes").contains(answers.getCarType());
	}
	
	private boolean fuelIsValid(AnswerSheet answers) {
		return getOptions().get("fuelTypes").contains(answers.getFuelType());
	}
	
	private boolean numbersAreValid(AnswerSheet answers) {
		return answers.getLoan() > 0 && answers.getTerm() > 0 & answers.getDownPayment() >= 0;
	}
}

