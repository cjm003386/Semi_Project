package net.mypage.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ReviewCommentDAO {
	private DataSource ds;
	public ReviewCommentDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : " +ex);
			return;
		}
	}
	public int commentsInsert(ReviewComment c) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;


		try {
			conn = ds.getConnection();
			String sql = "insert into review_comm " 
					+ " values(review_com_seq.nextval, ?, ?, sysdate, ?,?,?,review_com_seq.nextval)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getContent());
			pstmt.setInt(3, c.getComment_review_num());
			pstmt.setInt(4, c.getComment_re_lev());
			pstmt.setInt(5, c.getComment_re_seq());
			
			result = pstmt.executeUpdate();
			if(result==1) {
				System.out.println("댓글 데이터 삽입 완료");
			}
		}catch(Exception se) {
			System.out.println("reviewcommentsInsert()에러:" +se);
			se.printStackTrace();
		} finally {
			if(pstmt != null)
			try {
					pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			if(conn!=null)
			try {
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}//finally end
		return result;
	}
	public int getListCount(int comment_review_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x =0;
		String sql = " select count(*) "
				+ " from review_comm "
				+ " where comment_review_num = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, comment_review_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
					x=rs.getInt(1);
			}
		}catch(Exception se) {
			System.out.println("reviewgetListCount()에러:" +se);
			se.printStackTrace();
		} finally {
			if (rs!=null)
			try {
					rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			if(pstmt != null)
			try {
					pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			if(conn!=null)
			try {
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}//finally end
		return x;
	}
	public JsonArray getCommentList(int comment_review_num, int state) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sort="asc";
		if(state==2) {
			sort="desc";
		}
		String sql = "select num, review_comm.id, content, reg_date, comment_re_lev, "
				+ "			 comment_re_seq, "
				+ "			 comment_re_ref "
				+ "	  from   review_comm join customer "
				+ "	  on 	 review_comm.id= customer.id "
				+ "   where comment_review_num = ? "
				+ "	  order by comment_re_ref " + sort + ", "
						+ " comment_re_seq asc";
		JsonArray array = new JsonArray();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, comment_review_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				JsonObject object = new JsonObject();
				object.addProperty("num", rs.getInt(1));
				object.addProperty("id", rs.getString(2));
				object.addProperty("content", rs.getString(3));
				object.addProperty("reg_date", rs.getString(4));
				object.addProperty("comment_re_lev", rs.getInt(5));
				object.addProperty("comment_re_seq", rs.getInt(6));
				object.addProperty("comment_re_ref", rs.getInt(7));
				array.add(object);
			}
		}catch(Exception se) {
			System.out.println("getreviewcommentListCount()에러:" +se);
			se.printStackTrace();
		} finally {
			if (rs!=null)
			try {
					rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			if(pstmt != null)
			try {
					pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			if(conn!=null)
			try {
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}//finally end
		return array;
	}
	public int commentsDelete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		
		try {
			/*context.xml에 생성해 놓은 (JNDI에 설정해 놓은) 리소스 jdbc/OracleDB를
			  참조하여 Connection 객체를 얻어온다.*/
			conn = ds.getConnection();
			
			
			String select_sql = "delete from review_comm "
					+ "where num = ? ";
			//PreparedStatement 객체 얻기
			pstmt = conn.prepareStatement(select_sql.toString());
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if(pstmt !=null)
			try {
					pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
			if(conn != null)
			try {
					conn.close();//DB연결을 끊는다.
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}
		}//finally end
		return result;
	}
	public int commentsReply(ReviewComment c) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;

		
		try {
			conn = ds.getConnection();
			//트랜잭션을 이용하기 위해 setAutoCommit을 false로 설정
			conn.setAutoCommit(false);
			StringBuilder update_sql = new StringBuilder();
			update_sql.append("update review_comm ");
			update_sql.append("set comment_re_seq =  comment_re_seq + 1 " );
			update_sql.append("where  comment_re_ref = ? ");
			update_sql.append("and    comment_re_seq > ?");
			pstmt = conn.prepareStatement(update_sql.toString());
			pstmt.setInt(1, c.getComment_re_ref());
			pstmt.setInt(2, c.getComment_re_seq());
		    pstmt.executeUpdate();
			pstmt.close();
		    
			
			String sql = "insert into review_comm  " 
				     + "values(review_com_seq.nextval, ?,?, sysdate,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getContent());
			pstmt.setInt(3,c.getComment_review_num());
			pstmt.setInt(4, c.getComment_re_lev()+1);
			pstmt.setInt(5, c.getComment_re_seq()+1);//원문 글번호
			pstmt.setInt(6, c.getComment_re_ref());//답변에는 파일을 업로드하지 않는다.
			result = pstmt.executeUpdate();
			
			if(result==1) {
				System.out.println("댓글 답변 삽입 완료");
				conn.commit();//commit
			}
		
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("commentsReply() 에러:" + ex);
			try {
					conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} finally {
			if(pstmt !=null)
				try {
					pstmt.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			if(conn != null)
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}//finally
		return result;
	}
	public int commentsUpdate(ReviewComment co) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;


		try {
			conn = ds.getConnection();
			String sql = "update review_comm " 
					+ " set content=? where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, co.getContent());
			pstmt.setInt(2, co.getNum());
			
			result = pstmt.executeUpdate();
			if(result==1) {
				System.out.println("댓글 데이터 수정 완료");
			}
		}catch(Exception se) {
			System.out.println("commentsUpdate()에러:" +se);
			se.printStackTrace();
		} finally {
			if(pstmt != null)
			try {
					pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			if(conn!=null)
			try {
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}//finally end
		return result;
	}
	
	
}
