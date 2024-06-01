package net.product.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.WishlistDAO;

public class ProductWishlistDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		WishlistDAO dao = new WishlistDAO();	
		String id = request.getParameter("id");
		int wishlist_code = Integer.parseInt(request.getParameter("wishlist_code"));		
		response.setContentType("text/html;charset=utf-8");
		
		int result = dao.deleteWishlist(id, wishlist_code);
		
		if(result ==0) {
			System.out.println("관심상품 삭제 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "관심상품 삭제 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("관심상품 삭제 성공");
		
		forward.setRedirect(true);
		forward.setPath("wishlist.do?id="+id);//이동할 경로 지정
		return forward;
	}

}
