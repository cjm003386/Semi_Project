package net.mypage.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




public class Order_infoDAO {
	private DataSource ds;
	public Order_infoDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : " +ex);
			return;
		}
	}
	
	public List<Orderlist> getList(String id, int page, int limit) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String board_list_sql = "select * "
				+ "	from(select rownum rnum, j.* "
				+ "		 from (select o.id, o.order_date, p.product_image, p.product_name, oit.product_color, oit.product_size,  p.product_code, "
				+ "				oit.product_count, oit.product_price, oit.orderitem_code, oit.orderstate, o.order_cost  "
				+ "				from product p, order_info o, order_item oit "
				+ "				where p.product_code=oit.product_code "
				+ "				and o.order_code=oit.order_code "
				+ "				and o.id=?"
				+ "				 and (1=2 or oit.orderstate='배송 전' or oit.orderstate='배송 완료') "
				+ "				order by o.order_date desc) j "
				+ "		where rownum<=?) "
				+ "	where rnum>=? and rnum<=?";
				//+ "	and oit.orderstate='배송 전'";// or oit.orderstate='배송 완료'
		
		List<Orderlist> list = new ArrayList<Orderlist>();
		int startrow = (page -1) * limit +1; //읽기 시작할 row번호 (1	11	   21	  31...
		int endrow = startrow + limit -1;
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql.toString());//toStrin()없애도 내부적으로 메서드를 사용해준다.
			pstmt.setString(1, id);
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			rs = pstmt.executeQuery(); 
			
			//DB에서 가져온 데이터를 VO객체에 담는다.
			while(rs.next()) {//더이상 읽을 데이터가 없을때까지 반복
				Orderlist orderlist = new Orderlist();
				orderlist.setOrder_date(rs.getString("order_date"));
				orderlist.setProduct_image(rs.getString("product_image"));
				orderlist.setProduct_name(rs.getString("product_name"));
				orderlist.setProduct_count(rs.getInt("product_count"));
				orderlist.setOrderstate(rs.getString("orderstate"));
				orderlist.setId(rs.getString("id"));
				orderlist.setOrderitem_code(rs.getInt("orderitem_code"));
				orderlist.setProduct_price(rs.getInt("product_price"));
				orderlist.setProduct_code(rs.getInt("product_code"));
				orderlist.setOrder_cost(rs.getInt("order_cost"));
				orderlist.setProduct_color(rs.getString("product_color"));
				orderlist.setProduct_size(rs.getString("product_size"));
				list.add(orderlist);//값을 담은 객체를 리스트에 저장
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("orderlist() 에러: " +e);
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

	public List<Orderlist> getCancel(String id, int page, int limit) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String board_list_sql =  "select * "
				+ "	from(select rownum rnum, j.* "
				+ "		 from (select o.id, o.order_date, p.product_image, p.product_name, oit.product_color, oit.product_size, p.product_code, "
				+ "				oit.product_count, oit.product_price, oit.orderitem_code, oit.orderstate, o.order_cost  "
				+ "				from product p, order_info o, order_item oit "
				+ "				where p.product_code=oit.product_code "
				+ "				and o.order_code=oit.order_code "
				+ "				and o.id=? "
				+ "				and oit.orderstate = '배송 취소'"
				+ "				order by o.order_date desc) j "
				+ "		where rownum<=?) "
				+ "	where rnum>=? and rnum<=?";
		
		List<Orderlist> list = new ArrayList<Orderlist>();
		
		try {
			int startrow = (page -1) * limit +1; //읽기 시작할 row번호 (1	11	   21	  31...
			int endrow = startrow + limit -1;
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql.toString());//toStrin()없애도 내부적으로 메서드를 사용해준다.
			pstmt.setString(1, id);
			pstmt.setInt(2, endrow);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, endrow);
			
			rs = pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO객체에 담는다.
			while(rs.next()) {//더이상 읽을 데이터가 없을때까지 반복
				Orderlist orderlist = new Orderlist();
				orderlist.setOrder_date(rs.getString("order_date"));
				orderlist.setProduct_image(rs.getString("product_image"));
				orderlist.setProduct_name(rs.getString("product_name"));
				orderlist.setProduct_count(rs.getInt("product_count"));
				orderlist.setOrderstate(rs.getString("orderstate"));
				orderlist.setOrderitem_code(rs.getInt("orderitem_code" ));
				orderlist.setId(rs.getString("id"));
				orderlist.setProduct_price(rs.getInt("product_price"));
				orderlist.setOrder_cost(rs.getInt("order_cost"));
				orderlist.setProduct_color(rs.getString("product_color"));
				orderlist.setProduct_size(rs.getString("product_size"));
				list.add(orderlist);//값을 담은 객체를 리스트에 저장
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("orderlist() 에러: " +e);
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

	public int update(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		
		try {
			conn = ds.getConnection();
			
			String select_sql = "update order_item set "
					+ "orderstate='배송 취소'"
					+ "where orderitem_code = ? ";
			//PreparedStatement 객체 얻기
			pstmt = conn.prepareStatement(select_sql.toString());
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if(result==1) {
				System.out.println("주문 취소 완료");
			}
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

	public int getcount1(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x =0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*)"
					+ "   from product p, order_info o, order_item oit"
					+ "   where p.product_code=oit.product_code"
					+ "   and o.order_code=oit.order_code"
					+ "   and o.id= ?"
					+ "   and oit.orderstate='배송 전' ";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getcount1() 에러: " +ex);
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
		return x;
	}

	public int getcount2(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x =0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*)"
					+ "   from product p, order_info o, order_item oit"
					+ "   where p.product_code=oit.product_code"
					+ "   and o.order_code=oit.order_code"
					+ "   and o.id= ?"
					+ "   and oit.orderstate='배송 완료' ";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getcount2() 에러: " +ex);
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
		return x;
	}

	public Orderlist product(int product_code) {
		Orderlist o = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			/*context.xml에 생성해 놓은 (JNDI에 설정해 놓은) 리소스 jdbc/OracleDB를
			  참조하여 Connection 객체를 얻어온다.*/
			conn = ds.getConnection();
			
			String select_sql = "select * from product where product_code=?";
			
			//PreparedStatement 객체 얻기
			pstmt = conn.prepareStatement(select_sql.toString());
			pstmt.setInt(1, product_code);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {//더이상 읽을 데이터가 없을 때까지 반복
				o = new Orderlist();
				o.setProduct_name(rs.getString("product_name"));
			}
		}catch(Exception se) {
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
				System.out.println(e.getMessage());
			}
			if(conn!=null)
			try {
					conn.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}//finally end
		return o;
	}

	public int getListCount(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x =0;//db에 해당 아이디가 존재하지 않는 경우
		
		try {
			conn = ds.getConnection();
			String select_sql = "select count(*) "
					+ "   from product p, order_info o, order_item oit "
					+ "   where p.product_code=oit.product_code "
					+ "   and o.order_code=oit.order_code "
					+ "   and o.id=? "
					+ "   and (1=2 or oit.orderstate='배송 전' or oit.orderstate='배송 완료')";
			//PreparedStatement 객체 얻기
			pstmt = conn.prepareStatement(select_sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
					x=rs.getInt(1);
			}
		}catch(Exception se) {
			System.out.println("리뷰 getListCount()에러:" +se);
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

	public int getCancleListCount(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x =0;//db에 해당 아이디가 존재하지 않는 경우
		
		try {
			conn = ds.getConnection();
			String select_sql = "select count(*) "
					+ " from (select o.id, o.order_date, p.product_image, p.product_name, "
					+ "		  oit.product_count, oit.product_price, oit.orderitem_code, oit.orderstate "
					+ "		  from product p, order_info o, order_item oit "
					+ "		  where p.product_code=oit.product_code "
					+ "		  and o.order_code=oit.order_code "
					+ "		  and o.id=?"
					+ "		and oit.orderstate = '배송 취소')";
			//PreparedStatement 객체 얻기
			pstmt = conn.prepareStatement(select_sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
					x=rs.getInt(1);
			}
		}catch(Exception se) {
			System.out.println("리뷰 getListCount()에러:" +se);
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

	public int update_delivery(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		
		try {
			conn = ds.getConnection();
			
			String select_sql = "update order_item set "
					+ "orderstate='배송 완료'"
					+ "where orderitem_code = ? ";
			//PreparedStatement 객체 얻기
			pstmt = conn.prepareStatement(select_sql.toString());
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if(result==1) {
				System.out.println("배송 완료");
			}
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

}
