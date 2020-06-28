package jsppack;
import java.sql.*;

public class MemberDAO {
	//dao : 데이터베이스 접근 객체
	//실질적으로 db에서 회원정보를 불러오거나 넣을때 사용
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	int count = 0;
	
	//mysql에 접속하는 부분
	public void MemberInsert(MemberVo vo) {
		//생성자, 실행될때마다 자동으로 db연결이 이루어지게함
		try {
			//jdbc드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC&characterEncoding=UTF-8";
			String dbId = "lys0614";
			String dbPw = "rlflsdydtjr06^";
			
			
			//connection 얻어오기
			conn=DriverManager.getConnection(dbURL, dbId, dbPw);
			
			//Sql문 준비
			String sql = "insert into memdata values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
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
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	

}
