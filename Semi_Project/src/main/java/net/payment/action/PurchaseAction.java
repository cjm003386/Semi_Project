package net.payment.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.payment.db.Customer;
import net.payment.db.CustomerDAO;
import net.payment.db.ProductBean;
import net.payment.db.ProductDAO;

public class PurchaseAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductBean p = new ProductBean();
		ProductDAO dao = new ProductDAO();
		
		//id, 상품번호, 상품 갯수 예시
		//id와 num값은 구매하기 버튼에 걸린 a링크에 쿼리스트링으로 연결해서 가지고 옵니다. ex)<a href="purchaseAction.go?id=${id}&num=${p.product_code}">
		//String id = request.getParameter("id");
		String id = "sangmin";
		
		//상품 번호 파라미터 값을 num변수에 저장
		//int num = Integer.parseInt(request.getParameter("num"));
		int num=1;
		
		//상품 내용을 DAO에서 읽은 후 얻은 결과를 저장한다.
		p = dao.getDetail(num);
		
		Customer c = new Customer();
		CustomerDAO cdao = new CustomerDAO();
		
		c= cdao.getDetail(id);
		
		if(p==null||c==null) {
			System.out.println("상세보기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("상세보기 성공");
		//int count = Integer.parseInt(request.getParameter("p_num1"));//상품 상세보기에서 입력한 수량을 받아옴 
		int count = 5;
		
		//회원, 상품 객체를 request로 저장해 이동
		request.setAttribute("count", count );
		request.setAttribute("p", p);
		request.setAttribute("memberinfo", c);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("payment/orderprocess.jsp");//주문처리 보기 페이지로 이동 경로 설정
		return forward;
	}

}
