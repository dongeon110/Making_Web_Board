package boardproject.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;

import boardproject.dao.PostDao;
import boardproject.vo.PostVO;

public class RepostServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
			PostDao postDao = (PostDao) sc.getAttribute("postDao");
			PostVO postVO = postDao.selectOne(Integer.parseInt(request.getParameter("no")));
			request.setAttribute("mainPostVO", postVO);
			RequestDispatcher rd = request.getRequestDispatcher("/board/postRepost.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
			PostDao postDao = (PostDao) sc.getAttribute("postDao");
			int mainPostNo = Integer.parseInt(request.getParameter("repost"));
			PostVO repostVO = new PostVO()
					.setPostSubject("[RE:]" + request.getParameter("postSubject"))
					.setPostText(request.getParameter("postText"))
					.setPostPassword(request.getParameter("postPassword"))
					.setPosterName(request.getParameter("posterName"));
			postDao.insertRepost(mainPostNo, repostVO);
			
			response.sendRedirect("list");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}
}
