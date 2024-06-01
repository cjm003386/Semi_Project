package net.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.admin.db.Customer;
import net.admin.db.CustomerDAO;


public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		CustomerDAO customerdao = new CustomerDAO();
		Customer customer = new Customer();
		customer.setId(request.getParameter("id"));		
		customer.setName(request.getParameter("name"));
		customer.setPhone(request.getParameter("phone"));
		customer.setPost(request.getParameter("post")); 
		customer.setAddress(request.getParameter("address"));
		customer.setEmail(request.getParameter("email") + "@" + request.getParameter("email2"));
		customer.setGender(request.getParameter("gender"));
		customer.setGrade(request.getParameter("grade"));
		int c = customerdao.customer_update(customer);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		//삽입이 된 경우
		if (c == 1) {
			out.println("alert('회원 정보가 수정되었습니다.');");
			out.println("location.href='memberlist.com';");
		} else {
			out.println("alert('회원 정보 수정에 실패했습니다.');");
			out.println("history.back();");
		}
		out.println("</script>");
		out.close();
		return null;
			
			
	}


}
