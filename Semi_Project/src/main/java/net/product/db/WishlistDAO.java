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

public class WishlistDAO {
	private DataSource ds;
	
	public WishlistDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}

	public List<WishlistDTO> getwishList(String id) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String board_list_sql = "select * from wishlist, product "
				+ "where wishlist.product_code = product.product_code "
				+ "and id =?";
		
		List<WishlistDTO> list = new ArrayList<WishlistDTO>();
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				WishlistDTO wishlist = new WishlistDTO();
				wishlist.setwishlist_code(rs.getInt("wishlist_code"));			
				wishlist.setId(rs.getString("id"));
				wishlist.setOpt_color(rs.getString("opt_color"));
				wishlist.setOpt_size(rs.getString("opt_size"));
				wishlist.setProduct_code(rs.getInt("product_code"));
				wishlist.setProduct_image(rs.getString("product_image"));
				wishlist.setProduct_name(rs.getString("product_name"));
				wishlist.setProduct_price(rs.getInt("product_price"));
				list.add(wishlist);//값을 담은 객체를 리스트에 저장
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


	public int insetwishlist(WishlistDTO wishlist) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			conn = ds.getConnection();
			
			String sql = "insert into wishlist "
					+ "  values(wishlist_seq.nextval,?,?,?,?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, wishlist.getId());
				pstmt.setInt(2, wishlist.getProduct_code());;
				pstmt.setString(3, wishlist.getOpt_color());
				pstmt.setString(4, wishlist.getOpt_size());
				
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

	public int deleteWishlist(String id, int wishlist_code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			conn = ds.getConnection();
			
			String sql = "delete from wishlist "
					+ "   where id = ? "
			        + "   and wishlist_code = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, wishlist_code);
		
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

	public int updateWishlist(String id, int wishlist_code, String opt_size, String opt_color) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			conn = ds.getConnection();
			
			String sql = "update wishlist "
					+ "   set opt_size = ?, opt_color = ? "
					+ "   where id = ? "
			        + "   and wishlist_code = ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, opt_size);
				pstmt.setString(2, opt_color);
				pstmt.setString(3, id);
				pstmt.setInt(4, wishlist_code);
		
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

	public int moveWishlistToCart(WishlistDTO wishlist) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			conn = ds.getConnection();
			
			String sql = "insert into cart "
					+ "   values(cart_seq.nextval,?,?,1,?,?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, wishlist.getId());
				pstmt.setInt(2, wishlist.getProduct_code());;
				pstmt.setString(3, wishlist.getOpt_color());
				pstmt.setString(4, wishlist.getOpt_size());
				
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