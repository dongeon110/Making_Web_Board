package boardproject.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardproject.dao.PostDao;
import boardproject.vo.PostVO;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

public class PostUpdateServlet_2 extends HttpServlet {
	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ServletContext sc = this.getServletContext();
//		RequestDispatcher rd = request.getRequestDispatcher("/board/postUpdateDetail.jsp");
//		rd.forward(request, response);
//	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ServletContext sc = this.getServletContext();
//			response.setContentType("text/html; charset=UTF-8");
			
			PostDao postDao = (PostDao) sc.getAttribute("postDao");
			PostVO updatePostVO = new PostVO()
					.setPostNo(Integer.parseInt(request.getParameter("no")))
					.setPostSubject(request.getParameter("postSubject"))
					.setPostText(request.getParameter("postText"))
					.setPosterName(request.getParameter("posterName"))
					.setPostPassword(request.getParameter("postPassword"))
					.setRepost(Integer.parseInt(request.getParameter("repost")));
			if (updatePostVO.getPostNo() != updatePostVO.getRepost()) {
				updatePostVO.setPostSubject("[RE:]" + request.getParameter("postSubject"));
			}
			
			postDao.update(updatePostVO);
			
			response.sendRedirect("list");
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {}
		
	}

}
