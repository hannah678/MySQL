package dbConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MysqlConnection {
	//멤버변수ㅡ 객체생성
	//1. 드라이브로딩
	static {//static{}는 실행문을 기술할 수 있는 영역이 된다.
		//실행문, 멤버영역
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(Exception e){
			System.out.println("드라이브 로딩 예외 발생!!");
		}
	}
	//멤버영역에 변수를 선언한다.
	protected Connection conn = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	
	//2. 데이터베이스 연결 메소드
	public void getConn() {
		String url = "jdbc:mysql://localhost/mydb";
		String id = "root";
		String password = "root";
		try {
		conn = DriverManager.getConnection(url, id, password);
		} catch(Exception e) {
			System.out.println("데이터베이스 연결 예외 발생!!");
		}
	}
	//3. 데이터베이스 종료 메소드
	public void dbClose() {
		try  {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(Exception e) {
			System.out.println("데이터베이스 닫기 예외 발생!!");
		}
	}
}
