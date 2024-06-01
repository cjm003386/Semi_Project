package net.communityboard.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FAQDAO {
	
private DataSource ds;
	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public FAQDAO() {
	try {
		Context init = new InitialContext();
		ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
	} catch (Exception ex) {
		System.out.println("DB 연결 실패 : " + ex);
	}
	
	
	
}

}
