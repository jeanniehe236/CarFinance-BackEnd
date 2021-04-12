package carfinance.server;

import java.util.List;

import org.assertj.core.util.Arrays;

import carfinance.server.offerGenerator.Offer;
import carfinance.server.offerGenerator.Product;
import carfinance.server.preprocessor.AnswerSheet;

public class TestUtils {
	
	public static boolean areBasedOn(Offer[] offers, Product[] products) {
		List<Object> toVisit = Arrays.asList(products);
		
		if (products.length != offers.length) return false;
		for (int i = 0; i < offers.length; i++) {
			int index = findBaseProduct(offers[i], toVisit);
			if (index < 0) return false;
			toVisit.remove(index);
		}
		return true;
	}
	
	public static int findBaseProduct(Offer offer, List<Object> products) {
		for (int i = 0; i < products.size(); i++) {
			if (TestUtils.equals(offer, (Product) products.get(i))) return i;
		}
		return -1;
	}
	
	public static boolean equals(Product[] a, Product[] b) {
		if (a.length != b.length) return false;
		for (int i = 0; i < a.length; i++) {
			if (!equals(a[i],b[i])) return false;
		}
		return true;
	}
	
	public static boolean equals(Product a, Product b) {
		return a.getName() == b.getName() && a.getBaseInterestRate() == b.getBaseInterestRate();
	}
	
	public static boolean answerSheetsAreEqual(AnswerSheet a, AnswerSheet b) {
		if (a == null || b == null) return false;
		if (a.getCarType() != b.getCarType()) return false;
		if (a.getChannel() != b.getChannel()) return false;
		if (a.getFuelType() != b.getFuelType()) return false;
		if (a.getLoan() != b.getLoan()) return false;
		if (a.getTerm() != b.getTerm()) return false;
		if (a.getTotCost() != b.getTotCost()) return false;
		if (a.getDownPayment() != b.getDownPayment()) return false;
		return true;
	}
	
}
