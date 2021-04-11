package carfinance.server;

import java.util.Map;

public class ResponseGenerator {
	
	public static Response generateResponse(Map<String, String> params) {
		AnswerSheet answersheet = new Validator(new Parser().parse(params)).getValidatedAnswerSheet();
		if (answersheet == null) {
			System.out.println("invalid answers");
			return new Response(answersheet, "The answers you have provided seems invalid. Please note that only integers and the given choices are allowed.");
		}
		Offer[] offers = OfferGenerator.getOffers(answersheet);
		if (offers.length == 0) {
			System.out.println("no offers hm...");
			return new Response(answersheet, "Sorry, but there is no offer available for you");
		}
		else return new ResponseWithOffer(answersheet, offers);
	}
	
}
	
	
