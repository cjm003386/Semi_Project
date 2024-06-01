package net.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.mypage.db.ReviewCommentDAO;

public class ReviewCommentList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ReviewCommentDAO dao = new ReviewCommentDAO();
		
		int comment_review_num = Integer.parseInt(request.getParameter("comment_review_num"));
		System.out.println(comment_review_num);
		int state = Integer.parseInt(request.getParameter("state"));
		int listcount = dao.getListCount(comment_review_num);
		
		JsonObject object = new JsonObject();
		JsonArray jarray = dao.getCommentList(comment_review_num, state);
		object.addProperty("listcount", listcount);
		
		JsonElement je = new Gson().toJsonTree(jarray);
		object.add("reviewlist", je);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(object.toString());
		System.out.println(object.toString());
		return null;
	}

}
