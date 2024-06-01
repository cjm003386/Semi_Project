package net.communityboard.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.communityboard.db.QnaBean;
import net.communityboard.db.QnaDAO;


public class QnaListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//id 세션값 
		HttpSession session = request.getSession();	
		String id = (String) session.getAttribute("id");
		session.setAttribute("id", id);
		
			ActionForward forward = new ActionForward();
			QnaDAO qdao = new QnaDAO();
			
			//로그인 성공시 파라미터 page가 없어요. 그래서 초기값이 필요합니다.
			int page=1; //보여줄 page
			int limit=5; //한 페이지에 보여줄 게시판 목록의 수
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			System.out.println("넘어온 페이지 =" + page);
			
			List<QnaBean> list = null;
			int listcount = 0;
			int index=-1;//search_field에 존재하지 않는 값으로 초기화
			
			String search_word="";
			
			
			if (request.getParameter("search_word") == null 
				|| request.getParameter("search_word").equals("")){
				//총 리스트 수를 받아옵니다.
					listcount = qdao.getListCount();
					list = qdao.getList(page,limit);
				} else { //검색을 클릭한 경우
					index=Integer.parseInt(request.getParameter("search_field"));
					String[] search_field = new String[] { "qna_title","qna_content" };
					search_word = request.getParameter("search_word");
					listcount = qdao.getListCount(search_field[index], search_word);
					list = qdao.getList(search_field[index], search_word, page, limit);
				}
			
			int maxpage = (listcount + limit -1) / limit;
			System.out.println("총 페이지수 =" + maxpage);
			
			int startpage = ((page - 1) / 10) * 10 + 1;
			int endpage = startpage + 10 - 1;
			System.out.println("현재 페이지에 보여줄 시작 페이지 수 :" + startpage);
			System.out.println("현재 페이지에 보여줄 마지막 페이지 수:" + endpage);

			 if (endpage > maxpage)
				 endpage = maxpage;
			 
				 request.setAttribute("page", page); //현재 페이지 수
				 request.setAttribute("maxpage", maxpage); //최대 페이지 수
				 
				 //현재 페이지에 표시할 첫 페이지 수
				 request.setAttribute("startpage", startpage);
				 
				 //현재 페이지에 표시할 끝 페이지 수
				 request.setAttribute("endpage", endpage);
				 
				 request.setAttribute("limit", limit); //
				 request.setAttribute("listcount", listcount); //총 글의 수
				 request.setAttribute("totallist", list);
				 request.setAttribute("search_field", index);
				 request.setAttribute("search_word", search_word);
				 
				 //글 목록 페이지로 이동하기 위해 경로를 설정합니다.
				 forward.setPath("community_board/QnaList.jsp");
				 forward.setRedirect(false);
				 return forward; 

		


			}

}
		
		
