package bbs.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.bbs.Bbs;
import bbs.dao.BbsDAO;

public class ViewAction{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bbsID = 0;
		if (request.getParameter("bbsID") !=null) {
			bbsID = Integer.parseInt(request.getParameter("bbsID"));
		}
		Bbs bbs = new BbsDAO().getBbs(bbsID);
		
		request.setAttribute("bbs", bbs);
	}

}
