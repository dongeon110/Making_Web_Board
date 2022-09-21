package boardproject.controls.user;

import java.util.Map;

import boardproject.annotation.Component;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;
import boardproject.dao.UserDao;
import boardproject.vo.User;

import javax.servlet.http.HttpSession;

@Component("/auth/login.do")
public class LogInController implements Controller, DataBinding {
	UserDao userDao;
	public LogInController setUserDao(UserDao userDao) {
		this.userDao = userDao;
		return this;
	}
	
	
	public Object[] getDataBinders() {
		return new Object[] {
				"loginInfo", boardproject.vo.User.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		User loginInfo = (User)model.get("loginInfo");
		
		if (loginInfo.getUserID() == null) {
			return "/auth/LogInForm.jsp";
		} else {
			User user = userDao.exist(
					loginInfo.getUserID(),
					loginInfo.getUserPassword());
			
			if (user != null) {
				HttpSession session = (HttpSession) model.get("session");
				session.setAttribute("loginUser", user);
				return "redirect:../board/list.do";
			} else {
				return "/auth/LogInFail.jsp";
			}
		}
	}
}
