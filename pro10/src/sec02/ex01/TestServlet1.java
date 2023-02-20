package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet1
 */
@WebServlet("/first/test")
public class TestServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String context = request.getContextPath();
		String url = request.getRequestURL().toString();
		String mapping = request.getServletPath();
		String uri = request.getRequestURI();
		
		out.print("<html><head>"
				+ "<title>Test Servlet1</title>"
				+ "</head>"
				+ "<body bgcolor='green'>"
				+ "<b>TestServlet1입니다.</b><br>"
				+ "<b>컨텍스트명 : " + context + "</b><br>"
				+ "<b> 전체 경로 : " + url + "</b><br>"
				+ "<b> 매핑명 : " + mapping + "</b><br>"
				+ "<b> RUI : " + uri + "</b><br>"
				+ "</body>"
				+ "</html>");
		out.close();
		
		
	}

}
