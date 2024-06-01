package net.mypage.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mypage.db.ReviewComment;
import net.mypage.db.ReviewCommentDAO;


public class ReviewCommentUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReviewCommentDAO dao = new ReviewCommentDAO();
		ReviewComment co = new ReviewComment();
		co.setContent(request.getParameter("content"));
		System.out.println("content="+co.getContent());
		co.setNum(Integer.parseInt(request.getParameter("num")));
		int ok = dao.commentsUpdate(co);
		response.getWriter().print(ok);
		return null;
	}

}
