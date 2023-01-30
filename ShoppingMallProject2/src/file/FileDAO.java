package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDAO {
	private FileDAO() {
	}

	private static FileDAO instance = new FileDAO();

	static public FileDAO getInstance() {
		return instance;
	}

	private FileWriter fw;
	private FileReader fr;

	public void init() {
		fw = null;
		fr = null;
		setBoard();
	}

	void setBoard() {
		String path = "src/게시판";
		File folder = new File(path);
		if (!folder.exists()) {
			try {
				folder.mkdir();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

		path = "src/게시판/AllUser";
		folder = new File(path);
		if (!folder.exists()) {
			try {
				folder.mkdir();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}

	}

	public void saveFile(String fileName, String id, String post) {
		String path = "src/게시판/" + id;
		File folder = new File(path);
		if (!folder.exists()) {
			try {
				folder.mkdir();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		path = "src/게시판/" + id + "/" + fileName + ".txt";
		try {
			fw = new FileWriter(path);
			fw.write(post);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!id.equals("AllUser")) {
			System.out.println("작성이 완료되었습니다.");
		}
	}

	public String loadFile(String fileName, String id) {
		String path = "src/게시판/" + id + "/" + fileName + ".txt";
		String data = "";
		try {
			fr = new FileReader(path);
			int read = 0;
			while (true) {
				read = fr.read();
				if (read != -1) {
					data += (char) read;
				} else {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(data);
		return data;
	}

	public void deleteFile(String folderName, String fileName) {
		Path path = Paths.get("src/게시판/" + folderName + "/" + fileName + ".txt");
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
