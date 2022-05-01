<%@page import="bbs.bbs.Bbs"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp" %>
	 <c:choose>
	 	<c:when test="${userID eq null }">
			<div style="padding-top: 50px;" align="center">	
				<form method="post" action="/BBS/loginAction.user">
					<fieldset style="width: 200px;">
					<legend>로그인</legend>
						<input type="text" placeholder="아이디" name="userID" maxlength="20" >
						<input type="password" placeholder="비밀번호" name="userPassword" maxlength="20">
	
					<p><input type="submit" value="로그인"></p>
					<p><button type = "button" onclick = "location.href = 'join.jsp';">회원가입</button></p>
					</fieldset>
				</form>
			<br>
			</div>
		</c:when>
		<c:otherwise>
	 		<!-- 로그인 유무 확인 - 로그인 시 -->
			<div>
				<div style="padding-top: 20px;" align="center">
				<form method="post" action="/BBS/loginAction.user"> </form>
				<h1 style="text-align: center;">게시판</h1>
	
				<p><button type = "button" onclick = "location.href = '/BBS/logoutAction.user';">로그아웃</button ></p>
				<br>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	 <!-- 게시판 목록 나오기 -->
	 	<div align="center">
			<table border="1" style= "text-align: center;">
				<thead>
					<tr>
						<th style= "text-align: center;">번호</th>
						<th style= "text-align: center;">제목</th>
						<th style= "text-align: center;">작성자</th>
						<th style= "text-align: center;">작성일</th>
					</tr>
				</thead>
					<tbody>
						<c:forEach var="bbs" items="${list}">
							<tr>
								<td>${bbs.bbsID}</td>
								<td><a href="view.bbs?bbsID=${bbs.bbsID}">${bbs.bbsTitle}</a></td>
								<td>${bbs.userID}</td>
								<td>${bbs.bbsDate}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 페이징 -->
					<a href="/BBS/getList.bbs?pageNumber=${pagePrev }">이전</a>
					<a href="/BBS/getList.bbs?pageNumber=${pageNext }">다음</a>
				<a href="write.jsp">글쓰기</a>
			</div>
	<%@ include file="/layout/footer.jsp" %>