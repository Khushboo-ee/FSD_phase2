package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet ("/Teacher")
public class Teachers extends HttpServlet{
	private String url = "jdbc:mysql://localhost:3305/p2_project1";
	private String user = "root";
	private String pwd = "root";
	Connection con;
	PreparedStatement pstmt;
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		resp.setContentType("text/html");

		PrintWriter writer = resp.getWriter();
		
		String TeacherName = req.getParameter("tname");
		String s1 = req.getParameter("tid");
			int TeachersID = Integer.parseInt(s1);
		
		
		int x;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("hi1");
			con = DriverManager.getConnection(url, user, pwd);
			System.out.println(con);
			String sql = "insert into classes values (?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, TeacherName);
			pstmt.setInt(2, TeachersID);
		
		

			x = pstmt.executeUpdate();

			if (x > 0)
			{
				writer.println("<h3>Data stored successfully.</h3>");
				HttpSession session = req.getSession(true);
				session.setAttribute("tname", TeacherName);

				req.getRequestDispatcher("teacherslist.html").forward(req,resp);
				
			} 
			else
			{
				writer.println("<h3>Data storage failed.</h3>");
				writer.println("<a href='/LA_attempt04/teacherslist.html'>Back</a>");
			}



			pstmt.close();
			con.close();
		}

		catch (SQLException e)
		{

			e.printStackTrace();
		}

		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
