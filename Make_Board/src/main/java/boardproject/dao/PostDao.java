package boardproject.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import boardproject.vo.PostVO;

public class PostDao {
	DataSource ds;
	
	public void setDateSource(DataSource ds) {
		this.ds = ds;
	}
	
	
	// 게시글 목록
	public List<PostVO> selectList() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT PNO, PSUBJECT, POSTERNAME, CRE_DATE, REPOST"
					+ " FROM BOARD"
					+ " ORDER BY REPOST ASC, PNO ASC");
			
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
}
