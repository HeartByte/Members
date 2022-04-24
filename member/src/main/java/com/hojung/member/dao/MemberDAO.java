package com.hojung.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hojung.member.domain.DBConCloseUtil;
import com.hojung.member.vo.MemberVO;

public class MemberDAO {
	
	//회원정보 삽입
	public int insert(MemberVO vo) {
		Connection con = null;//DB커넥션
		PreparedStatement ps = null;//텍스트로 된 sql문 호출..
		
		String sql = "insert into members(memId,memPwd,memName) values(?,?,?)";
		try {
			con = DBConCloseUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, vo.getMemId());
			ps.setString(2, vo.getMemPwd());
			ps.setString(3, vo.getMemName());
			//ps.setString(4, vo.getRegDate());
			return ps.executeUpdate();
		}catch (SQLException e) {
			
		}finally {
			DBConCloseUtil.close(con, ps, null);//오버로딩 하기 싫으면 null
		}
		
		return 0;
	}
	
	//회원정보 삭제	
	public int delete(String memId) {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "delete from members where memId = ?";
		try {
			con = DBConCloseUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memId);//회원아이디를 통해 members테이블에서 삭제할정보 가져오기
			
			return ps.executeUpdate();
		}catch (SQLException e) {
			
		}finally {
			DBConCloseUtil.close(con, ps, null);//오버로딩 하기 싫으면 null
		}
		return 0;
	}
	
	//회원정보 업데이트
	public int update(MemberVO vo) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "update members set memPwd = ?, memName = ?, where memId = ?";
		try {
			con = DBConCloseUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(3, vo.getMemId());
			ps.setString(1, vo.getMemPwd());
			ps.setString(2, vo.getMemName());
			
			return ps.executeUpdate();
		}catch (SQLException e) {
			
		}finally {
			DBConCloseUtil.close(con, ps, null);//오버로딩 하기 싫으면 null
		}
		return 0;
	}
	
	//회원정보 전체삭제
	public int deleteAll() {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "delete from members";
		try {
			con = DBConCloseUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			return ps.executeUpdate();
		}catch (SQLException e) {
			
		}finally {
			DBConCloseUtil.close(con, ps, null);//오버로딩 하기 싫으면 null
		}
		return 0;
	}
	//회원정보 id로 가져오기
	public MemberVO getOne(String memId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from members where memId = ?";
		try {
			con = DBConCloseUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, memId);
			
			
			rs = ps.executeQuery();
			//List = new ArrayList<>();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setMemId(rs.getString("memId"));
				vo.setMemName(rs.getString("memName"));
				vo.setMemPwd(rs.getString("memPwd"));
				//vo.setRegDate(rs.getString("regDate"));
				return vo;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}finally {
			DBConCloseUtil.close(con, ps, rs);//오버로딩 하기 싫으면 null
		}
		return null;
	}
	
	//전체조회
	public List<MemberVO> getAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MemberVO> list = null;
		String sql = "select * from members";
		try {
			con = DBConCloseUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			list = new ArrayList<>();
			
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setMemId(rs.getString("memId"));
				vo.setMemName(rs.getString("memName"));
				vo.setMemPwd(rs.getString("memPwd"));
				//vo.setRegDate(rs.getString("regDate"));
				list.add(vo);
			}
			
			return list;
		}catch (SQLException e) {
			
		}finally {
			DBConCloseUtil.close(con, ps, rs);//오버로딩 하기 싫으면 null
		}
		
		return null;
	}
	
	//회원테이블 개수 카운트
	public int getCount() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int resultCnt = 0;
		String sql = "select count(*) as memcnt from members";
		try {
			con = DBConCloseUtil.getConnection();
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				return rs.getInt("memCnt"); 
			}
			
		
		}catch (SQLException e) {
			
		}finally {
			DBConCloseUtil.close(con, ps, rs);//오버로딩 하기 싫으면 null
		}
		
		return resultCnt;
	}
}
