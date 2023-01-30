package menu_memeber;

import java.time.LocalDate;
import java.util.ArrayList;

import _mall.MenuCommand;
import board.Board;
import board.BoardDAO;
import controller.MallController;
import file.FileDAO;
import util.Util;

public class MemberBoard implements MenuCommand {
	private MallController mallCont;
	BoardDAO bdao;
	FileDAO fdao;

	@Override
	public void init() {
		mallCont = MallController.getInstance();
		bdao = BoardDAO.getInstance();
		fdao = FileDAO.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("=====[ 게시판 ]=====");
		System.out.println("[1] 전체 글 보기 [2] 글쓰기 [3] 내가 쓴 글 [0] 뒤로가기");
		int sel = Util.getValue("메뉴 입력", 0, 3);
		if (sel == 0) {
			mallCont.setNext("MemberMain");
		}
		if (sel == 1) {
			loadBoard();
		} else if (sel == 2) {
			insertBoard();
		} else if (sel == 3) {
			loadMyBoard();
		}

		return false;
	}

	void insertBoard() {
		String filename = Util.getValue("제목 작성");
		Board b = new Board();
		b.setfileName(filename);
		b.setId(mallCont.getLoginId());
		b.setHits(0);
		b.setDate(LocalDate.now().toString());
		bdao.addBoard(b);
		String post = Util.getValue("내용 작성");
		fdao.saveFile(filename, b.getId(), post);
		fdao.saveFile(filename, "AllUser", post);
	}

	void loadBoard() {
		ArrayList<Board> boardList = bdao.getBoardList();
		if (boardList.size() == 0) {
			System.out.println("작성된 게시물이 없습니다");
			return;
		}
		bdao.printBoardList();
		int sel = Util.getValue("번호 선택", 1, boardList.size());
		fdao.loadFile(boardList.get(--sel).getfileName(), "AllUser");
		if (!boardList.get(sel).getId().equals(mallCont.getLoginId())) {
			int hits = boardList.get(sel).getHits();
			bdao.getBoardList().get(sel).setHits(++hits);
		}
	}

	void loadMyBoard() {
		ArrayList<Board> myBoardList = bdao.getMyBoardList(mallCont.getLoginId());
		if (myBoardList.size() == 0) {
			System.out.println("작성된 게시물이 없습니다");
			return;
		}
		bdao.printMyBoardList(myBoardList);
		int sel = Util.getValue("번호 선택", 1, myBoardList.size());
		fdao.loadFile(myBoardList.get(--sel).getfileName(), "AllUser");
		removeBoard(myBoardList, sel);
	}

	void removeBoard(ArrayList<Board> myBoardList, int select) {
		System.out.println("[1] 수정 [2] 삭제 [0] 뒤로가기");
		int sel = Util.getValue("메뉴 선택", 0, 1);
		if (sel == 0) {
			return;
		}
		int boardIdx = bdao.getBoardIdx(myBoardList.get(select).getfileName(), myBoardList);
		if (sel == 1) {
		} else if (sel == 2) {
			bdao.deleteBoard(boardIdx);
			System.out.println("게시물이 삭제되었습니다");
		}
	}

}
