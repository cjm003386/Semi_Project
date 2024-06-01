package net.product.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.ProductBean;
import net.product.db.ProductDAO;

public class ProductDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO dao = new ProductDAO();
		ProductBean product = new ProductBean();
		
		int num = Integer.parseInt(request.getParameter("product_code"));
		
		product = dao.getDetail(num);
		
		if(product==null) {
			System.out.println("제품 상세보기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "상품 데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("상세보기 성공");
		
		request.setAttribute("product", product);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("product/detail.jsp");
		return forward;
	}

}
