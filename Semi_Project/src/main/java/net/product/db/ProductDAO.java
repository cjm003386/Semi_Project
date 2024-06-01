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
	
	public ProductBean getDetail(int num) {
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

	public List<ProductBean> getAccessoriesList(String type) {
		ProductBean p = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductBean> list = new ArrayList<ProductBean>();
		int code = 0;
		
		if(type.equals("shoes")) {
			code = 20;
		} else if(type.equals("hat")){
			code = 19;
		} else if(type.equals("bag")){
			code = 18;
		} else if(type.equals("tie")){
			code = 17;
		} else if(type.equals("accessories")){
			code = 16;
		}
		
		
		
		try {
			con = ds.getConnection();
		
			String sql = "select * "
					   + "from product "
					   + "where category_code = " + code;
			
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
	
	public List<ProductBean> getAccessoriesList() {
		ProductBean p = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductBean> list = new ArrayList<ProductBean>();
		
		try {
			con = ds.getConnection();
		
			String sql = "select * "
					   + "from product "
					   + "where category_code in (16, 17, 18, 19, 20)";
			
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
	
	public List<ProductBean> getBottomList(String type) {
		ProductBean p = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductBean> list = new ArrayList<ProductBean>();
		int code = 0;
		
		if(type.equals("wide-pants")) {
			code = 10;
		} else if(type.equals("sweatpants")){
			code = 9;
		} else if(type.equals("slacks")){
			code = 8;
		} else if(type.equals("jeans")){
			code = 7;
		} else if(type.equals("short-pants")){
			code = 6;
		}
			
		try {
			con = ds.getConnection();
		
			String sql = "select * "
					   + "from product "
					   + "where category_code = " + code;
			
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

	public List<ProductBean> getBottomList() {
		ProductBean p = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductBean> list = new ArrayList<ProductBean>();
		
		try {
			con = ds.getConnection();
		
			String sql = "select * "
					   + "from product "
					   + "where category_code in (6, 7, 8, 9, 10)";
			
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
	
	public List<ProductBean> getTopList(String type) {
		ProductBean p = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductBean> list = new ArrayList<ProductBean>();
		int code = 0;
		
		if(type.equals("hoodie")) {
			code = 5;
		} else if(type.equals("knit")){
			code = 4;
		} else if(type.equals("shirts")){
			code = 3;
		} else if(type.equals("long-tee")){
			code = 2;
		} else if(type.equals("short-tee")){
			code = 1;
		}
		
		
		
		try {
			con = ds.getConnection();
		
			String sql = "select * "
					   + "from product "
					   + "where category_code = " + code;
			
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

	public List<ProductBean> getTopList() {
		ProductBean p = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductBean> list = new ArrayList<ProductBean>();
		
		try {
			con = ds.getConnection();
		
			String sql = "select * "
					   + "from product "
					   + "where category_code in (1, 2, 3, 4, 5)";
			
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
	
	public List<ProductBean> getOuterList(String type) {
		ProductBean p = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductBean> list = new ArrayList<ProductBean>();
		int code = 0;
		
		if(type.equals("suit")) {
			code = 15;
		} else if(type.equals("padding")){
			code = 14;
		} else if(type.equals("coat")){
			code = 13;
		} else if(type.equals("jacket")){
			code = 12;
		} else if(type.equals("cardigan")){
			code = 11;
		}
		
		
		
		try {
			con = ds.getConnection();
		
			String sql = "select * "
					   + "from product "
					   + "where category_code = " + code;
			
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

	public List<ProductBean> getOuterList() {
		ProductBean p = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ProductBean> list = new ArrayList<ProductBean>();
		
		try {
			con = ds.getConnection();
		
			String sql = "select * "
					   + "from product "
					   + "where category_code in (11, 12, 13, 14, 15)";
			
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

	

}