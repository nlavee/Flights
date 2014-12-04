

public class Price {
	double price;

	public Price(double price) {
		this.price = price;
	}

	double getPrice() {
		return price;
	}

	void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "" + price ;
	}
	
	
}
