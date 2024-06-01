package net.mypage.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mypage.db.ReviewBean;
import net.mypage.db.ReviewDAO;

public class ReviewDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReviewDAO reviewdao = new ReviewDAO();
		ReviewBean reviewdata = new ReviewBean();
		
		//글 번호 파라미터 값을 num변수에 저장
		int num = Integer.parseInt(request.getParameter("num"));
		
		//내용을 확인 할 글의 조회수를 증가시킨다.
		reviewdao.setReadCountUpdate(num);
		
		//글의 내용을 DAO에서 읽은 후 얻은 결과를 boarddata 객체에 저장한다.
		reviewdata = reviewdao.getDetail(num);
		
		//boarddata = null ;//error 테스트를 위한 값 설정
		//DAO에서 글의 내용을 읽지 못했을 경우 null을 반환한다.
		if(reviewdata==null) {
			System.out.println("상세보기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("상세보기 성공");
		
		//boarddata 객체를 request 객체에 저장
		request.setAttribute("reviewdata", reviewdata);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("mypage/ReviewView.jsp");//글 내용 보기 페이지로 이동 경로 설정
		return forward;
	}

}
