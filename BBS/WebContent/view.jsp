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
			</div>
		</c:when>
		<c:otherwise>
			<div style="padding-top: 20px;" align="center">
			<p><button type = "button" onclick = "location.href = '/BBS/logoutAction.user';">로그아웃</button ></p>
			</div>
		</c:otherwise>
	 </c:choose>
	 <div align="center">
		<table style="text-align: center;" border="1">
			<tbody>
				<tr>
					<td style="width: 20%">글 제목</td>
					<td colspan="2">${bbs.bbsTitle}</td>
				</tr>
				<tr>
					<td>작성자</td>
					<td colspan="2">${bbs.userID}</td>
				</tr>
				<tr>
					<td>작성일자</td>
					<td colspan="2">${bbs.bbsDate}</td>
				</tr>	
				<tr>
					<td>내용</td>
					<td colspan="2" style="min-height: 200px; text-align: left;">${bbs.bbsContent}</td>
				</tr>		
			</tbody>
		</table>
		
		<a href="/BBS/getList.bbs">목록</a>
		<a href="/BBS/updateCheck.bbs?bbsID=${bbs.bbsID}">수정</a>
		<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="/BBS/deleteAction.bbs?bbsID=${bbs.bbsID}">삭제</a>
	</div>
<%@ include file="/layout/footer.jsp" %>