package com.hojung.member.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConCloseUtil {

	//오라클DB와 연결시켜주는 커넥션 함수.
	//DriveManager를 통해 사용자의 id와 비밀번호를 통해 오라클DB에 접속한다.
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "mem1", "1234");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}

	
	//Connection, PreparedStatement, Resultset 하나라도 null상태면 close해줄 함수
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs!= null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
		if(ps!= null)try {ps.close();} catch (SQLException e) {e.printStackTrace();}
		if(con!= null)try {con.close();} catch (SQLException e) {e.printStackTrace();}
	}

	

}
