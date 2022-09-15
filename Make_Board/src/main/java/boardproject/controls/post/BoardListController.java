package boardproject.controls.post;

import java.util.Map;
import java.util.List;
import boardproject.dao.PostDao;
import boardproject.annotation.Component;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;
import boardproject.vo.PostVO;

@Component("/board/list.do")
public class BoardListController implements Controller, DataBinding {
	
	PostDao postDao;
	public BoardListController setPostDao(PostDao postDao) {
		this.postDao = postDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"pageNum", String.class,
				"column", String.class,
				"search", String.class				
		};
	}
	
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		// 현재 페이지 (입력 없으면 1페이지)
		int currentPage;
		if(model.get("pageNum") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt((String)model.get("pageNum"));
		}
		model.put("pageNum", currentPage);
		
		
		// 한 페이지에 보여줄 게시물 갯수
		model.put("pageSize", 10);
		
		// 검색이 있는지 확인
		if (model.get("search") == null || model.get("search") == "") { // 검색 없음
			// 게시글 리스트
			List<PostVO> postList = postDao.selectList();
			model.put("postVOs", postList);
			
			// 총 게시글 갯수
			int cntPost = 0;
			for (PostVO postVO : postList) {
				cntPost++;
			}
			model.put("cntPost", cntPost);
			
		} else { // 검색 있음
			List<PostVO> postList = 
					postDao.selectList(
							(String)model.get("column"),
							(String)model.get("search"));
			model.put("postVOs", postList);
			
			// 총 게시글 갯수
			int cntPost = 0;
			for (PostVO postVO : postList) {
				cntPost++;
			}
			model.put("cntPost", cntPost);
			
		}
		
		
		return "/board/postList.jsp";
	}
}
