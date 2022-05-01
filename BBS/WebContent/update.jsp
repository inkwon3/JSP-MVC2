<%@page import="bbs.dao.BbsDAO"%>
<%@page import="bbs.bbs.Bbs"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/layout/header.jsp" %>
		<div style="padding-top: 20px;" align="center">
			<h3 style="text-align: center;"></h3>
			<p><button type = "button" onclick = "location.href = '/BBS/logoutAction.user';">로그아웃</button></p>
	
			<form method = "post" action="/BBS/update.bbs?bbsID=${bbs.bbsID }">
				<table style="text-align: center;" border="1">
					<thead>
						<tr>
							<th colspan="2" style= "text-align: center;">게시판 글 수정</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" placeholder="글 제목" name="bbsTitle" maxlength="50" value="${bbs.bbsTitle }" style="width: 400px;"></td>
						</tr>
						<tr>
							<td><textarea placeholder="글 내용" name="bbsContent" maxlength="2048" style="height: 350px; width: 400px;">${bbs.bbsContent }</textarea></td>
						</tr>
					</tbody>
				</table>
				<input type="submit" value="글수정">				
			</form>
		</div>
<%@ include file="/layout/footer.jsp" %>