package boardproject.controls.post;

import java.util.Map;

import boardproject.dao.PostDao;
import boardproject.vo.User;
import boardproject.vo.PostVO;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;
import boardproject.annotation.Component;

import javax.servlet.http.HttpSession; 

@Component("/board/add.do")
public class PostAddController implements Controller, DataBinding {
	PostDao postDao;
	
	public PostAddController setPostDao(PostDao postDao) {
		this.postDao = postDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object [] {
				"postVO", boardproject.vo.PostVO.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		PostVO postVO = (PostVO) model.get("postVO");
		
		HttpSession session = (HttpSession) model.get("session");
		// loginUser가 있는지
		User loginUser = (User) session.getAttribute("loginUser");
		
		
		// 제목이 null이면 GET요청
		if (postVO.getPostSubject() == null) {
			return "/board/postAdd.jsp";
		} else { // POST요청
			if (postVO.getPostSubject() == "" || postVO.getPostPassword() == ""
					|| postVO.getPostText() == "" || postVO.getPosterName() == "") {
				return "/auth/CheckNull.jsp";
			}
			
			if (loginUser != null && loginUser.getUserNo() != 0) {
				postDao.insertbyUser(postVO.setPostUserNo(loginUser.getUserNo()));
			} else {
				postDao.insert(postVO);
			}
			return "redirect:list.do";
		}
	}
}
