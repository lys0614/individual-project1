package jsppack;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//좌석까지 선택한 후의 데이터베이스 관련 Vo 클래스
public class MemberDAO4 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MemberDAO4(MemberVo3 vo) {
		String dbURL = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC&characterEncoding=UTF-8";
		String dbId = "lys0614";
		String dbPw = "rlflsdydtjr06^";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(dbURL, dbId, dbPw);
			String sql = "update moviereserv set sitnumber=?,reservdate=?,reservadult=?,reservteen=?,reservkids=? where reservid=?;";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSitnum());
			pstmt.setString(2, vo.getDatetime());
			pstmt.setString(3, vo.getReservAdult());
			pstmt.setString(4, vo.getReservTeen());
			pstmt.setString(5, vo.getReservKid());
			pstmt.setString(6, vo.getReservid());
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				pstmt.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
