package boardproject.controls.post;

import java.util.Map;

import boardproject.dao.PostDao;
import boardproject.vo.PostVO;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;
import boardproject.annotation.Component;

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
		
		// 제목이 null이면 GET요청
		if (postVO.getPostSubject() == null) {
			return "/board/postAdd.jsp";
		} else { // POST요청
			if (postVO.getPostSubject() == "" || postVO.getPostPassword() == ""
					|| postVO.getPostText() == "" || postVO.getPosterName() == "") {
				return "/auth/CheckNull.jsp";
			}
			postDao.insert(postVO);
			
			return "redirect:list.do";
		}
	}
}
