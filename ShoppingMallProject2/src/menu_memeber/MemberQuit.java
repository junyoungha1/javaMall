package menu_memeber;


import _mall.MenuCommand;
import cart.CartDAO;
import controller.MallController;
import member.MemberDAO;
import util.Util;

public class MemberQuit implements MenuCommand{
	MallController mallCont;
	MemberDAO mdao;
	CartDAO cdao;
	@Override
	public void init() {
		mallCont = MallController.getInstance();
		mdao = MemberDAO.getInstance();
		cdao = CartDAO.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("=====[ 회원탈퇴 ]=====");
		String id = mallCont.getLoginId();
		String pw = Util.getValue("[ 회원탈퇴 ] PW : ");
		if (mdao.checkMember(id, pw)) {
			mdao.deleteMember(id);
			cdao.clearMyCart(id);
			System.out.println("[ 회원탈퇴 ] 완료");
		} else {
			System.err.println("비밀번호가 일치하지 않습니다");
			return true;
		}
		return false;
	}

	
}
