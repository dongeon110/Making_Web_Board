package boardproject.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;

import java.io.IOException;

import boardproject.vo.PostVO;
import boardproject.dao.PostDao;

public class PostUpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext sc = this.getServletContext();
		sc.setAttribute("thisNo", Integer.parseInt(request.getParameter("no")));
		RequestDispatcher rd = request.getRequestDispatcher("/auth/CheckPostPw.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 아이디 확인
		try {
			ServletContext sc = this.getServletContext();
			response.setContentType("text/html; charset=UTF-8");
			
			PostDao postDao = (PostDao) sc.getAttribute("postDao");
			
			
			PostVO checkPostVO = new PostVO()
					.setPostNo((Integer)sc.getAttribute("thisNo"))
					.setPostPassword(request.getParameter("password"));
			if (postDao.checkPw(checkPostVO)) {
				request.setAttribute("postVO", postDao.selectOne(Integer.parseInt(request.getParameter("no"))));
				RequestDispatcher rd = request.getRequestDispatcher("/board/postUpdate.jsp");

				rd.forward(request, response);

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/auth/CheckPwFail.jsp");
				rd.forward(request, response);
			}
			
			
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {}
	}
}
