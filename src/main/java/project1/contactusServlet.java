package project1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class contactusServlet
 */
@WebServlet("/contactusServlet")
public class contactusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public contactusServlet() {
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
		response.setContentType("text/html");
		
		String name=request.getParameter("name");
		System.out.println(name);
		
		String email=request.getParameter("email");
		System.out.println(email);
		
		String phone=request.getParameter("phone");
		System.out.println(phone);
		
		String message=request.getParameter("message");
		System.out.println(message);
		
		try
		{
			String query="INSERT INTO report(NAME, EMAIL, PHONE, MESSAGE) values(?,?,?,?)";
			
			PreparedStatement ps=JdbcExample.getConnection().prepareStatement(query);
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, phone);
			ps.setString(4, message);
			
			if(ps.executeUpdate()>0)
			{
				RequestDispatcher rd=request.getRequestDispatcher("index.html");
				rd.include(request,response);
				PrintWriter out = response.getWriter();
				out.print("<script>alert('Your Report is submitted');</script>");
				//out.close();
			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

}
