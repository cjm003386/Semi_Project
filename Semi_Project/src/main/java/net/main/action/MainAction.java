package net.main.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.main.db.ProductBean;
import net.main.db.ProductDAO;

public class MainAction implements Action{
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductDAO pdao = new ProductDAO();
		List<ProductBean> productlist = new ArrayList<ProductBean>();
		
		productlist = pdao.getProductList();
		request.setAttribute("productlist", productlist); 

		ActionForward forward = new ActionForward();		
		forward.setPath("mainpage/main.jsp");
		forward.setRedirect(false);
		return forward;
	}
}