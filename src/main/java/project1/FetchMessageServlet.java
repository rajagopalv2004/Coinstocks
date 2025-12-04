package project1;

import jakarta.servlet.ServletException;
import java.sql.ResultSet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class FetchMessageServlet
 */
@WebServlet("/FetchMessageServlet")
public class FetchMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			Connection conn = JdbcExample.getConnection();
			String sql = "SELECT * FROM report";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("NAME");
				String email = rs.getString("EMAIL");
				String phone = rs.getString("PHONE");
				String message = rs.getString("MESSAGE");
				
				out.println("<tr>");
				out.println("<td>" + name + "</td>");
				out.println("<td>" + email + "</td>");
				out.println("<td>" + phone + "</td>");
				out.println("<td>" + message + "</td>");
				out.println("<td><i class='fas fa-trash delete-btn' data-email='" + email + "' title='Delete'></i></td>");
				out.println("</tr>");
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
			out.print("<tr><td colspan='6'>Error loading users</td></tr>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
