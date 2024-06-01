package net.product.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.ProductBean;
import net.product.db.ProductDAO;



public class CategoryAccAction implements Action {

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		ProductDAO dao = new ProductDAO();
		List<ProductBean> accessorieslist = new ArrayList<ProductBean>();
		String type = request.getParameter("type");
		
		if(type == null) {
			accessorieslist = dao.getAccessoriesList();			
		} else {	
			accessorieslist = dao.getAccessoriesList(type);		
		}
		System.out.println(type);
		request.setAttribute("accessorieslist", accessorieslist);
		forward.setPath("product/category-Accessories.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
