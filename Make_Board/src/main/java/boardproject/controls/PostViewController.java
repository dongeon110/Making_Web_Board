package boardproject.controls;

import java.util.Map;

import boardproject.dao.PostDao;
import boardproject.bind.DataBinding;
import boardproject.vo.PostVO;
import boardproject.annotation.Component;

@Component("/board/view.do")
public class PostViewController implements Controller, DataBinding {
	PostDao postDao;
	
	public PostViewController setPostDao(PostDao postDao) {
		this.postDao = postDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("postVO", postDao.selectOne((int)model.get("no")));
		return "/board/postView.jsp";
	}
}
