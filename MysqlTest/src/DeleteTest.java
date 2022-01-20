import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteTest {
	Scanner scan = new Scanner(System.in);
	Connection con = null;
	PreparedStatement pstmt = null;
	
	
	public DeleteTest() {
		// TODO Auto-generated constructor stub
	}

		public void start() {
			try {
				System.out.println("삭제할 사원번호 -> ");
				int no = Integer.parseInt(scan.nextLine());
				
				//1.
				Class.forName("com.mysql.cj.jdbc.Driver");
				//2.
				con = DriverManager.getConnection("jdbc:mysql://localhost/demo", "root", "root");
				//3.
				String sql = "delete from emp where empno = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, no);
				
				//4.
				int cnt = pstmt.executeUpdate();
				
				if(cnt > 0) {
					System.out.println(cnt + "개의 레코드가 삭제되었습니다.");
				}else {
					System.out.println("레코드 삭제 실패하였습니다.");
				}
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
				//5.
				try {
					if(pstmt != null) pstmt.close();
					if(con != null) con.close();
				} catch (Exception ee) {}
			}
		}
		public static void main(String[] args) {
	        new DeleteTest().start();
	    }
}


