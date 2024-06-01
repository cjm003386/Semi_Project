package net.product.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.CartDAO;

public class ProductCartUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		CartDAO dao = new CartDAO();	
		String id = request.getParameter("id");
		int cart_code = Integer.parseInt(request.getParameter("cart_code"));
		String opt_size = request.getParameter("size");
		String opt_color = request.getParameter("color");
		response.setContentType("text/html;charset=utf-8");
		
		int result = dao.updateCart(id, cart_code, opt_size, opt_color);
		
		if(result ==0) {
			System.out.println("옵션 변경 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "옵션 변경 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("옵션 변경 성공");
		
		forward.setRedirect(true);
		forward.setPath("cart.do?id="+id);//이동할 경로 지정
		return forward;
	}

}
