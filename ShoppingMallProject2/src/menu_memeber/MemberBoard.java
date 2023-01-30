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
			loadFile();
		} else if (sel == 2) {
			createFile();
		} else if (sel == 3) {
			loadMyPost();
		}

		return false;
	}

	void createFile() {
		String filename = Util.getValue("제목 작성");
		bdao.addBoard(filename, mallCont.getLoginId());
		String post = Util.getValue("내용 작성");
		fdao.saveFile(filename, mallCont.getLoginId(), post);
		fdao.saveFile(filename, "AllUser", post);
	}

	void loadFile() {
		ArrayList<Board> boardList = bdao.getBoardList();
		if (boardList.size() == 0) {
			System.out.println("작성된 게시물이 없습니다");
			return;
		}
		bdao.printBoardList();
		System.out.println("0) 뒤로가기");
		int sel = Util.getValue("번호 선택", 0, boardList.size());
		if (sel == 0) {
			return;
		}

		System.out.println("===================================");
		fdao.loadFile(boardList.get(--sel).getfileName(), "AllUser");
		System.out.println("===================================");
		if (!boardList.get(sel).getId().equals(mallCont.getLoginId())) {
			int hits = boardList.get(sel).getHits();
			bdao.getBoardList().get(sel).setHits(++hits);
		}
	}

	void loadMyPost() {
		ArrayList<Board> myBoardList = bdao.getMyBoardList(mallCont.getLoginId());
		if (myBoardList.size() == 0) {
			System.out.println("작성된 게시물이 없습니다");
			return;
		}
		bdao.printMyBoardList(myBoardList);
		System.out.println("0) 뒤로가기");
		int sel = Util.getValue("번호 선택", 0, myBoardList.size());
		if (sel == 0) {
			return;
		}
		System.out.println("===================================");
		fdao.loadFile(myBoardList.get(--sel).getfileName(), "AllUser");
		System.out.println("===================================");
		boardMenu(myBoardList, sel);
	}

	void boardMenu(ArrayList<Board> myBoardList, int select) {
		System.out.println("[1] 수정 [2] 삭제 [0] 뒤로가기");
		int sel = Util.getValue("메뉴 선택", 0, 2);
		if (sel == 0) {
			return;
		}
		int boardIdx = bdao.getBoardIdx(myBoardList.get(select).getfileName(), myBoardList);
		if (sel == 1) {
			String post = fdao.loadFile(myBoardList.get(boardIdx).getfileName(), "AllUser");
			while (sel != 0) {
				System.out.println("===================================");
				System.out.println(post);
				System.out.println("===================================");
				System.out.println("[1] 작성하기 [2] 지우기 [3] 저장하기 [0] 뒤로가기");
				sel = Util.getValue("메뉴 선택", 0, 3);
				if (sel == 1) {
					String addPost = Util.getValue("내용 작성");
					post += addPost;
				} else if (sel == 2) {
					if (post.length() != 0) {
						post = post.substring(0, post.length() - 1);
					}
				} else if (sel == 3) {
					fdao.saveFile(myBoardList.get(boardIdx).getfileName(), mallCont.getLoginId(), post);
					fdao.saveFile(myBoardList.get(boardIdx).getfileName(), "AllUser", post);
				}
			}
		} else if (sel == 2) {
			bdao.deleteBoard(boardIdx);
			fdao.deleteFile("AllUser", myBoardList.get(boardIdx).getfileName());
			System.out.println("게시물이 삭제되었습니다");
		}
	}

}
