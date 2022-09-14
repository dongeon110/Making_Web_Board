package boardproject.controls;

import java.util.Map;

import boardproject.annotation.Component;
import boardproject.bind.DataBinding;
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
		
		if (model.get("password") == null // GET
				&& postVO.getPostPassword() == null) {
			return "/auth/CheckPostPw.jsp";
		} else if (postVO.getPostPassword() == null){ // POST
			PostVO checkPostVO = new PostVO()
					.setPostNo((Integer)model.get("no"))
					.setPostPassword((String)model.get("password"));
			
			if (postDao.checkPw(checkPostVO)) {
				postVO = postDao.selectOne((int)model.get("no"));
				model.put("postVO", postVO);
				return "/board/postUpdate.jsp";
			} else {
				return "/auth/CheckPwFail.jsp";
			}
		} else {
			postDao.update(postVO);
			return "redirect:list.do";
		}
	}
}
