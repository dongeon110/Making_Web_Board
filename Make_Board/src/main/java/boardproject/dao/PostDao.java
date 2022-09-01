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
					"SELECT PNO, PSUBJECT, POSTERNAME, CRE_DATE"
					+ " FROM BOARD"
					+ " ORDER BY PNO ASC");
			
			ArrayList<PostVO> posts = new ArrayList<>();
			
			while (rs.next()) {
				posts.add(new PostVO()
						.setPostNo(rs.getInt("PNO"))
						.setPostSubject(rs.getString("PSUBJECT"))
						.setPosterName(rs.getString("POSTERNAME"))
						.setPostCreatedDate(rs.getDate("CRE_DATE")) );
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
}
