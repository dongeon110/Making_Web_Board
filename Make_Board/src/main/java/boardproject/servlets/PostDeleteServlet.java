package boardproject.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import java.io.IOException;

import boardproject.dao.PostDao;
import boardproject.vo.PostVO;

public class PostDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		sc.setAttribute("thisNo", Integer.parseInt(request.getParameter("no")));
		RequestDispatcher rd = sc.getRequestDispatcher("/auth/CheckDeletePw.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
			
			PostDao postDao = (PostDao) sc.getAttribute("postDao");
			PostVO checkPostVO = new PostVO()
					.setPostNo(Integer.parseInt(request.getParameter("no")))
					.setPostPassword(request.getParameter("password"));
			if (postDao.checkPw(checkPostVO)) {
				postDao.delete(checkPostVO.getPostNo());
				response.sendRedirect("list");
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("/auth/CheckPwFail.jsp");
				rd.forward(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd  = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}
