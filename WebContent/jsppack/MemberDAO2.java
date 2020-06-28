package jsppack;
import java.sql.*;
import java.util.ArrayList;

public class MemberDAO2 {
	//dao : 데이터베이스 접근 객체
	//실질적으로 db에서 회원정보를 불러오거나 넣을때 사용
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	int count = 0;
	
	public MemberDAO2(){
		
		String dbURL = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC&characterEncoding=UTF-8";
		String dbId = "lys0614";
		String dbPw = "rlflsdydtjr06^";
		try {
			//jdbc드라이버로딩
			Class.forName("com.mysql.jdbc.Driver");
			//connection 얻어오기
			conn = DriverManager.getConnection(dbURL,dbId,dbPw);

			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int memberLogin(String loginId, String loginPw) {
		//sql문 준비 - 현재 db에 저장된 정보를 불러오는 sql 문
		String dbURL = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC&characterEncoding=UTF-8";
		String dbId = "lys0614";
		String dbPw = "rlflsdydtjr06^";
		String sql = "select * from memdata where id=?";
		try {
			//바인딩 - pstmt를 통해 db와으 통로 연결
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  loginId);
			//실행 - 데이터베이스에서 정보를 받아와서 확인할땐 executeQuery(); 를 사용한다
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//패스워드가 일치한다면 진행
				if(rs.getString(1).equals(loginPw)) {
					return 1;//로그인성공
				}
				else {
					return 0;//비밀번호 불일치
				}
				return -1; // 아이디없음
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터베이스 오류
	}
	
	public void MemberInsert(MemberVo vo) {
		//생성자, 실행될때마다 자동으로 db연결이 이루어지게함
		try {
			//jdbc드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC&characterEncoding=UTF-8";
			String dbId = "lys0614";
			String dbPw = "rlflsdydtjr06^";
			
			
			//connection 얻어오기
			conn=DriverManager.getConnection(dbURL, dbId, dbPw);
			
			//Sql문 준비
			String sql = "insert into memdata values (?,?,?,?,?)";
			
			//바인딩
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getNickname());
			pstmt.setString(5, vo.getEmail());
			
			//실행
			count = pstmt.executeUpdate();
			if(count >0 ) {
				System.out.println("가입완료");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//준비햇던 db연결문들 종료
				conn.close();
				pstmt.close();
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
