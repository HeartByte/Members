package com.hojung.member.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import com.hojung.member.dao.MemberDAO;
import com.hojung.member.domain.DBConCloseUtil;
import com.hojung.member.vo.MemberVO;


public class MemberTest {
	
	@Test 
	public void dbConnection(){
		Connection con = DBConCloseUtil.getConnection();
		assertNotNull(con);
	}
	
	@Test 
	public void dbInsert() {
		//샘플데이터 만들기
		MemberVO vo = new MemberVO("yangdoll","qwert12345","강감찬"); 
		MemberVO vo2 = new MemberVO("dolllclub","qwert12345","을지문덕"); 
		MemberVO vo3 = new MemberVO("dolllclubyang","asdfg12345","최영"); 
		
		//자료를 넘겨서 저장되는지 확인 테스트
		MemberDAO dao = new MemberDAO();
		dao.deleteAll();
		assertEquals(dao.getCount(),0);
		int result = dao.insert(vo);
		
		assertEquals(1,result);
		assertEquals(dao.getCount(),1);
		
		assertEquals(1, dao.insert(vo2));
		assertEquals(dao.getCount(),2);
		

		assertEquals(1, dao.insert(vo3));
		assertEquals(dao.getCount(),3);
		
	}
	
	@Test
	public void getMember() {
		MemberVO vo = new MemberVO("yangdoll","qwert12345","강감찬");
		MemberDAO dao = new MemberDAO();
		dao.deleteAll();
		assertEquals(0, dao.getClass());
		
		dao.insert(vo);
		assertEquals(1, dao.getClass());
		MemberVO rtnVO = dao.getOne(vo.getMemId());
		assertEquals(vo.getMemName(), rtnVO.getMemName());
		assertEquals(vo.getMemPwd(), rtnVO.getMemPwd());
		
		//assertEquals(vo, rtnVO);
		
	}
}
