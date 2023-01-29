package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import member.MemberDAO;
import util.Util;

public class AdminMember implements MenuCommand {
	private MallController mallCont;
	private MemberDAO mdao;

	@Override
	public void init() {
		mallCont = MallController.getInstance();
		mdao = MemberDAO.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("=====[ 회원관리 ]=====");
		System.out.println("[1] 회원목록 [2] 회원삭제 [0] 뒤로가기");
		int sel = Util.getValue("메뉴 입력", 0, 3);
		if (sel == 0) {
			mallCont.setNext("AdminMain");
		}
		if (sel == 1) {
			mdao.printMember(null);
		} else if (sel == 2) {
			mdao.printMember(null);
			String id = Util.getValue("삭제할 회원 ID");
			if (mdao.checkMember(id, null)) {
				mdao.deleteMember(id);
			} else {
				System.err.println("존재하지 않는 회원");
				return true;
			}
		}

		return false;
	}

}
