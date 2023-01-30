package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import member.MemberDAO;
import util.Util;

public class MallLogin implements MenuCommand {
	private MallController mallCont;
	private MemberDAO mdao;

	@Override
	public void init() {
		mallCont = MallController.getInstance();
		mdao = MemberDAO.getInstance();
		mdao.init();
	}

	@Override
	public boolean update() {
		System.out.println("=====[ 로그인 ]=====");
		String id = Util.getValue("[로그인] ID : ");
		String pw = Util.getValue("[로그인] PW : ");
		if (mdao.checkMember(id, pw)) {
			if (id.equals("admin")) {
				mallCont.setLoginId("admin");
				mallCont.setNext("AdminMain");
			} else {
				mallCont.setLoginId(id);
				mallCont.setNext("MemberMain");
			}
			System.out.println("[ 로그인 성공 ]");
		} else {
			System.err.println("아이디 혹은 비밀번호를 확인해주세요");
			mallCont.setLoginId(null);
			mallCont.setNext("MallMain");
		}
		return false;
	}

}
