package net.mypage.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mypage.db.AddressBean;
import net.mypage.db.AddressDAO;


public class AddresslistModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int num = Integer.parseInt(request.getParameter("num"));
		
		AddressDAO adao = new AddressDAO();
		AddressBean address = adao.address_info(id,num);
		
		forward.setPath("mypage/addresslistmodify.jsp");
		forward.setRedirect(false);
		request.setAttribute("addressinfo", address);
		return forward;
	}

}
