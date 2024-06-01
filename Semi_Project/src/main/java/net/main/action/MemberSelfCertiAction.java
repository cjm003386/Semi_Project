package net.main.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.main.db.CustomerDAO;


public class MemberSelfCertiAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String checkid = request.getParameter("checkid");
		session.setAttribute("checkid", checkid);

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String certi_type = request.getParameter("certi");
		System.out.println("checkid=" + checkid);
		System.out.println("name=" + name);
		System.out.println("email=" + email);
		System.out.println("phone=" + phone);
		System.out.println("certi_type=" + certi_type);
		
		CustomerDAO cdao = new CustomerDAO();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(certi_type.equals("e")) {
			String result = cdao.passFindEmail(checkid, name, email);
			System.out.println("result=" + result);
				if(result != "") {
					session.setAttribute("email", email);
					session.setAttribute("certi_type", certi_type);		
					
					ActionForward forward = new ActionForward();
					forward.setRedirect(false);    
					forward.setPath("mainpage/selfCerti.jsp");
	
					return forward; 		
				} else {
					String message = "해당 비밀번호를 찾을 수 없습니다. 다시 입력해주세요.";
					out.println( "<script> location.href='passFind.net'; alert('" + message + "'); </script>" );
					out.close();
				}
				return null;
			}
		
		if(certi_type.equals("p")) {
			String result = cdao.passFindPhone(checkid, name, phone);
			System.out.println("result=" + result);
				if(result != "") {
					session.setAttribute("phone", phone);
					session.setAttribute("certi_type", certi_type);		
					
					ActionForward forward = new ActionForward();
					forward.setRedirect(false);    
					forward.setPath("mainpage/selfCerti.jsp");
	
					return forward; 		
				} else {
					String message = "해당 비밀번호를 찾을 수 없습니다. 다시 입력해주세요.";
					out.println( "<script> location.href='passFind.net'; alert('" + message + "'); </script>" );
					out.close();
				}
				return null;
			}	
		return null;
		}
	}
	


	


			

	