package net.mypage.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mypage.db.ReviewBean;
import net.mypage.db.ReviewDAO;


public class ReviewModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		int num = Integer.parseInt(request.getParameter("num"));
		 
		ReviewDAO dao = new ReviewDAO();
		ReviewBean reviewinfo = dao.getDetail(num);
		if(reviewinfo==null) {
			System.out.println("리뷰 수정 상세보기 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 수정 상세보기 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("리뷰 수정 상세보기 성공");
		request.setAttribute("reviewinfo", reviewinfo);
		forward.setRedirect(false);
		forward.setPath("mypage/reviewmodify.jsp");
		return forward;
	}

}
