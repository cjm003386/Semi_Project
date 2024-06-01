package net.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("*.com")
public class AdminFrontController extends javax.servlet.http.HttpServlet{
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
		case "/noticewrite.com":
			action = new NoticeWriteAction();
			break;
		case "/noticeadd.com":
			action = new NoticeAddAction();
			break;
//		case "/qnareply.com":
//			action = new QnaReplyAction();
//			break;
		case "/memberlist.com":
			action = new MemberListAction();
			break;
		case "/memberinfo.com":
			action = new MemberInfoAction();
			break;
		case "/noticedelete.com":
			action = new NoticeDeleteAction();
			break;
		case "/noticemodifyview.com":
			action = new NoticeModifyView();
			break;
		case "/noticemodifyaction.com":
			action = new NoticeModifyAction();
			break;
		case "/memberdelete.com":
			action = new MemberDeleteInfoAction();
			break;
		case "/memberupdateinfo.com":
			action = new MemberUpdateAction();
			break;
		
		}
		
		forward = action.execute(request, response);

		if (forward != null) {
			if (forward.isRedirect()) {//리다이렉트
				response.sendRedirect(forward.getPath());
			} else {//포워딩
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
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