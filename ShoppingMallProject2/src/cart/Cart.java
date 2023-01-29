package cart;

public class Cart {
	private int number;
	private String id;
	private String itemName;
	private int price;

	public Cart() {
	}

	public Cart(int number, String id, String itemName, int price) {
		super();
		this.number = number;
		this.id = id;
		this.itemName = itemName;
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	public String toString() {
		String print = String.format("[%-6d] [%10s] [%10s] [%10d]", number, id, itemName, price);
		return print;
	}
}
