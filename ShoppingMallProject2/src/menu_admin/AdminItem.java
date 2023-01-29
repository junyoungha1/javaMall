package menu_admin;

import java.util.ArrayList;

import _mall.MenuCommand;
import cart.CartDAO;
import controller.MallController;
import item.Item;
import item.ItemDAO;
import util.Util;

public class AdminItem implements MenuCommand {
	private MallController mallCont;
	private ItemDAO idao;
	private CartDAO cdao;

	@Override
	public void init() {
		mallCont = MallController.getInstance();
		idao = ItemDAO.getInstance();
		cdao = CartDAO.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("=====[ 상품관리 ]=====");
		System.out.println("[1] 상품추가 [2] 상품삭제 [0] 뒤로가기");
		int sel = Util.getValue("메뉴 입력", 0, 2);
		if (sel == 0) {
			mallCont.setNext("AdminMain");
		}
		if (sel == 1) {
			addItem();
		} else if (sel == 2) {
			deleteItem();

		}

		return false;
	}

	public void addItem() {
		System.out.println("=====[ 상품추가 ]=====");
		String categoryName = Util.getValue("[ 상품추가 ] 카테고리");
		String itemName = Util.getValue("[ 상품추가 ] 상품 이름");
		if (idao.checkItemName(itemName)) {
			System.err.println("이미 존재하는 상품입니다");
			return;
		}

		int price = Util.getValue("[ 상품추가 ] 가격", 1, 9999999);
		idao.addItem(new Item(idao.getNextNum(), categoryName, itemName, price));
	}

	public void deleteItem() {
		System.out.println("=====[ 상품삭제 ]=====");
		ArrayList<String> categoryList = idao.getCategoryList();
		idao.printCategoryList(categoryList);
		int sel = Util.getValue("메뉴 입력", 0, categoryList.size());
		System.out.println("0) 이전 페이지");
		if (sel == 0) {
			return;
		} else {
			String category = idao.getCategoryList().get(--sel);
			ArrayList<Item> itemList = idao.getCategorysItemList(category);
			idao.printItemList(itemList);
			System.out.println("0) 이전 페이지");
			sel = Util.getValue("[ 상품삭제 ] 상품번호", 0, categoryList.size());

			if (sel == 0) {
				return;
			}
			String itemName = itemList.get(--sel).getItemName();
			if (cdao.checkCartList(itemName) == -1) {
				idao.deleteItem(itemList.get(sel));

			} else {
				System.err.println("현재 구매 예약된 상품입니다");
			}
		}
	}
}
