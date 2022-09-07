package boardproject.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;

import java.io.IOException;

import boardproject.vo.PostVO;
import boardproject.dao.PostDao;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			 ServletContext sc = this.getServletContext();
			 PostDao postDao = (PostDao)sc.getAttribute("postDao");
			 
			 // 페이징 처리
			 int cntPost = postDao.countPost();
			 int currentPage;
			 if(request.getParameter("pageNum") == null) {
				 currentPage = 1;
			 } else {
				 currentPage = Integer.parseInt(request.getParameter("pageNum"));
			 }
			 int pageSize = 5;

			 // 총 페이지 수
			 int cntPage = cntPost / pageSize + (cntPost%pageSize==0? 0:1);
			 
			 // 한 화면에 보여줄 페이지 블록 수
			 int pageBlock = 5;
			 // 블록 시작과 끝
			 int startPage = ((currentPage-1)/pageBlock)*pageBlock + 1;
			 int endPage = startPage + pageBlock - 1;
			 if (endPage>cntPage) {endPage = cntPage;}
			 request.setAttribute("startPage", startPage);
			 request.setAttribute("endPage", endPage);
			 request.setAttribute("cntPage", cntPage);
			 
			 
			 int startRow = (currentPage-1) * pageSize + 1;
			 request.setAttribute("postVOs", postDao.selectList(pageSize, startRow));
			 
			 response.setContentType("text/html; charset=UTF-8");
			 
			 RequestDispatcher rd = request.getRequestDispatcher("/board/postList.jsp");
			 rd.include(request, response);
		 
		 } catch (Exception e) {
			 e.printStackTrace();
			 request.setAttribute("error", e);
			 RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			 rd.forward(request, response);
		 }
	}
}
