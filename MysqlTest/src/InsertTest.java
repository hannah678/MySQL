import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertTest {
    Connection conn = null;
    PreparedStatement ps = null;

    public InsertTest() {
    }
    public void empInsert() {
        // 사원번호, 이름, 급여, 입사일을 레코드 추가하기
        try {
            // 1. 드라이브 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. DB연결
            conn = DriverManager.getConnection("jdbc:mysql://localhost/demo", "root", "root");
            
            // 3. prepareStatement 객체 생성 : 쿼리문 이용하여
            // 사원번호 : 5555, 이름 : kim, 급여 : 4500, 입사일 : 현재 날짜와 시간으로 설정
            String sql = "insert into emp(empno, ename, sal, hiredate) values(?,?,?,now())";
            ps = conn.prepareStatement(sql);
            
            // ?에 값 세팅
            ps.setInt(1, 5555);
            ps.setString(2, "kim");
            ps.setInt(3, 4500);
            
            // 4. 실행
            int cnt = ps.executeUpdate();
            if(cnt>0) {
                System.out.println("레코드가 추가 되어있습니다.");
            } else {
                System.out.println("레코드추가 실패하였습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(ps != null) ps.close();
                if(conn != null) conn.close();
            } catch (Exception e2) { e2.printStackTrace(); }
        }
    }

    public static void main(String[] args) {
        new InsertTest().empInsert();
    }
}

