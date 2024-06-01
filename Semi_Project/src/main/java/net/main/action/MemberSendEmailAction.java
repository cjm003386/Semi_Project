package net.main.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import org.apache.commons.lang3.RandomUtils;


public class MemberSendEmailAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("email");
		String receiver = "";
		
		if(email.contains("@")) {
			receiver = email;
		} else {
			String domain = request.getParameter("domain");
			String sel_domain = request.getParameter("sel_domain");
			System.out.println(email);
			System.out.println(domain);
			System.out.println(sel_domain);
			
			if(sel_domain == "") {
				receiver = email + "@" + domain;
			} else {
				receiver = email + "@" + sel_domain;
			}
		}		
		System.out.println("받는 사람 = " + receiver);		
		
		String sender = "bullard90@naver.com";
		String subject = "안녕하세요 Online Shop입니다";
		int certification = RandomUtils.nextInt(100000, 1000000);
		String content = "<h1>Code : <h1>" + "<strong>" + certification + "</strong>";  
				
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter writer = response.getWriter();				
				
		String server = "smtp.naver.com";
		int port=587;
		try{
			Properties properties = new Properties();
			properties.put("mail.smtp.host", server);
			properties.put("mail.smtp.port", port);
			properties.put("mail.smtp.ssl.protocols","TLSv1.2");
	
			
			Session s = Session.getInstance(properties);
			
			Address sender_address=new InternetAddress(sender);
			Address receiver_address=new InternetAddress(receiver);
			
			Message message = new MimeMessage(s);
			
			message.setHeader("content-type", "text/html;charset=euc-kr");			
			message.setFrom(sender_address);			
			message.addRecipient(Message.RecipientType.TO, receiver_address);			
			message.setSubject(subject);			
			message.setContent(content, "text/html; charser=utf-8");			
			message.setSentDate(new java.util.Date());			
			
			Transport transport = s.getTransport("smtp");			
			transport.connect(server, MyEmailInfo.naverid, MyEmailInfo.naverpass);			
			transport.sendMessage(message, message.getAllRecipients());			
			transport.close();
			
			HttpSession session = request.getSession();		
			session.setAttribute("certification", certification);
				
			if(email.contains("@")) {
				writer.println("<script>alert('인증메일이 정상적으로 전송되었습니다'); location.href='mainpage/selfCerti.jsp'; </script>"); 
			} else {
				writer.println("<script>alert('인증메일이 정상적으로 전송되었습니다'); history.back(); </script>"); 
			}
			
	        PrintWriter out = response.getWriter();
	        out.print("인증코드를 전송하였습니다");

		}catch(Exception e) {
			if(email.contains("@")) {
				writer.println("<script>alert('전송할 수 없는 이메일입니다.'); location.href='mainpage/selfCerti.jsp';</script>"); 
			} else {
				writer.println("<script>alert('전송할 수 없는 이메일입니다.'); history.back(); </script>"); 
			}
			e.printStackTrace();
		} finally {
			writer.close();
		}
		return null;
		
		

	}
}
