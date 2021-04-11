package carfinance.server.responseGenerator;

import java.util.Map;

import carfinance.server.offerGenerator.Offer;
import carfinance.server.offerGenerator.OfferGenerator;
import carfinance.server.preprocessor.AnswerSheet;
import carfinance.server.preprocessor.Parser;
import carfinance.server.preprocessor.Validator;

public class ResponseGenerator {
	
	public static Response generateResponse(Map<String, String> params, Validator validator, OfferGenerator offerGenerator) {
		
		AnswerSheet answersheet = validator.getValidatedAnswerSheet(new Parser().parse(params));
		if (answersheet == null) {
			return new Response(answersheet, "The answers you have provided seems invalid. Please note that only integers and the given choices are allowed and that the loan after down payment must be above 0 kr.");
		} else {
			Offer[] offers = offerGenerator.getOffers(answersheet);
			if (offers.length == 0) {
				return new Response(answersheet, "Sorry, but there is no offer available for you. Please contact the bank.");
			}
			else return new ResponseWithOffer(answersheet, offers);
		}
	}
	
}
	
	
