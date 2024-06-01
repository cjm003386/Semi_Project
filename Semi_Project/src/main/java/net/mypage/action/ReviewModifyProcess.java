package net.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.mypage.db.ReviewBean;
import net.mypage.db.ReviewDAO;

public class ReviewModifyProcess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReviewDAO reviewdao = new ReviewDAO();
		ReviewBean review = new ReviewBean();
		ActionForward forward = new ActionForward();
		
		String realFolder ="";
		
		String saveFolder="reviewupload";// webapp/
		
		int fileSize= 5*1024*1024;//파일 최대 크기 5MB
		
		//실제 저장경로 지정
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder=" + realFolder);
		boolean result=false;
		try{
			MultipartRequest multi = new MultipartRequest(request, 
										realFolder,
										fileSize,
										"utf-8",
								new DefaultFileRenamePolicy());
			
			int num=Integer.parseInt(multi.getParameter("review_num"));
			String pass=multi.getParameter("review_pass");
			
			boolean usercheck= reviewdao.isReviewWriter(num,pass);
			
			//비밀번호가 다른 경우
			if(usercheck==false) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 다릅니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			}
			//BoardBean 객체에 글 등록 폼에서 입력받은 정보들을 저장한다.
			review.setReview_num(num);
			review.setReview_subject(multi.getParameter("review_subject"));
			review.setReview_content(multi.getParameter("review_content"));
			
			String check = multi.getParameter("check");
			System.out.println("check="+check);
			if(check!=null) {//파일첨부를 변경하지 않으면
				review.setReview_file(check);
			}else {
				//업로드된 파일의 시스템 상에 업로드된 실제 파일명을 얻어온다.
				String filename = multi.getFilesystemName("review_file");
				review.setReview_file(filename);
			}
			
			//글 등록 처리를 위해 DAO의 boardInsert메서드 호출
			//글 등록 폼에서 입력한 정보가 저장되어있는 boarddata객체를 전달한다.
			result=reviewdao.reviewmodify(review);
			
			//글 등록에 실패할 경우 false 반환
			if(result==false) {
				System.out.println("리뷰 수정 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "리뷰 수정 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("리뷰 수정 완료");
			
			//글 등록이 완료되면 글 목록을 보여주기 위해 :BoardList.bo로 이동한다.
			//Redirect여부를 true로 설정
			forward.setRedirect(true);
			forward.setPath("reviewdetail.pg?num="+review.getReview_num());//이동할 경로 지정
			return forward;
			
			
		}catch(IOException e){
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "리뷰 수정 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}
	}

}
