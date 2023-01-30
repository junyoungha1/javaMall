package menu_admin;

import java.io.File;

import _mall.MenuCommand;
import board.BoardDAO;
import controller.MallController;
import file.FileDAO;
import util.Util;

public class AdminBoard implements MenuCommand {
	private MallController mallCont;
	private BoardDAO bdao;
	private FileDAO fdao;
	File[] files;

	@Override
	public void init() {
		mallCont = MallController.getInstance();
		bdao = BoardDAO.getInstance();
		fdao = FileDAO.getInstance();
		files = null;
	}

	@Override
	public boolean update() {
		System.out.println("=====[ 게시판 ]=====");
		File dir = new File("src/게시판");
		files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			System.out.println("[" + (i + 1) + "] " + files[i].toString().substring(8));
		}

		int sel = Util.getValue("메뉴 선택", 0, files.length);
		if (sel == 0) {
			mallCont.setNext("AdminMain");
		}
		String foldername = files[--sel].toString().substring(8);
		dir = new File("src/게시판/" + foldername);
		files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			System.out.println("[" + (i + 1) + "] " + files[i].toString().substring(8 + foldername.length() + 1));
		}
		sel = Util.getValue("메뉴 선택", 0, files.length);
		if (sel == 0) {
			mallCont.setNext("AdminMain");
		}
		String filename = files[--sel].toString().substring(8 + foldername.length() + 1);
		filename = filename.substring(0, filename.length() - 4);
		System.out.println("===================================");
		fdao.loadFile(filename, foldername);
		System.out.println("===================================");
		System.out.println("[1] 삭제 [0] 뒤로가기");
		sel = Util.getValue("메뉴 선택", 0, 1);
		if (sel == 1) {
			bdao.deleteBoard(bdao.getBoardIdx(filename, bdao.getBoardList()));
			System.out.println("게시글이 삭제되었습니다");
		}
		
		mallCont.setNext("AdminMain");

		return false;
	}

}
