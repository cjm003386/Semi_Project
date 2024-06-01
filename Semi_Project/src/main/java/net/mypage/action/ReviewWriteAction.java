package net.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mypage.db.Order_infoDAO;
import net.mypage.db.Orderlist;


public class ReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		int product_code = Integer.parseInt(request.getParameter("num"));
		Order_infoDAO dao =  new Order_infoDAO();
		Orderlist p = dao.product(product_code);
		
		if(p==null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('주문정보가 존재하지 않습니다.');");
				out.println("history.back()");
				out.println("</script>");
		}
		
		forward.setRedirect(false);//주소변경없이 jsp페이지의 내용을 보여준다.
		request.setAttribute("product", p);
		forward.setPath("mypage/ReviewWrite.jsp");
		return forward;
	}

}
