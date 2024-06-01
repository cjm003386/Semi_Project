package net.product.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.CartDAO;
import net.product.db.CartDTO;



public class ProductCartToWishlistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CartDTO cart = new CartDTO();
		CartDAO dao = new CartDAO();
		
		ActionForward forward = new ActionForward();
		
		String id = request.getParameter("id");
		int product_code = Integer.parseInt(request.getParameter("product_code"));
		String size = request.getParameter("size");
		String color = request.getParameter("color");
		String image = request.getParameter("product_image");
		String name = request.getParameter("product_name");
		int price =  Integer.parseInt(request.getParameter("product_price"));
		System.out.println(price);
		cart.setId(id);
		cart.setOpt_color(color);
		cart.setOpt_size(size);
		cart.setProduct_code(product_code);
		cart.setProduct_image(image);
		cart.setProduct_name(name);
		cart.setProduct_price(price);
		response.setContentType("text/html;charset=utf-8");
		
		int result = dao.moveCartToWishlist(cart);
		
		if(result ==0) {
			System.out.println("장바구니 이동 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "장바구니 이동 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("장바구니 이동 성공");
		
		forward.setRedirect(true);
		forward.setPath("wishlist.do?id="+id);//이동할 경로 지정
		return forward;
	}

}
