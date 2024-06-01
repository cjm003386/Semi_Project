package net.main.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.db.CustomerDAO;

public class MemberIdFindSuccessAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String certi_type = request.getParameter("certi");
		System.out.println("name=" + name);
		System.out.println("email=" + email);
		System.out.println("phone=" + phone);
		System.out.println("certi_type=" + certi_type);
		String id = "";
		
		CustomerDAO cdao = new CustomerDAO();

		if(certi_type.equals("e")) {
			id = cdao.idFindEmail(name,email);
			System.out.println("id=" + id);
		} else if(certi_type.equals("p")) {
			id = cdao.idFindPhone(name,phone);
			System.out.println("id=" + id);
		}
		
		if(id != "") {
			String registerdate = cdao.dateFind(id);
			System.out.println("registerdate=" + registerdate);
			
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("registerdate", registerdate);			
			
			ActionForward forward = new ActionForward();
			forward.setPath("mainpage/idFindSuccess.jsp");
			forward.setRedirect(false); 
			return forward;
			
			
		} else {
			String message = "해당하는 id가 없습니다. 다시 입력해주세요.";

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("location.href='idFind.net';");
			out.println("</script>");
			out.close();
			return null;
		}	
		
	}
}
