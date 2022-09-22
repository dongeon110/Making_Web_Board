package boardproject.controls.post;

import java.util.Map;

import boardproject.annotation.Component;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;
import boardproject.dao.PostDao;
import boardproject.dao.UserDao;
import boardproject.vo.PostVO;
import boardproject.vo.User;

import javax.servlet.http.HttpSession;

@Component("/board/update.do")
public class PostUpdateController implements Controller, DataBinding {
	PostDao postDao;
	UserDao userDao;
	public PostUpdateController setPostDao(PostDao postDao) {
		this.postDao = postDao;
		return this;
	}
	public PostUpdateController setUserDao(UserDao userDao) {
		this.userDao = userDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class,
				"password", String.class,
				"postVO", boardproject.vo.PostVO.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		PostVO postVO = (PostVO) model.get("postVO");
		
		// 로그인 정보
		HttpSession session = (HttpSession) model.get("session");
		User loginUser = (User) session.getAttribute("loginUser");
		
		// 게시글 기존 정보
		PostVO beforePostVO = postDao.selectOne((int)model.get("no"));
		Integer postUserNo = beforePostVO.getPostUserNo();
		
		boolean isLogin = (loginUser != null); // 로그인 여부
		boolean isAdmin = isLogin && loginUser.getGrade() == 1; // 관리자 여부
		boolean isUserPost = (postUserNo != null && postUserNo != 0); // User 작성 여부
		
		boolean isGET = (postVO.getPostText() == null);
		
		// 관리자는 항상 수정 가능
		if (isAdmin) {
			if (isGET) {
				model.put("postVO", beforePostVO);
				return "/board/postUpdate.jsp";
			} else {
				// 빈 값 확인
				if (postVO.getPostSubject().trim() == "" ||postVO.getPostText().trim() == "") {
					return "/auth/CheckNull.jsp";
				} else {
					postDao.updatebyUser(postVO.setPostUserNo(loginUser.getUserNo()));
					return "redirect:list.do";
				}
			}
		
		// 비로그인은 User작성글 수정 불가
		} else if (!isLogin && isUserPost) {
			return "/auth/NotWriter.jsp";
		
		// 로그인 + User작성글
		} else if (isLogin && isUserPost) {
			// 작성자 일치
			if (loginUser.getUserNo() == beforePostVO.getPostUserNo()) {
				if (isGET) {
					model.put("postVO", beforePostVO);
					return "/board/postUpdate.jsp";
				} else {
					// 빈 값 확인
					if (postVO.getPostSubject().trim() == "" ||postVO.getPostText().trim() == "") {
						return "/auth/CheckNull.jsp";
					} else {
						postDao.updatebyUser(postVO.setPostUserNo(loginUser.getUserNo()));
						return "redirect:list.do";
					}
				}
				
			// 작성자 불일치
			} else {
				return "/auth/NotWriter.jsp";
			}
			
		// 비로그인 작성글이면 비밀번호 확인
		} else {
			// GET - 비밀번호 확인 페이지
			if (model.get("password") == null
					&& isGET
					) {
				return "/auth/CheckPostPw.jsp";
				
			// POST - 비밀번호 확인
			} else if (isGET){
				PostVO checkPostVO = new PostVO()
						.setPostNo((Integer)model.get("no"))
						.setPostPassword((String)model.get("password"));
				
				// 비밀번호 확인
				if (postDao.checkPw(checkPostVO)) {
					postVO = postDao.selectOne((int)model.get("no"));
					model.put("postVO", postVO);
					return "/board/postUpdate.jsp";
				} else {
					return "/auth/CheckPwFail.jsp";
				}
				
			// POST - NULL 값 체크
			} else {
				
				if (!isLogin) {
					if (postVO.getPostPassword() == "" || postVO.getPostSubject().trim() == ""
							|| postVO.getPosterName().trim() == "" || postVO.getPostText() == "") {
						return "/auth/CheckNull.jsp";
					}	
					postDao.update(postVO);
					return "redirect:list.do";
					
				} else {
					if (postVO.getPostSubject() == "" || postVO.getPostText() == "") {
						return "/auth/CheckNull.jsp";
					}
					postDao.updatebyUser(postVO.setPostUserNo(loginUser.getUserNo()));
					return "redirect:list.do";
					
				}
				
			}
		}
		
	}
}
