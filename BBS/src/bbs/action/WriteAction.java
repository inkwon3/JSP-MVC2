package bbs.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.bbs.Bbs;
import bbs.dao.BbsDAO;

public class WriteAction{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userID = null;
		HttpSession session = request.getSession();
		Bbs bbs = new Bbs();
		
		bbs.setBbsTitle((String)request.getParameter("bbsTitle"));
		bbs.setBbsContent((String)request.getParameter("bbsContent"));
		
		if(session.getAttribute("userID") !=null){
			userID = (String) session.getAttribute("userID");
		}
		if (userID == null){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href = 'login.jsp'");
			script.println("</script>");
		} else {
			if(bbs.getBbsTitle() == null || bbs.getBbsContent() == null ){
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력이 안 된 사항이 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				BbsDAO bbsDAO = new BbsDAO();
				int result = bbsDAO.write(bbs.getBbsTitle(), userID, bbs.getBbsContent());
				if (result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글쓰기에 실패했습니다.')"); //db오류
					script.println("history.back()");
					script.println("</script>");
				}
				else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href = 'bbs.jsp'");
					script.println("</script>");
				}		
			}
		}
	}

}
