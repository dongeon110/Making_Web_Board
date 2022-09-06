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
			 int pageSize = 10;
			 int currentPage;
			 if(request.getParameter("pageNum") == null) {
				 currentPage = 1;
			 } else {
				 currentPage = Integer.parseInt(request.getParameter("pageNum"));
			 }
			 int startRow = (currentPage-1) * pageSize + 1;
			 request.setAttribute("postVOs", postDao.selectList(startRow, pageSize));
			 
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
