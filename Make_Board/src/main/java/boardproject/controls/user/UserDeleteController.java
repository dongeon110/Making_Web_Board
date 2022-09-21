package boardproject.controls.user;

import java.util.Map;

import javax.servlet.http.HttpSession;

import boardproject.annotation.Component;
import boardproject.dao.UserDao;
import boardproject.vo.User;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;

@Component("/user/delete.do")
public class UserDeleteController implements Controller, DataBinding {
	UserDao userDao;
	
	public UserDeleteController setUserDao(UserDao userDao) {
		this.userDao = userDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"userNo", Integer.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		HttpSession session = (HttpSession) model.get("session");
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null || loginUser.getGrade() != 1) {
			return "/auth/LogInForm.jsp";
		}
		
		userDao.delete((int)model.get("userNo"));
		
		return "redirect:list.do";
	}
}
