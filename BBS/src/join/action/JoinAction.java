package join.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.User;
import user.UserDAO;


public class JoinAction {
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	String userID = null;
	User user = new User();
	
	user.setUserID(request.getParameter("userID").toString());
	user.setUserPassword(request.getParameter("userPassword").toString());
	user.setUserName(request.getParameter("userName").toString());
	user.setUserGender(request.getParameter("userGender").toString());
	user.setUserEmail(request.getParameter("userEmail").toString());
	
	if(session.getAttribute("userID") !=null){
		userID = (String) session.getAttribute("userID");
	}
	if (userID != null){
		request.setAttribute("warning", "이미 로그인이 되어있습니다.");
		request.setAttribute("url", "main.jsp");
	}
	if(user.getUserID().isEmpty() || user.getUserPassword().isEmpty() || user.getUserName().isEmpty() || 
	user.getUserGender().isEmpty() || user.getUserEmail().isEmpty()){
		request.setAttribute("warning", "입력이 안 된 사항이 있습니다.");
		request.setAttribute("url", "join.jsp");
	} else {
		UserDAO userDAO = new UserDAO();
		int result = userDAO.join(user);
		if (result == -1) {
			request.setAttribute("warning", "이미 존재하는 아이디입니다.");
			request.setAttribute("url", "join.jsp");
		}
		else {
			session.setAttribute("userID", user.getUserID());
			request.setAttribute("warning", "회원가입 완료!");
			request.setAttribute("url", "main.jsp");
		}
	}
	}
}
