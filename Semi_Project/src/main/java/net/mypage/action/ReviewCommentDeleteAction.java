package net.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mypage.db.ReviewCommentDAO;


public class ReviewCommentDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
int num = Integer.parseInt(request.getParameter("num"));
		
		ReviewCommentDAO dao = new ReviewCommentDAO();
		int result = dao.commentsDelete(num);
		PrintWriter out = response.getWriter();
		out.print(result);
		return null;
	}

}
