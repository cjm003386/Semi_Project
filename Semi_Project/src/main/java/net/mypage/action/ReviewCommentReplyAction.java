package net.mypage.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mypage.db.ReviewComment;
import net.mypage.db.ReviewCommentDAO;

public class ReviewCommentReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReviewCommentDAO dao = new ReviewCommentDAO();
		ReviewComment co = new ReviewComment();
		
		co.setId(request.getParameter("id"));
		co.setContent(request.getParameter("content"));
		co.setComment_re_lev(Integer.parseInt(request.getParameter("comment_re_lev")));
		co.setComment_review_num(Integer.parseInt(request.getParameter("comment_review_num")));
		co.setComment_re_seq(Integer.parseInt(request.getParameter("comment_re_seq")));
		co.setComment_re_ref(Integer.parseInt(request.getParameter("comment_re_ref")));
		int ok = dao.commentsReply(co);
		response.getWriter().print(ok);
		return null;
	}

}
