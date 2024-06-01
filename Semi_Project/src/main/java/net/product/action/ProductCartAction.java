package net.product.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.product.db.CartDAO;
import net.product.db.CartDTO;

public class ProductCartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		List<CartDTO> cart = null; 
		CartDAO dao = new CartDAO();

		String id = request.getParameter("id");
		
		cart = dao.getcartList(id);
		request.setAttribute("cart", cart);
		
		if(cart!=null) {
			forward.setRedirect(false);
			forward.setPath("product/cart.jsp");
			return forward;
		}
		
		return null;
		
		
		
	}

}
