package net.payment.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.payment.db.Customer;
import net.payment.db.CustomerDAO;
import net.payment.db.ProductBean;
import net.payment.db.ProductDAO;

public class PurchaseAction2 implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductBean p = new ProductBean();
		ProductDAO dao = new ProductDAO();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		//상품 번호 파라미터 값을 num변수에 저장
		int num = Integer.parseInt(request.getParameter("num"));
		
		//상품 내용을 DAO에서 읽은 후 얻은 결과를 저장한다.
		System.out.println(request.getParameter("color"));
		System.out.println(request.getParameter("size"));
		String color=request.getParameter("color");
		String size=request.getParameter("size");
		p = dao.getDetail(num,color,size);
		
		Customer c = new Customer();
		CustomerDAO cdao = new CustomerDAO();
		
		c= cdao.getDetail(id);
		
		if(p==null) {
			System.out.println("상세보기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}else if(c==null) {
			String message="로그인이 필요한 기능입니다.";
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+message+"');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		}
		System.out.println("상세보기 성공");
		int count = Integer.parseInt(request.getParameter("p_num1"));//상품 상세보기에서 입력한 수량을 받아옴 
		
		
		//회원, 상품 객체를 request로 저장해 이동
		request.setAttribute("count", count );
		request.setAttribute("p", p);
		request.setAttribute("memberinfo", c);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("payment/orderprocess2.jsp");//주문처리 보기 페이지로 이동 경로 설정
		return forward;
	}

}
