package boardproject.controls;

import java.util.Map;
import boardproject.dao.PostDao;
import boardproject.annotation.Component;
import boardproject.bind.DataBinding;

@Component("/board/list.do")
public class BoardListController implements Controller, DataBinding {
	
	PostDao postDao;
	public BoardListController setPostDao(PostDao postDao) {
		this.postDao = postDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"pageNum", String.class
		};
	}
	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		int cntPost = postDao.countPost();
		
		int currentPage;
		if(model.get("pageNum") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt((String)model.get("pageNum"));
		}
		
		// 한 페이지에 볼 수 있는 게시글 갯수
		int pageSize = 10;
		
		// 총 페이지 수
		int cntPage = cntPost / pageSize + (cntPost%pageSize==0? 0:1);
		
		// 한 화면에 보여줄 페이지 블록 수
		int pageBlock = 5;
		
		// 블록 시작과 끝
		int startPage = ((currentPage-1)/pageBlock)*pageBlock +1;
		int endPage = startPage + pageBlock - 1;
		if (endPage>cntPage) {endPage = cntPage;}
		
		int startRow = (currentPage-1) * pageSize + 1;
		
		model.put("startPage", startPage);
		model.put("endPage", endPage);
		model.put("cntPage", cntPage);
		model.put("postVOs", postDao.selectList(pageSize, startRow));
		
		return "/board/postList.jsp";
	}
}
