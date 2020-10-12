package com.demo.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class MemberMapperTest {

	@Setter(onMethod_ = @Autowired)
	private DataSource ds;
	
	@Setter(onMethod_ = @Autowired)
	private PasswordEncoder passwordEncoder;
	
//	@Test
//	public void testInsertMember() {
//		String sql = "insert into tbl_member(userid, userpw, username) values(?, ?, ?)";
//		
//		for (int i = 0; i < 2; i++) {
//			Connection con = null;
//			PreparedStatement pstmt = null;
//			
//			try {
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(sql);
//				
//				pstmt.setString(2, passwordEncoder.encode("pw"+i));
//				
//				if (i == 0) {
//					pstmt.setString(1, "member");
//					pstmt.setString(3, "일반사용자");
//				} else if (i == 1) {
//					pstmt.setString(1, "admin");
//					pstmt.setString(3, "관리자");
//				}
//				
//				pstmt.executeUpdate();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
//				if(con != null) {try {con.close();} catch (Exception e) {}}
//			}
//		}
//	}
	
	@Test
	public void testAuthInsert() {
		String sql = "insert into tbl_member_auth(userid, auth) values(?, ?)";
		
		for (int i = 0; i < 2; i++) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				if (i == 0) {
					pstmt.setString(1, "member");
					pstmt.setString(2, "ROLE_MEMBER");
				} else if (i == 1) {
					pstmt.setString(1, "admin");
					pstmt.setString(2, "ROLE_ADMIN");
				}
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
				if(con != null) {try {con.close();} catch (Exception e) {}}
			}
		}
	}
	
//	@Test
//	public void testMemberInsert() {
//		MemberVO member = MemberVO.builder()
//				.userId("member")
//				.userPw(passwordEncoder.encode("1111"))
//				.userName("member")
//				.build();
//		
//		int memberCount = memberMapper.insert(member);
//		log.info("memberCount : " + memberCount);
//	}
	
//	@Test
//	public void testAdminInsert() {
//		log.info("userPassword" + passwordEncoder.encode("2222"));
//		
//		MemberVO member = MemberVO.builder()
//				.userId("admin")
//				.userPw(passwordEncoder.encode("2222"))
//				.userName("admin")
//				.build();
//		
//		AuthenticationVO auth = AuthenticationVO.builder()
//				.userId(member.getUserId())
//				.authentication("ROLE_ADMIN")
//				.build();
//		
//		int memberCount = memberMapper.insert(member);
//		int authCount = authenticationMapper.insert(auth);
//		
//		log.info("memberCount : " + memberCount);
//		log.info("authCount : " + authCount);
//	}
}
