package net.product.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.ProductBean;
import net.product.db.ProductDAO;

public class CategoryOuterAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		ProductDAO dao = new ProductDAO();
		List<ProductBean> outerlist = new ArrayList<ProductBean>();
		String type = request.getParameter("type");
		
		if(type == null) {
			outerlist = dao.getOuterList();			
		} else {	
			outerlist = dao.getOuterList(type);		
		}
		System.out.println(type);
		request.setAttribute("outerlist", outerlist);
		forward.setPath("product/category-Outer.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
