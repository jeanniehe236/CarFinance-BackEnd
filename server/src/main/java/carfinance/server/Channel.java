package carfinance.server;

public class Channel {
	private String name;
	private Product[] products;
	
	public Channel(String name, Product[] products) {
		this.name = name;
		this.products = products;
	}
	
	public String getName() {
		return name;
	}
	
	public Product[] getProducts() {
		return products;
	}
	
}
