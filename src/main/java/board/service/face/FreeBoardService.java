package board.service.face;

import java.util.List;
import java.util.Map;

import board.dto.Alram;
import board.dto.FreeBoard;
import board.dto.Reply;
import board.dto.UpFile;
import user.dto.User_table;
import util.Paging;

public interface FreeBoardService {
	
	/** 2019 - 12 - 23
	 * 고인호
	 * 
	 * 전체 게시글 개수 구하기
	 * @param searchcategory - 검색 조건
	 * @param searchtarget - 검색어
	 * 
	 */
	public int getListCnt(Map<String, String> map);
	
	
	/** 
	 * 2019-12-23
	 * 고인호
	 * 
	 * 자유게시판 전체 게시글 페이징 처리해서 보여주기
	 * 
	 * @param paging - 요청 페이징 정보 객체
	 * @return
	 */
	public List<FreeBoard> getList(Paging paging);

	
	/**
	 * 2019-12-26
	 * 고인호
	 * 
	 * 자유게시판 게시글 상세보기
	 * 
	 * @param boardno - 요청 게시글번호 정보 객체
	 * @return - 
	 */
	public FreeBoard freeDetail(int boardno);

	/**
	 * 2019-12-26
	 * 고인호
	 * 
	 * 자유게시판 게시글 쓰기
	 * 
	 * @param freeboard - DB에 저장될 게시글 정보 객체
	 */
	public void writeFree(FreeBoard freeboard);

	/**
	 * 2019-12-26
	 * 고인호
	 * 
	 * 게시글 상세보기 조회수 증가
	 * 
	 * @param boardDetail- 상세보기 조회된 게시글 정보 객체
	 */
	public void increaseViews(int boardno);

	/**
	 * 2019-12-26
	 * 고인호
	 * 
	 * 게시글 작성을 위한 유저아이디 조회
	 * 
	 * @param attribute - 로그인한 유정 정보 객체
	 * @return 
	 */
	public FreeBoard getUserNoByNick(Object attribute);

	/**
	 * 2019-12-26
	 * 고인호
	 * 
	 * 자유게시판 전체 게시글중 조회수 높은 순으로 조회
	 * 
	 * @return List - 조회수 가장 높은 게시글 리스트
	 */
	public List<FreeBoard> getViewsList();

	/**
	 * 2019-12-29
	 * 고인호
	 * 
	 * 자유게시판 게시글 삭제
	 * 
	 * @param boardno - 삭제할 게시글 번호
	 */
	public void freeDelete(int boardno);

	/**
	 * 2019-12-29
	 * 고인호
	 * 
	 * 자유게시판 게시글 수정
	 * 
	 * @param freeboard - 수정에 반영될 게시글 제목, 내용 정보
	 * @return 
	 */
	public void updateFreeBoard(FreeBoard freeboard);

	/**
	 * 2019-12-30
	 * 고인호
	 * 
	 * 자유게시판 게시글에 첨부할 파일
	 * 
	 * @param upfile - 게시글에 첨부할 파일 정보
	 * @param boardno - 첨부파일 정보에 저장할 게시글번호
	 */
	public void filesave(UpFile upfile, int boardno);

	/**
	 * 2019-12-30
	 * 고인호
	 * 
	 * 자유게시판 게시글 첨부파일 정보
	 * 
	 * @param boardno - 첨부파일정보 조회할 게시글 번호
	 * @return - DB에서 조회된 첨부파일 정보 
	 */
	public UpFile getFile(int boardno);

	/**
	 * 2019-12-30
	 * 고인호
	 * 
	 * 자유게시판 게시글 첨부파일 정보
	 * 
	 * @param fileno - 첨부파일정보 조회할 파일번호
	 * @return - DB에서 조회된 첨부파일 정보
	 */
	public UpFile getFileNo(int fileno);

	/**
	 * 2019-12-30
	 * 고인호
	 * 
	 * 자유게시판 게시글 첨부파일 삭제
	 * 
	 * @param fileno - 삭제할 첨부파일번호
	 */
	public void fileDelete(UpFile fileno);

	/**
	 * 2020-01-03
	 * 고인호
	 * 
	 * 추천 여부 조회
	 * 
	 * @param freeBoard - 추천 여부 조회할 정보가 담긴 객체
	 * @return int - 추천 여부
	 */
	public int recommendCheck(FreeBoard freeBoard);

	/**
	 * 2020-01-03
	 * 고인호
	 * 
	 * 추천을 한다.
	 * 
	 * @param freeBoard - 추천 테이블에 삽입할 데이터 객체
	 */
	public void recommend(FreeBoard freeBoard);

	/**
	 * 2020-01-03
	 * 고인호
	 * 
	 * 추천 취소를 한다.
	 * 
	 * @param freeBoard - 추천 테이블에서 삭제할 데이터 객체
	 */
	public void recommendCancal(FreeBoard freeBoard);

	/**
	 * 2020-01-03
	 * 고인호
	 * 
	 * 게시글의 추천 갯수를 센다
	 * 
	 * @param freeBoard - 게시글 정보가 담긴 객체
	 * @return int - 추천수
	 */
	public int recommendView(FreeBoard freeBoard);

	/**
	 * 2020-01-03
	 * 고인호
	 * 
	 * 좋아요 테이블 삭제
	 * @param boardno - 삭제할 게시글 번호가 담긴 객체
	 */
	public void deleteBlike(int boardno);

	/**
	 * 2019-01-03
	 * 고인호
	 * 
	 * 댓글 입력
	 * 
	 * @param reply - 입력될 댓글
	 */
	public void insertReply(Reply reply);

	/**
	 * 2019-01-03
	 * 고인호
	 * 
	 * 댓글 리스트
	 * 
	 * @param boardno - 댓글 리스트 조회할 게시글번호
	 * @return - 조회된 게시글 댓글 리스트
	 */
	public List<Reply> getReplyList(int boardno);

	/**
	 * 2020 - 01 - 09
	 * 고인호
	 * 
	 * 댓글 삭제
	 * 
	 * @param reply - 삭제할 댓글
	 * @return boolean - 삭제 성공 여부
	 */
	public void deleteReply(Reply reply);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 댓글의 답글 갯수 조회
	 * 
	 * @param groupno - 댓글의 그룹 번호
	 * @return int - 답글 갯수
	 */
	public int getREreplyCnt(int groupno);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 댓글번호를 이용해 그룹번호 가져오기
	 * 
	 * @param reply - 댓글번호가 담긴 객체
	 * @return int - 그룹번호
	 */
	public int getGroupNoByReplyNo(Reply reply);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 댓글 수정
	 * 
	 * @param reply - 댓글 번호가 담긴 객체
	 */
	public void updateReplyByNo(Reply reply);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 답글 리스트 조회
	 * 
	 * @param groupNo - 댓글 그룹 번호
	 * @return List - 답글 리스트
	 */
	public List<Reply> getReReplyByNo(int groupNo);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 최대 그룹번호 값을 가져온다.
	 * 
	 * @param reply - 그룹번호가 담긴 객체
	 * @return int - 최대 그룹번호
	 */
	public int getMaxReplyOrder(Reply reply);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 답글 삽입
	 * 
	 * @param reply - 삽입할 답글정보가 담긴 객체
	 */
	public void addReReply(Reply reply);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 댓글 좋아요를 위한 유저번호 조회
	 * 
	 * @param usernick - 유저 닉네임
	 * @return Reply - 유저번호가 담긴 댓글 객체
	 */
	public Reply getUserNoForReplyLike(String attribute);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 댓글 추천 여부 조회
	 * 
	 * @param reply - 추천 여부 조회할 정보가 담긴 객체
	 * @return int - 추천 여부
	 */
	public int replyRecommendCheck(Reply reply);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 댓글의 추천 갯수를 센다
	 * 
	 * @param reply - 댓글 정보가 담긴 객체
	 * @return int - 추천수
	 */
	public int replyRecommendView(Reply reply);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 댓글 추천을 한다.
	 * 
	 * @param reply - 댓글 추천 테이블에 삽입할 데이터 객체
	 */
	public void replyRecommend(Reply reply);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 댓글 추천 취소를 한다.
	 * 
	 * @param reply - 댓글 추천 테이블에 삭제할 데이터 객체
	 */
	public void replyRecommendCancal(Reply reply);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 베스트 댓글 리스트 조회
	 * 
	 * @param reply - 댓글 정보 담긴 객체
	 * @return List - 답글 리스트
	 */
	public List<Reply> getBestReplyByboardNo(Reply reply);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 답글 많은 순 댓글 리스트 조회
	 * 
	 * @param reply - 댓글 정보 담긴 객체
	 * @return List - 답글 리스트
	 */
	public List<Reply> getMostReplyByboardNo(Reply reply);

	/**
	 * 2020-01-09
	 * 고인호
	 * 
	 * 댓글 좋아요 삭제
	 * 
	 * @param replyno - 삭제할 댓글 번호
	 */
	public void deleteReLike(int replyno);

	/**
	 * 2020-01-10
	 * 고인호
	 * 
	 * 댓글 리스트 조회
	 * 
	 * @param reply - 게시판 번호가 담긴 객체
	 * @return List - 댓글 리스트
	 */
	public List<Reply> getReplyByboardNo(Reply reply);

	/**
	 * 2020-01-10
	 * 고인호
	 * 
	 * 댓글 삭제(게시판 삭제될때)
	 * 
	 * @param boardno - 게시판 번호가 담긴 객체
	 */
	public void deleteReplyToBoard(int boardno);

	/**
	 * 2020-01-13
	 * 고인호
	 * 
	 * 게시글번호로 유저번호 가져오기
	 * 
	 * @param boardno - 게시글번호가 담긴 객체
	 * @return 
	 */
	public Alram getUserno(int boardno);

	/**
	 * 2020-01-13
	 * 고인호
	 * 
	 * 알람 삽입
	 * 
	 * @param alram - 알람에 등록될 정보가 담긴 객체
	 */
	public void insertReplyAlram(Alram alram);

	/**
	 * 2020-01-13
	 * 고인호
	 * 
	 * 댓글 알람 삽입
	 * 
	 * @param alram - 알람에 등록될 정보가 담긴 객체
	 */
	public void insertRecommendAlram(Alram alram);

	/**
	 * 2020-01-14
	 * 고인호
	 * 
	 * 댓글 알람 삭제
	 * 
	 * @param reply - 삭제할 알람 정보가 담긴 객체
	 */
	public void deleteAlramReply(Reply reply);

	/**
	 * 2020-01-14
	 * 고인호
	 * 
	 * 답글 삭제(댓글 삭제할때)
	 *  
	 * @param groupNo - 삭제될 답글의 그룹 번호
	 */
	public void deleteReReplyByGroupNo(int groupNo);

	/**
	 * 2020-01-14
	 * 고인호
	 * 
	 * 좋아요 번호 구하기
	 * 
	 * @param prBoard - PR게시판 정보가 담긴 객체
	 * @return int - 좋아요 번호
	 */
	public int getLikeNo(FreeBoard freeBoard);

	/**
	 * 2020-01-14
	 * 고인호
	 * 
	 * 좋아요 알람 삭제
	 * 
	 * @param reply - 삭제할 알람 정보가 담긴 객체
	 */
	public void deleteLikeAlram(int likeno);

	/**
	 * 2020-01-14
	 * 고인호
	 * 
	 * 
	 * @param replyno
	 * @return
	 */
	public int getUsernoByReplyNo(int replyno);

	/**
	 * 2020-01-14
	 * 고인호
	 * 
	 * 답글 알람 삽입
	 * 
	 * @param alram - 알람에 등록될 정보가 담긴 객체
	 */
	public void insertReReplyAlram(Alram alram);

}
