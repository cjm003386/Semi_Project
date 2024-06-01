package net.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mypage.db.Customer;
import net.mypage.db.CustomerDAO;



public class UpdateProfileProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Customer member = new Customer();
		
		member.setId(request.getParameter("id"));
		member.setPassword(request.getParameter("pass"));
		member.setName(request.getParameter("name"));
		member.setPost(request.getParameter("post"));
		member.setAddress(request.getParameter("address1"));
		member.setAddress_detail(request.getParameter("address2"));
		member.setPhone(request.getParameter("tel"));
		member.setEmail(request.getParameter("email"));
		member.setGender(request.getParameter("gender"));
		//응답하는 데이터 타입이 html 타입이고
		//charset=utf-8로 지정하면서 응답되는 데이터 처리를 한 부분이다.
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		CustomerDAO dao = new CustomerDAO();

		int result = dao.update(member);
		response.setContentType("text/html;charset=utf-8");
		out.println("<script>");
		//업데이트가 된 경우
		if(result==1) {
			out.println("alert('수정되었습니다')");
			out.println("location.href='mypage.pg';");
		}else {
			out.println("alert('회원정보 수정에 실패했습니다.');");
			out.println("history.back()");//비밀번호 제외 다른 데이터 유지됨
		}
		out.println("</script>");
		out.close();
		return null;
	}

}
