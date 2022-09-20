package boardproject.controls.user;

import java.util.Map;
import java.util.List;
import boardproject.annotation.Component;
import boardproject.vo.User;
import boardproject.bind.DataBinding;
import boardproject.controls.Controller;
import boardproject.dao.UserDao;

@Component("/user/add.do")
public class UserAddController implements Controller, DataBinding {
	
	UserDao userDao;
	public UserAddController setUserDao(UserDao userDao) {
		this.userDao = userDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"user", boardproject.vo.User.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		User user = (User) model.get("user");
		
		if (user.getUserID() == null) {
			return "/user/userForm.jsp";
		} else {
			userDao.insert(user);
			
			return "redirect:list.do";
		}
	}
}
