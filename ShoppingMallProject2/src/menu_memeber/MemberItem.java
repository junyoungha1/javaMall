package menu_memeber;

import java.util.ArrayList;

import _mall.MenuCommand;
import cart.Cart;
import cart.CartDAO;
import controller.MallController;
import item.Item;
import item.ItemDAO;
import util.Util;

public class MemberItem implements MenuCommand {
	private MallController mallCont;
	private String category;
	private CartDAO cdao;
	private ItemDAO idao;
	
	@Override
	public void init() {
		mallCont = MallController.getInstance();
		category = MemberShopping.category;
		idao = ItemDAO.getInstance();
		cdao = CartDAO.getInstance();
	}

	@Override
	public boolean update() {
		ArrayList<Item> itemList = idao.getCategorysItemList(category);
		System.out.println("=====[ " + category + " ]=====");
		idao.printItemList(itemList);
		System.out.println("0) 뒤로 가기");
		int sel = Util.getValue("상품번호", 0, itemList.size());
		if (sel == 0) {
			mallCont.setNext("MemberShopping");
		}else {
			Item i = itemList.get(--sel);
			Cart c = new Cart();
			c.setId(mallCont.getLoginId());
			c.setNumber(cdao.getNextNum());
			c.setItemName(i.getItemName());
			c.setPrice(i.getPrice());
			cdao.addCart(c);
			System.out.println("장바구니 추가 완료");
		}
		
		return false;
	}

}
