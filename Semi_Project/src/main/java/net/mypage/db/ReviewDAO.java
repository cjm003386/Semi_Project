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




public class ReviewDAO {
	private DataSource ds;
	public ReviewDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : " +ex);
			return;
		}
	}

	public int getListCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x =0;//db에 해당 아이디가 존재하지 않는 경우
		
		try {
			conn = ds.getConnection();
			String select_sql = "select count(*) from review";
			//PreparedStatement 객체 얻기
			pstmt = conn.prepareStatement(select_sql.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
					x=rs.getInt(1);
			}
		}catch(Exception se) {
			System.out.println("getListCount()에러:" +se);
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
	public int getListCount(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x =0;//db에 해당 아이디가 존재하지 않는 경우
		
		try {
			conn = ds.getConnection();
			String select_sql = "select count(*) from review"
					+ " where review_name =?";
			//PreparedStatement 객체 얻기
			pstmt = conn.prepareStatement(select_sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x=rs.getInt(1);
			}
		}catch(Exception se) {
			System.out.println("getListCount()에러:" +se);
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

	public List<ReviewBean> getList(int page, int limit) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		//page:페이지 limit: 페이지 당 목록 수 board_re_ref desc, board_re_seq asc에 의해 정렬한것을
		//조건절에 맞는 rnum의 범위만큼 가져오는 쿼리문
		String board_list_sql = "select *  "
				+ "from (select rownum rnum, j.* "
				+ "	from ( "
				+ "			SELECT review.*, nvl(cnt,0) as cnt "
				+ "			FROM review left outer join(select comment_review_num, count(*) cnt "
				+ "										from review_comm "
				+ "										group by comment_review_num) "
				+ "			on review_num = comment_review_num "
				+ "			order by review_re_ref desc, "
				+ "			review_re_seq asc "
				+ "			) j "
				+ "		where rownum<=? "
				+ "		)"
				+ "	where rnum>=? and rnum<=? ";
		
		List<ReviewBean> list = new ArrayList<ReviewBean>();
		//한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지...
		int startrow = (page -1) * limit +1; //읽기 시작할 row번호 (1	11	   21	  31...
		int endrow = startrow + limit -1;//읽을 마지막 row번호     (10	20	   30	  40...
		
		try {
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(board_list_sql.toString());//toStrin()없애도 내부적으로 메서드를 사용해준다.
			pstmt.setInt(1, endrow);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO객체에 담는다.
			while(rs.next()) {//더이상 읽을 데이터가 없을때까지 반복
				ReviewBean r = new ReviewBean();
				r.setReview_num(rs.getInt("review_num"));
				r.setReview_name(rs.getString("review_name"));
				r.setReview_pass(rs.getString("review_pass"));
				r.setReview_subject(rs.getString("review_subject"));
				r.setReview_content(rs.getString("review_content"));
				r.setReview_file(rs.getString("review_file"));
				r.setReview_re_ref(rs.getInt("review_re_ref"));
				r.setReview_re_lev(rs.getInt("review_re_lev"));
				r.setReview_re_seq(rs.getInt("review_re_seq"));
				r.setReview_readcount(rs.getInt("review_readcount"));
				r.setReview_date(rs.getString("review_date"));
				r.setCnt(rs.getInt("cnt"));
				list.add(r);//값을 담은 객체를 리스트에 저장
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

	public int getListCount(String field, String value) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x =0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) from review "
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

	public List<ReviewBean> getList(String field, String value, int page, int limit) {
		List<ReviewBean> list = new ArrayList<ReviewBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select * "
					+ "		from (select rownum rnum, j.* "
					+ "	from ( "
					+ "		SELECT review.*, nvl(cnt,0) as cnt "
					+ "		FROM review left outer join(select comment_review_num, count(*) cnt "
					+ "									from review_comm "
					+ "				group by comment_review_num) "
					+ "		on review_num = comment_review_num "
					+ "	where " + field + " like ?"
					+ "		order by review_re_ref desc, "
					+ "			review_re_seq asc "
					+ "				) j "
					+ "		where rownum<=? "
					+ "			) "
					+ "		where rnum>=? and rnum<=?";
			
			System.out.println("review getList: "+sql);
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
				ReviewBean r = new ReviewBean();
				r.setReview_num(rs.getInt("review_num"));
				r.setReview_name(rs.getString("review_name"));
				r.setReview_pass(rs.getString("review_pass"));
				r.setReview_subject(rs.getString("review_subject"));
				r.setReview_content(rs.getString("review_content"));
				r.setReview_file(rs.getString("review_file"));
				r.setReview_re_ref(rs.getInt("review_re_ref"));
				r.setReview_re_lev(rs.getInt("review_re_lev"));
				r.setReview_re_seq(rs.getInt("review_re_seq"));
				r.setReview_readcount(rs.getInt("review_readcount"));
				r.setReview_date(rs.getString("review_date"));
				r.setCnt(rs.getInt("cnt"));
				list.add(r);//값을 담은 객체를 리스트에 저장
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

	public boolean reviewInsert(ReviewBean review) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			conn = ds.getConnection();
			
			//원문글의 BOARD_RE_REF는 자신의 글번호가된다.
			String sql = "insert into review "
					+ "	   (review_num, review_name,review_pass, review_subject,"
					+ "		review_content, review_file, review_re_ref, "
					+ "		review_re_lev,  review_re_seq, review_readcount, product_name)"
					+ "  	values(review_seq.nextval, ?, ?, ?,"
					+ "		   		?, ?, review_seq.nextval,"
					+ "		    	?,?,?,?)";
			//새로운 글을 등록한다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getReview_name());
			pstmt.setString(2, review.getReview_pass());
			pstmt.setString(3, review.getReview_subject());
			pstmt.setString(4, review.getReview_content());
			pstmt.setString(5, review.getReview_file());
			//원문의 경우 BOARD_RE_LEV, BOARD_RE_SEQ 필드 값은 0
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);//Readcount
			pstmt.setString(9, review.getProduct_name());
			
			result = pstmt.executeUpdate();
			if(result==1) {
				System.out.println("데이터 삽입 완료");
				return true;
			}
				
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("reviewInsert() 에러: " +ex);
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

	public void setReadCountUpdate(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sel_where_sql =
				"update review set "
						+ "review_readcount =review_readcount+1"
						+ "where review_num = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sel_where_sql.toString());//toStrin()없애도 내부적으로 메서드를 사용해준다.
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		}catch(SQLException ex) {
			System.out.println("setReadCountUpdate() 에러:" + ex);
			ex.printStackTrace();
		} finally {
			if(pstmt !=null)
			try {
					pstmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			if(conn != null)
			try {
					conn.close();//DB연결을 끊는다.
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
		}//finally
		
	}

	public ReviewBean getDetail(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReviewBean review = null;
		
		try {
			/*context.xml에 생성해 놓은 (JNDI에 설정해 놓은) 리소스 jdbc/OracleDB를
			  참조하여 Connection 객체를 얻어온다.*/
			conn = ds.getConnection();
			
			String select_sql = "select * from review where review_num=?";
			
			//PreparedStatement 객체 얻기
			pstmt = conn.prepareStatement(select_sql.toString());
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {//더이상 읽을 데이터가 없을때까지 반복
				review= new ReviewBean();
				review.setReview_num(rs.getInt("review_num"));
				review.setReview_name(rs.getString("review_name"));
				review.setReview_subject(rs.getString("review_subject"));
				review.setReview_content(rs.getString("review_content"));
				review.setReview_file(rs.getString("review_file"));
				review.setReview_re_ref(rs.getInt("review_re_ref"));
				review.setReview_re_lev(rs.getInt("review_re_lev"));
				review.setReview_re_seq(rs.getInt("review_re_seq"));
				review.setReview_readcount(rs.getInt("review_readcount"));
				review.setReview_date(rs.getString("review_date"));
				review.setProduct_name(rs.getString("product_name"));

			}
		}catch(Exception se) {
			System.out.println("getDetail()에러:" +se);
			se.printStackTrace();
		} finally {
			if (rs!=null)
			try {
					rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
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
		return review;
	}

	public boolean isReviewWriter(int num, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result =false;//db에 해당 아이디가 존재하지 않는 경우
		String select_sql = "select review_pass from review where review_num = ?";
		
		try {
			conn = ds.getConnection();
			//PreparedStatement 객체 얻기
			pstmt = conn.prepareStatement(select_sql.toString());
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
					if(pass.equals(rs.getString("review_pass"))) {
						result=true;
					}
			}
		}catch(SQLException se) {
			System.out.println("isReviewWriter()에러:"+se);
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
		return result;
	}

	public boolean reviewmodify(ReviewBean review) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result =0;
		try {
			conn = ds.getConnection();
			//원문글의 BOARD_RE_REF는 자신의 글번호가된다.
			String sql = "update review set "
					+ "review_subject =? ,"
					+ "review_content =? , "
					+ "review_file =? "
					+ "where review_num = ?";
			//새로운 글을 등록한다.
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getReview_subject());
			pstmt.setString(2, review.getReview_content());
			pstmt.setString(3, review.getReview_file());
			pstmt.setInt(4, review.getReview_num());
			
			result = pstmt.executeUpdate();
			if(result==1) {
				System.out.println("리뷰 업데이트 완료");
				return true;
			}
				
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("reviewmodify() 에러: " +ex);
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

	public boolean reviewDelete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null, pstmt2=null;
		ResultSet rs = null;
		String select_sql = "select review_re_ref, review_re_lev, review_re_seq "
				+ "	from review"
				+ " where review_num=?";
		
		String delete_sql =
		"delete from review "
		+ "				where  review_re_ref = ?"
		+ "				and    review_re_lev >=?"
		+ "				and    review_re_seq >=?"
		+ "				and    review_re_seq <=(  nvl((SELECT min(review_re_seq)-1"
		+ "				                             FROM   review"
		+ "				                             WHERE  review_re_ref=?"
		+ "				                             AND    review_re_lev=?"
		+ "				                             AND    review_re_seq>?) , "
		+ "				                            (SELECT max(review_re_seq)"
		+ "				                             FROM   review"
		+ "							                 WHERE  review_re_ref=? ))"
		+ "											 )";
		boolean result_check = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(select_sql);
			pstmt.setInt(1,num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				pstmt2 = conn.prepareStatement(delete_sql);
				pstmt2.setInt(1, rs.getInt("review_re_ref"));
				pstmt2.setInt(2, rs.getInt("review_re_lev"));
				pstmt2.setInt(3, rs.getInt("review_re_seq"));
				pstmt2.setInt(4, rs.getInt("review_re_ref"));
				pstmt2.setInt(5, rs.getInt("review_re_lev"));
				pstmt2.setInt(6, rs.getInt("review_re_seq") );
				pstmt2.setInt(7, rs.getInt("review_re_ref"));
				
				int count=pstmt2.executeUpdate();
				if(count>=1)
					result_check=true;//삭제 안된 경우 false반환
			}
			
		}catch(Exception ex) {
			System.out.println("reviewDelete() 에러:" + ex);
			ex.printStackTrace();
		} finally {
			if(rs !=null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if(pstmt !=null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if(pstmt2 !=null)
				try {
					pstmt2.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if(conn != null)
				try {
					conn.close();//DB연결을 끊는다.
				}catch (SQLException ex) {
					ex.printStackTrace();
				}
		}//finally
		return result_check;
	}

	public int reviewReply(ReviewBean review) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		
		//board테이블의 글번호를 구하기 위해 board_num 컬럼의 최대값+1을 구해온다.
		String review_max_sql = "select max(review_num)+1 from review";
		/*
		 * 답변을 할 원문 글 그룹 번호
		 * 답변을 달게되면 답변 글은 이 번호와 같은 관련글 번호를 갖게 처리되면서
		 * 같은 그룹에 속하게 된다. 글 목록에서 보여줄 때 하나의 그룹으로 묶어 출력된다.
		 */
		int re_ref = review.getReview_re_ref();

		
		/*
		 * 답글의 깊이를 의미
		 * 원문에 대한 답글이 출력될 때 한 번 들여쓰기 처리가 되고 답글에 대한 답글은 들여쓰기가 두 번 처리되게한다.
		 * 원문인 경우에는 이 값이 0이고 원문의 답글은 1, 답글의 답글은 2가된다.
		 */
		int re_lev = review.getReview_re_lev();

		/*
		 * 같은 관련 글 중에서 해당 글이 출력되는 순서
		 */
		int re_seq = review.getReview_re_seq();
		
		try {
			conn = ds.getConnection();
			//트랜잭션을 이용하기 위해 setAutoCommit을 false로 설정
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(review_max_sql);//toStrin()없애도 내부적으로 메서드를 사용해준다.
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num=rs.getInt(1);
			}
			pstmt.close();
			
			//BOARD_RE_REF, BOARD_RE_SEQ 값을 확인하여 원문 글에 다른 답글이 있으면
			//다른 답글들의 BOARD_RE_SEQ값을 1씩 증가
			//현재 글을 다른 답글보다 앞에 출력되게 하기 위해서
			String sql = " update review " 
				     + "set    review_re_seq = review_re_seq + 1 " 
					 + "where  review_re_ref = ? "
				  	 + "and    review_re_seq > ?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
		    pstmt.executeUpdate();
			pstmt.close();
		    
			//등록할 답변 글의 BOARD_RE_LEV, BOARD_RE_REF값을 원문 글보다 1씩 증가시킨다.
			re_seq = re_seq +1;
			re_lev = re_lev +1;
			
			sql = "insert into review " 
				     + "(review_num,review_name,review_pass,review_subject,"
					 + " review_content, review_file, review_re_ref," 
				     + " review_re_lev, review_re_seq, review_readcount) " 
				     + "values(" + num + ","
				     		+ "?,?,?,"
				     		+ "?,?,?,"
				     		+ "?,?,?)";   
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getReview_name());
			pstmt.setString(2, review.getReview_pass());
			pstmt.setString(3, review.getReview_subject());
			pstmt.setString(4, review.getReview_content());
			pstmt.setString(5, "");//답변에는 파일을 업로드하지 않는다.
			pstmt.setInt(6, re_ref);//원문 글번호
			pstmt.setInt(7, re_lev);
			pstmt.setInt(8, re_seq);
			pstmt.setInt(9, 0);//BOARD_READCOUNT(조회수)는 0
			
			if(pstmt.executeUpdate()==1) {
				conn.commit();//commit
			}else {
				conn.rollback();
			}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("reviewReply() 에러:" + ex);
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
		return num;
	}

	public List<ReviewBean> getList(int page, int limit, String id) {
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		//page:페이지 limit: 페이지 당 목록 수 board_re_ref desc, board_re_seq asc에 의해 정렬한것을
		//조건절에 맞는 rnum의 범위만큼 가져오는 쿼리문
		String board_list_sql = "select * "
				+ "from (select rownum rnum, j.* "
				+ "	from ( "
				+ "	SELECT review.*, nvl(cnt,0) as cnt "
				+ "	FROM review left outer join(select comment_review_num, count(*) cnt "
				+ "								from review_comm "
				+ "								group by comment_review_num"
				+ "								) "
				+ "								on review_num = comment_review_num "
				+ "	where review_name=? "
				+ "								order by review_re_ref desc, "
				+ "	review_re_seq asc "
				+ "	) j "
				+ "	where rownum<=? "
				+ "	) "
				+ "where rnum>=? and rnum<=?";
		
		List<ReviewBean> list = new ArrayList<ReviewBean>();
		//한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지...
		int startrow = (page -1) * limit +1; //읽기 시작할 row번호 (1	11	   21	  31...
		int endrow = startrow + limit -1;//읽을 마지막 row번호     (10	20	   30	  40...
		
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
				ReviewBean r = new ReviewBean();
				r.setReview_num(rs.getInt("review_num"));
				r.setReview_name(rs.getString("review_name"));
				r.setReview_pass(rs.getString("review_pass"));
				r.setReview_subject(rs.getString("review_subject"));
				r.setReview_content(rs.getString("review_content"));
				r.setReview_file(rs.getString("review_file"));
				r.setReview_re_ref(rs.getInt("review_re_ref"));
				r.setReview_re_lev(rs.getInt("review_re_lev"));
				r.setReview_re_seq(rs.getInt("review_re_seq"));
				r.setReview_readcount(rs.getInt("review_readcount"));
				r.setReview_date(rs.getString("review_date"));
				r.setCnt(rs.getInt("cnt"));
				list.add(r);//값을 담은 객체를 리스트에 저장
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

	public List<ReviewBean> getList(String field, String value, int page, int limit, String id) {
		List<ReviewBean> list = new ArrayList<ReviewBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			String sql = "select * "
					+ "from  (select rownum rnum, review_num, review_name, "
					+ " 	  review_pass, review_subject,review_content, "
					+ "  	  review_file, review_re_ref, review_re_lev,  review_re_seq, "
					+ "   	  review_readcount, review_date"
					+ "    	  from (SELECT * FROM review "
					+ "				where " + field + " like ?"
							+ "		and review_name=?"
					+ " 			ORDER BY review_re_ref DESC, "
					+ " 			review_re_seq ASC)"
					+ " 			where rownum<=?) "
					+ " 			where rnum>=? and rnum<=?";
			
			
			System.out.println("review getList: "+sql);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+value+"%");
			//한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지...
			int startrow = (page -1) * limit +1; 
						//읽기 시작할 row번호 (1	11	   21	  31...
			int endrow = startrow + limit -1;
						//읽을 마지막 row번호     (10	20	   30	  40...
			
			pstmt.setString(2, id);
			pstmt.setInt(3, endrow);
			pstmt.setInt(4, startrow);
			pstmt.setInt(5, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ReviewBean r = new ReviewBean();
				r.setReview_num(rs.getInt("review_num"));
				r.setReview_name(rs.getString("review_name"));
				r.setReview_pass(rs.getString("review_pass"));
				r.setReview_subject(rs.getString("review_subject"));
				r.setReview_content(rs.getString("review_content"));
				r.setReview_file(rs.getString("review_file"));
				r.setReview_re_ref(rs.getInt("review_re_ref"));
				r.setReview_re_lev(rs.getInt("review_re_lev"));
				r.setReview_re_seq(rs.getInt("review_re_seq"));
				r.setReview_readcount(rs.getInt("review_readcount"));
				r.setReview_date(rs.getString("review_date"));
				list.add(r);//값을 담은 객체를 리스트에 저장
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
	
}
