package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/login4")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(user_id);
		memberVO.setPwd(user_pw);
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(memberVO);
		
		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogin", true);
			session.setAttribute("login.id", user_id);
			session.setAttribute("login.pw", user_pw);
			
			out.print("<html><body>");
			out.print("안녕하세요 " + user_id + "님!!<br>");
			out.print("<a href='show'>회원정보보기</a>");
			out.print("</html></body>");
		} else {
			out.print("<html><body>");
			out.print("회원 아이디가 틀립니다.<br>");
			out.print("<a href='login4.html'>다시 로그인 하기</a>");
			out.print("</html></body>");
		}
	}

}
