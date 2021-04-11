package carfinance.server.preprocessor;
import java.util.Map;
import java.util.Set;

import carfinance.server.calculator.MasterInterestRateRegulator;
import carfinance.server.database.Catalogue;
import carfinance.server.responseGenerator.OptionsCollector;

public class Validator extends OptionsCollector{
	
	private Map<String, Set<String>> options;
	
	public Validator() {
		super();
	}
	
	public Validator(Catalogue catalogue, MasterInterestRateRegulator interestRateRegulator) {
		options = interestRateRegulator.getCategories();
		options.put("channels", catalogue.getChannels());
	}

	public AnswerSheet getValidatedAnswerSheet(AnswerSheet answers) {
		if (channelIsValid(answers) && carIsValid(answers) && fuelIsValid(answers) && numbersAreValid(answers)) return answers;
		else return null;
	}
	
	private boolean channelIsValid(AnswerSheet answers) {
		return getOptions("channels").contains(answers.getChannel());
	}
	
	private boolean carIsValid(AnswerSheet answers) {
		return getOptions("carTypes").contains(answers.getCarType());
	}
	
	private boolean fuelIsValid(AnswerSheet answers) {
		return getOptions("fuelTypes").contains(answers.getFuelType());
	}
	
	private boolean numbersAreValid(AnswerSheet answers) {
		return answers.getLoan() > 0 && answers.getTerm() > 0 & answers.getDownPayment() >= 0;
	}
}

