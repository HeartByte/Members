<%@page import="com.hojung.member.dao.MemberDAO"%>
<%@page import="com.hojung.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//입력화면에서 전달된 데이터받기
	String memId = request.getParameter("memId");
	String memPwd = request.getParameter("memPwd");
	String memName = request.getParameter("memName");
	
	//전달받은 데이터 묶음 객체 담기
	MemberVO vo = new MemberVO(memId, memPwd, memName);
	//DAO클래스에게 해당작업 지시하기=> 필요한 자료 넘겨주기
	MemberDAO dao = new MemberDAO();
	dao.insert(vo);
	
	//결과보고받기
	int result = dao.getCount();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 확인페이지</title>
</head>
<body>
	<h1><%=result %>의 회원가입 되었습니다.</h1>
	<p>정상적으로 가입되었으면 세션에 정보 수락...</p>
</body>
</html>