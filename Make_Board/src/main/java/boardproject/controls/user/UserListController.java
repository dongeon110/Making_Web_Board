package boardproject.controls.user;

import java.util.Map;
import java.util.List;
import boardproject.dao.UserDao;
import boardproject.vo.User;
import boardproject.controls.Controller;
import boardproject.bind.DataBinding;
import boardproject.annotation.Component;

@Component("/user/list.do")
public class UserListController implements Controller, DataBinding {
	
	UserDao userDao;
	public UserListController setUserDao(UserDao userDao) {
		this.userDao = userDao;
		return this;
	}
	
	public Object[] getDataBinders() {
		return new Object[] {
				"pageNum", String.class
		};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		int currentPage;
		int pageSize = 10;
		if(model.get("pageNum") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt((String)model.get("pageNum"));
		}
		model.put("pageNum", currentPage);
		model.put("pageSize", pageSize);
		
		List<User> userList = userDao.selectList();
		model.put("userList", userList);
		
		int cntUser = 0;
		for (User user: userList) {
			cntUser++;
		}
		model.put("cntUser", cntUser);
		
		return "/user/userList.jsp";
	}
}
