package net.mypage.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mypage.db.ReviewBean;
import net.mypage.db.ReviewDAO;


public class ReviewReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReviewDAO dao = new ReviewDAO();
		ReviewBean review = new ReviewBean();
		ActionForward forward = new ActionForward();
		int result =0;
		
		//파라미터로 넘어온 값들을 boarddata객체에 저장
		review.setReview_name(request.getParameter("review_name"));
		review.setReview_pass(request.getParameter("review_pass"));
		review.setReview_subject(request.getParameter("review_subject"));
		review.setReview_content(request.getParameter("review_content"));
		System.out.println(request.getParameter("review_subject"));
		System.out.println(request.getParameter("review_re_ref"));
		review.setReview_re_ref(Integer.parseInt(request.getParameter("review_re_ref")));
		review.setReview_re_lev(Integer.parseInt(request.getParameter("review_re_lev")));
		review.setReview_re_seq(Integer.parseInt(request.getParameter("review_re_seq")));
		
		//답변을 DB에 저장하기 위해 boarddata 객체를 파라미터로 전달
		//DAO의 메서드 boardReply를 호출한다.
		result = dao.reviewReply(review);
		
		//답변 저장에 실패한 경우
		if(result==0) {
			System.out.println("답장 저장 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "답변 등록 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("답변 등록 완료");
		
		//답변이 완료되면 답변으로 이동한다.
		//Redirect여부를 true로 설정
		forward.setRedirect(true);
		forward.setPath("reviewdetail.pg?num="+result);//이동할 경로 지정
		return forward;
	}

}
