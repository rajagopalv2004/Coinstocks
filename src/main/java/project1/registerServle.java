package project1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServle")
public class registerServle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection con=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServle() {
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
		
		response.setContentType("text/html");
		
		
		String name=request.getParameter("name");
		System.out.println(name);
		
		String gender=request.getParameter("gender");
		System.out.println(gender);
		
		String dob=request.getParameter("dob");
		System.out.println(dob);
		
		String email=request.getParameter("email");
		System.out.println(email);
		
		String mobile=request.getParameter("mobile");
		System.out.println(mobile);
		
		String password=request.getParameter("password");
		System.out.println(password);
		
		
		
		try
		{
			String query="INSERT INTO register(NAME, GENDER, DOB, EMAIL, MOBILE, PASSWORD) values(?,?,?,?,?,?)";
			
			
			PreparedStatement ps=JdbcExample.getConnection().prepareStatement(query);
			
			ps.setString(1, name);
			ps.setString(2, gender);
			ps.setString(3, dob);
			ps.setString(4, email);
			ps.setString(5, mobile);
			ps.setString(6, password);
			
			if(ps.executeUpdate()>0)
			{
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.include(request, response);
				PrintWriter out = response.getWriter();
				out.print("<script>alert('Registration Successful');</script>");
			//out.close();	
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		
	}

}
