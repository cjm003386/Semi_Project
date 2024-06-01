package net.communityboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FAQListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("community_board/FAQList.jsp");
		
		
		return forward;
	}

}
