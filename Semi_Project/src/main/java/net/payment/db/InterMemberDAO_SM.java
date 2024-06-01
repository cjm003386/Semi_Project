package net.payment.db;

import java.sql.SQLException;

public interface InterMemberDAO_SM {

	// 회원정보를 조회하는 메소드(getParameter로 넘어온 id를 이용)
	MemberVO_SM showMemberInfo(String id) throws SQLException;

}
