package project1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
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
		doGet(request, response);
		response.setContentType("text/html");
		
		PrintWriter out=response.getWriter();
		
		
		String n=request.getParameter("user");
		
		String p=request.getParameter("pass");
		
		
		try
		{
			String query="SELECT * FROM register WHERE EMAIL=? AND PASSWORD=?";
			
			PreparedStatement ps=JdbcExample.getConnection().prepareStatement(query);
			
			ps.setString(1, n);
			ps.setString(2, p);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				RequestDispatcher rd=request.getRequestDispatcher("index.html");
				rd.forward(request, response);
			}
			else
			{
				out.print("<p style-text-align:center:red;>Sorry, username or password is incorrect</p>");
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
		}
		catch(Exception ex)
		{
			System.out.println("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

}
