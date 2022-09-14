package boardproject.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import boardproject.vo.PostVO;
import boardproject.annotation.Component;

@Component("postDao")
public class PostDao {
	DataSource ds;
	
	public void setDateSource(DataSource ds) {
		this.ds = ds;
	}
	
	// 게시글 목록
	public List<PostVO> selectList(int pageSize, int startRow) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
					"SELECT PNO, PSUBJECT, POSTERNAME, CRE_DATE, REPOST"
					+ " FROM BOARD"
					+ " ORDER BY REPOST DESC, PNO DESC"
					+ " LIMIT ? OFFSET ?");
			stmt.setInt(1, pageSize); // 시작 row index 번호
			stmt.setInt(2, startRow-1); // 한번에 출력되는 수
			
			rs = stmt.executeQuery();
			
			ArrayList<PostVO> posts = new ArrayList<>();
			
			while (rs.next()) {
				posts.add(new PostVO()
						.setPostNo(rs.getInt("PNO"))
						.setPostSubject(rs.getString("PSUBJECT"))
						.setPosterName(rs.getString("POSTERNAME"))
						.setPostCreatedDate(rs.getDate("CRE_DATE"))
						.setRepost(rs.getInt("REPOST")) );
			}
			
			return posts;
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
	
	// 총 게시글 수
	public int countPost() throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
					"SELECT count(*) FROM board");
			rs = stmt.executeQuery();
			int cntPost = 0;
			
			while (rs.next()) {
				cntPost = rs.getInt("count");
			}
			
			return cntPost;
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (conn != null) conn.close();} catch (Exception e) {}
		}
	}
	
	
	
	
	// PostAddServlet
	public int insert(PostVO postVO) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
					"INSERT INTO board(psubject, ptext, ppwd, postername, cre_date, mod_date)"
					+ " VALUES (?, ?, ?, ?, NOW(), NOW())");
			stmt.setString(1, postVO.getPostSubject());
			stmt.setString(2, postVO.getPostText());
			stmt.setString(3, postVO.getPostPassword());
			stmt.setString(4, postVO.getPosterName());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
	
	
	// PostViewServlet
	public PostVO selectOne(Integer postNo) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT pno, psubject, ptext, postername, cre_date, mod_date, repost"
					+ " FROM board"
					+ " WHERE pno = " + postNo);
			rs.next();
			
			PostVO postVO = new PostVO()
					.setPostNo(rs.getInt("pno"))
					.setPostSubject(rs.getString("psubject"))
					.setPostText(rs.getString("ptext"))
					.setPosterName(rs.getString("postername"))
					.setPostCreatedDate(rs.getDate("cre_date"))
					.setPostModifyDate(rs.getDate("mod_date"))
					.setRepost(rs.getInt("repost"));
			return postVO;
			
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {};
			try {if (stmt != null) stmt.close();} catch (Exception e) {};
			try {if (conn != null) conn.close();} catch (Exception e) {};
		}
	}
	
	// PostUpdateServlet - ID, PWD 확인
	public boolean checkPw(PostVO postVO) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
					"SELECT count(*) FROM board"
					+ " WHERE pno=? and ppwd=?");
			stmt.setInt(1, postVO.getPostNo());
			stmt.setString(2, postVO.getPostPassword());
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				if (rs.getInt("count") == 1) {
					
					return true;
				} else {return false;}
			}
			
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {};
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try {if (conn != null) conn.close();} catch (Exception e) {}
		}
	}
	
	// PostUpdateServlet - update
	public int update(PostVO postVO) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
					"UPDATE board SET psubject=?, ptext=?, ppwd=?, postername=?, "
					+ "mod_date=now() WHERE pno=?");
			stmt.setString(1, postVO.getPostSubject());
			stmt.setString(2, postVO.getPostText());
			stmt.setString(3, postVO.getPostPassword());
			stmt.setString(4, postVO.getPosterName());
			stmt.setInt(5, postVO.getPostNo());
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
	
	
	// PostDeleteServlet - delete
	public int delete(Integer no) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
					"DELETE FROM board WHERE pno=?");
			stmt.setInt(1, no);
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
		
	}
	
	// RepostServlet
	public int insertRepost(int postMainNo, PostVO repostVO) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement(
					"INSERT INTO board(psubject, ptext, ppwd, postername,"
					+ " cre_date, mod_date, repost)"
					+ " VALUES (?, ?, ?, ?, NOW(), NOW(), ?)");
			stmt.setString(1, repostVO.getPostSubject());
			stmt.setString(2, repostVO.getPostText());
			stmt.setString(3, repostVO.getPostPassword());
			stmt.setString(4, repostVO.getPosterName());
			stmt.setInt(5, postMainNo);
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {};
			try {if (conn != null) conn.close();} catch(Exception e) {};
		}
	}
	

}
