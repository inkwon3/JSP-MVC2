package bbs.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.bbs.Bbs;
import bbs.dao.BbsDAO;

public class UpdateCheckAction {

	public int execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userID = null;
		userID = (String) session.getAttribute("userID");
		
		if (userID == null) {
			request.setAttribute("warning", "로그인을 하세요.");
			request.setAttribute("url", "login.jsp");
			return 0;
		}
		
		int bbsID = 0;
		
		if (request.getParameter("bbsID") !=null) {
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		
		if (bbsID == 0) {
			request.setAttribute("warning", "유효하지 않은 글입니다.");
			request.setAttribute("url", "/BBS/getList.bbs");
			return 0;
		}
		
		Bbs bbs = new Bbs();
		BbsDAO bbsDAO = new BbsDAO();
		bbs = bbsDAO.getBbs(bbsID);
		
		if (!userID.equals(bbs.getUserID())) {
			request.setAttribute("warning", "권한이 없습니다.");
			request.setAttribute("url", "/BBS/getList.bbs");
			return 0;
		}
		request.setAttribute("bbs", bbs);
		
		return 1;
	}
}
