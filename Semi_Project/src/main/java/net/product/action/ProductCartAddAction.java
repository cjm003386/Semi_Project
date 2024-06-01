package net.product.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.product.db.CartDAO;
import net.product.db.CartDTO;

public class ProductCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  CartDTO cart = new CartDTO();
		  CartDAO dao = new CartDAO();
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
	      int cart_count =  Integer.parseInt(request.getParameter("p_num1"));
	      cart.setId(id);
	      cart.setOpt_color(color);
	      cart.setOpt_size(size);
	      cart.setProduct_code(product_code);
	      cart.setProduct_image(image);
	      cart.setProduct_name(name);
	      cart.setProduct_price(price);
	      cart.setCart_count(cart_count);
	      response.setContentType("text/html;charset=utf-8");
	      
	      int result = dao.insetCart(cart);
	      
	      if(result ==0) {
	         System.out.println("관심상품 담기 실패");
	         forward.setRedirect(false);
	         request.setAttribute("message", "관심상품 담기 실패입니다.");
	         forward.setPath("error/error.jsp");
	         return forward;
	      }
	      System.out.println("관심상품 담기 성공");
	      
	      forward.setRedirect(true);
	      forward.setPath("cart.do?id="+id);//이동할 경로 지정
	      return forward;
	      
	      
	   }

}