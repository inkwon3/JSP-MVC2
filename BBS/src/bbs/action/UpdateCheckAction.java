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
			request.setAttribute("warning", "�α����� �ϼ���.");
			request.setAttribute("url", "login.jsp");
			return 0;
		}
		
		int bbsID = 0;
		
		if (request.getParameter("bbsID") !=null) {
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		
		if (bbsID == 0) {
			request.setAttribute("warning", "��ȿ���� ���� ���Դϴ�.");
			request.setAttribute("url", "/BBS/getList.bbs");
			return 0;
		}
		
		Bbs bbs = new Bbs();
		BbsDAO bbsDAO = new BbsDAO();
		bbs = bbsDAO.getBbs(bbsID);
		
		if (!userID.equals(bbs.getUserID())) {
			request.setAttribute("warning", "������ �����ϴ�.");
			request.setAttribute("url", "/BBS/getList.bbs");
			return 0;
		}
		request.setAttribute("bbs", bbs);
		
		return 1;
	}
}
