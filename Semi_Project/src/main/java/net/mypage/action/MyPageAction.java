package net.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mypage.db.Order_infoDAO;


public class MyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		Order_infoDAO dao = new Order_infoDAO();
		
		int delivery = dao.getcount1(id);
		int delivery2 = dao.getcount2(id);
		
		if(id==null) {
			String message="로그인이 필요한 기능입니다.";
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+message+"');");
			out.println("location.href='login.net';");
			out.println("</script>");
			out.close();
			return null;
		}else {
			forward.setRedirect(false);//주소변경없이 jsp페이지의 내용을 보여준다.
			forward.setPath("mypage/Mypage.jsp");
			request.setAttribute("delivery",delivery);
			request.setAttribute("delivery2",delivery2);
			return forward;
		}
	}

}
