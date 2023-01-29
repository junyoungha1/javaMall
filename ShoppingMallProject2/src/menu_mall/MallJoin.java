package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import member.Member;
import member.MemberDAO;
import util.Util;

public class MallJoin implements MenuCommand {
	private MallController mallCont;
	private MemberDAO mdao;

	@Override
	public void init() {
		mallCont = MallController.getInstance();
		mdao = MemberDAO.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("=====[ 회원가입 ]=====");
		String id = Util.getValue("[ 회원가입 ] ID : ");
		if (mdao.checkMember(id, null)) {
			System.err.println("중복된 아이디 입니다");
		} else {
			String pw = Util.getValue("[ 회원가입 ] PW : ");
			String name = Util.getValue("[ 회원가입 ] 이름 : ");
			Member m = new Member(mdao.getNextNum(), id, pw, name);
			mdao.addMember(m);
			System.out.println(name + "님, 가입을 환영합니다");
		}
		mallCont.setNext("MallMain");
		return false;
	}

}
