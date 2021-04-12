package carfinance.server.offerGenerator;
import java.util.Arrays;
import java.util.Map;

import carfinance.server.calculator.InterestRateRegulator;
import carfinance.server.preprocessor.AnswerSheet;

/**
 * The offer generator
 */
public class OfferGenerator {
	
	InterestRateRegulator interestRateRegulator;
	Catalogue catalogue;
	
	/**
	 * A custom constructor to create an offer generator for the given catalogue
	 * and interest rate regulator.
	 * @param catalogue the catalogue holding the available product for all channels.
	 * @param interestRateRegulator: the interest rate adjustment calculator to calculate 
	 * the interest rate adjustment for the requester.
	 */
	public OfferGenerator(Catalogue catalogue, InterestRateRegulator interestRateRegulator) {
		this.interestRateRegulator = interestRateRegulator;
		this.catalogue = catalogue;
	}
	
	/**
	 * Iterates through the available products for the requester to compute and return
	 * a sorted list of offers.
	 * @param answersheet: a parsed version of the users' input
	 * @return a sorted list of offers 
	 * @require the answer sheet cannot be null. 
	 */
	public Offer[] getOffers(AnswerSheet answerSheet){
		Product[] products = catalogue.getProducts(answerSheet.getChannel());
		Offer[] offers = new Offer[products.length];
		
		double interestAdjustment = interestRateRegulator.getInterestRateAdjustment(
				Map.of("carTypes", answerSheet.getCarType(), "fuelTypes", answerSheet.getFuelType()));
		
		for (int i = 0 ; i < products.length; i++) {
			offers[i] = new Offer(products[i], interestAdjustment, answerSheet.getLoan(), answerSheet.getTerm());
		}
		
		Arrays.sort(offers);
		return offers;
	}
	
}
