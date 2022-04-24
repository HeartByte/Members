<%@page import="com.hojung.member.vo.MemberVO"%>
<%@page import="com.hojung.member.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String memId = request.getParameter("memId");
	String memPwd = request.getParameter("memPwd");
	String id = "";
	String pwd = "";
	String name = "";
	String message = null;
	
	MemberDAO dao = new MemberDAO();
	MemberVO rsVO = new MemberVO();
	
	String date = "";
	rsVO = dao.getOne(memId);
	//입력한 아이디와 일치하지 않은 경우.
	if (rsVO == null) {
		System.out.println("입력한 아이디에 일치한 자료가 없다.");
	} else { //일치하면..
		if (memPwd.equals(rsVO.getMemPwd())) {
			System.out.println(rsVO);//일치하는 vo객체 출력.
			message = rsVO.toString();
			id = rsVO.getMemId();
			pwd = rsVO.getMemName();
			name = rsVO.getMemPwd();
			date = rsVO.getRegDate();
		} else {
			System.out.println("입력한 아이디에 일치한 비밀번호가 없다.");
			message = "비밀번호가 일치하지 않습니다.";
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
		결과 :
		<%=message%></h1>
	<p>
		아이디:
		<%=id%></p>
	<p>
		비밀번호:
		<%=pwd%></p>
	<p>
		이름:
		<%=name%></p>
	<p> 가입일:
		<%=date%>
</body>
</html>