package boardproject.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boardproject.vo.PostVO;
import boardproject.listeners.ContextLoaderListener;
import boardproject.bind.DataBinding;
import boardproject.bind.ServletRequestDataBinder;
import boardproject.context.ApplicationContext;
import boardproject.controls.*;


@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String servletPath = request.getServletPath();
		
		try {
			ApplicationContext context = ContextLoaderListener.getApplicationContext();
			
			HashMap<String, Object> model = new HashMap<>();
			model.put("session", request.getSession());
			model.put("ContextPath", request.getContextPath());
			Controller pageController = (Controller) context.getBean(servletPath);
			
			if (pageController == null) {
				throw new Exception("요청한 서비스를 찾을 수 없습니다.");
			}
			
			if (pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding)pageController);
			}
			
			String viewUrl = pageController.execute(model);
			
			for (String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}
			
			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewUrl);
				requestDispatcher.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
			requestDispatcher.forward(request, response);
		}
	}
	
	private void prepareRequestData(
			HttpServletRequest request,
			HashMap<String, Object> model,
			DataBinding dataBinding) throws Exception {
		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;
		Class<?> dataType = null;
		Object dataObj = null;
		
		for (int i=0; i<dataBinders.length; i+=2) {
			dataName = (String) dataBinders[i];
			dataType = (Class<?>) dataBinders[i+1];
			dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
			model.put(dataName, dataObj);
		}
	}
	
}
