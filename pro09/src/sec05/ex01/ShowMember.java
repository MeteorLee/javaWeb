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
 * Servlet implementation class ShowMember
 */
@WebServlet("/show")
public class ShowMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = "";
		String pw = "";
		
		
		Boolean isLogon = false;
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			isLogon = (Boolean) session.getAttribute("isLogin");
			if (isLogon) {
				id = (String) session.getAttribute("login.id");
				pw = (String) session.getAttribute("login.pw");
				out.print("<html><body>");
				out.print("아이디: " + id);
				out.print("비밀번혼 : " + pw);
				out.print("</html></body>");
			} else {
				response.sendRedirect("login4.html");
			}
		} else {
			response.sendRedirect("login4.html");
		}
		
	}

}
