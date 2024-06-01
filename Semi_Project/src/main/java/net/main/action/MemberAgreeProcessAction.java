package net.main.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.main.db.Customer;
import net.main.db.CustomerDAO;


public class MemberAgreeProcessAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("pass");
		String name = request.getParameter("name");
		
		String jumin1 = request.getParameter("jumin1");
		String jumin2 = request.getParameter("jumin2");
		String jumin = jumin1 + "-" + jumin2;
				
		String gender = request.getParameter("gender");
		System.out.println("gender=" + gender);
		String post = request.getParameter("post");
		
		String address = request.getParameter("address");
		String address_detail = request.getParameter("address_detail");
		
		String phone_back = request.getParameter("phone");
		String phone = "010" + phone_back;
		System.out.println(phone);
		
		String email_front = request.getParameter("email");
		String domain = request.getParameter("domain");
		String sel_domain = request.getParameter("sel_domain");
		String email = email_front + "@";
		if(sel_domain == "") {
			email += domain;
		} else {
			email += sel_domain;
		}
		
		Customer c = new Customer();
		c.setId(id);
		c.setPassword(password);
		c.setName(name);
		c.setJumin(jumin);
		c.setGender(gender);
		c.setPost(post);
		c.setAddress(address);
		c.setPhone(phone);
		c.setAddress_detail(address_detail);
		c.setEmail(email);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		CustomerDAO dao = new CustomerDAO();
		int result = dao.insert(c);
		
		if(result == 0) {
			System.out.println("회원 가입 실패입니다.");
			ActionForward forward = new ActionForward();	
			forward.setRedirect(false);
			request.setAttribute("message", "회원 가입 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;		
		}
		
		out.println("<script>");
		if(result == 1) { 
			out.println("location.href='mainpage/agree.jsp';");
			HttpSession session = request.getSession();
			session.setAttribute("join_result", c);
		} else if (result == -1) {
			out.println("alert('아이디가 중복되었습니다. 다시 입력하세요');");
			out.println("history.back()"); 
		}
		out.println("</script>");
		out.close();
		return null;
	}
}
