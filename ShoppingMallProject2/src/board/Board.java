package board;

public class Board {
	String fileName;
	String id;
	String date;
	int hits;

	public Board() {
	}

	public Board(String id, String date, int hits, String fileName) {
		super();
		this.fileName = fileName;
		this.id = id;
		this.date = date;
		this.hits = hits;
	}

	public String getfileName() {
		return fileName;
	}

	public void setfileName(String fileName) {
		this.fileName = fileName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	@Override
	public String toString() {
		return "제목 : " + fileName + "\t 작성자 : " + id + "\t 날짜 : " + date + "\t 조회수 : " + hits;
	}

}
