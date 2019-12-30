package artboard.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import artboard.dto.Board;
import artboard.service.face.PFBoardService;
import user.bo.NaverLoginBO;
import user.service.face.KakaoService;
import util.Paging;

@Controller
public class ArtboardListController {
	
	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	
	@Autowired private KakaoService kakaoService;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	/* GoogleLogin */
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;
	
	private static final Logger logger = LoggerFactory.getLogger(ArtboardListController.class);
	@Autowired PFBoardService pfboardService;
	
	@RequestMapping(value = "/artboard/list", method = RequestMethod.GET)
	public void pfList(Model model, Paging paging, HttpSession session, String cal_year, String cal_month) {
		
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		//https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		//redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		
		//카카오 인증 URL 생성
		String kakaoUrl = kakaoService.getAuthorizationUrl(session);
		
		/* 구글code 발행 */
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String googleUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		
		logger.info("네이버 URL : " + naverAuthUrl);
		logger.info("카카오 URL: " + kakaoUrl);
		logger.info("구글 URL: " + googleUrl);
		
		//네이버 
		model.addAttribute("naver_url", naverAuthUrl);
		
		//카카오
		model.addAttribute("kakao_url", kakaoUrl);
		
		//구글
		model.addAttribute("google_url", googleUrl);
		
		paging = pfboardService.getPaging(paging);
		
		model.addAttribute("paging",paging);
		
//		logger.info(paging.toString()); 
		
		String searchMonth = cal_year+cal_month;
		logger.info("searchMonth : " + searchMonth);
		List<Board> list = pfboardService.getList(searchMonth);
		
//		List<Board> list = pfboardService.getList(paging);
		    		
		model.addAttribute("list", list);
		
//		System.out.println(list.toString());
		
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat ( "MM");
				
		Date time = new Date();
				
		String nowYear = format1.format(time);
		String nowMonth = format2.format(time);
				
		model.addAttribute("nowYear",cal_year);
		model.addAttribute("nowMonth",cal_month);
//		return "artboard/list?bo_table=calendar&cal_year="+time1+"&cal_month="+time2;
	}
	
	

}
