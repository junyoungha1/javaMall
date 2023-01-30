package controller;

import java.util.HashMap;
import java.util.Map;

import _mall.MenuCommand;
import board.Board;
import board.BoardDAO;
import cart.CartDAO;
import file.FileDAO;
import item.ItemDAO;
import member.MemberDAO;
import menu_admin.AdminBoard;
import menu_admin.AdminItem;
import menu_admin.AdminMain;
import menu_admin.AdminMember;
import menu_mall.MallJoin;
import menu_mall.MallLogin;
import menu_mall.MallMain;
import menu_memeber.MemberBoard;
import menu_memeber.MemberCart;
import menu_memeber.MemberInfo;
import menu_memeber.MemberItem;
import menu_memeber.MemberMain;
import menu_memeber.MemberQuit;
import menu_memeber.MemberShopping;

public class MallController {
	private MallController() {
	}

	static private MallController instance = new MallController();

	static public MallController getInstance() {
		return instance;
	}

	private String loginId;
	private String next;
	private MenuCommand menuCom;
	private Map<String, MenuCommand> mapCont;

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void init() {
		mapCont = new HashMap<>();
		MemberDAO.getInstance().init();
		FileDAO.getInstance().init();
		ItemDAO.getInstance().init();
		CartDAO.getInstance().init();
		BoardDAO.getInstance().init();
		
		mapCont.put("MallMain", new MallMain());
		mapCont.put("MallJoin", new MallJoin());
		mapCont.put("MallLogin", new MallLogin());
		mapCont.put("AdminBoard", new AdminBoard());
		mapCont.put("AdminItem", new AdminItem());
		mapCont.put("AdminMain", new AdminMain());
		mapCont.put("AdminMember", new AdminMember());
		mapCont.put("MemberBoard", new MemberBoard());
		mapCont.put("MemberCart", new MemberCart());
		mapCont.put("MemberInfo", new MemberInfo());
		mapCont.put("MemberItem", new MemberItem());
		mapCont.put("MemberMain", new MemberMain());
		mapCont.put("MemberShopping", new MemberShopping());
		mapCont.put("MemberQuit", new MemberQuit());
		
		menuCom = mapCont.get("MallMain");
		menuCom.init();
		update();
	}

	public void update() {
		while (true) {
			if (!menuCom.update()) {
				if (next != null) {
					menuCom = mapCont.get(next);
					menuCom.init();
				} else {
					return;
				}
			}
		}
	}

}
