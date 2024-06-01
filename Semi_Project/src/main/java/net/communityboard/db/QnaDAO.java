package net.communityboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QnaDAO {

	
	private DataSource ds;

	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public QnaDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}

	
	
	public int getListCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int n = 0; 
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from qna");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				n = rs.getInt(1);
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				}catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}//finally
		return n;
		}

	public List<QnaBean> getList(int page, int limit) {
		List<QnaBean> list = new ArrayList<QnaBean>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String sql = "select * "
					   + " from (select q.*, rownum rnum"
					   + "		from(select * from qna"
					   + " 			 order by qna_num desc) q"
					   +  		")"
					   + " where rnum>=? and rnum<=?";
			
			
			
			pstmt = con.prepareStatement(sql);
			int startrow = (page - 1) * limit + 1;
			int endrow = startrow + limit - 1;
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
						
			while(rs.next()) {
				QnaBean q = new QnaBean();
				q.setQna_num(rs.getInt("qna_num"));
				q.setId(rs.getString("id"));
				q.setQna_cate(rs.getString("qna_cate"));
				q.setQna_title(rs.getString("qna_title"));
				q.setQna_content(rs.getString("qna_content"));
				q.setQna_date(rs.getString("qna_date"));
				q.setQna_state(rs.getString("qna_state"));
				q.setQna_re_ref(rs.getInt("qna_re_ref"));
				q.setQna_re_lev(rs.getInt("qna_re_lev"));
				q.setQna_re_seq(rs.getInt("qna_re_seq"));
				list.add(q);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				}catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}//finally
		
		
		return list;
	}



	public int getListCount(String field, String value) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int q =0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from qna "
					+ "where " + field + " like ?";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				q=rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " +ex);
		} finally {
			if(rs !=null)
			try {
					rs.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
			if(pstmt != null)
			try {
				pstmt.close();//DB연결을 끊는다.
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
		}//finally end
		return q;
	}



	public List<QnaBean> getList(String field, String value, int page, int limit) {
		List<QnaBean> list = new ArrayList<QnaBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select * "
					+ "from  (select rownum rnum, q.* "
					+ "    	  from (SELECT * FROM qna "
					+ "				where " + field + " like ?"
					+ " 			ORDER BY qna_re_ref DESC, "
					+ " 			qna_re_seq ASC) q"
					+ " 			where rownum<=?) "
					+ " 			where rnum>=? and rnum<=?";
			
			
			System.out.println("notice getList: "+sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%");
			//한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지...
			int startrow = (page -1) * limit +1; 
						//읽기 시작할 row번호 (1	11	   21	  31...
			int endrow = startrow + limit -1;
						//읽을 마지막 row번호     (10	20	   30	  40...
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				QnaBean q = new QnaBean();
				q.setQna_num(rs.getInt("qna_num"));
				q.setId(rs.getString("id"));
				q.setQna_cate(rs.getString("qna_cate"));
				q.setQna_title(rs.getString("qna_title"));
				q.setQna_content(rs.getString("qna_content"));
				q.setQna_date(rs.getString("qna_date"));
				q.setQna_state(rs.getString("qna_state"));
				q.setQna_re_ref(rs.getInt("qna_re_ref"));
				q.setQna_re_lev(rs.getInt("qna_re_lev"));
				q.setQna_re_seq(rs.getInt("qna_re_seq"));
				list.add(q);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getList() 에러: " +ex);
		} finally {
			if(rs !=null)
			try {
					rs.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
			if(pstmt != null)
			try {
				pstmt.close();//DB연결을 끊는다.
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
		}//finally end
		return list;
	}



}
