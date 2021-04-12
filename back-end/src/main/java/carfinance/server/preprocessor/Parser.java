package carfinance.server.preprocessor;

import java.util.Map;

/**
 * A parser to parse the user input from the client application
 * to an answer sheet understandable by the server application.
 */
public class Parser {
	
	private String[] nameOfRequiredStringInputs; // the name of the inputs to be stored as strings.
	private String[] nameOfRequiredIntegerInputs; // the name of the inputs to be parsed into integers.
	
	/**
	 * A default constructor to create a parser.
	 */
	public Parser() {
		this.nameOfRequiredStringInputs = new String[] {"channel", "car-type", "fuel-type"};	
		this.nameOfRequiredIntegerInputs = new String[] {"tot-cost", "down-payment", "term"};	
	}
	
	/**
	 * Computes and returns an answer sheet if the user input can be parsed into an answer sheet with
	 * all required inputs. Returns null otherwise.
	 * @param userInputs: a map storing the inputs received through the client application. Maps field names to field values.
	 * @return the resulting answer sheet, provided the input is valid, null otherwise.
	 */
	public AnswerSheet parse(Map<String, String> userInputs) {
		String[] stringInputs = parseStringInputs(userInputs);
		int[] integerInputs = parseIntegerInputs(userInputs);
		if (stringInputs != null && integerInputs != null) return new AnswerSheet(stringInputs, integerInputs);
		else return null;
	}
	
	/**
	 * Parses the required categorical inputs to an array of strings in the same order as 
	 * it appears in nameOfRequiredStringInputs.
	 * @param userInputs:  map storing the inputs received through the client application.
	 * @return an array of user inputs corresponding to the fields in nameOfRequiredStringInputs.
	 */
	private String[] parseStringInputs(Map<String, String> userInputs) {
		String[] res = new String[nameOfRequiredStringInputs.length];
		for (int i = 0; i < nameOfRequiredStringInputs.length; i++) {
			try {
				res[i] = userInputs.get(nameOfRequiredStringInputs[i]);
			} catch (Exception e){
				System.out.println("Invalid input at: " + nameOfRequiredIntegerInputs[i]);
				return null;
 			}
		}
		return res;
	}
	
	/**
	 * Parses the required categorical inputs to an array of integers in the same order as 
	 * it appears in nameOfRequiredIntegerInputs.
	 * @param userInputs:  map storing the inputs received through the client application.
	 * @return an array of user inputs corresponding to the fields in nameOfRequiredIntegerInputs.
	 */
	private int[] parseIntegerInputs(Map<String, String> params) {
		int[] res = new int[nameOfRequiredIntegerInputs.length];
		for (int i = 0; i < nameOfRequiredIntegerInputs.length; i++) {
			try {
				res[i] = Integer.parseInt(params.get(nameOfRequiredIntegerInputs[i]));
 				
			} catch (Exception e){
				System.out.println("Invalid input at: " + nameOfRequiredIntegerInputs[i]);
				return null;
 			}
		}
		return res;
	}
}
