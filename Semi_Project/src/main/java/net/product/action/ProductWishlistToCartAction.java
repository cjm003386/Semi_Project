package net.product.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.WishlistDAO;
import net.product.db.WishlistDTO;

public class ProductWishlistToCartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		WishlistDTO wishlist = new WishlistDTO();
		WishlistDAO dao = new WishlistDAO();
		
		ActionForward forward = new ActionForward();
		
		String id = request.getParameter("id");
		int product_code = Integer.parseInt(request.getParameter("product_code"));
		String size = request.getParameter("size");
		String color = request.getParameter("color");
		String image = request.getParameter("product_image");
		String name = request.getParameter("product_name");
		int price =  Integer.parseInt(request.getParameter("product_price"));
		System.out.println(price);
		wishlist.setId(id);
		wishlist.setOpt_color(color);
		wishlist.setOpt_size(size);
		wishlist.setProduct_code(product_code);
		wishlist.setProduct_image(image);
		wishlist.setProduct_name(name);
		wishlist.setProduct_price(price);
		response.setContentType("text/html;charset=utf-8");
		
		int result = dao.moveWishlistToCart(wishlist);
		
		if(result ==0) {
			System.out.println("관심상품 이동 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "관심상품 이동 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("관심상품 이동 성공");
		
		forward.setRedirect(true);
		forward.setPath("cart.do?id="+id);//이동할 경로 지정
		return forward;
	}
}
