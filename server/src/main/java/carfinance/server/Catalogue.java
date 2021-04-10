package carfinance.server;

import java.util.Map;
import java.util.Set;


public class Catalogue {
	
	private Map<String, Product[]> products;
	
	public Catalogue(Map<String, Product[]> products) {
		this.products = products;
	}
	public Catalogue() {
		this.products = Map.of("Private_Consumer", 
				new Product[]{
				new Product("Hire_Purchase", 0.05),
				new Product("Lease", 0.06)
		}, "Dealer",
		new Product[]{
				new Product("Hire_Purchase", 0.04),
				new Product("Lease", 0.05)
		});
	}

	
	public Product[] getProducts(String channel) {
		return products.get(channel);
	}
	
	public Set<String> getChannels() {
		return products.keySet();
	}
	
}
