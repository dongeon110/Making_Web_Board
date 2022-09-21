package boardproject.controls.post;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import boardproject.dao.PostDao;
import boardproject.dao.UserDao;
import boardproject.annotation.Component;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;
import boardproject.vo.PostVO;
import boardproject.vo.User;

@Component("/board/list.do")
public class BoardListController implements Controller, DataBinding {
	
	PostDao postDao;
	UserDao userDao;
	
	public BoardListController setPostDao(PostDao postDao) {
		this.postDao = postDao;
		return this;
	}
	
	public BoardListController setUserDao(UserDao userDao) {
		this.userDao = userDao;
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
		
		
		// 검색이 있는지 확인
		if (model.get("search") == null || model.get("search") == "") { // 검색 없음

		} else { // 검색 있음
			session.setAttribute("column", (String)model.get("column"));
			session.setAttribute("search", (String)model.get("search"));
		}
		
		List<PostVO> postList;
		// 세션에 검색어 구별해서 VO 집어넣기
		if (session.getAttribute("search") != null && session.getAttribute("search") != "") {
			postList =
					postDao.searchList(
							(String)session.getAttribute("column"),
							(String)session.getAttribute("search"));
		} else { // 검색어 없음
			postList = postDao.selectList();
		}
		
		// PostVO 객체의 posterName값에 유저 아이디를 저장
		for (PostVO postVO : postList) {
			Integer userNo = postVO.getPostUserNo();
			if (userNo != null && userNo != 0) {
				User user = userDao.selectOne(userNo);
				postVO.setPosterName(user.getUserName());
				postVO.setPostPassword(Integer.toString(user.getGrade()));
			}
		}
		
		model.put("postVOs", postList);
		// 총 게시글 갯수
		int cntPost = 0;
		for (PostVO postVO : postList) {
			cntPost++;
		}
		model.put("cntPost", cntPost);
		
		return "/board/postList.jsp";
	}
}
