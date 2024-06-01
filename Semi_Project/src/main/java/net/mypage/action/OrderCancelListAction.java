package net.mypage.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mypage.db.Order_infoDAO;
import net.mypage.db.Orderlist;

public class OrderCancelListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		int page=1;//보여줄 page
		int limit =5;//한 페이지에 보여줄 게시판 목록의 수

		List<Orderlist> list = null;
		Order_infoDAO dao = new Order_infoDAO();
		
		list = dao.getCancel(id, page, limit);

		
		if(request.getParameter("page")!= null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 주문취소 페이지 =" +page);
		
		int listcount = dao.getCancleListCount(id);
		System.out.println("주문취소 목록 수 " + listcount);
		
		list=dao.getCancel(id,page,limit);
		int maxpage = (listcount + limit -1)/limit;
		int startpage = ((page-1)/10) *10 +1;
		int endpage = startpage +10-1;
		if(endpage>maxpage)
			endpage=maxpage;
		

		if(list==null) {
			forward.setPath("error/error.jsp");
			forward.setRedirect(false);
			request.setAttribute("message", "주문 정보가 존재하지 않습니다.");
			return forward;
		}
		request.setAttribute("page", page);//현재 페이지 수
		request.setAttribute("maxpage", maxpage);//최대 페이지 수
		//현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("startpage", startpage);
		//현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		request.setAttribute("limit", limit);
		
		
		forward.setRedirect(false);
		forward.setPath("mypage/Ordercancel.jsp");
		return forward;
	}

}
