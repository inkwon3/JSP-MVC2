<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/layout/header.jsp" %>
	 <c:choose>
	 	<c:when test="${userID eq null }">
			<div style="padding-top: 50px;" align="center">	
				<form method="post" action="/BBS/loginAction.user">
				<fieldset style="width: 200px;">
					<h3 style="text-align: center;">로그인을 해야 글쓰기가 가능합니다.</h3>
				<legend>로그인</legend>
					<input type="text" placeholder="아이디" name="userID" maxlength="20" >
					<input type="password" placeholder="비밀번호" name="userPassword" maxlength="20">
				<p><input type="submit" value="로그인"></p>
				<p><button type = "button" onclick = "location.href = 'join.jsp';">회원가입</button></p>
				</fieldset>
				</form>
			</div>
		</c:when>
		<c:otherwise>
		<div style="padding-top: 20px;" align="center">
				<p><button type = "button" onclick = "location.href = '/BBS/logoutAction.user';">로그아웃</button ></p>
				
			<form method = "post" action="/BBS/writeAction.bbs">
				<table style="text-align: center; width: 400px;" border="1">
					<thead>
						<tr>
							<th colspan="2" style= "text-align: center;">게시판 글쓰기</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" placeholder="글 제목" name="bbsTitle" maxlength="50" style="width: 400px;"></td>
						</tr>
						<tr>
							<td><textarea placeholder="글 내용" name="bbsContent" maxlength="2048" style="height: 350px; width: 400px;"></textarea></td>
						</tr>
					</tbody>
				</table>
				<p><input type="submit" value="글쓰기"></p>				
			</form>
		</div>
		</c:otherwise>
	</c:choose>
<%@ include file="/layout/footer.jsp" %>