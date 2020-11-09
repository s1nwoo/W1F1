package com.wifi.client;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AppClientApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	@Autowired
	private DataSource datasource;

	@Autowired
	private SqlSessionFactory sessionFactory;

	@Test
	public void contextLoads() {
	}

	@Test
	public void connectionTest() throws SQLException {
		System.out.println(datasource);
		Connection conn = datasource.getConnection();
		System.out.println(conn);
		conn.close();
	}

	@Test
	public void testSqlSessionFactory() {
		System.out.println("check SessionFactory : " + sessionFactory);
	}

}
