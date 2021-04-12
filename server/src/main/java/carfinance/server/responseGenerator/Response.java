package carfinance.server.responseGenerator;

import carfinance.server.preprocessor.AnswerSheet;

/**
 * A response storing the offers and message generated by the 
 * server application along with the parsed version of the input
 * provided by the user
 */
public class Response{
	
	private AnswerSheet answerSheet; // the parsed version of the user's input
	private String message; // the message generated by the server application
	
	/**
	 * A custom constructor to allow initialization of a response with the given
	 * the parsed version of the user's input (answerSheet) and message.
	 */
	public Response(AnswerSheet answerSheet, String message) {
		this.answerSheet = answerSheet;
		this.message = message;
	}
	
	/**
	 * @return the parsed version of the user's input
	 */
	public AnswerSheet getAnswerSheet() {
		return this.answerSheet;
	}
	
	/**
	 * @return the message generated by the server application
	 */
	public String getMessage(){
		return this.message;
	}
}
