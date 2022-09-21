package boardproject.controls.post;

import java.util.Map;

import boardproject.annotation.Component;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;
import boardproject.dao.PostDao;
import boardproject.vo.PostVO;

@Component("/board/update.do")
public class PostUpdateController implements Controller, DataBinding {
	PostDao postDao;
	
	public PostUpdateController setPostDao(PostDao postDao) {
		this.postDao = postDao;
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
		
		// GET - 비밀번호 확인 페이지
		if (model.get("password") == null
				&& postVO.getPostPassword() == null
				) {
			return "/auth/CheckPostPw.jsp";
			
		// POST - 비밀번호 확인
		} else if (postVO.getPostPassword() == null){
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
			if (postVO.getPostPassword() == "" || postVO.getPostSubject() == ""
					|| postVO.getPosterName() == "" || postVO.getPostText() == "") {
				return "/auth/CheckNull.jsp";
			}
			
			postDao.update(postVO);
			return "redirect:list.do";
		}
	}
}
