package net.main.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomUtils;

public class MemberSendPhoneAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String phone = (String) session.getAttribute("phone");
		int rand = RandomUtils.nextInt(100000, 1000000);
		session.setAttribute("certification", rand);

		Naver_Sens message = new Naver_Sens();
		message.send_msg(phone, rand);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("mainpage/selfCerti.jsp");
		return forward;
	}
}
