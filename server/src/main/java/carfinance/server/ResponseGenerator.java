package carfinance.server;

import java.util.Map;

public class ResponseGenerator {
	
	public static Response generateResponse(Map<String, String> params) {
		AnswerSheet answersheet = new Validator(new Parser().parse(params)).getValidatedAnswerSheet();
		if (answersheet == null) {
			System.out.println("invalid answers");
			return new Response(answersheet);
		}
		Offer[] offers = OfferGenerator.getOffers(answersheet);
		if (offers.length == 0) {
			System.out.println("no offers hm...");
			return new Response(answersheet);
		}
		else return new Response(offers, answersheet);
	}
	
}
	
	
