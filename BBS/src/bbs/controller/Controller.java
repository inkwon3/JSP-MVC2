package bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.action.DeleteAction;
import bbs.action.GetListAction;
import bbs.action.UpdateAction;
import bbs.action.UpdateCheckAction;
import bbs.action.ViewAction;
import bbs.action.WriteAction;

@WebServlet("*.bbs")
public class Controller extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bbsUrl = request.getRequestURI().toString();
		
		int start = bbsUrl.lastIndexOf("/");
		int end = bbsUrl.lastIndexOf(".bbs");
		
		String url = bbsUrl.substring(start + 1, end);
		
		switch(url) {
		case "getList" :
			GetListAction getListAction = new GetListAction();
			getListAction.execute(request, response);
			request.getRequestDispatcher("bbs.jsp").forward(request, response);
			break;
		case "writeAction" :
			WriteAction writeAction = new WriteAction();
			writeAction.execute(request, response);
			response.sendRedirect("/BBS/getList.bbs");
			break;
		case "view" :
			ViewAction viewAction = new ViewAction();
			viewAction.execute(request, response);
			request.getRequestDispatcher("view.jsp").forward(request, response);
			break;
		case "deleteAction" :
			DeleteAction deleteAction = new DeleteAction();
			deleteAction.execute(request, response);
			request.getRequestDispatcher("warning.jsp").forward(request, response);
			break;
		case "updateCheck" :
			UpdateCheckAction updateCheck = new UpdateCheckAction();
			if(updateCheck.execute(request, response) == 1) {
				request.getRequestDispatcher("update.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("warning.jsp").forward(request, response);
			}
			break;
		case "update" :
			UpdateAction updateAction = new UpdateAction();
			updateAction.execute(request, response);
			if(updateAction.execute(request, response) == 1) {
				response.sendRedirect("/BBS/getList.bbs");
			} else {
				request.getRequestDispatcher("warning.jsp").forward(request, response);
			}
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.doGet(request, response);
	}
}
