package com.demo.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.config.RootConfig;
import com.demo.config.ServletConfig;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@ContextConfiguration(classes= {RootConfig.class, ServletConfig.class})
@Log4j
public class JDBCTest {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void testConnection() {
//		try (Connection con = 
//				DriverManager.getConnection(
//						"jdbc:oracle:thin:@localhost:1521:XE",
//						"book_ex",
//						"book_ex")){
//			log.info("con : " + con);
//		} catch (Exception e) {
//			fail(e.getMessage());
//		}
//	}
	
	@Test
	public void testRdsConnection() {
		try (Connection con = 
				DriverManager.getConnection(
						"jdbc:log4jdbc:oracle:thin:@database-1.c956m8xmn2ee.ap-northeast-2.rds.amazonaws.com:1521:ORCL",
						"noah00o",
						"ozrngus88")){
			log.info("con : " + con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
