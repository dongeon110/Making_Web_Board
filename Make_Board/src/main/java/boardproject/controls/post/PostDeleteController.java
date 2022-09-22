package boardproject.controls.post;

import java.util.Map;

import boardproject.vo.PostVO;
import boardproject.vo.User;
import boardproject.dao.UserDao;
import boardproject.dao.PostDao;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;
import boardproject.annotation.Component;

import javax.servlet.http.HttpSession;

@Component("/board/delete.do")
public class PostDeleteController implements Controller, DataBinding {
	
	PostDao postDao;
	UserDao userDao;
	public PostDeleteController setPostDao(PostDao postDao) {
		this.postDao = postDao;
		return this;
	}
	
	public PostDeleteController setUserDao(UserDao userDao) {
		this.userDao = userDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class,
				"password", String.class
		};
	}
	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		// 로그인 정보
		HttpSession session = (HttpSession) model.get("session");
		User loginUser = (User) session.getAttribute("loginUser");
		
		// 게시글 정보
		PostVO postVO = postDao.selectOne((int)model.get("no"));
		Integer postUserNo = postVO.getPostUserNo();
		
		
		boolean isLogin = (loginUser != null); // 로그인 여부? T/F
		boolean isAdmin = isLogin && loginUser.getGrade() == 1; // 로그인이 관리자?
		boolean isUserPost = (postUserNo != null && postUserNo != 0); // User작성 여부? T/F
		
		// 관리자는 항상 가능
		if (isAdmin) {
			postDao.delete(postVO.getPostNo());
			return "redirect:list.do";

		// 비로그인은 User작성글을 삭제 불가능
		} else if (!isLogin && isUserPost) { 
			return "/auth/NotWriter.jsp";
			
		// 로그인 + User작성글
		} else if (isLogin && isUserPost) { 
			// 작성자 일치 가능
			if (loginUser.getUserNo() == postVO.getPostUserNo()) { 
				postDao.delete(postVO.getPostNo());
				return "redirect:list.do";
			// 작성자 불일치 불가능
			} else {
				return "/auth/NotWriter.jsp";
			}
		
		// 비로그인 작성글이면 비밀번호 확인할 것
		} else {
			if(model.get("password") == null) { // GET
				return "/auth/CheckDeletePw.jsp";
			} else { // POST
				PostVO checkPostVO = new PostVO()
						.setPostNo((int)model.get("no"))
						.setPostPassword((String)model.get("password"));
				
				// 비밀번호 확인
				if (postDao.checkPw(checkPostVO)) { // 확인
					postDao.delete(checkPostVO.getPostNo());
					return "redirect:list.do";
				} else { // 실패
					return "/auth/CheckPwFail.jsp";
				}
			}
		}
		
	}
}
