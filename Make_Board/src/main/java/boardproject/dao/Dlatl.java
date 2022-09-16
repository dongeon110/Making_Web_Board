//	
//	// 게시글 목록
//	public List<PostVO> selectList() throws Exception {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = ds.getConnection();
//			stmt = conn.prepareStatement(
//					"SELECT (ROW_NUMBER() OVER()) AS ROWNUM, PNO, PSUBJECT, POSTERNAME, CRE_DATE, REPOST, PVIEWS"
//					+ " FROM ("
//					+ "SELECT PNO, PSUBJECT, POSTERNAME, CRE_DATE, REPOST, PVIEWS"
//					+ " FROM BOARD ORDER BY REPOST DESC, PNO DESC) ROWBOARD");
//			
//			rs = stmt.executeQuery();
//			
//			ArrayList<PostVO> posts = new ArrayList<>();
//			
//			while (rs.next()) {
//				posts.add(new PostVO()
//						.setRowNum(rs.getInt("ROWNUM"))
//						.setPostNo(rs.getInt("PNO"))
//						.setPostSubject(rs.getString("PSUBJECT"))
//						.setPosterName(rs.getString("POSTERNAME"))
//						.setPostCreatedDate(rs.getDate("CRE_DATE"))
//						.setRepost(rs.getInt("REPOST"))
//						.setPostViews(rs.getInt("PVIEWS")));
//			}
//			
//			return posts;
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			try {if (rs != null) rs.close();} catch (Exception e) {}
//			try {if (stmt != null) stmt.close();} catch (Exception e) {}
//			try {if (conn != null) conn.close();} catch(Exception e) {}
//		}
//	}
//	
//	
//	public List<PostVO> selectList(String column, String search) throws Exception {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = ds.getConnection();
//			
//			
//			
//			if (column.equals("ptext")) {
//				stmt = conn.prepareStatement(
//						"SELECT (ROW_NUMBER() OVER()) AS ROWNUM, PNO, PSUBJECT, POSTERNAME, CRE_DATE, REPOST, PVIEWS"
//						+ " FROM ("
//						+ "SELECT PNO, PSUBJECT, POSTERNAME, CRE_DATE, REPOST, PVIEWS"
//						+ " FROM BOARD"
//						+ " WHERE PTEXT LIKE ?"
//						+ " ORDER BY REPOST DESC, PNO DESC) ROWBOARD");
//			} else if (column.equals("postername")) {
//				stmt = conn.prepareStatement(
//						"SELECT (ROW_NUMBER() OVER()) AS ROWNUM, PNO, PSUBJECT, POSTERNAME, CRE_DATE, REPOST, PVIEWS"
//						+ " FROM ("
//						+ "SELECT PNO, PSUBJECT, POSTERNAME, CRE_DATE, REPOST, PVIEWS"
//						+ " FROM BOARD"
//						+ " WHERE POSTERNAME LIKE ?"
//						+ " ORDER BY REPOST DESC, PNO DESC) ROWBOARD");
//			} else {
//				stmt = conn.prepareStatement(
//						"SELECT (ROW_NUMBER() OVER()) AS ROWNUM, PNO, PSUBJECT, POSTERNAME, CRE_DATE, REPOST, PVIEWS"
//						+ " FROM ("
//						+ "SELECT PNO, PSUBJECT, POSTERNAME, CRE_DATE, REPOST, PVIEWS"
//						+ " FROM BOARD"
//						+ " WHERE PSUBJECT LIKE ?"
//						+ " ORDER BY REPOST DESC, PNO DESC) ROWBOARD");
//			}
//			stmt.setString(1, "%"+search+"%");
//			
//			rs = stmt.executeQuery();
//			
//			ArrayList<PostVO> posts = new ArrayList<>();
//			
//			while (rs.next()) {
//				posts.add(new PostVO()
//						.setRowNum(rs.getInt("ROWNUM"))
//						.setPostNo(rs.getInt("PNO"))
//						.setPostSubject(rs.getString("PSUBJECT"))
//						.setPosterName(rs.getString("POSTERNAME"))
//						.setPostCreatedDate(rs.getDate("CRE_DATE"))
//						.setRepost(rs.getInt("REPOST"))
//						.setPostViews(rs.getInt("PVIEWS")));
//			}
//			
//			return posts;
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			try {if (rs != null) rs.close();} catch (Exception e) {}
//			try {if (stmt != null) stmt.close();} catch (Exception e) {}
//			try {if (conn != null) conn.close();} catch(Exception e) {}
//		}
//	}
//	
//	
//	// PostAddServlet
//	public int insert(PostVO postVO) throws Exception {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		
//		try {
//			conn = ds.getConnection();
//			stmt = conn.prepareStatement(
//					"INSERT INTO board(psubject, ptext, ppwd, postername, cre_date, mod_date)"
//					+ " VALUES (?, ?, ?, ?, NOW(), NOW())");
//			stmt.setString(1, postVO.getPostSubject());
//			stmt.setString(2, postVO.getPostText());
//			stmt.setString(3, postVO.getPostPassword());
//			stmt.setString(4, postVO.getPosterName());
//			return stmt.executeUpdate();
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			try {if (stmt != null) stmt.close();} catch(Exception e) {}
//			try {if (conn != null) conn.close();} catch(Exception e) {}
//		}
//	}
//	
//	
//	// PostViewServlet
//	public PostVO selectOne(Integer postNo) throws Exception {
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = ds.getConnection();
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(
//					"SELECT pno, psubject, ptext, postername, cre_date, mod_date, repost, pviews"
//					+ " FROM board"
//					+ " WHERE pno = " + postNo);
//			rs.next();
//			
//			PostVO postVO = new PostVO()
//					.setPostNo(rs.getInt("pno"))
//					.setPostSubject(rs.getString("psubject"))
//					.setPostText(rs.getString("ptext"))
//					.setPosterName(rs.getString("postername"))
//					.setPostCreatedDate(rs.getDate("cre_date"))
//					.setPostModifyDate(rs.getDate("mod_date"))
//					.setRepost(rs.getInt("repost"))
//					.setPostViews(rs.getInt("pviews"));
//			return postVO;
//			
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			try {if (rs != null) rs.close();} catch (Exception e) {};
//			try {if (stmt != null) stmt.close();} catch (Exception e) {};
//			try {if (conn != null) conn.close();} catch (Exception e) {};
//		}
//	}
//	
//	// PostUpdateServlet - ID, PWD 확인
//	public boolean checkPw(PostVO postVO) throws Exception {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = ds.getConnection();
//			stmt = conn.prepareStatement(
//					"SELECT count(*) FROM board"
//					+ " WHERE pno=? and ppwd=?");
//			stmt.setInt(1, postVO.getPostNo());
//			stmt.setString(2, postVO.getPostPassword());
//			rs = stmt.executeQuery();
//			
//			if (rs.next()) {
//				if (rs.getInt("count") == 1) {
//					
//					return true;
//				} else {return false;}
//			}
//			
//			return false;
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			try {if (rs != null) rs.close();} catch (Exception e) {};
//			try {if (stmt != null) stmt.close();} catch (Exception e) {}
//			try {if (conn != null) conn.close();} catch (Exception e) {}
//		}
//	}
//	
//	// PostUpdateServlet - update
//	public int update(PostVO postVO) throws Exception {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		
//		try {
//			conn = ds.getConnection();
//			stmt = conn.prepareStatement(
//					"UPDATE board SET psubject=?, ptext=?, ppwd=?, postername=?, "
//					+ "mod_date=now() WHERE pno=?");
//			stmt.setString(1, postVO.getPostSubject());
//			stmt.setString(2, postVO.getPostText());
//			stmt.setString(3, postVO.getPostPassword());
//			stmt.setString(4, postVO.getPosterName());
//			stmt.setInt(5, postVO.getPostNo());
//			return stmt.executeUpdate();
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			try {if (stmt != null) stmt.close();} catch(Exception e) {}
//			try {if (conn != null) conn.close();} catch(Exception e) {}
//		}
//	}
//	
//	
//	// PostDeleteServlet - delete
//	public int delete(Integer no) throws Exception {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		
//		try {
//			conn = ds.getConnection();
//			stmt = conn.prepareStatement(
//					"DELETE FROM board WHERE pno=?");
//			stmt.setInt(1, no);
//			return stmt.executeUpdate();
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			try {if (stmt != null) stmt.close();} catch(Exception e) {}
//			try {if (conn != null) conn.close();} catch(Exception e) {}
//		}
//		
//	}
//	
//	// RepostServlet
//	public int insertRepost(int postMainNo, PostVO repostVO) throws Exception {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		
//		try {
//			conn = ds.getConnection();
//			stmt = conn.prepareStatement(
//					"INSERT INTO board(psubject, ptext, ppwd, postername,"
//					+ " cre_date, mod_date, repost)"
//					+ " VALUES (?, ?, ?, ?, NOW(), NOW(), ?)");
//			stmt.setString(1, repostVO.getPostSubject());
//			stmt.setString(2, repostVO.getPostText());
//			stmt.setString(3, repostVO.getPostPassword());
//			stmt.setString(4, repostVO.getPosterName());
//			stmt.setInt(5, postMainNo);
//			return stmt.executeUpdate();
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			try {if (stmt != null) stmt.close();} catch(Exception e) {};
//			try {if (conn != null) conn.close();} catch(Exception e) {};
//		}
//	}
//	
//	// Views
//	public int increaseViews(int no) throws Exception {
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		
//		try {
//			conn = ds.getConnection();
//			stmt = conn.prepareStatement(
//					"UPDATE board SET pviews = pviews + 1"
//					+ " WHERE pno = ?");
//			stmt.setInt(1, no);
//			return stmt.executeUpdate();
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			try {if (stmt != null) stmt.close();} catch(Exception e) {};
//			try {if (conn != null) conn.close();} catch(Exception e) {};
//		}
//	}