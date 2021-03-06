package board.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.controller.FreeWriteController;
import board.dao.face.FreeBoardDao;
import board.dto.Alram;
import board.dto.FreeBoard;
import board.dto.Reply;
import board.dto.UpFile;
import board.service.face.FreeBoardService;
import user.dto.User_table;
import util.Paging;


@Service
public class FreeBoardServiceImpl implements FreeBoardService {
	
	private static final Logger logger = LoggerFactory.getLogger(FreeWriteController.class);
	
	@Autowired private FreeBoardDao freeboardDao;
	@Autowired ServletContext context;
	
	@Override
	public List<FreeBoard> getList(Paging paging) {
		
		return freeboardDao.selectAll(paging);
	}

	@Override
	public int getListCnt(Map<String, String> map) {
		
		return freeboardDao.selectCnt(map);
	}

	@Override
	public FreeBoard freeDetail(int boardno) {
		
		return freeboardDao.selectFreeDetail(boardno);
	}

	@Override
	public void writeFree(FreeBoard freeboard) {
		
		freeboardDao.insertFreeBoard(freeboard);
		
	}

	@Override
	public void increaseViews(int boardno) {
		
		freeboardDao.updateViews(boardno);
		
	}

	@Override
	public FreeBoard getUserNoByNick(Object attribute) {
		
		return freeboardDao.selectByUserNick(attribute);
	}

	@Override
	public List<FreeBoard> getViewsList() {
		
		return freeboardDao.selectViewsAll();
	}

	@Override
	public void freeDelete(int boardno) {
		
		freeboardDao.deleteFreeBoard(boardno);
		
	}

	@Override
	public void updateFreeBoard(FreeBoard freeboard) {

		freeboardDao.updateFreeBoard(freeboard);
		
	}

	@Override
	public void filesave(UpFile upfile, int boardno) {
		
		//파일이 저장될 경로
		String storedPath = context.getRealPath("upload");

		//UUID
		String uid = UUID.randomUUID().toString().split("-")[4];

		//저장될 파일의 이름 (원본명 + UUID)
		String filename = upfile.getFile().getOriginalFilename()+"_"+uid;
		

		//저장될 파일 객체
		File dest = new File(storedPath, filename);

		try {
			upfile.getFile().transferTo(dest); //실제 파일 저장
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		// DB에 저장 (업로드된 파일의 정보를 기록)
		upfile.setOriginname(upfile.getFile().getOriginalFilename());
		upfile.setStoredname(filename);
		upfile.setFilesize(upfile.getFile().getSize());
		upfile.setBoardno(boardno);
		logger.info(upfile.toString());
		
		freeboardDao.insertFile(upfile);

	}

	@Override
	public UpFile getFile(int boardno) {
		
		return freeboardDao.selectFile(boardno);
	}

	@Override
	public UpFile getFileNo(int fileno) {
		
		return freeboardDao.selectFileNo(fileno);
	}

	@Override
	public void fileDelete(UpFile fileinfo) {
		
		logger.info(fileinfo.toString());
		
		File file = new File(context.getRealPath("upload\\"+fileinfo.getStoredname()));
		
		// 삭제할 파일의 경로
		if(file.exists() == true){
		file.delete();
		}	
		freeboardDao.deleteFile(fileinfo);
		
	}

	@Override
	public int recommendCheck(FreeBoard freeBoard) {
		int check = freeboardDao.selectRecommend(freeBoard);

		//전에 추천한적이 없다면
		if(check == 0) {
			
			return check; //추천
		}
		else {
			return check; //추천 취소
		}
	}

	@Override
	public void recommend(FreeBoard freeBoard) {

		freeboardDao.insertRecommend(freeBoard);
		
	}

	@Override
	public void recommendCancal(FreeBoard freeBoard) {

		freeboardDao.deleteRecommend(freeBoard);
		
	}

	@Override
	public int recommendView(FreeBoard freeBoard) {
		
		return freeboardDao.selectrecommendView(freeBoard);
	}

	@Override
	public void deleteBlike(int boardno) {

		freeboardDao.deleteBlike(boardno);
		
	}

	@Override
	public void insertReply(Reply reply) {
		
		freeboardDao.insertReply(reply);
		
	}

	@Override
	public List<Reply> getReplyList(int boardno) {
		
		return freeboardDao.selectReply(boardno);
		
	}

	@Override
	public void deleteReply(Reply reply) {
		
		freeboardDao.deleteReply(reply);
			
	}

	@Override
	public int getREreplyCnt(int groupno) {
		
		return freeboardDao.selectREreplyCnt(groupno);
	}

	@Override
	public int getGroupNoByReplyNo(Reply reply) {
		
		return freeboardDao.selectGroupNo(reply);
	}

	@Override
	public void updateReplyByNo(Reply reply) {
		
		freeboardDao.updateReplyByNo(reply);
		
	}

	@Override
	public List<Reply> getReReplyByNo(int groupNo) {
		
		return freeboardDao.selectReReplyList(groupNo);
	}

	@Override
	public int getMaxReplyOrder(Reply reply) {
		
		return freeboardDao.selectMaxReplyOrder(reply);
	}

	@Override
	public void addReReply(Reply reply) {

		freeboardDao.insertReReply(reply);
		
	}

	@Override
	public Reply getUserNoForReplyLike(String usernick) {
		
		return freeboardDao.selectUserNoToReplyLike(usernick);
	}

	@Override
	public int replyRecommendCheck(Reply reply) {
		
		int check = freeboardDao.selectReplyRecommend(reply);

		//전에 추천한적이 없다면
		if(check == 0) {
			
			return check; //추천
		}
		else {
			return check; //추천 취소
		}
	}

	@Override
	public int replyRecommendView(Reply reply) {
		
		return freeboardDao.selectReplyRecommendView(reply);
	}

	@Override
	public void replyRecommend(Reply reply) {
		
		freeboardDao.insertReplyRecommend(reply);
		
	}

	@Override
	public void replyRecommendCancal(Reply reply) {
		
		freeboardDao.deleteReLike(reply);
		
	}

	@Override
	public List<Reply> getBestReplyByboardNo(Reply reply) {
		
		return freeboardDao.selectBestReplyList(reply);
	}

	@Override
	public List<Reply> getMostReplyByboardNo(Reply reply) {
		
		return freeboardDao.selectMostReplyList(reply);
	}

	@Override
	public void deleteReLike(int replyno) {
		
		freeboardDao.deleteReLikeForBoard(replyno);
		
	}

	@Override
	public List<Reply> getReplyByboardNo(Reply reply) {
		
		return freeboardDao.selectReplyList(reply);
	}

	@Override
	public void deleteReplyToBoard(int boardno) {
		
		freeboardDao.deleteReplyToBoard(boardno);
		
	}

	@Override
	public Alram getUserno(int boardno) {

		return freeboardDao.selectByUserNo(boardno);
		
	}

	@Override
	public void insertReplyAlram(Alram alram) {
		
		freeboardDao.insertReplyAlram(alram);
		
	}

	@Override
	public void insertRecommendAlram(Alram alram) {
		
		freeboardDao.insertRecommendAlram(alram);
		
	}

	@Override
	public void deleteAlramReply(Reply reply) {
		
		freeboardDao.deleteAlramReply(reply);
		
	}

	@Override
	public void deleteReReplyByGroupNo(int groupNo) {
		
		freeboardDao.deleteReReplyByGroupNo(groupNo);
		
	}

	@Override
	public int getLikeNo(FreeBoard freeBoard) {
		
		return freeboardDao.selectLikeNo(freeBoard);
	}

	@Override
	public void deleteLikeAlram(int likeno) {
		
		freeboardDao.deleteLikeAlram(likeno);
		
	}

	@Override
	public int getUsernoByReplyNo(int replyno) {
		
		return freeboardDao.selectUserNoByReplyNo(replyno);
	}

	@Override
	public void insertReReplyAlram(Alram alram) {
		
		freeboardDao.insertReReplyAlram(alram);
		
	}
	

	


}
