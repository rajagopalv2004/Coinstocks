package project1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class FetchUserServlet
 */
@WebServlet("/FetchUserServlet")
public class FetchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchUserServlet() {
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
			String sql = "SELECT * FROM register";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString("NAME");
				String gender = rs.getString("GENDER");
				String dob = rs.getString("DOB");
				String email = rs.getString("EMAIL");
				String mobile = rs.getString("MOBILE");

				out.println("<tr>");
				out.println("<td>" + name + "</td>");
				out.println("<td>" + gender + "</td>");
				out.println("<td>" + dob + "</td>");
				out.println("<td>" + email + "</td>");
				out.println("<td>" + mobile + "</td>");
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
