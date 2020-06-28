package jsppack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class MemberDAO5 {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public MemberDAO5(MemberVo3 vo) {
		String dbURL = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC&characterEncoding=UTF-8";
		String dbId = "lys0614";
		String dbPw = "rlflsdydtjr06^";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn=DriverManager.getConnection(dbURL, dbId, dbPw);
			String sql = "delete from moviereserv where reservid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getReservid());
			
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
