package boardproject.dao;

import java.util.List;
import java.util.HashMap;
import java.util.Hashtable;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import boardproject.vo.User;
import boardproject.annotation.Component;

@Component("userDao")
public class UserDao {
	SqlSessionFactory sqlSessionFactory;
	
	// 마이바티스 의존성 주입
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	// 유저 리스트
	public List<User> selectList() throws Exception{
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("boardproject.dao.UserDao.selectList");
		} finally {
			sqlSession.close();
		}
	}
	
	// 유저 가입
	public int insert(User user) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("boardproject.dao.UserDao.insert", user);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	// 유저 상세보기
	public User selectOne(int userNo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("boardproject.dao.UserDao.selectOne", userNo);
		} finally {
			sqlSession.close();
		}
	}
	
	// 유저 정보 수정
	public int update(User user) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			User original = sqlSession.selectOne("boardproject.dao.UserDao.selectOne", user.getUserNo());
			Hashtable<String, Object> paramMap = new Hashtable<>();
			
			if (!user.getUserID().equals(original.getUserID())) {
				paramMap.put("userID", user.getUserID());
			}
			if (!user.getUserName().equals(original.getUserName())) {
				paramMap.put("userName", user.getUserName());
			}
			if (!user.getUserPassword().equals(original.getUserPassword())) {
				paramMap.put("userPassword", user.getUserPassword());
			}
			if (user.getGrade() != original.getGrade()) {
				paramMap.put("grade", user.getGrade());
			}
			
			if (paramMap.size() > 0) {
				paramMap.put("userNo", user.getUserNo());
				int count = sqlSession.update("boardproject.dao.UserDao.update", paramMap);
				sqlSession.commit();
				return count;
			} else {
				return 0;
			}
		} finally {
			sqlSession.close();
		}
	}
	
	public int delete(int userNo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("boardproject.dao.UserDao.delete", userNo);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	public User exist(String userID, String userPassword) throws Exception {
		HashMap<String, String> paramMap = new HashMap<>();
		paramMap.put("userID", userID);
		paramMap.put("userPassword", userPassword);
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("boardproject.dao.UserDao.exist", paramMap);
		} finally {
			sqlSession.close();
		}
	}
}
