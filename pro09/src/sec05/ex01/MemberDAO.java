package sec05.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {

	Connection con;
	PreparedStatement pstmt;
	DataSource dataFactory;
	
	public MemberDAO() {
		Context ctx;
		try {
			ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/mariadb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean isExisted(MemberVO memberVO) {
		boolean result = false;
		String id = memberVO.getId();
		String pw = memberVO.getPwd();
		
		try {
			con = dataFactory.getConnection();
			String query = "select if(count(*) >= 1, 'true', 'false') as result from t_member";
			query += " where id=? and pwd=?";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result = " + result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
		
		
	}
	
	
	
	
}
