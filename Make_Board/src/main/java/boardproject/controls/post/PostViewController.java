package boardproject.controls.post;

import java.util.Map;

import boardproject.dao.PostDao;
import boardproject.dao.UserDao;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;
import boardproject.vo.PostVO;
import boardproject.vo.User;
import boardproject.annotation.Component;

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
		
		postDao.increaseViews((int)model.get("no")); // 조회수 1 증가
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
