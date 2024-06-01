package net.mypage.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mypage.db.ReviewBean;
import net.mypage.db.ReviewDAO;

public class ReviewReplyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		ReviewDAO dao = new ReviewDAO();
		ReviewBean review = new ReviewBean();
		
		//파라미터로 전달받은 답변할 글 번호를 num변수에 저장
		int num = Integer.parseInt(request.getParameter("num"));
		
		//글 번호 num에 해당하는 내요을 가져와 boarddata 객체에 저장
		review = dao.getDetail(num);
		
		//글 내용이 없는 경우
		if(review==null) {
			System.out.println("해당 리뷰가 존재하지 않습니다.");
			forward.setRedirect(false);
			request.setAttribute("message","해당 리뷰가 존재하지 않습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("리뷰 답변 페이지 이동 완료");
		//답변 폼 페이지로 이동할 때 원본 글 내용을 보여주기 위해 
		//boarddata객체를 request객체에 저장한다.
		request.setAttribute("review", review);
		
		forward.setRedirect(false);
		//글 답변 페이지 경로 지정
		forward.setPath("mypage/reviewreply.jsp");
		return forward;
	}

}
