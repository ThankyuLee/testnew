package member;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("1.���� 2.��ȸ >> ");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		if (choice == 1) {
			System.out.print("�̸� ");
			String name = sc.next();
			System.out.print("���� ");
			int age = sc.nextInt();
			System.out.print("��ȭ��ȣ ");
			String tel = sc.next();
			System.out.print("����ȣ ");
			String memNum = sc.next();
			Member mem = new Member(name, age, tel, memNum);
			MemberDAO dao = new MemberDAO();
			dao.insert(mem);
		}
		if (choice == 2) {

			System.out.print("����ȣ ");
			String memNum = sc.next();
			MemberDAO dao = new MemberDAO();
			Member m = dao.selectOne(memNum);
			System.out.println("�̸� / ���� / ��ȭ��ȣ  / ����ȣ");
			System.out.println(m.getName() + " / " + m.getAge() + " / " + m.getTel() + " / " + m.getMemNum());
			// JDBC ����ϱ�
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "test";
			String password = "test";
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;

		

		}

	}

}
