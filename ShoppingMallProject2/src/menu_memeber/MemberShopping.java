package menu_memeber;

import java.util.ArrayList;

import _mall.MenuCommand;
import controller.MallController;
import item.ItemDAO;
import util.Util;

public class MemberShopping implements MenuCommand {
	private MallController mallCont;
	private ItemDAO idao;
	static public String category;

	@Override
	public void init() {
		mallCont = MallController.getInstance();
		idao = ItemDAO.getInstance();
		idao.init();
		category = null;
	}

	@Override
	public boolean update() {
		ArrayList<String> categoryList = idao.getCategoryList();
		idao.printCategoryList(categoryList);
		System.out.println("0) 뒤로 가기");
		int sel = Util.getValue("메뉴 입력", 0, categoryList.size());
		if (sel == 0) {
			mallCont.setNext("MemberMain");
		} else {
			category = categoryList.get(--sel);
			mallCont.setNext("MemberItem");
		}
		return false;
	}

}
