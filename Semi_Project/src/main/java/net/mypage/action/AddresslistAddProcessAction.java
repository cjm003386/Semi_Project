package net.mypage.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mypage.db.AddressBean;
import net.mypage.db.AddressDAO;


public class AddresslistAddProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AddressBean address = new AddressBean();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		address.setAddress_id(id);
		address.setAddress_name(request.getParameter("address_name"));
		address.setAddress_receiver(request.getParameter("address_receiver"));
		address.setAddress_phone(request.getParameter("address_phone"));
		address.setAddress_post(Integer.parseInt(request.getParameter("post")));
		address.setAddress1(request.getParameter("address1"));
		address.setAddress2(request.getParameter("address2"));
		//응답하는 데이터 타입이 html 타입이고
		//charset=utf-8로 지정하면서 응답되는 데이터 처리를 한 부분이다.
		response.setContentType("text/html;charset=utf-8");

		AddressDAO dao = new AddressDAO();

		ActionForward forward = new ActionForward();
		int result = dao.insert(address);
		
		if(result==0) {
			System.out.println("주소등록 실패입니다.");
			forward.setRedirect(false);
			request.setAttribute("message", "주소등록 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("주소지 등록 완료");
		
		//Redirect여부를 true로 설정
		forward.setRedirect(true);
		forward.setPath("addresslist.pg");//이동할 경로 지정
		return forward;
	}

}
