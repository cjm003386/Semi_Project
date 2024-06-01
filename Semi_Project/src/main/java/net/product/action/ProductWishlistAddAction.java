package net.product.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.product.db.WishlistDAO;
import net.product.db.WishlistDTO;

public class ProductWishlistAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  WishlistDTO wishlist = new WishlistDTO();
	      WishlistDAO dao = new WishlistDAO();
	      ActionForward forward = new ActionForward();
	      
	      HttpSession session = request.getSession();
	      String id = (String)session.getAttribute("id");
	      if(id==null) {
	         String message="로그인이 필요한 기능입니다.";
	         response.setContentType("text/html;charset=utf-8");
	         PrintWriter out = response.getWriter();
	         out.println("<script>");
	         out.println("alert('"+message+"');");
	         out.println("history.back();");
	         out.println("</script>");
	         out.close();
	         return null;
	      }
	      int product_code = Integer.parseInt(request.getParameter("product_code"));
	      String size = request.getParameter("size");
	      String color = request.getParameter("color");
	      String image = request.getParameter("img");
	      String name = request.getParameter("name");
	      int price =  Integer.parseInt(request.getParameter("price"));
	      
	      wishlist.setId(id);
	      wishlist.setOpt_color(color);
	      wishlist.setOpt_size(size);
	      wishlist.setProduct_code(product_code);
	      wishlist.setProduct_image(image);
	      wishlist.setProduct_name(name);
	      wishlist.setProduct_price(price);
	      response.setContentType("text/html;charset=utf-8");
	      
	      int result = dao.insetwishlist(wishlist);
	      
	      if(result ==0) {
	         System.out.println("관심상품 담기 실패");
	         forward.setRedirect(false);
	         request.setAttribute("message", "관심상품 담기 실패입니다.");
	         forward.setPath("error/error.jsp");
	         return forward;
	      }
	      System.out.println("관심상품 담기 성공");
	      
	      forward.setRedirect(true);
	      forward.setPath("wishlist.do?id="+id);//이동할 경로 지정
	      return forward;
	      
	      
	   }

}