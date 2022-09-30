package boardproject.controls.post;

import java.util.Map;

import boardproject.dao.PostDao;
import boardproject.dao.UserDao;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;
import boardproject.vo.PostVO;
import boardproject.vo.User;
import boardproject.annotation.Component;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@Component("/board/view.do")
public class PostViewController implements Controller, DataBinding {
	PostDao postDao;
	UserDao userDao;
	public PostViewController setPostDao(PostDao postDao) {
		this.postDao = postDao;
		return this;
	}
	
	public PostViewController setUserDao(UserDao userDao) {
		this.userDao = userDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		int no = (int)model.get("no");
		Cookie[] cookies = (Cookie[])model.get("cookies");
		Cookie cookie = null;
		String viewNo = "view" + Integer.toString(no); // no에 맞는 viewNo 문자열
		HttpSession session = (HttpSession) model.get("session");
		User loginUser = (User) session.getAttribute("loginUser");
		String loginUserNo;
		if (loginUser == null) {
			loginUserNo = "nologin";
		} else {
			loginUserNo = Integer.toString(loginUser.getUserNo());
		}
		
		Cookie[] newCookies = new Cookie[cookies.length + 1];
		for(int index=0; index<cookies.length; index++) {
			newCookies[index] = cookies[index];
		}
		
		
		for(Cookie c: cookies) {
			if(c.getName().equals(viewNo)) { // viewNo 쿠키 찾기
				cookie = c;
			}
		}
		
		if(cookie == null) { // viewNo 쿠키 없으면 만들고 조회수 +1
			cookie = new Cookie(viewNo, loginUserNo);
			postDao.increaseViews(no); // 조회수 1 증가
			cookie.setMaxAge(60*60); // 1시간
			newCookies[cookies.length] = cookie;
			model.put("cookies", newCookies);
		} else {
			String cookieValue = cookie.getValue();
			if (!cookieValue.equals(loginUserNo)) {
				postDao.increaseViews(no); // 조회수 1 증가
			}
			cookie.setValue(loginUserNo);
		}
		
		
		
//		postDao.increaseViews(no); // 조회수 1 증가
		PostVO postVO = postDao.selectOne((int)model.get("no"));
		
		// 작성자 User 있으면 User정보
		Integer userNo = postVO.getPostUserNo();
		if (userNo != null && userNo != 0) {
			User user = userDao.selectOne(userNo);
			postVO.setPosterName(user.getUserName()); // PosterName 에 UserName 저장
			postVO.setPostPassword(Integer.toString(user.getGrade())); // Password에 Grade 저장
		}
		
		model.put("postVO", postVO);
		return "/board/postView.jsp";
	}
}
