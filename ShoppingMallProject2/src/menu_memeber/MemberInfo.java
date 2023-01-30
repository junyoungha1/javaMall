package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import member.MemberDAO;
import util.Util;

public class MemberInfo implements MenuCommand {
	private MallController mallCont;
	MemberDAO mdao;

	@Override
	public void init() {
		mallCont = MallController.getInstance();
		mdao = MemberDAO.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("=====[ 내 정보 ] =====");
		mdao.printMember(mallCont.getLoginId());
		System.out.println("[1] 비밀번호 변경 [2]회원탈퇴 [0] 뒤로가기");
		int sel = Util.getValue("메뉴 입력", 0, 2);
		if (sel == 0) {
			mallCont.setNext("MemberMain");
		}
		if (sel == 1) {
			setNewPw(mallCont.getLoginId());
		} else if (sel == 2) {
			mallCont.setNext("MemberQuit");
		}
		return false;
	}

	void setNewPw(String id) {
		System.out.println("=====[ 비밀번호 변경 ]=====");
		String pw = Util.getValue("[비밀번호 변경] PW : ");
		if (mdao.checkMember(id, pw)) {
			String newPw = Util.getValue("새로운 비밀번호");
			mdao.updateMemberPassword(id, newPw);
			System.out.println("[ 비밀번호 변경 완료 ]");
		} else {
			System.err.println("비밀번호가 일치하지 않습니다");
		}
	}
}