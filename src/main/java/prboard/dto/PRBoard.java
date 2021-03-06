package prboard.dto;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class PRBoard {
	
	//관리자, 마이 페이지 게시글 번호
	private int rnum;	
	
	private int boardno;
	private String title;
	private String writtendate;
	private String content;
	private int views;
	private int userno;
	private int postno;
	private String prname;
	
	private String usernick;
	
	private int blike;
	
	private int replyCnt;

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWrittendate() {
		return writtendate;
	}

	public void setWrittendate(String writtendate) {
		this.writtendate = writtendate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public int getPostno() {
		return postno;
	}

	public void setPostno(int postno) {
		this.postno = postno;
	}

	public String getPrname() {
		return prname;
	}

	public void setPrname(String prname) {
		this.prname = prname;
	}

	public String getUsernick() {
		return usernick;
	}

	public void setUsernick(String usernick) {
		this.usernick = usernick;
	}

	public int getBlike() {
		return blike;
	}

	public void setBlike(int blike) {
		this.blike = blike;
	}

	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	@Override
	public String toString() {
		return "PRBoard [rnum=" + rnum + ", boardno=" + boardno + ", title=" + title + ", writtendate=" + writtendate
				+ ", content=" + content + ", views=" + views + ", userno=" + userno + ", postno=" + postno
				+ ", prname=" + prname + ", usernick=" + usernick + ", blike=" + blike + ", replyCnt=" + replyCnt + "]";
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	
}
