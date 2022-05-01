package bbs.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.bbs.Bbs;
import bbs.dao.BbsDAO;

public class GetListAction {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징처리 pageNumber
		int pageNumber = 1;
		if (request.getParameter("pageNumber") != null) {
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		
		// 게시판 목록 가져오기
		BbsDAO bbsDAO = new BbsDAO();
		ArrayList<Bbs> list = bbsDAO.getList(pageNumber);
		request.setAttribute("list", list);
		
		// 페이징처리 (이전, 다음)
		if(pageNumber != 1) {
			request.setAttribute("pagePrev", pageNumber - 1);
		}
		if(bbsDAO.nextPage(pageNumber + 1)) {
			request.setAttribute("pageNext", pageNumber + 1);
		}
		
		
	}

}
