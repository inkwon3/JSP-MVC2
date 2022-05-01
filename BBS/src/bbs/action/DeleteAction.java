package bbs.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.bbs.Bbs;
import bbs.dao.BbsDAO;

public class DeleteAction {
	public int execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userID = null;
		userID = (String) session.getAttribute("userID");
		
		if (userID == null){
			request.setAttribute("warning", "�α����� �ϼ���.");
			request.setAttribute("url", "login.jsp");
			return 1;
		}
		
		int bbsID = 0;
		if (request.getParameter("bbsID") !=null) {
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		
		if (bbsID == 0) {
			request.setAttribute("warning", "��ȿ���� ���� ���Դϴ�.");
			request.setAttribute("url", "/BBS/getList.bbs");
			return 1;
		}
		
		BbsDAO bbsDAO = new BbsDAO();
		Bbs bbs = new Bbs();
		bbs = bbsDAO.getBbs(bbsID);
		
		if (!userID.equals(bbs.getUserID())) {
			request.setAttribute("warning", "������ �����ϴ�.");
			request.setAttribute("url", "/BBS/getList.bbs");
			return 1;
		} else {
			int result = bbsDAO.delete(bbsID);
			if (result == -1) {
				request.setAttribute("warning", "�� ������ �����߽��ϴ�.");
				request.setAttribute("url", "/BBS/view.bbs");
				return 1;
			}
			else {
				request.setAttribute("warning", "���� ����!.");
				request.setAttribute("url", "/BBS/getList.bbs");
				return 1;
			}		
		}
	}
}
