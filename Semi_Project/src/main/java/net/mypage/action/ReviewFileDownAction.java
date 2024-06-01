package net.mypage.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewFileDownAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("filename");
		System.out.println("filename =" + fileName);
		String savePath = "reviewupload";
		
		ServletContext context = request.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		
		String sFilePath = sDownloadPath + "\\" + fileName;
		System.out.println(sFilePath);
		
		byte b[] = new byte[4096];
		
		String sMimeType = context.getMimeType(sFilePath);
		System.out.println("sMimeType>>>" + sMimeType);
		
		if(sMimeType == null)
			sMimeType = "application/octet-stream";
		response.setContentType(sMimeType);
		
		String sEncoding = new String(fileName.getBytes("utf-8"),"ISO-8859-1");
		System.out.println(sEncoding);
		
		response.setHeader("Content-Disposition","attachment; filename= "+sEncoding);
		
		try(
			BufferedOutputStream out2 = new BufferedOutputStream(response.getOutputStream());
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFilePath));
				)
		{
			int numRead;
			while((numRead = in.read(b,0,b.length))!=-1) {
				out2.write(b,0,numRead);
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

}
