package carfinance.server.offerGenerator;

import java.util.Map;
import java.util.Set;


/**
 * A catalogue to store the available products.
 */
public class Catalogue {
	
	private Map<String, Product[]> products; // maps channel names to products
	
	/**
	 * A custom constructor to create a catalogue with the given products
	 * @param products: a map mapping channel names to the products 
	 * collectable through that channel.
	 */
	public Catalogue(Map<String, Product[]> products) {
		this.products = products;
	}
	
	/**
	 * The default constructor with default products.
	 */
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

	/**
	 * Returns the product obtainable through the given channel
	 * @channel: the name of the channel
	 * @return: an array of product obtainable throuh the given channel.
	 */
	public Product[] getProducts(String channel) {
		return products.get(channel).clone();
	}
	
	/**
	 * Returns the name of the existing channels.
	 */
	public Set<String> getChannels() {
		return products.keySet();
	}
	
}
