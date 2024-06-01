package net.payment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ProductDAO {
	private DataSource ds;
	
	public ProductDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}	
	
	public List<ProductBean> getProductList() {
		ProductBean p = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductBean> list = new ArrayList<ProductBean>();
		
		try {
			con = ds.getConnection();
		
			String sql = "select * "
					   + "from product ";
			
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				p = new ProductBean();
				p.setProduct_code(rs.getInt("product_code"));
				p.setProduct_name(rs.getString("product_name"));
				p.setProduct_price(rs.getInt("product_price"));
				p.setProduct_image(rs.getString("product_image"));
				p.setCategory_code(rs.getInt("category_code"));
				list.add(p);								
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			if (rs != null)
			  try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}			
			if (pstmt != null)
			  try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			if (con != null)
			  try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return list;
	}

	public ProductBean getDetail(int num, String color, String size) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductBean p = null;
		
		
		try {
			con = ds.getConnection();
		
			String sql = "select * "
					   + "from product where product_code=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				p = new ProductBean();
				p.setProduct_code(rs.getInt("product_code"));
				p.setProduct_name(rs.getString("product_name"));
				p.setCategory_code(rs.getInt("category_code"));
				p.setProduct_price(rs.getInt("product_price"));
				p.setProduct_image(rs.getString("product_image"));
				p.setProduct_color(color);
				p.setProduct_size(size);
				System.out.println("상품 정보 읽기 성공");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		} finally {
			if (rs != null)
			  try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}			
			if (pstmt != null)
			  try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			if (con != null)
			  try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return p;
	}
}
