package carfinance.server.preprocessor;

import carfinance.server.calculator.InterestRateRegulator;
import carfinance.server.offerGenerator.Catalogue;
import carfinance.server.responseGenerator.OptionsCollector;

/**
 * A validator validating an answer sheet by checking whether the categorical options exists
 * in the application and whether the number inputs are allowed.
 */
public class Validator extends OptionsCollector{
	
	/**
	 * A constructor inherited from the options collector
	 * @param catalogue: a catelogue mapping channel names to corresponding products.
	 * @param interestRateRegulator: an interest rate adjustment calculator to calculate the interest discount/additional interest
	 * for a certain loan application.
	 */
	public Validator(Catalogue catalogue, InterestRateRegulator interestRateRegulator) {
		super(catalogue, interestRateRegulator);
	}

	/**
	 * Checks if an answer sheet is valid, i.e. contains valid inputs. Return it as it is
	 * if it is true, null otherwise.
	 * @param answers: the answer sheet to be validated
	 * @return the given answer sheet if the answer sheet is valid, null otherwise.
	 */
	public AnswerSheet getValidatedAnswerSheet(AnswerSheet answers) {
		if (answers == null) return null;
		if (channelIsValid(answers) && carIsValid(answers) && fuelIsValid(answers) && numbersAreValid(answers)) return answers;
		else return null;
	}
	
	/**
	 * Validates the channel name stored in the answer sheet.
	 * @param answers: the answer sheet to be validated
	 * @return true if the answer contains a valid channel name, i.e. exists in the catalogue as a key to products.
	 */
	private boolean channelIsValid(AnswerSheet answers) {
		return getOptions().get("channels").contains(answers.getChannel());
	}
	
	/**
	 * Validates the car type stored in the answer sheet.
	 * @param answers: the answer sheet to be validated
	 * @return true if the answer contains a valid car type, i.e. exists in the interest rate calculator as a category key.
	 */
	private boolean carIsValid(AnswerSheet answers) {
		return getOptions().get("carTypes").contains(answers.getCarType());
	}
	
	/**
	 * Validates the fuel type stored in the answer sheet.
	 * @param answers: the answer sheet to be validated
	 * @return true if the answer contains a valid fuel type, i.e. exists in the interest rate calculator as a category key.
	 */
	private boolean fuelIsValid(AnswerSheet answers) {
		return getOptions().get("fuelTypes").contains(answers.getFuelType());
	}
	
	/**
	 * Validates the number inputs stored in the answer sheet.
	 * @param answers: the answer sheet to be validated
	 * @return true if the loan, term is above zero and that the down payment is at least zero.
	 */
	private boolean numbersAreValid(AnswerSheet answers) {
		return answers.getLoan() > 0 && answers.getTerm() > 0 & answers.getDownPayment() >= 0;
	}
}

