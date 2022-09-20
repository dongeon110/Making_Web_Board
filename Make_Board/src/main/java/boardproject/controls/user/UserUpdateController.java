package boardproject.controls.user;

import java.util.Map;
import boardproject.vo.User;
import boardproject.dao.UserDao;
import boardproject.bind.DataBinding;
import boardproject.annotation.Component;
import boardproject.controls.Controller;

@Component("/user/update.do")
public class UserUpdateController implements Controller, DataBinding {
	UserDao userDao;
	
	public UserUpdateController setUserDao(UserDao userDao) {
		this.userDao = userDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"no", Integer.class,
				"user", boardproject.vo.User.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		User updateUser = (User) model.get("user");
		if (updateUser.getUserID() == null) {
			model.put("user", userDao.selectOne((int)model.get("no")));
			return "/user/userUpdateForm.jsp";
		} else {
			userDao.update(updateUser);
			
			return "redirect:list.do";
		}
	}
}
