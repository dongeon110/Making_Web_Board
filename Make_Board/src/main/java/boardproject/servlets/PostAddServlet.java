package boardproject.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import boardproject.dao.PostDao;
import boardproject.vo.PostVO;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PostAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		RequestDispatcher rd = request.getRequestDispatcher("/board/postAdd.jsp");
		rd.include(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			ServletContext sc = this.getServletContext();
			
			PostDao postDao = (PostDao) sc.getAttribute("postDao");
			postDao.insert(new PostVO()
					.setPostSubject(request.getParameter("postsubject"))
					.setPostText(request.getParameter("posttext"))
					.setPostPassword(request.getParameter("postpassword"))
					.setPosterName(request.getParameter("postername")));
			
			response.sendRedirect("list");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
	
}
