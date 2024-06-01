package net.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.Customer;
import net.admin.db.CustomerDAO;



public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			ActionForward forward = new ActionForward();
			CustomerDAO customerdao = new CustomerDAO();
			Customer c = new Customer();
			
			String id=request.getParameter("id");
			c = customerdao.customer_info(id); 
			
			
			if(c==null) {
				forward.setPath("error/error.jsp");
				forward.setRedirect(false);
				request.setAttribute("message", "아이디에 해당하는 정보가 없습니다.");
				return forward;
				
			}
			
			forward.setPath("adminpage/MemberInfo.jsp");
			forward.setRedirect(false);
			request.setAttribute("customerinfo", c);
			return forward;
		

	
		
		
	}

}
