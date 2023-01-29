package member;

import java.util.ArrayList;

public class MemberDAO {
	private MemberDAO() {
	}

	private static MemberDAO instance = new MemberDAO();

	static public MemberDAO getInstance() {
		return instance;
	}

	private ArrayList<Member> memberList;
	private int num;

	public void init() {
		num = 1000;
		memberList = new ArrayList<>();
		setAdmin();
	}

	public void setAdmin() {
		if (!checkMember("admin", null)) {
			Member m = new Member(1, "admin", "admin", "관리자");
			addMember(m);
		}
	}

	public void addMember(Member m) {
		memberList.add(m);
	}

	public void deleteMember(String id) {
		memberList.remove(getMemberIdx(id));
	}

	public void updateMemberPassword(String id, String newPw) {
		memberList.get(getMemberIdx(id)).setM_pw(newPw);
	}

	public int getNextNum() {
		return ++num;
	}

	public int getMemberIdx(String id) {
		for (int i = 0; i < memberList.size(); i++) {
			if (id.equals(memberList.get(i).getM_id())) {
				return i;
			}
		}
		return -1;
	}

	public boolean checkMember(String id, String pw) {
		for (int i = 0; i < memberList.size(); i++) {
			if (pw == null) {
				if (memberList.get(i).getM_id().equals(id)) {
					return true;
				}
			} else {
				if (memberList.get(i).getM_id().equals(id) && memberList.get(i).getM_pw().equals(pw)) {
					return true;
				}
			}
		}
		return false;
	}

	public void printMember(String id) {
		if (id == null) {
			for (int i = 0; i < memberList.size(); i++) {
				if (!memberList.get(i).getM_id().equals("admin")) {
					System.out.println(i + ") " + memberList.get(i));
				}
			}
		} else {
			int idx = getMemberIdx(id);
			System.out.println(memberList.get(idx));
		}
	}

}
