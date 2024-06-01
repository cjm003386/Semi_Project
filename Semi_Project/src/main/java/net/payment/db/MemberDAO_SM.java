package net.payment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO_SM implements InterMemberDAO_SM {
		
		private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool) 이다.
		private Connection conn;
		private PreparedStatement pstmt;
		private ResultSet rs;
		
		// 기본생성자
		public MemberDAO_SM() {
			try {
				Context initContext = new InitialContext();
			    Context envContext  = (Context)initContext.lookup("java:/comp/env");
			    ds = (DataSource)envContext.lookup("jdbc/semioracle");
			   
			} catch(NamingException e) {
				e.printStackTrace();
			}
		}
		
		// 사용한 자원을 반납하는 close() 메소드 생성하기 
	    private void close() {
	    	try {
	    		if(rs != null)    {rs.close();    rs=null;}
	    		if(pstmt != null) {pstmt.close(); pstmt=null;}
	    		if(conn != null)  {conn.close();  conn=null;}
	    	} catch(SQLException e) {
	    		e.printStackTrace();
	    	}
	    }

	    ////////////////////////////////////////////////////////////////////////////////////
	    
	    
	    // 회원정보를 조회하는 메소드 (getParameter로 넘어온 id를 이용) -> 회원명,주소,연락처,이메일
		@Override
		public MemberVO_SM showMemberInfo(String id)  {
			
			MemberVO_SM member = null;
			
			try {
				conn = ds.getConnection();
				
				String sql = " select name, post, address, extraaddress, tel, email " + 
							 " from customer " + 
							 " where id = ? ";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					member = new MemberVO_SM();
					
					member.setName(rs.getString(1));
					member.setPost(rs.getString(2));
					member.setAddress(rs.getString(3));
					member.setExtraaddress(rs.getString(4));
					member.setTel(rs.getString(5)); 
					member.setEmail(rs.getString(6)); 
				}
				
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("getListCount() 에러: " + ex);
			} finally {
				close();
			}
			
			return member;
		}
	
	}
