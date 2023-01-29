package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class AdminMain implements MenuCommand {
	private MallController mallCont;
	@Override
	public void init() {
		mallCont = MallController.getInstance();
		
	}

	@Override
	public boolean update() {
		System.out.println("[1] 회원관리 [2] 상품관리 [3] 게시판관리 [0] 로그아웃");
		int sel = Util.getValue("메뉴 입력", 0, 3);
		if (sel == 0) {
			mallCont.setNext("MallMain");
		}

		if (sel == 1) {
			mallCont.setNext("AdminMember");
		} else if (sel == 2) {
			mallCont.setNext("AdminItem");
		}else if(sel==3) {
			mallCont.setNext("AdminBoard");
		}
		return false;
	}

}
