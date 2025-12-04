package project1;

import jakarta.servlet.ServletException;
import java.sql.PreparedStatement;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Servlet implementation class DeleteMessageServlet
 */
@WebServlet("/DeleteMessageServlet")
public class DeleteMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		response.setContentType("application/plain");
		PrintWriter out = response.getWriter();
		
		try {
			Connection conn = JdbcExample.getConnection();
			String sql = "DELETE FROM report WHERE EMAIL=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			
			int rowAffected = ps.executeUpdate();
			conn.close();
			
			if(rowAffected > 0) {
				out.print("success");
			}
			
			else {
				out.print("not_found");
			}
		}catch(Exception e) {
			e.printStackTrace();
			out.print("error");
		}
	}

}
