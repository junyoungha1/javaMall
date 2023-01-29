package item;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class ItemDAO {
	private ItemDAO() {
	}

	static private ItemDAO instance = new ItemDAO();

	static public ItemDAO getInstance() {
		return instance;
	}

	private ArrayList<Item> itemList;
	private int num;

	public void init() {
		itemList = new ArrayList<Item>();
		num = 1000;
		setSampleData();
	}

	public void setSampleData() {

		String categoryData[] = { "과자", "음료수", "과자", "음료수" };
		String itemNameData[] = { "새우깡", "콜라", "감자깡", "사이다" };
		int priceData[] = { 1000, 2000, 1500, 2500 };
		for (int i = 0; i < categoryData.length; i++) {
			itemList.add(new Item(getNextNum(), categoryData[i], itemNameData[i], priceData[i]));
		}
	}

	public int getNextNum() {
		return ++num;
	}

	public boolean checkItemName(String itemName) {
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getItemName().equals(itemName)) {
				return true;
			}
		}
		return false;
	}

	public void addItem(Item item) {
		itemList.add(item);

	}

	public void deleteItem(Item item) {
		itemList.remove(getItemIdx(item.getItemName()));
	}

	public int getItemIdx(String name) {
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getItemName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public void printItemList() {
		for (int i = 0; i < itemList.size(); i++) {
			System.out.println(i + 1 + ") " + itemList.get(i));
		}
	}

	public void printItemList(ArrayList<Item> itemList) {
		for (int i = 0; i < itemList.size(); i++) {
			System.out.println(i + 1 + ") " + itemList.get(i));
		}
	}
	public void printCategoryList(ArrayList<String> categoryList) {
		for (int i = 0; i < categoryList.size(); i++) {
			System.out.println(i + 1 + ") " + categoryList.get(i));
		}
	}
	public ArrayList<String> getCategoryList() {
		Set<String> categorySet = new LinkedHashSet<String>();
		for (int i = 0; i < itemList.size(); i++) {
			categorySet.add(itemList.get(i).getCategoryName());
		}
		Iterator<String> iterator = categorySet.iterator();
		ArrayList<String> categoryList = new ArrayList<String>();
		while (iterator.hasNext()) {
			categoryList.add(iterator.next());
		}
		return categoryList;
	}

	public ArrayList<Item> getCategorysItemList(String category) {
		ArrayList<Item> categorysItemList = new ArrayList<Item>();
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getCategoryName().equals(category)) {
				categorysItemList.add(itemList.get(i));
			}
		}
		return categorysItemList;
	}

}
