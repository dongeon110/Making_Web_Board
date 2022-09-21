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

@Component("/board/repost.do")
public class RepostController implements Controller, DataBinding {
	PostDao postDao;
	UserDao userDao;
	public RepostController setPostDao(PostDao postDao) {
		this.postDao = postDao;
		return this;
	}
	
	public RepostController setUserDao(UserDao userDao) {
		this.userDao = userDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class,
				"postVO", boardproject.vo.PostVO.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		PostVO postVO = (PostVO) model.get("postVO");
		
		HttpSession session = (HttpSession) model.get("session");
		// loginUser가 있는지
		User loginUser = (User) session.getAttribute("loginUser");
		
		if (postVO.getPostSubject() == null) {
			return "/board/postRepost.jsp";
		} else {
			if(loginUser != null && loginUser.getUserNo() != 0) {
				postDao.repostbyUser(postVO
						.setPostSubject(postVO.getPostSubject())
						.setRepost((int)model.get("no"))
						.setPostUserNo(loginUser.getUserNo()));
			} else {
				postDao.insertRepost(postVO
						.setPostSubject(postVO.getPostSubject())
						.setRepost((int)model.get("no")));
			}
			return "redirect:list.do";
		}
	}
	
}
