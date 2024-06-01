package net.mypage.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mypage.db.AddressBean;
import net.mypage.db.AddressDAO;

public class addresslistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		AddressDAO dao = new AddressDAO();
		
		
		List<AddressBean> list = null;
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		list=dao.getList(id);
		request.setAttribute("list", list);
		
		if(list != null) {
			forward.setRedirect(false);
			forward.setPath("mypage/AddressList.jsp");
			return forward;
		}
		return null;
	}

}
