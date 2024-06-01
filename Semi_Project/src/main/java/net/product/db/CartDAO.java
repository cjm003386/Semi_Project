package net.product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class CartDAO {
	private DataSource ds;
	
	public CartDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}

	public List<CartDTO> getcartList(String id) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String board_list_sql = "select * from cart, product "
				+ "where cart.product_code = product.product_code "
				+ "and id =?";
		
		List<CartDTO> list = new ArrayList<CartDTO>();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql.toString());//toStrin()없애도 내부적으로 메서드를 사용해준다.
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO객체에 담는다.
			while(rs.next()) {//더이상 읽을 데이터가 없을때까지 반복
				CartDTO cart = new CartDTO();
				cart.setCart_code(rs.getInt("cart_code"));
				cart.setCart_count(rs.getInt("cart_count"));
				cart.setId(rs.getString("id"));
				cart.setOpt_color(rs.getString("opt_color"));
				cart.setOpt_size(rs.getString("opt_size"));
				cart.setProduct_code(rs.getInt("product_code"));
				cart.setProduct_image(rs.getString("product_image"));
				cart.setProduct_name(rs.getString("product_name"));
				cart.setProduct_price(rs.getInt("product_price"));
				list.add(cart);//값을 담은 객체를 리스트에 저장
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("getBoardList() 에러: " +e);
		} finally {
			if(rs!=null)
			try {
					rs.close(); 
			} catch(SQLException e) {
				e.printStackTrace();
			}
			if(pstmt !=null)
			try {
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(conn != null)
			try {
					conn.close();//DB연결을 끊는다.
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}//finally
		return list;
	}


	public int insetCart(CartDTO cart) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			conn = ds.getConnection();
			
			String sql = "insert into cart "
					+ "  values(cart_seq.nextval,?,?,?,?,?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cart.getId());
				pstmt.setInt(2, cart.getProduct_code());
				pstmt.setInt(3, cart.getCart_count());
				pstmt.setString(4, cart.getOpt_color());
				pstmt.setString(5, cart.getOpt_size());
				
				result = pstmt.executeUpdate();
		//primary key 제약조건 위반 경우 발생 에러
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
				if(pstmt !=null)
					try{
						pstmt.close();
					} catch(SQLException ex) {
						
						ex.printStackTrace();
					}
				if(conn != null)
			try {
					conn.close();//DB연결을 끊는다.
			}catch (SQLException ex) {
			}
		}//finally
		return result;
	}

	public int deleteCart(String id, int cart_code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			conn = ds.getConnection();
			
			String sql = "delete from cart "
					+ "   where id = ? "
			        + "   and cart_code = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, cart_code);
		
				result = pstmt.executeUpdate();
				
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
				if(pstmt !=null)
					try{
						pstmt.close();
					} catch(SQLException ex) {
						
						ex.printStackTrace();
					}
				if(conn != null)
			try {
					conn.close();//DB연결을 끊는다.
			}catch (SQLException ex) {
			}
		}//finally
		return result;
	}

	public int updateCart(String id, int cart_code, String opt_size, String opt_color) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			conn = ds.getConnection();
			
			String sql = "update cart "
					+ "   set opt_size = ?, opt_color = ? "
					+ "   where id = ? "
			        + "   and cart_code = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, opt_size);
				pstmt.setString(2, opt_color);
				pstmt.setString(3, id);
				pstmt.setInt(4, cart_code);
		
				result = pstmt.executeUpdate();
				
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
				if(pstmt !=null)
					try{
						pstmt.close();
					} catch(SQLException ex) {
						
						ex.printStackTrace();
					}
				if(conn != null)
			try {
					conn.close();//DB연결을 끊는다.
			}catch (SQLException ex) {
			}
		}//finally
		return result;
	}

	public int moveCartToWishlist(CartDTO cart) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			conn = ds.getConnection();
			
			String sql = "insert into wishlist "
					+ "   values(wishlist_seq.nextval,?,?,?,?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cart.getId());
				pstmt.setInt(2, cart.getProduct_code());;
				pstmt.setString(3, cart.getOpt_color());
				pstmt.setString(4, cart.getOpt_size());
				
				result = pstmt.executeUpdate();
		//primary key 제약조건 위반 경우 발생 에러
		}catch(Exception ex) {
			ex.printStackTrace();
		} finally {
				if(pstmt !=null)
					try{
						pstmt.close();
					} catch(SQLException ex) {
						
						ex.printStackTrace();
					}
				if(conn != null)
			try {
					conn.close();//DB연결을 끊는다.
			}catch (SQLException ex) {
			}
		}//finally
		return result;
	}
	
	
}