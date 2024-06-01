package net.product.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.action.MemberAgreeProcessAction;
import net.main.action.MemberIdFindAction;
import net.main.action.MemberIdFindSuccessAction;
import net.main.action.MemberJoinAction;
import net.main.action.MemberJoinSuccessAction;
import net.main.action.MemberLoginAction;
import net.main.action.MemberPassFindAction;
import net.main.action.MemberPassFindSuccessAction;
import net.main.action.MemberResetAction;
import net.main.action.MemberSelfCertiAction;

@WebServlet("*.do")
public class productFrontController extends javax.servlet.http.HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		ActionForward forward = null;
		Action action = null;
		
		switch(command) {
		case "/cart.do":
			action = new ProductCartAction(); 
			break;

		case "/cartadd.do":
			action = new ProductCartAddAction(); 
			break;
			
		case "/cartdelete.do":
			action = new ProductCartDeleteAction(); 
			break;
			
		case "/cartupdate.do":
			action = new ProductCartUpdateAction(); 
			break;
			
		case "/cartTowishlist.do":
			action = new ProductCartToWishlistAction(); 
			break;
	
		case "/accessories.do":
			action = new CategoryAccAction(); 
			break;
		
		case "/bottom.do":
			action = new CategoryBottomAction(); 
			break;
			
		case "/outer.do":
			action = new CategoryOuterAction(); 
			break;
			
		case "/top.do":
			action = new CategoryTopAction(); 
			break;
			
		case "/detail.do":
			action = new ProductDetailAction(); 
			break;
			
		case "/search.do":
			action = new ProductSearchAction(); 
			break;
			
		case "/wishlist.do":
			action = new ProductWishlistAction(); 
			break;
			
		case "/wishlistadd.do":
			action = new ProductWishlistAddAction(); 
			break;
			
		case "/wishlistdelete.do":
			action = new ProductWishlistDeleteAction(); 
			break;
			
		case "/wishlistupdate.do":
			action = new ProductWishlistUpdateAction(); 
			break;
			
		case "/wishlistTocart.do":
			action = new ProductWishlistToCartAction(); 
			break;
		}
		
		forward = action.execute(request, response);
		
		
		if(forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher =
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request,response);
			}
		}
	
	
	}
			
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}
}
