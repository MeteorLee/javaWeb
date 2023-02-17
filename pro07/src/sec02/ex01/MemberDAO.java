package sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	/*
	 * private static final String driver = "org.mariadb.jdbc.Driver"; private
	 * static final String url = "jdbc:mariadb://localhost:3307/javaWeb"; private
	 * static final String user = "root"; private static final String pwd = "1234";
	 */	
	
	
	private Connection con;
//	private Statement stmt;
	private PreparedStatement pstmt;
	
	private DataSource dataFactory;
	
	public MemberDAO() {
		
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/mariadb");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<>();
		
		try {
//			connDB();
			con = dataFactory.getConnection();
			String query = "select * from t_member";
			System.out.println(query);
			System.out.println("PreparedStatement(" + query + ") 생성 성공");
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			// ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
//			stmt.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	/*
	 * private void connDB() { try { Class.forName(driver);
	 * System.out.println("MariaDB 드라이버 로딩 성공"); con =
	 * DriverManager.getConnection(url, user, pwd);
	 * System.out.println("Connection 생성 성공"); // stmt = con.createStatement(); //
	 * System.out.println("Statement 생성 성공"); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 */
}
