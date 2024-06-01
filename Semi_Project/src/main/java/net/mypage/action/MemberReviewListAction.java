package net.mypage.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mypage.db.ReviewBean;
import net.mypage.db.ReviewDAO;

public class MemberReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		ReviewDAO dao = new ReviewDAO();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		//로그인 성공 시 파라미터 page가 없어 초기값 설정이 필요하다.
		int page=1;//보여줄 page
		int limit =10;
		if(request.getParameter("page")!= null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		System.out.println("넘어온 페이지 =" +page);
		
		List<ReviewBean> list = null;
		int listcount = 0;
		int index=-1;//search_field에 존재하지 않는 값으로 초기화>>전체 회원 조회됨
		
		String search_word="";
		/*
		메뉴-관리자-회원정보 클릭한 경우(member_list.net)
		또는 메뉴-관리자-회원정보 클릭후 페이지를 클릭한 경우
		(member_list.net?page=2&search_field=-1&search_word=)
		*/
		if(request.getParameter("search_word")==null
					|| request.getParameter("search_word").equals("")) {
			///총 리스트 수를 받아온다.
			listcount = dao.getListCount(id);
			list=dao.getList(page,limit,id);
		}else {//검색 클릭시
			index=Integer.parseInt(request.getParameter("search_field"));
			String[] search_field = new String[] {"review_subject","review_content"};
			search_word = request.getParameter("search_word");
			listcount = dao.getListCount(search_field[index], search_word);
			list = dao.getList(search_field[index], search_word, page, limit,id);
		}
		
		//글이 101개라면.. 101 + 10 -1 / 10 >> 12
		int maxpage = (listcount + limit -1)/limit;
		System.out.println("총 페이지수 =" +maxpage);
		
		/*
		startpage: 현재 페이지 그룹에서 맨 처음에 표시될 페이지 수를 의미
		([1],[11],[21]등...) 보여줄 페이지가 30개일 경우
		보통 한 페이지에 10페이지 정도까지 이동할 수 있게 표시한다.
		
		예) 페이지 그룹이 아래와 같은 경우
		[1][2][3][4][5]...[10]
		페이지그룹의 시작페이지는 startpage에 마지막 페이지는 endpage에 구한다.
		예로 1~10페이지의 내용을 나타낼 때는 페이지 그룹은 [1][2]..[10]으로 표시되고
		11~20페이지를 나타낼 때는 [11][12]..[20]까지 표시된다.
		
		*/
		
		int startpage = ((page-1)/10) *10 +1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 :" +startpage);
		
		//endpage:현재 페이지 그룹에서 보여줄 마지막 페이지 수 ([10],[20],[30]...)
		int endpage = startpage +10-1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 :"+endpage);
		
		/*
		마지막 그룹의 마지막 페이지 값은 최대 페이지 값이다.
		예로 마지막 페이지 그룹이 [21]~[30]인 경우
		시작 페이지는 21(startpage=21)와 마지막 페이지는 30(endpage=30)이지만
		최대 페이지(maxpage)가 25라면 [21]~[25]까지만 표시되도록 한다.
		
		*/
		
		if(endpage>maxpage)
			endpage=maxpage;
		
			request.setAttribute("page", page);//현재 페이지 수
			request.setAttribute("maxpage", maxpage);//최대 페이지 수
			
			//현재 페이지에 표시할 첫 페이지 수
			request.setAttribute("startpage", startpage);
			//현재 페이지에 표시할 끝 페이지 수
			request.setAttribute("endpage", endpage);
			request.setAttribute("listcount", listcount);//총 글의 수
			//해당 페이지의 글 목록을 갖고 있는 리스트
			request.setAttribute("totallist", list);
			request.setAttribute("search_field", index);
			request.setAttribute("search_word", search_word);
			System.out.println("총 글 수"+listcount);
			
		forward.setRedirect(false);
		forward.setPath("mypage/MemberReviewList.jsp");
		return forward;// BoardFrontController.java로 리턴된다.
	}

}
