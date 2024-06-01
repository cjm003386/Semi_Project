package net.mypage.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.pg")
public class MemberFrontController extends javax.servlet.http.HttpServlet{
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
		case "/mypage.pg":
			action = new MyPageAction();
			break;
			
		case "/reviewlist.pg":
			action = new ReviewListAction(); 
			break;
			
		case "/reviewmodify.pg":
			action = new ReviewModifyAction(); 
			break;
			
		case "/reviewmodifyprocess.pg":
			action = new ReviewModifyProcess(); 
			break;

		case "/reviewdeleteaction.pg":
			action = new ReviewDeleteAction(); 
			break;
			
		case "/reviewreplyview.pg":
			action = new ReviewReplyView(); 
			break;
			
		case "/reviewreplyaction.pg":
			action = new ReviewReplyAction(); 
			break;
			
		case "/updateprofile.pg":
			action = new UpdateProfileAction(); 
			break;
			
		case "/updateprofileProcess.pg":
			action = new UpdateProfileProcessAction(); 
			break;
			
		case "/addresslist.pg":
			action = new addresslistAction(); 
			break;

		case "/addressdelete.pg":
			action = new addressDeleteAction(); 
			break;
			
		case "/addresslistadd.pg":
			action = new AddresslistAddAction(); 
			break;
			
		case "/addresslistaddprocess.pg":
			action = new AddresslistAddProcessAction(); 
			break;
			
		case "/addresslistmodify.pg":
			action = new AddresslistModifyAction(); 
			break;

		case "/addresslistmodifyprocess.pg":
			action = new AddresslistModifyProcessAction(); 
			break;
			
		case "/memberreviewlist.pg": 
			action = new MemberReviewListAction();
			break;	
			
		case "/orderlist.pg": 
			action = new OrderListAction();
			break;	
			
		case "/ordercancellist.pg": 
			action = new OrderCancelListAction();
			break;	
		
		case "/reviewwrite.pg":
			action = new ReviewWriteAction();
			break;
		
		case "/reviewaddaction.pg": 
			action = new ReviewAddAction(); 
			break;
			
		case "/reviewdetail.pg":
			action = new ReviewDetailAction();
			break;
			
		case "/ordercancel.pg":
			action = new OrdercancelAction();
			break;

		case "/deliveryok.pg":
			action = new DeliveryOkAction();
			break;
			
		case "/reviewfiledown.pg":
			action = new ReviewFileDownAction();
			break;
			
		case "/reviewcommentlist.pg":
			action = new ReviewCommentList(); 
			break;

		case "/reviewcommentadd.pg":
			action = new ReviewCommentAddAction(); 
			break;
			
		case "/reviewcommentdelte.pg":
			action = new ReviewCommentDeleteAction(); 
			break;
			
		case "/reviewcommentreply.pg":
			action = new ReviewCommentReplyAction(); 
			break;
			
		case "/reviewcommentupdate.pg":
			action = new ReviewCommentUpdateAction(); 
			break;
			
		/*
		 * 
		 * case "/memberrivewlist.pg": action = new MemberReviewListAction(); break;
		 * 
		 */
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
