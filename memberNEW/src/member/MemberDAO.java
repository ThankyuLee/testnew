package member;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

	public void insert(Member mem) {

		// JDBC 사용하기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "test";
		String password = "test";
		Connection con = null;
		PreparedStatement psmt = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 실행중에 에러 발생 예상부분
			// 2. 데이터베이스와 연결해 Connection 객체 얻기
			con = DriverManager.getConnection(url, user, password);
			// 3. SQL 문장 준비하기
			String sql = "INSERT INTO MEMBER VALUES(?,?,?,?)";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, mem.getName());
			psmt.setInt(2, mem.getAge());
			psmt.setString(3, mem.getTel());
			psmt.setString(4, mem.getMemNum());
			// 4. 실행하기

			int num = psmt.executeUpdate();
			System.out.println(num);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("클래스를 찾을 수 없습니다.");
			e.printStackTrace(); //
		} // static method
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL 실행중 문제가 발생");
			e.printStackTrace();
		} finally {
			// 5. 연결 끊기

			try {
				psmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public Member selectOne(String memNum) {
		// JDBC 사용하기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "test";
		String password = "test";
		Member m = null;
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 실행중에 에러 발생 예상부분
			// 2. 데이터베이스와 연결해 Connection 객체 얻기
			con = DriverManager.getConnection(url, user, password);
			// 3. SQL 문장 준비하기
			String sql = "SELECT * FROM MEMBER WHERE MEMNUM = ?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, memNum);
			// 4. 실행하기

			rs = psmt.executeQuery();

			// 5. ResultSet 객체이서 데이터 꺼내기
			rs.next(); // 값이 할당된 공간을 커서처럼 위에서 아래로 이동하면서 true 혹은 false를 반환
			String name = rs.getString(1);
			int age = rs.getInt(2);
			String tel = rs.getString(3);
			String memNum2 = rs.getString(4);
			// System.out.println(name + " / " + age + " / " + tel + " / " + memNum);

			m = new Member(name, age, tel, memNum2);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("클래스를 찾을 수 없습니다.");
			e.printStackTrace(); //
		} // static method
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL 실행중 문제가 발생");
			e.printStackTrace();
		} finally {
			// 5. 연결 끊기

			try {
				if (psmt != null)
					psmt.close();

				if (con != null)
					con.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}

}
