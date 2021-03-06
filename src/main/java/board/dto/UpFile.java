package board.dto;

import org.springframework.web.multipart.MultipartFile;

public class UpFile {
	
	private int fileno;
	private String originname;
	private String storedname;
	private long filesize;
	private int boardno;
	private MultipartFile file;
	
	@Override
	public String toString() {
		return "UpFile [fileno=" + fileno + ", originname=" + originname + ", storedname=" + storedname + ", filesize="
				+ filesize + ", boardno=" + boardno + ", file=" + file + "]";
	}

	public int getFileno() {
		return fileno;
	}

	public void setFileno(int fileno) {
		this.fileno = fileno;
	}

	public String getOriginname() {
		return originname;
	}

	public void setOriginname(String originname) {
		this.originname = originname;
	}

	public String getStoredname() {
		return storedname;
	}

	public void setStoredname(String storedname) {
		this.storedname = storedname;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}
