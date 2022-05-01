package join.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import join.action.JoinAction;

@WebServlet("*.join")
public class Controller extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String joinUrl = request.getRequestURI().toString();
		
		int start = joinUrl.lastIndexOf("/");
		int end = joinUrl.lastIndexOf(".join");
		
		String url = joinUrl.substring(start + 1, end);
		
		switch(url) {
		case "joinAction" :
			JoinAction action = new JoinAction();
			action.execute(request, response);
			request.getRequestDispatcher("warning.jsp").forward(request, response);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.doGet(request, response);
	}
}
