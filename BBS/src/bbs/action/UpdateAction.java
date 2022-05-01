package bbs.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.bbs.Bbs;
import bbs.dao.BbsDAO;

public class UpdateAction {
	public int execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userID = null;
		HttpSession session = request.getSession();
		userID = session.getAttribute("userID").toString();
		
		if (userID == null){
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
		
		Bbs bbs = new BbsDAO().getBbs(bbsID);
		
		if (!userID.equals(bbs.getUserID())) {
			request.setAttribute("warning", "������ �����ϴ�.");
			request.setAttribute("url", "/BBS/getList.bbs");
			return 0;
		} else {
			if(request.getParameter("bbsTitle") == null || request.getParameter("bbsContent") == null 
				    || request.getParameter("bbsTitle").equals("")|| request.getParameter("bbsContent").equals("")){
				request.setAttribute("warning", "�Է��� �� �� ������ �ֽ��ϴ�.");
				request.setAttribute("url", "/BBS/updateCheck.bbs?bbsID=" + bbsID);
				return 0;
			} else {
				BbsDAO bbsDAO = new BbsDAO();
				int result = bbsDAO.update(bbsID, request.getParameter("bbsTitle"),request.getParameter("bbsContent"));
				if (result == -1) {
					request.setAttribute("warning", "�ۼ����� �����߽��ϴ�.");
					request.setAttribute("url", "/BBS/updateCheck.bbs?bbsID=" + bbsID);
					return 0;
				}
				else {
					return 1;
				}		
			}
		}
	}
}
