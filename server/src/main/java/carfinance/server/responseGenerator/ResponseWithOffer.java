package carfinance.server.responseGenerator;

import carfinance.server.offerGenerator.Offer;
import carfinance.server.preprocessor.AnswerSheet;

public class ResponseWithOffer extends Response{
	private Offer[] offers;
	
	public ResponseWithOffer(AnswerSheet answerSheet, String string) {
		super(answerSheet, string);
	}
	
	public ResponseWithOffer(AnswerSheet answersheet, Offer[] offers) {	
		super(answersheet, generateCongratMessage(offers.length, answersheet));
		this.offers = offers;
	}

	private static String generateCongratMessage(int numOfOffers, AnswerSheet answersheet) {
		String carType = answersheet.getCarType();
		String channel = answersheet.getChannel();
		
		return String.format("Congratulations! You've got %d offer%s "
				+ " The interest rate is calculated for your purchase of %s %s %s-based car as %s %s. The annual payment%s generated "
				+ "based on that interest rate along with your loan term and loan amount after down payment.",
				numOfOffers,
				numOfOffers < 2 ? "!" : "s! The offers are sorted in increasing order by our recommendation.",
				GrammarHelper.getArticle(carType), carType.toLowerCase().replace("_", " "), answersheet.getFuelType().toLowerCase(), 
				GrammarHelper.getArticle(channel), channel.toLowerCase().replace("_", " "), numOfOffers == 1 ? " is":"s are");
	}
	
	public Offer[] getOffers(){
		return this.offers;
	}
	
}
