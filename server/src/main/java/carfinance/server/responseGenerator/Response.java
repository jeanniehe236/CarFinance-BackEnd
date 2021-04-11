package carfinance.server.responseGenerator;

import carfinance.server.preprocessor.AnswerSheet;

public class Response{
	private AnswerSheet answersheet;
	private String message;
	
	
	public Response(AnswerSheet answersheet, String message) {
		this.answersheet = answersheet;
		this.message = message;
	}
	
	public AnswerSheet getAnswersheet() {
		return this.answersheet;
	}
	
	public String getMessage(){
		return this.message;
	}
}
