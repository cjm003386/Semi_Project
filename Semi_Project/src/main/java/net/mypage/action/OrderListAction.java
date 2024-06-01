package net.mypage.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mypage.db.Order_infoDAO;
import net.mypage.db.Orderlist;


public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		Order_infoDAO dao = new Order_infoDAO();
		List<Orderlist> list = null;

		int page=1;
		int limit =5;
		
		if(request.getParameter("page")!= null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 주문 페이지 =" +page);
		
		int listcount = dao.getListCount(id);
		System.out.println("주문 내역의 수"+listcount);
		
		list=dao.getList(id,page,limit);
		
		int maxpage = (listcount + limit -1)/limit;
		int startpage = ((page-1)/10) *10 +1;
		int endpage = startpage +10-1;
		if(endpage>maxpage)
			endpage=maxpage;
		
		ActionForward forward = new ActionForward();

		if(list==null) {
			forward.setPath("error/error.jsp");
			forward.setRedirect(false);
			request.setAttribute("message", "주문 정보가 존재하지 않습니다.");
			return forward;
		}
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		request.setAttribute("limit", limit);
		
		forward.setRedirect(false);
		forward.setPath("mypage/OrderList.jsp");
		return forward;
	}
}
