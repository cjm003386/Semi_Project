package net.product.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.WishlistDAO;
import net.product.db.WishlistDTO;




public class ProductWishlistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        ActionForward forward = new ActionForward();
		
		List<WishlistDTO> wishlist = null; 
		WishlistDAO dao = new WishlistDAO();

		String id = request.getParameter("id");
		
		wishlist = dao.getwishList(id);
		request.setAttribute("wishlist", wishlist);
		
		if(wishlist!=null) {
			forward.setRedirect(false);
			forward.setPath("product/wishList.jsp");
			return forward;
		}
		
		return null;
	}

}
