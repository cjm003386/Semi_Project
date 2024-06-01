package net.payment.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.payment.db.MemberDAO_SM;
import net.payment.db.MemberVO_SM;

public class PaymentAction implements Action {

		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			
			/*
			// 아임포트 결제창을 사용하기 위한 전제조건은 먼저 로그인을 해야 하는 것이다.
			if( super.checkLogin(request) ) {
				// 로그인을 했으면
			*/	
//				String id = request.getParameter("id"); // 주소창에 넘어온 id
				
				HttpSession session = request.getSession();
				String id = (String)session.getAttribute("id");
				if(id !=null ) {
					
					// 회원정보를 조회해서 뷰단에 보낸다.(getParameter로 넘어온 id를 이용)
					MemberDAO_SM mdao = new MemberDAO_SM();
					MemberVO_SM  member = mdao.showMemberInfo(id) ;
					
					request.setAttribute("name", member.getName());
					request.setAttribute("address", member.getAddress());
					request.setAttribute("email", member.getEmail()); 
					request.setAttribute("tel", member.getTel());
				
				
					ActionForward forward = new ActionForward();
					forward.setRedirect(false);
					forward.setPath("/payment/payment.jsp");//주문처리 보기 페이지로 이동 경로 설정
					return forward;
	
				}else {
				// 로그인을 안 했으면
				String message = "결제를 하기 위해서는 먼저 로그인을 하세요!!";
				 
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('" + message + "');");
				out.println("location.href='history.back()';");
				out.println("</script>");
				out.close();
				return null;
				     
				
			}
	
		}

	}