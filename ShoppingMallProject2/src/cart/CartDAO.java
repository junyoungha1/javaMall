package cart;

import java.util.ArrayList;

public class CartDAO {
	private CartDAO() {

	}

	private static CartDAO instance = new CartDAO();

	static public CartDAO getInstance() {
		return instance;
	}

	private ArrayList<Cart> cartList;
	private int num;

	public void init() {
		cartList = new ArrayList<Cart>();
		num = 1000;
	}

	public int getNextNum() {
		return ++num;
	}

	public void addCart(Cart cart) {
		cartList.add(cart);
	}

	public void deleteCart(int idx) {
		cartList.remove(idx);
	}

	public int checkCartList(String name) {
		return getItemIdx(name, cartList);
	}

	public void clearMyCart(String id) {
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getId().equals(id)) {
				deleteCart(i);
			}
		}
	}

	public int getItemIdx(String name, ArrayList<Cart> myCartList) {
		for (int i = 0; i < myCartList.size(); i++) {
			if (myCartList.get(i).getItemName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public ArrayList<Cart> getMyCartList(String id) {
		ArrayList<Cart> myCartList = new ArrayList<Cart>();
		for (int i = 0; i < cartList.size(); i++) {
			if (cartList.get(i).getId().equals(id)) {
				myCartList.add(cartList.get(i));
			}
		}
		return myCartList;
	}

	public ArrayList<Cart> getCartList() {
		return cartList;
	}

	public void printCartList() {
		for (int i = 0; i < cartList.size(); i++) {
			System.out.println(i + 1 + ")" + cartList.get(i));
		}
	}

	public void printMyCartList(ArrayList<Cart> myCartList) {
		for (int i = 0; i < myCartList.size(); i++) {
			System.out.println(i + 1 + ")" + myCartList.get(i));
		}
	}

}
