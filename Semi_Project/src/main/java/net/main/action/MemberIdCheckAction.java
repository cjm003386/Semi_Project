package net.main.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.main.db.CustomerDAO;


public class MemberIdCheckAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CustomerDAO dao = new CustomerDAO();
		int result = dao.isId(request.getParameter("id"));
		response.getWriter().println(Integer.toString(result));
		System.out.println(result);
		return null;  
	}
	
	
}
