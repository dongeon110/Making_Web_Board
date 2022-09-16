package boardproject.controls.post;

import java.util.Map;

import boardproject.annotation.Component;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;
import boardproject.dao.PostDao;
import boardproject.vo.PostVO;

@Component("/board/repost.do")
public class RepostController implements Controller, DataBinding {
	PostDao postDao;
	
	public RepostController setPostDao(PostDao postDao) {
		this.postDao = postDao;
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
		
		if (postVO.getPostSubject() == null) {
			return "/board/postRepost.jsp";
		} else {
			postDao.insertRepost(postVO
					.setPostSubject(postVO.getPostSubject())
					.setRepost((int)model.get("no")));
			return "redirect:list.do";
		}
	}
	
}
