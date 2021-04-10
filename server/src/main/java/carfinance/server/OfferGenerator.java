package carfinance.server;
import java.util.Arrays;
import java.util.Map;

public class OfferGenerator {
	
	public static Offer[] getOffers(AnswerSheet answersheet){
		Product[] products = new Catalogue().getProducts(answersheet.getChannel());
		Offer[] offers = new Offer[products.length];
		
		double interestAdjustment = new MasterInterestRateRegulator().getInterestRateAdjustment(
				Map.of("carTypes", answersheet.getCarType(), "fuelTypes", answersheet.getFuelType()));
		
		for (int i = 0 ; i < products.length; i++) {
			offers[i] = new Offer(products[i], interestAdjustment, answersheet.getLoan(), answersheet.getTerm());
		}
		
		System.out.println("Got offers. Generating a response.");
		Arrays.sort(offers);
		
		return offers;
	}
}
