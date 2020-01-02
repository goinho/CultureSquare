package prboard.dao.face;

import java.util.List;
import java.util.Map;

import prboard.dto.PRBoard;
import prboard.dto.PRType;
import prboard.dto.UpFile;
import util.PRPaging;

public interface PRBoardDao {
	
	/**
	 * 2019-12-30
	 * 조홍철
	 * 
	 * PR 게시글 작성
	 * 
	 * @param prBoard - 작성 내용이 담긴 객체
	 */
	public void insertPR(PRBoard prBoard);
	
	/**
	 * 2019-12-30
	 * 조홍철
	 * 
	 * 유저 닉네임을 통한 유저번혹 가져오기
	 * 
	 * @param userNick - 유저 닉네임
	 * @return int - 유저 번호
	 */
	public int selectUserNoByUserNick(String userNick);
	
	/**
	 * 2019-12-30
	 * 조홍철
	 * 
	 * PRType 테이블 데이터 삽입
	 * 
	 * @param prType - prType 내용이 담긴 객체
	 */
	public void insertPRType(PRType prType);
	
	/**
	 * 2019-12-30
	 * 조홍철
	 * 
	 * 첨부파일 삽입
	 * 
	 * @param upFile - 첨부파일 데이터가 들어있는 객체
	 */
	public void insertFile(UpFile upFile);
	
	/**
	 * 2019-12-30
	 * 조홍철
	 * 
	 * PRWRITEDATE를 현재시간으로 업데이트
	 * 
	 * @param userNo - 유저 번호
	 */
	public void updateWritePrDate(int userNo);
	
	/**
	 * 2019-12-30
	 * 조홍철
	 * 
	 * prwritedate를 유저번호로 가져오기
	 * 
	 * @param userNo - 유저 번호
	 * @return String - prwritedate
	 */
	public String selectWriteDate(int userNo);
	
	/**
	 * 2019-12-30
	 * 조홍철
	 * 
	 * 글 작성시간과 현재시간 차이를 구한다
	 * 
	 * @param writeDate - 사용자가 최근에 작성한 시간
	 * @return int - 현재 시간과의 차이
	 */
	public int selectTimePass(String writeDate);
	
	/**
	 * 2019-12-30
	 * 조홍철
	 * 
	 * PR 게시글 리스트를 구한다.
	 * 
	 * @param paging - 페이징 객체
	 * @return List - PR 게시글 리스트
	 */
	public List selectAll(PRPaging paging);
	
	/**
	 * 2019-12-31
	 * 조홍철
	 * 
	 * PR 게시글 갯수를 구한다
	 * 
	 * @param map - 검색 조건 담긴 맵
	 * @return int - PR 게시글 갯수
	 */
	public int selectCntAll(Map<String, String> map);
	
	/**
	 * 2019-12-31
	 * 조홍철
	 * 
	 * 게시글 세부정보 조회
	 * 
	 * @param boardno - 게시글 번호
	 * @return PRBoard - 게시글 세부정보가 담긴 객체
	 */
	public PRBoard selectViewInfo(int boardno);
	
	/**
	 * 2019-12-31
	 * 조홍철
	 * 
	 * 조회수를 1 증가시킨다.
	 * 
	 * @param boardno - 게시글 번호
	 */
	public void hit(int boardno);
	
	/**
	 * 2019-12-31
	 * 조홍철
	 * 
	 * 게시글에 번호에 해당하는 파일 리스트를 가져온다.
	 * 
	 * boardno - 게시글 번호
	 * @return List - 파일 리스트
	 */
	public List<UpFile> selectFileList(int boardno);
	
	/**
	 * 2019-12-31
	 * 조홍철
	 * 
	 * 파일정보를 가져온다.
	 * 
	 * @param fileno - 사용자가 클릭한 파일의 번호
	 * @return UpFile - 파일정보가 담긴 객체
	 */
	public UpFile selectFileByFileno(int fileno);
	
	/**
	 * 2020-01-01
	 * 조홍철
	 * 
	 * PR 게시판 내용을 수정한다
	 * 
	 * @param prBoard - 수정 내용이 담긴 객체
	 */
	public void updatePR(PRBoard prBoard);
	
	/**
	 * 2020-01-01
	 * 조홍철
	 * 
	 * PR 게시판 유형을 수정한다
	 * 
	 * @param prBoard - 수정 내용이 담긴 객체
	 */
	public void updatePRType(PRBoard prBoard);
	
	/**
	 * 2020-01-01
	 * 조홍철
	 * 
	 * PR 게시판 첨부파일을 삭제한다
	 * 
	 * @param boardno - 게시글 번호
	 */
	public void deleteFile(int boardno);
	
	/**
	 * 2020-01-01
	 * 조홍철
	 * 
	 * PR 유형 삭제
	 * 
	 * @param prBoard - 삭제할 게시글 정보가 담긴 객체
	 */
	public void deletePRType(PRBoard prBoard);
	
	/**
	 * 2020-01-01
	 * 조홍철
	 * 
	 * 게시글 삭제
	 * 
	 * @param prBoard - 삭제할 게시글 정보가 담긴 객체
	 */
	public void deletePR(PRBoard prBoard);
}
