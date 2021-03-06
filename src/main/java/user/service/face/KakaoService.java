package user.service.face;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.springframework.social.oauth2.OAuth2Parameters;

import user.dto.User_table;

public interface KakaoService {
	
	/**
	 * 2019-12-23
	 * 조홍철
	 * 
	 * 인증 URL 얻기
	 * 
	 * @param session - 세션객체
	 * @return String - 인증 URL
	 */
	public String getAuthorizationUrl(HttpSession session);
	
	/**
	 * 2019-12-23
	 * 조홍철
	 * 
	 * 인증 토큰 얻기
	 * 
	 * @param autorize_code - 인증 코드
	 * @return JsonNode - 엑세스 토큰이 담긴 객체
	 */
	public JsonNode getAccessToken(String autorize_code);
	
	/**
	 * 2019-12-23
	 * 조홍철
	 * 
	 * 유저 정보 얻기
	 * 
	 * @param accessToken - 유저정보 얻기 위한 엑세스 토큰
	 * @return JsonNode - 유저정보가 담긴 객체
	 */
	public JsonNode getKakaoUserInfo(JsonNode accessToken);
	
	/**
	 * 2019-12-23
	 * 조홍철
	 * 
	 * 카카오 로그아웃
	 * 
	 * @param autorize_code - 인증 코드
	 * @return JsonNode - 유저 정보가 담긴 객체
	 */
	public JsonNode Logout(String autorize_code);
	
	/**
	 * 2019-12-24
	 * 조홍철
	 * 
	 * 카카오 로그인 아이디가 존재하는지 검사한다.
	 * 
	 * @param user - 소셜로그인 정보가 담긴 객체
	 * @return  int - 소셜로그인 정보 갯수
	 *  	
	 */
	public int getSocialAccountCnt(User_table user);
	
	
	/**
	 * 2019-12-24
	 * 조홍철
	 * 
	 * 카카오 로그인 시도시, User테이블에 정보가 없으면 데이터 삽입(회원가입과 유사)
	 * 
	 * @param user - 소셜로그인 정보가 담긴 객체
	 */
	public void insertKakaoInfo(User_table user);
	
	/**
	 * 2019-12-24
	 * 조홍철
	 * 
	 * 카카오 로그인 성공시, 필요한 데이터들을 설정해준다.
	 * 
	 * @param code - 카카오 로그인 인증코드
	 * @param session - 세션 설정위한 객체
	 */
	public void setKakaoLogin(String code, HttpSession session);
	
	/**
	 * 2019-12-29
	 * 조홍철
	 * 
	 * 카카오 로그인 시도시, 소셜 테이블에 데이터 삽입
	 * 
	 * @param user - 소셜로그인 닉네임이 담긴 유저 객체
	 */
	public void insertKakaoSocial(User_table socialuser);
	
	/**
	 * 2019-12-29
	 * 조홍철
	 * 
	 * 소셜로그인 한 사용자의 유저번호를 가져온다
	 * 
	 * @param user - 소셜로그인 닉네임
	 * @return int - 유저 번호
	 */
	public int getUserNo(String socialnick);
}
