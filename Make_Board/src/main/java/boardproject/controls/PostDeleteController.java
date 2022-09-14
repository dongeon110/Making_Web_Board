package boardproject.controls;

import java.util.Map;

import boardproject.vo.PostVO;
import boardproject.dao.PostDao;
import boardproject.bind.DataBinding;
import boardproject.annotation.Component;

@Component("/board/delete.do")
public class PostDeleteController implements Controller, DataBinding {
	
	PostDao postDao;
	
	public PostDeleteController setPostDao(PostDao postDao) {
		this.postDao = postDao;
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
