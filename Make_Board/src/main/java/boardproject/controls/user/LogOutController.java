package boardproject.controls.user;

import java.util.Map;

import boardproject.controls.Controller;
import boardproject.annotation.Component;

import javax.servlet.http.HttpSession;

@Component("/auth/logout.do")
public class LogOutController implements Controller {
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		HttpSession session = (HttpSession) model.get("session");
		session.invalidate();
		String contextPath = (String) model.get("ContextPath");

		return "redirect:"+ contextPath +"/board/list.do";
	}
}
