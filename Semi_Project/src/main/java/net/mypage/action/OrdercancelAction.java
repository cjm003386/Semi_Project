package net.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mypage.db.Order_infoDAO;



public class OrdercancelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Order_infoDAO dao = new Order_infoDAO();
		String id = request.getParameter("id");
		int num = Integer.parseInt(request.getParameter("num"));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int result = dao.update(num);
		if(result ==1) {
			out.println("<script>");
			out.println("alert('주문이 취소되었습니다.');");
			out.println("location.href='ordercancellist.pg?id="+id+"'");
			out.println("</script>");
		}else {
			out.println("</script>");
			out.println("alert('주문 취소 처리에 실패했습니다.');");
			out.println("history.back()");
			out.println("</script>");
		}
		out.close();
		return null;
	}

}
