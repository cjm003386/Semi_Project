package net.admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;






public class CustomerDAO {
	private DataSource ds;
	
	//생성자에서 JNDI에서 리소스를 참조하여 Connection 객체를 얻어옵니다.	
	public CustomerDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}
	
	
	//관리자-회원정보 게시판 총 리스트
	public int getListCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int c = 0; 
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from customer");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				c = rs.getInt(1);
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
		return c;
		}

	
	//관리자-회원정보 게시판 리스트 회원정보 리스트
	public List<Customer> getList(int page, int limit) {
		List<Customer> list = new ArrayList<Customer>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String sql = "select * "
					   + " from (select c.*, rownum rnum"
					   + "		from(select * from customer"
					   + " 			 order by grade desc) c"
					   +  		")"
					   + " where rnum>=? and rnum<=?";
			
			
			
			pstmt = con.prepareStatement(sql);
			//한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지 , 4페이지 ...
			int startrow = (page - 1) * limit + 1;
					     //읽기 시작할 row 번호(1 11 21 31 ...
			int endrow = startrow + limit - 1;
						//읽을 마지막 row 번호(10 20 30 40 ...
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
						
			while(rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setPhone(rs.getString("phone"));
				c.setPost(rs.getString("post"));
				c.setAddress(rs.getString("address"));
				c.setEmail(rs.getString("email"));
				c.setGender(rs.getString("gender"));
				c.setGrade(rs.getString("grade"));
				//c.set...상품 등록 상태 추가
				list.add(c);
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
		int c =0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from customer "
					+ "where " + field + " like ?";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				c=rs.getInt(1);
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
		return c;
	}


	
	
	public List<Customer> getList(String field, String value, int page, int limit) {
		List<Customer> list = new ArrayList<Customer>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select * "
					+ "from  (select rownum rnum, c.* "
					+ "    	  from (SELECT * FROM customer "
					+ "				where " + field + " like ?"
					+ " 			ORDER BY grade DESC) c "
					+ " 			where rownum<=?) "
					+ " 			where rnum>=? and rnum<=?";
			
			
			System.out.println("customer getList: "+sql);
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
				Customer c = new Customer();
				c.setId(rs.getString("id"));
				c.setName(rs.getString("name"));
				c.setGender(rs.getString("gender"));
				c.setPost(rs.getString("post"));
				c.setAddress(rs.getString("address"));
				c.setPhone(rs.getString("phone"));
				c.setEmail(rs.getString("email"));
				c.setGrade(rs.getString("grade"));
				list.add(c);//값을 담은 객체를 리스트에 저장
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


	public Customer customer_info(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Customer c = null;
		try {
			con = ds.getConnection();
			
			String sql = "select id, name, phone, post, address, email, gender, grade from customer where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
			    c = new Customer();
			    
				c.setId(rs.getString(1));
				c.setName(rs.getString(2));
				c.setPhone(rs.getString(3));
				c.setPost(rs.getString(4));
				c.setAddress(rs.getString(5));
				c.setEmail(rs.getString(6));
				c.setGender(rs.getString(7));
				c.setGrade(rs.getString(8));
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
		
		return c;
	}


	public int delete(String id) {
		Connection con = null;
		PreparedStatement pstmt=null;
		int result = 0;
		try {
			con = ds.getConnection();
			String sql = "delete from Customer where id = ?";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result=pstmt.executeUpdate();
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
			
		return result;
		
	}


	public int customer_update(Customer c) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = ds.getConnection();
			
			String sql = "update customer"
					   + " set id=?, name=?, phone=?, post=?, address=?, email=?, gender=?, grade=?"
					   + " where id=? ";
		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c.getId());
			pstmt.setString(2, c.getName());
			pstmt.setString(3, c.getPhone());
			pstmt.setString(4, c.getPost());
			pstmt.setString(5, c.getAddress());
			pstmt.setString(6, c.getEmail());
			pstmt.setString(7, c.getGender());
			pstmt.setString(8, c.getGrade());
			pstmt.setString(9, c.getId());
			result = pstmt.executeUpdate();
			
				
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
			return result;
	}


	


	
	
}
