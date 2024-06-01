package net.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mypage.db.ReviewDAO;


public class ReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean result = false;
		boolean usercheck=false;
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		ReviewDAO review = new ReviewDAO();
		//글 삭제 명령을 요청한 사용자가 글을 작성한 사용자인지 판단하기 위해
		//입력한 비밀번호와 저장된 비밀번호를 비교하여 일치하면 삭제한다.
		usercheck = review.isReviewWriter(num, request.getParameter("review_pass"));
		
		//비밀번호 일치하지 않는 경우
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
		
		//비밀번호 일치하는 경우 삭제 처리한다.
		result = review.reviewDelete(num);
		
		//삭제 처리 실패
		if(result==false) {
			System.out.println("리뷰 삭제 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "리뷰를 삭제하지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		//삭제 처리 성공 > 글 목록 보기 요청 전송
		System.out.println("리뷰 삭제 성공");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제 되었습니다.');");
		out.println("location.href='reviewlist.pg';");
		out.println("</script>");
		out.close();
		return null;
	}

}
