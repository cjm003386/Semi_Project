package net.product.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.ProductBean;
import net.product.db.ProductDAO;

public class CategoryTopAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		ProductDAO dao = new ProductDAO();
		List<ProductBean> toplist = new ArrayList<ProductBean>();
		String type = request.getParameter("type");
		
		if(type == null) {
			toplist = dao.getTopList();			
		} else {	
			toplist = dao.getTopList(type);		
		}
		System.out.println(type);
		request.setAttribute("toplist", toplist);
		forward.setPath("product/category-Top.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
