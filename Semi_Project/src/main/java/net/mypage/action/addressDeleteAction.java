package net.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mypage.db.AddressDAO;

public class addressDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AddressDAO mdao = new AddressDAO();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		int num = Integer.parseInt(request.getParameter("num"));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int result = mdao.delete(id, num);
		if(result ==1) {
			/*
			 * out.println("<script>"); out.println("alert('삭제 성공입니다.');");
			 * out.println("</script>"); 왜 안되지??
			 */
			forward.setRedirect(true);
			forward.setPath("addresslist.pg");//이동할 경로 지정
			return forward;
		}else {
			out.println("<script>");
			out.println("alert('주소 삭제가 실패되었습니다.');");
			out.println("history.back()");
			out.println("</script>");
		}
		out.close();
		return null;
	}

}
