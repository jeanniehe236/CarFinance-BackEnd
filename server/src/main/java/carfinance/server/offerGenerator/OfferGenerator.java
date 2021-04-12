package carfinance.server.offerGenerator;
import java.util.Arrays;
import java.util.Map;

import carfinance.server.calculator.InterestRateRegulator;
import carfinance.server.preprocessor.AnswerSheet;

public class OfferGenerator {
	InterestRateRegulator interestRateRegulator;
	Catalogue catalogue;
	
	public OfferGenerator(Catalogue catalogue, InterestRateRegulator interestRateRegulator) {
		this.interestRateRegulator = interestRateRegulator;
		this.catalogue = catalogue;
	}
	
	public Offer[] getOffers(AnswerSheet answersheet){
		Product[] products = catalogue.getProducts(answersheet.getChannel());
		Offer[] offers = new Offer[products.length];
		
		double interestAdjustment = interestRateRegulator.getInterestRateAdjustment(
				Map.of("carTypes", answersheet.getCarType(), "fuelTypes", answersheet.getFuelType()));
		
		for (int i = 0 ; i < products.length; i++) {
			offers[i] = new Offer(products[i], interestAdjustment, answersheet.getLoan(), answersheet.getTerm());
		}
		
		Arrays.sort(offers);
		
		return offers;
	}
	
}
