package sec03.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionTest2
 */
@WebServlet("/sess3")
public class SessionTest3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		out.print("세션 아이디 : " + session.getId() + "<br>");
		out.print("최초 세션 생성 시각 : " + session.getCreationTime() + "<br>");
		out.print("최근 세션 접근 시각 : " + session.getLastAccessedTime() + "<br>");
		out.print("기본 세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br>");
		
		if (session.isNew()) {
			out.print("새로운 세션이 생성되었습니다.");
		} else {
			out.print("기존 세션을 호출했습니다.");
		}

		session.invalidate();
		
	}

}
