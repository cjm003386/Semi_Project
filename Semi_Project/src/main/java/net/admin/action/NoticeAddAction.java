package net.admin.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.communityboard.db.NoticeBean;
import net.communityboard.db.NoticeDAO;


public class NoticeAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NoticeDAO ndao = new NoticeDAO();
		NoticeBean notice = new NoticeBean();
		ActionForward forward = new ActionForward();
		
		String realFolder ="";
		
		String saveFolder="boardupload";// webapp/boardupload
		
		int fileSize= 5*1024*1024;//파일 최대 크기 5MB
		
		//실제 저장경로 지정
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder=" + realFolder);
		boolean result=false;
		try{
			MultipartRequest multi = new MultipartRequest(request, 
										realFolder,
										fileSize,
										"utf-8",
								new DefaultFileRenamePolicy());
			
			//BoardBean 객체에 글 등록 폼에서 입력받은 정보들을 저장한다.
			notice.setNotice_id(multi.getParameter("board_name"));
			notice.setNotice_title(multi.getParameter("board_subject"));
			notice.setNotice_content(multi.getParameter("board_content"));
			
			//시스템 상에 업로드된 실제 파일명
			String filename =multi.getFilesystemName("board_file");
			notice.setNotice_file(filename);
			
			//글 등록 처리를 위해 DAO의 boardInsert메서드 호출
			//글 등록 폼에서 입력한 정보가 저장되어있는 boarddata객체를 전달한다.
			result=ndao.noticeInsert(notice);
			
			//글 등록에 실패할 경우 false 반환
			if(result==false) {
				System.out.println("공지 등록 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "공지 등록 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("공지 등록 완료");
			
			//글 등록이 완료되면 글 목록을 보여주기 위해 :BoardList.bo로 이동한다.
			//Redirect여부를 true로 설정
			forward.setRedirect(true);
			forward.setPath("noticelist.co");//이동할 경로 지정
			return forward;
			
			
		}catch(IOException e){
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "게시판 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}
	}

}
