package boardproject.controls.user;

import java.util.Map;
import boardproject.annotation.Component;
import boardproject.dao.UserDao;
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
		userDao.delete((int)model.get("userNo"));
		
		return "redirect:list.do";
	}
}
