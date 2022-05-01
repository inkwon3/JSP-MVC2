package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import usesr.action.LoginAction;
import usesr.action.LogoutAction;

@WebServlet("*.user")
public class Controller extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginUrl = request.getRequestURL().toString();
		
		int start = loginUrl.lastIndexOf("/");
		int end = loginUrl.lastIndexOf(".user");
		
		String url = loginUrl.substring(start + 1, end);
		
		switch(url) {
		case "loginAction" :
			LoginAction loginAction = new LoginAction();
			loginAction.execute(request, response);
			response.sendRedirect("main.jsp");
			//request.getRequestDispatcher("main.jsp").forward(request, response);
			break;
		case "logoutAction" :
			LogoutAction logoutAction = new LogoutAction();
			logoutAction.execute(request, response);
			response.sendRedirect("main.jsp");
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.doGet(request, response);
	}
}
