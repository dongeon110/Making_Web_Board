package boardproject.controls.post;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
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
		
		HttpSession session = (HttpSession) model.get("session");
		// 현재 페이지 (입력 없으면 1페이지) + 세션 초기화
		int currentPage;
		int pageSize = 10; // 한페이지 게시물 수 초기값
		if(model.get("pageNum") == null) {
			currentPage = 1;
			// 세션 초기화
			session.setAttribute("search", "");
		} else {
			currentPage = Integer.parseInt((String)model.get("pageNum"));
		}
		model.put("pageNum", currentPage);
		
		// pageSize 입력 있으면 입력값 설정
		if (model.get("pageSize") != null && model.get("pageSize") != "") {
			pageSize = Integer.parseInt((String)model.get("pageSize"));
		}
		session.setAttribute("pageSize", pageSize);
		
		
		
		
		
		// 한 페이지에 보여줄 게시물 갯수
		model.put("pageSize", pageSize);
		
		List<PostVO> postList;
		// 검색이 있는지 확인
		if (model.get("search") == null || model.get("search") == "") { // 검색 없음

		} else { // 검색 있음
			
			session.setAttribute("column", (String)model.get("column"));
			session.setAttribute("search", (String)model.get("search"));
			
		}
		
		
		// 세션에 검색어 있으면 model.put
		if (session.getAttribute("search") != null && session.getAttribute("search") != "") {
			postList =
					postDao.searchList(
							(String)session.getAttribute("column"),
							(String)session.getAttribute("search"));
			model.put("postVOs", postList);
		} else { // 검색어 없음
			postList = postDao.selectList();
			model.put("postVOs", postList);
		}
		
		// 총 게시글 갯수
		int cntPost = 0;
		for (PostVO postVO : postList) {
			cntPost++;
		}
		model.put("cntPost", cntPost);
		
		return "/board/postList.jsp";
	}
}
