package carfinance.server.offerGenerator;

import java.util.Map;
import java.util.Set;


public class Catalogue {
	
	private Map<String, Product[]> products;
	
	public Catalogue(Map<String, Product[]> products) {
		this.products = products;
	}
	
	public Catalogue() {
		this.products = Map.of("End_Customer", 
				new Product[]{
				new Product("Hire_Purchase", 0.04),
				new Product("Lease", 0.05)
		}, "Dealer",
		new Product[]{
				new Product("Hire_Purchase", 0.03),
				new Product("Lease", 0.04)
		});
	}

	public Product[] getProducts(String channel) {
		return products.get(channel).clone();
	}
	
	public Set<String> getChannels() {
		return products.keySet();
	}
	
}
