package board;

import java.util.ArrayList;

public class BoardDAO {
	private BoardDAO() {
	}

	private static BoardDAO instance = new BoardDAO();

	static public BoardDAO getInstance() {
		return instance;
	}

	private ArrayList<Board> boardList;
	private int num;

	public void init() {
		num = 1;
		boardList = new ArrayList<>();
	}

	public int getNextNum() {
		return ++num;
	}

	public void addBoard(Board board) {
		boardList.add(board);
	}

	public void deleteBoard(int idx) {
		boardList.remove(idx);
	}

	public void deleteMembersBoard(String id) {
		for (int i = 0; i < boardList.size(); i++) {
			if (boardList.get(i).getId().equals(id)) {
				deleteBoard(i);
			}
		}
	}

	public ArrayList<Board> getBoardList() {
		return boardList;
	}

	public ArrayList<Board> getMyBoardList(String id) {
		ArrayList<Board> myBoardList = new ArrayList<Board>();
		for (int i = 0; i < boardList.size(); i++) {
			if (boardList.get(i).getId().equals(id)) {
				myBoardList.add(boardList.get(i));
			}
		}
		return myBoardList;
	}

	public int getBoardIdx(String filename, ArrayList<Board> myBoardList) {
		for (int i = 0; i < myBoardList.size(); i++) {
			if (myBoardList.get(i).getfileName().equals(filename)) {
				return i;
			}
		}
		return -1;
	}

	public void printBoardList() {
		for (int i = 0; i < boardList.size(); i++) {
			System.out.println(i + 1 + ")" + boardList.get(i));
		}
	}

	public void printMyBoardList(ArrayList<Board> myBoardList) {
		for (int i = 0; i < myBoardList.size(); i++) {
			System.out.println(i + 1 + ")" + myBoardList.get(i));
		}
	}

}
