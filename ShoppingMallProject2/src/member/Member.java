package member;

public class Member {
	int m_num;
	String m_id;
	String m_pw;
	String m_name;

	public Member() {
	}

	public Member(int m_num, String m_id, String m_pw, String m_name) {
		super();
		this.m_num = m_num;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
	}

	public int getM_num() {
		return m_num;
	}

	public void setM_num(int m_num) {
		this.m_num = m_num;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_pw() {
		return m_pw;
	}

	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	@Override
	public String toString() {
		return "[회원번호] " + m_num + ", [이름] " + m_name + ", [ID] " + m_id + ", [PW] " + m_pw;
	}
}
