package net.main.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.net")
public class MainFrontController extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		ActionForward forward = null;
		Action action = null;
		
		switch (command) {
		case "/main.net":
			action = new MainAction(); 
			break;
	
		case "/login.net":
			action = new MemberLoginAction(); 
			break;
		
		case "/loginProcess.net":
			action = new MemberLoginProcessAction(); 
			break;
			
		case "/logout.net":
			action = new MemberLogoutAction(); 
			break;
			
		case "/join.net":
			action = new MemberJoinAction(); 
			break;
			
		case "/sendEmail.net":
			action = new MemberSendEmailAction(); 
			break;
			
		case "/sendPhone.net":
			action = new MemberSendPhoneAction(); 
			break;
			
		case "/emailcertiprocess.net":
			action = new EmailCertiProcessAction(); 
			break;
			
		case "/phonecertiprocess.net":
			action = new PhoneCertiProcessAction(); 
			break;
			
		case "/idcheck.net":
			action = new MemberIdCheckAction(); 
			break;
			
		case "/idFind.net":
			action = new MemberIdFindAction(); 
			break;
			
		case "/passFind.net":
			action = new MemberPassFindAction(); 
			break;
			
		case "/agreeProcess.net":
			action = new MemberAgreeProcessAction(); 
			break;
			
		case "/joinSuccess.net":
			action = new MemberJoinSuccessAction(); 
			break;
			
		case "/idFindSuccess.net":
			action = new MemberIdFindSuccessAction(); 
			break;
			
		case "/selfCerti.net":
			action = new MemberSelfCertiAction(); 
			break;	
			
		case "/passFindSuccess.net":
			action = new MemberPassFindSuccessAction(); 
			break;
			
		case "/resetpass.net":
			action = new MemberResetAction(); 
			break;
			
		case "/resetpassSuccess.net":
			action = new MemberResetSuccessAction(); 
			break;
		}
	
		forward = action.execute(request, response); 
		
		if(forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());	
			} else {  
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request,response);
			}
		}
	}
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
			doProcess(request, response);
		}		
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			request.setCharacterEncoding("utf-8"); 
			doProcess(request, response);
		}
	}
