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





public class NoticeDAO {

	
	private DataSource ds;
	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public NoticeDAO() {
	try {
		Context init = new InitialContext();
		ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	} catch (Exception ex) {
		System.out.println("DB 연결 실패 : " + ex);
	}
}

	
	
	//글 갯수 구하기
	public int getListCount() {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int n = 0; 
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from notice");
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

	
	
	public List<NoticeBean> getList(int page, int limit) {
		List<NoticeBean> list = new ArrayList<NoticeBean>();
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String sql = "select * "
					   + " from (select n.*, rownum rnum"
					   + "		from(select * from notice"
					   + " 			 order by notice_num desc) n"
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
				NoticeBean n = new NoticeBean();
				n.setNotice_num(rs.getInt("notice_num"));
				n.setNotice_id(rs.getString("notice_id"));
				n.setNotice_title(rs.getString("notice_title"));
				n.setNotice_content(rs.getString("notice_content"));
				n.setNotice_file(rs.getString("notice_file"));
				n.setNotice_re_ref(rs.getInt("notice_re_ref"));
				n.setNotice_re_lev(rs.getInt("notice_re_lev"));
				n.setNotice_re_seq(rs.getInt("notice_re_seq"));
				n.setNotice_date(rs.getString("notice_date"));
				n.setNotice_readcount(rs.getInt("notice_readcount"));
				list.add(n);
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
		int x =0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from notice "
					+ "where " + field + " like ?";
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x=rs.getInt(1);
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
		return x;
	}


	
	public List<NoticeBean> getList(String field, String value, int page, int limit) {
		List<NoticeBean> list = new ArrayList<NoticeBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select * "
					+ "from  (select rownum rnum, n.* "
					+ "    	  from (SELECT * FROM notice "
					+ "				where " + field + " like ?"
					+ " 			ORDER BY notice_re_ref DESC, "
					+ " 			notice_re_seq ASC) n"
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
				NoticeBean n = new NoticeBean();
				n.setNotice_num(rs.getInt("notice_num"));
				n.setNotice_id(rs.getString("notice_id"));
				n.setNotice_title(rs.getString("notice_title"));
				n.setNotice_content(rs.getString("notice_content"));
				n.setNotice_file(rs.getString("notice_file"));
				n.setNotice_re_ref(rs.getInt("notice_re_ref"));
				n.setNotice_re_lev(rs.getInt("notice_re_lev"));
				n.setNotice_re_seq(rs.getInt("notice_re_seq"));
				n.setNotice_readcount(rs.getInt("notice_readcount"));
				n.setNotice_date(rs.getString("notice_date"));
				list.add(n);//값을 담은 객체를 리스트에 저장
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



	//글 번호에 해당하는 조회수 추가
	public void setReadCountUpdate(int num) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		//try 문으로 넣는 것은 예외를 던져주는 문장들 때문이다 예)prepareStatement(); 
		//그러므로 String sql은 try 밖으로 나가도 크게 상관없다.
		try {
			con = ds.getConnection();
			
			String sql = "update notice"
					+ " set    notice_readcount = notice_readcount+1 " 
					+ " where  notice_num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		
					
		} catch (SQLException ex) {
			System.out.println("setReadCountUpdate(0 에러: " + ex);
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}//finally
	
	}


	
	//글 내용 보기
	public NoticeBean getDetail(int num) {
		NoticeBean notice = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from notice where notice_num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				notice = new NoticeBean();
				notice.setNotice_num(rs.getInt("notice_num"));
				notice.setNotice_id(rs.getString("notice_id"));
				notice.setNotice_title(rs.getString("notice_title"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setNotice_file(rs.getString("notice_file"));
				notice.setNotice_re_ref(rs.getInt("notice_re_ref"));
				notice.setNotice_re_lev(rs.getInt("notice_re_lev"));
				notice.setNotice_re_seq(rs.getInt("notice_re_seq"));
				notice.setNotice_date(rs.getString("notice_date"));
				notice.setNotice_readcount(rs.getInt("notice_readcount"));
			}
		}catch (Exception ex) {
			System.out.println("getDetail() 에러: " + ex);
		}finally {
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
		
		
		return notice;
		
	}//getDetail() 메서드 end



	public boolean noticeInsert(NoticeBean notice) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			conn = ds.getConnection();
			
			String max_sql = "(select nvl(max(notice_num),0)+1 from notice)";
			
			//원문글의 BOARD_RE_REF는 자신의 글번호가된다.
			String sql = "insert into notice "
					+ "	   (notice_num, notice_id, notice_title,"
					+ " 	notice_content , notice_file, notice_re_ref,"
					+ "		notice_re_lev, notice_re_seq, notice_readcount)"
					+ "  values(" + max_sql  + ", ?, ?,"
					+ "		    ?, ?,"+  max_sql + ","
					+ "		    ?,?,?)";
			//새로운 글을 등록한다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getNotice_id());
			pstmt.setString(2, notice.getNotice_title());
			pstmt.setString(3, notice.getNotice_content());
			pstmt.setString(4, notice.getNotice_file());
			//원문의 경우 BOARD_RE_LEV, BOARD_RE_SEQ 필드 값은 0
			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);//Readcount
			
			result = pstmt.executeUpdate();
			if(result==1) {
				System.out.println("데이터 삽입 완료");
				return true;
			}
				
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("boardInsert() 에러: " +ex);
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
		return false;
	}



	public boolean noticeDelete(int num) {
		Connection con = null;
		PreparedStatement pstmt = null,pstmt2 = null;
		ResultSet rs = null;
		String select_sql = "select notice_re_ref, notice_re_lev, notice_re_seq "
				  + " from notice"
				  + " where notice_num=?";

		
		String notice_delete_sql ="delete from notice"
				+" 				where  notice_re_ref = ?"
				+"				and    notice_re_lev >=?"
				+"				and    notice_re_seq >=?"
				+"				and    notice_re_seq <=( " 
				+"									nvl((select min(notice_re_seq)-1"
				+"                             			from   notice"
				+"                             			where  notice_re_ref=?"
				+"                             			and    notice_re_lev=?"
				+"                             			and    notice_re_seq>?) ," 
				+"                            			(select max(notice_re_seq)"
				+"                            			from   notice"
				+"										where  notice_re_ref=? ))"
                +"                        )";
		
		boolean result_check = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(select_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pstmt2 = con.prepareStatement(notice_delete_sql);
				pstmt2.setInt(1, rs.getInt("notice_re_ref"));
				pstmt2.setInt(2, rs.getInt("notice_re_lev"));
				pstmt2.setInt(3, rs.getInt("notice_re_seq"));
				pstmt2.setInt(4, rs.getInt("notice_re_ref"));
				pstmt2.setInt(5, rs.getInt("notice_re_lev"));
				pstmt2.setInt(6, rs.getInt("notice_re_seq"));
				pstmt2.setInt(7, rs.getInt("notice_re_ref"));
				
				int count=pstmt2.executeUpdate();
				
				if(count>=1)
					result_check = true; //삭제가 안된 경우에는 false를 반환합니다.
			}
		}  catch (SQLException ex) {
			System.out.println("isBoardWriter() 에러: " + ex);
		}finally {
			if (rs != null)
				try {
					rs.close();
				}catch (SQLException ex) {
					ex.printStackTrace();
				}if (pstmt != null)
					try {
						pstmt.close();
					}catch (SQLException ex) {
						ex.printStackTrace();
					}
				if (pstmt2 != null)
					try {
						pstmt2.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				if (con != null)
					try {
						con.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}//finally
		
		return result_check;
	}



	public boolean noticeModify(NoticeBean modifyboard) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update notice"
				   + " set notice_title=?, notice_content=?, notice_file=?"
				   + " where notice_num=? ";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modifyboard.getNotice_title());
			pstmt.setString(2, modifyboard.getNotice_content());
			pstmt.setString(3, modifyboard.getNotice_file());
			pstmt.setInt(4, modifyboard.getNotice_num());
			int result = pstmt.executeUpdate();
			if(result == 1) {
				System.out.println("성공 업데이트");
				return true;
			}
		}catch (Exception ex) {
			System.out.println("boardModify() 에러: " + ex);
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
		
		return false;

	}



//	public boolean isBoardWriter(int num, String id) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		boolean result = false;
//		String notice_sql = "select notice_id from notice where notice_num=?";
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(notice_sql);
//			pstmt.setInt(1, num);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				if (id.equals(rs.getString("notice_id"))) {
//					result = true;
//				}
//			}
//		} catch (SQLException ex) {
//			System.out.println("isBoardWriter() 에러: " + ex);
//		}finally {
//			if (rs != null)
//				try {
//					rs.close();
//				}catch (SQLException ex) {
//					ex.printStackTrace();
//				}if (pstmt != null)
//					try {
//						pstmt.close();
//					}catch (SQLException e) {
//						System.out.println(e.getMessage());
//					}
//				if (con != null)
//					try {
//						con.close();
//					} catch (Exception e) {
//						System.out.println(e.getMessage());
//					}
//				}//finally
//	
//
//		return result;
//	}

	
	





	


}
