package boardproject.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import javax.sql.DataSource;
import boardproject.vo.PostVO;
import boardproject.annotation.Component;

@Component("postDao")
public class PostDao {
	SqlSessionFactory sqlSessionFactory;
	
	public void setSqlSessionFactroy(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	 
	
	// 게시글 목록
	public List<PostVO> selectList() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("boardproject.dao.PostDao.selectList");
		} finally {
			sqlSession.close();
		}
	}
	
	// 게시글 목록
	public List<PostVO> searchList(String column, String search) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		HashMap<String, String> searchList = new HashMap<>();
		searchList.put("column", column);
		searchList.put("search", "%"+search+"%");
		try {
			return sqlSession.selectList("boardproject.dao.PostDao.searchList", searchList);
		} finally {
			sqlSession.close();
		}
	}
	
	// 게시글 등록
	public int insert(PostVO postVO) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("boardproject.dao.PostDao.insert", postVO);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	// 게시글 보기 View
	public PostVO selectOne(int postNo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectOne("boardproject.dao.PostDao.selectOne", postNo);
		} finally {
			sqlSession.close();
		}
	}
	
	// ID, PWD 확인
	public boolean checkPw(PostVO postVO) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.selectOne("boardproject.dao.PostDao.checkPw", postVO);
			if (count == 1) {return true;} else {return false;}
		} finally {
			sqlSession.close();
		}
	}
	
	// Update
	public int update(PostVO postVO) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.update("boardproject.dao.PostDao.update", postVO);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	// Delete
	public int delete(int postNo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.delete("boardproject.dao.PostDao.delete", postNo);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	// Repost 답글달기
	public int insertRepost(PostVO repostVO) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("boardproject.dao.PostDao.insertRepost", repostVO);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
	public int increaseViews(int postNo) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int count = sqlSession.insert("boardproject.dao.PostDao.increaseViews", postNo);
			sqlSession.commit();
			return count;
		} finally {
			sqlSession.close();
		}
	}
	
}
	