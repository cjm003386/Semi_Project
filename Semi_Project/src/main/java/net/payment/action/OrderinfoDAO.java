package net.payment.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.mypage.db.Orderlist;
import net.payment.db.Order_Info;
import net.payment.db.Order_Item;

public class OrderinfoDAO {
	private DataSource ds;
	public OrderinfoDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : " +ex);
			return;
		}
	}

	/*
	 * public int infoinsert(Order_Info info) { Connection conn = null;
	 * PreparedStatement pstmt = null; int result =0; try { conn =
	 * ds.getConnection();
	 * 
	 * //원문글의 BOARD_RE_REF는 자신의 글번호가된다. String sql = "insert into order_info " +
	 * "  	values (order_info_seq.nextval, ?, ?, ?," + "		   		?, ?, ?, " +
	 * "				?,?,?,sysdate)"; //새로운 글을 등록한다. pstmt =
	 * conn.prepareStatement(sql); pstmt.setString(1, info.getId());
	 * pstmt.setString(2, info.getPayment_option()); pstmt.setString(3,
	 * info.getAddress1()); pstmt.setString(4, info.getAddress2());
	 * pstmt.setString(5, info.getPost()); //원문의 경우 BOARD_RE_LEV, BOARD_RE_SEQ 필드 값은
	 * 0 pstmt.setString(6, info.getReceiver_name()); pstmt.setString(7,
	 * info.getReceiver_phone()); pstmt.setInt(8, info.getOrder_cost());//Readcount
	 * pstmt.setString(9, info.getDelivery_message());
	 * 
	 * result = pstmt.executeUpdate(); if(result==1) {
	 * System.out.println("데이터 삽입 완료"); }
	 * 
	 * }catch(Exception ex) { ex.printStackTrace();
	 * System.out.println("orderInsert() 에러: " +ex); } finally { if(pstmt !=null)
	 * try{ pstmt.close(); } catch(SQLException ex) {
	 * 
	 * ex.printStackTrace(); } if(conn != null) try { conn.close();//DB연결을 끊는다.
	 * }catch (SQLException ex) { } }//finally return result; }
	 */

	
	public int infoinsert(Order_Info info, Order_Item item) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result =0;
		int result1 =0;
		int num=0;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			//원문글의 BOARD_RE_REF는 자신의 글번호가된다.
			String sql = "insert into order_info "
					+ "  	values (order_info_seq.nextval, ?, ?, ?,"
					+ "		   		?, ?, ?, "
					+ "				?,?,?,sysdate)";
			//새로운 글을 등록한다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, info.getId());
			pstmt.setString(2, info.getPayment_option());
			pstmt.setString(3, info.getAddress1());
			pstmt.setString(4, info.getAddress2());
			pstmt.setString(5, info.getPost());
			//원문의 경우 BOARD_RE_LEV, BOARD_RE_SEQ 필드 값은 0
			pstmt.setString(6, info.getReceiver_name());
			pstmt.setString(7, info.getReceiver_phone());
			pstmt.setInt(8, info.getOrder_cost());//Readcount
			pstmt.setString(9, info.getDelivery_message());
			
			result = pstmt.executeUpdate();
			if(result==1) {
				System.out.println("데이터 삽입 완료");
			}
			pstmt.close();
			
			
			String max_sql = "SELECT MAX(order_code) FROM order_info";
			pstmt = conn.prepareStatement(max_sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1);
				System.out.println("order_code는 "+num);
			}else {
				System.out.println("값이 존재하지 않습니다.");
			}
			pstmt.close();
			
			String orderitem_sql = "insert into order_item "
					+ "  	values(order_info_seq.nextval, "+num+","
							+ "		?, ?, ?, '배송 전',?,?)";
			
			pstmt=conn.prepareStatement(orderitem_sql);
			pstmt.setInt(1, item.getProduct_code());
			pstmt.setInt(2, item.getProduct_count());
			pstmt.setInt(3, item.getProduct_price());
			pstmt.setString(4, item.getProduct_color());
			pstmt.setString(5, item.getProduct_size());
			
			result1 = pstmt.executeUpdate();
			if(result1==1) {
				System.out.println("주문 상품 정보 삽입 완료");
				conn.commit();
			}else {
				conn.rollback();
			}
				
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("OrderitemInsert() 에러: " +ex);
			if(conn !=null)
				try {
						conn.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} finally {
				if(rs != null)
				try {
						rs.close();
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
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
		return result1;
	}

	public int iteminset(Order_Item item) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result =0;
		int num=0;
		String max_sql = "SELECT MAX(order_code) FROM order_info";
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(max_sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1);
			}
			pstmt.close();
			//원문글의 BOARD_RE_REF는 자신의 글번호가된다.
			String sql = "insert into order_item "
					+ "  	values(order_info_seq.nextval, "+num+","
							+ "		?, ?, ?, '배송 전',?,?)";
			//새로운 글을 등록한다.
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, item.getProduct_code());
			pstmt.setInt(2, item.getProduct_count());
			pstmt.setInt(3, item.getProduct_price());
			pstmt.setString(4, item.getProduct_color());
			pstmt.setString(5, item.getProduct_size());
			
			result = pstmt.executeUpdate();
			if(result==1) {
				System.out.println("주문 상품 정보 삽입 완료");
			}else {
				conn.rollback();
			}
				
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("OrderitemInsert() 에러: " +ex);
			if(conn !=null)
				try {
						conn.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} finally {
				if(rs != null)
				try {
						rs.close();
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
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

}
