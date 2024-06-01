package net.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.main.db.CustomerDAO;


public class MemberResetSuccessAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();

		
		String newpass = request.getParameter("newpass");
		String id = request.getParameter("checkid");
		System.out.println("newpass=" + newpass);
		System.out.println("id="+ id);
		int result = 0;
		
		CustomerDAO cdao = new CustomerDAO();
		result = cdao.reset(id, newpass);
		System.out.println("result= " + result);
		
		if(result == 1) {
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);    
			forward.setPath("login.net");
			return forward; 
		} else {
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);    
			forward.setPath("mainpage/resetpass.jsp");
			return forward; 
		}
	}
}
